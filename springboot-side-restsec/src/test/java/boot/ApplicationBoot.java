package boot;

import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ApplicationBoot {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }

    @RequiresAuthentication
    @RequestMapping("test")
    public String test() {
        return "test";
    }

}
