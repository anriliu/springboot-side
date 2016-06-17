package boot;

import com.github.yingzhuo.springboot.side.qiniuyun.QiniuyunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationBoot implements ApplicationRunner {

    @Autowired
    private QiniuyunManager qiniuyunManager;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args).close();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String in = "https://aecpm.alicdn.com/simba/img/TB1gu_NKpXXXXarXVXXSutbFXXX.jpg";
        String url = qiniuyunManager.upload(in, "cat", true);
        System.out.println(url);
    }

}
