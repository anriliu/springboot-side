package boot;

import com.github.yingzhuo.springboot.side.restsec.annotation.Logical;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresAuthentication;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresGuest;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresPermissions;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restsec")
public class MyController {

    @RequiresAuthentication     // 访问方法者必须是经认证的
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String m1() {
        return "1";
    }

    @RequiresGuest              // 访问方法者必须是未经认证的
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String m2() {
        return "2";
    }

    @RequiresRoles(logical = Logical.AND, value = {"ROLE_USER", "ROLE_ADMIN"})      // 访问者必须有指定的角色 (AND)
    @RequestMapping(value = "/3", method = RequestMethod.DELETE)
    public String m3() {
        return "3";
    }

    @RequiresPermissions(logical = Logical.OR, value = {"PER_ACCESS_DB", "PER_ACCESS_REDIS"})      // 访问者必须有指定的权限 (OR)
    @RequestMapping(value = "/4", method = RequestMethod.PUT)
    public String m4() {
        return "3";
    }
}
