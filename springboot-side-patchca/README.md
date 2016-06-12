# springboot-side-patchca

[patchca-0.5.0](https://code.google.com/archive/p/patchca/)曾经托管于Google Code。但是由于一些历史原因，源代码失散了，本项目完全采用patchca-0.5.0的源代码，并添加`SpringBoot`支持，旨在帮助程序员快速完成基于`SpringMVC`的人机验证码程序。

依赖
---

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-patchca</artifactId>
    <version>1.0.0</version>
</dependency>
```

配置

```properties
# 开关 (默认为打开)
springboot.side.patchca.enabled=true

# 字体颜色
springboot.side.patchca.r=255
springboot.side.patchca.g=0
springboot.side.patchca.b=0

# 字体颜色
springboot.side.patchca.foreground.r=0
springboot.side.patchca.foreground.g=0
springboot.side.patchca.foreground.b=0

# 模糊滤镜种类 (共五种)
springboot.side.patchca.filter-type=double

# 随机文本字符范围
springboot.side.patchca.word.characters=123456vwm

# 随机文本长度
springboot.side.patchca.word.min-length=4
springboot.side.patchca.word.max-length=4

# 拉宽字符
springboot.side.patchca.word.wide-characters=vwm

# 字体设置
springboot.side.patchca.font.families[0]=微软雅黑
springboot.side.patchca.font.min-size=45
springboot.side.patchca.font.max-size=45

# 验证码URL模式 (可配置多个)
springboot.side.patchca.url-patterns[0]=/ca

# 验证码图片边距
springboot.side.patchca.text-renderer.top-margin=5
springboot.side.patchca.text-renderer.bottom-margin=5
springboot.side.patchca.text-renderer.left-margin=5
springboot.side.patchca.text-renderer.right-margin=5
```

**Note:** 绝大多数配置都有默认值，开箱即用。

参考资料
---

* [Google Code](https://code.google.com/archive/p/patchca/)