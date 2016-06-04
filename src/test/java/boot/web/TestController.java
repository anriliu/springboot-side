package boot.web;

import com.github.yingzhuo.springboot.side.util.IP;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tests")
public class TestController {

    @RequestMapping("1")
    public String test1(@IP String ip) {
        return ip;
    }

}
