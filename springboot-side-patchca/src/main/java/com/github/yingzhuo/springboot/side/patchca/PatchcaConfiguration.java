package com.github.yingzhuo.springboot.side.patchca;

import com.github.yingzhuo.springboot.side.patchca.properties.BackgroundProperties;
import com.github.yingzhuo.springboot.side.patchca.properties.PatchcaFilterProperties;
import com.github.yingzhuo.springboot.side.patchca.properties.FontProperties;
import com.github.yingzhuo.springboot.side.patchca.properties.TextRendererProperties;
import com.github.yingzhuo.springboot.side.patchca.properties.WordProperties;
import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.FilterFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.AdaptiveRandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.util.Arrays;

@EnableConfigurationProperties({
        PatchcaFilterProperties.class,
        BackgroundProperties.class,
        FontProperties.class,
        WordProperties.class,
        TextRendererProperties.class
})
@ConditionalOnProperty(prefix = "springboot.side.patchca", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PatchcaConfiguration {

    @Autowired
    private PatchcaFilterProperties filterProperties;

    @Autowired
    private BackgroundProperties backgroundProperties;

    @Autowired
    private WordProperties wordProperties;

    @Autowired
    private FontProperties fontProperties;

    @Autowired
    private TextRendererProperties textRendererProperties;

    @Bean
    public FilterRegistrationBean patchcaFilter() {
        // 文本内容
        AdaptiveRandomWordFactory wordFactory= new AdaptiveRandomWordFactory();
        wordFactory.setWideCharacters(wordProperties.getWideCharacters());
        wordFactory.setCharacters(wordProperties.getCharacters());
        wordFactory.setMaxLength(wordProperties.getMaxLength());
        wordFactory.setMinLength(wordProperties.getMinLength());

        // 字体
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setFamilies(Arrays.asList(fontProperties.getFamilies()));
        fontFactory.setMaxSize(fontProperties.getMaxSize());
        fontFactory.setMinSize(fontProperties.getMinSize());

        // 效果
        BestFitTextRenderer textRenderer = new BestFitTextRenderer();
        textRenderer.setBottomMargin(textRendererProperties.getBottomMargin());
        textRenderer.setTopMargin(textRendererProperties.getTopMargin());
        textRenderer.setLeftMargin(textRendererProperties.getLeftMargin());
        textRenderer.setRightMargin(textRendererProperties.getRightMargin());

        // 背景 (目前只能配置单色背景)
        SingleColorBackgroundFactory backgroundFactory = new SingleColorBackgroundFactory();
        backgroundFactory.setColorFactory(new SingleColorFactory(
                new Color(backgroundProperties.getR(), backgroundProperties.getG(), backgroundProperties.getB())));

        // 字体
        SingleColorFactory colorFactory = new SingleColorFactory(
                new Color(filterProperties.getR(), filterProperties.getG(), filterProperties.getB()));

        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setBackgroundFactory(backgroundFactory);
        cs.setFontFactory(fontFactory);
        cs.setTextRenderer(textRenderer);
        cs.setColorFactory(colorFactory);
        cs.setWordFactory(wordFactory);
        cs.setWidth(filterProperties.getWidth());
        cs.setHeight(filterProperties.getHeight());

        // 滤镜
        FilterFactory filterFactory;
        switch (filterProperties.getFilterType()) {
            case CURVES: filterFactory = new CurvesRippleFilterFactory(colorFactory); break;
            case DIFFUSE: filterFactory = new DiffuseRippleFilterFactory(); break;
            case DOUBLE: filterFactory = new DoubleRippleFilterFactory(); break;
            case MARBLE: filterFactory = new MarbleRippleFilterFactory(); break;
            case WOBBLE: filterFactory = new WobbleRippleFilterFactory(); break;
            default: throw new AssertionError(); // 代码不可能运行到此处
        }
        cs.setFilterFactory(filterFactory);

        PatchcaFilter patchcaFilter = new PatchcaFilter();
        patchcaFilter.setCaptchaService(cs);
        patchcaFilter.setPatchcaSessionAttributeName(filterProperties.getSessionAttributeName());

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setEnabled(filterProperties.isEnabled());
        bean.setFilter(patchcaFilter);
        bean.addUrlPatterns(filterProperties.getUrlPatterns());
        bean.setName(filterProperties.getFilterName());
        bean.setOrder(filterProperties.getFilterOrder());

        return bean;
    }

}
