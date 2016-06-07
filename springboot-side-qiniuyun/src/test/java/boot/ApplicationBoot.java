package boot;

import com.github.yingzhuo.springboot.side.qiniuyun.QiniuyunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class ApplicationBoot implements ApplicationRunner {

    @Autowired
    private QiniuyunManager qiniuyunManager;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = qiniuyunManager.upload((InputStream) null, "mock-key", true);
        System.out.println(url);
    }

}
