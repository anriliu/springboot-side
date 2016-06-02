package boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tests")
public class TestController {

    @RequestMapping("1")
    public String test1() {
        return "1";
    }

}
