package boot;

import com.github.yingzhuo.springboot.side.qiniuyun.QiniuyunManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.InputStream;

@SpringBootApplication
public class ApplicationBoot implements ApplicationRunner {

    @Autowired
    private QiniuyunManager qiniuyunManager;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args).close();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream in = FileUtils.openInputStream(new File("/Users/yingzhuo/Desktop/avatar.jpg"));
        String url = qiniuyunManager.upload(in, "cat", true);
        System.out.println(url);
        IOUtils.closeQuietly(in);
    }

}
