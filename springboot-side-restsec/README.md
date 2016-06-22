# springboot-side-restsec

本项目与[apache-shiro](http://shiro.apache.org/)或[spring-security](http://projects.spring.io/spring-security/)类似，对HTTP资源的访问进行控制，如角色/权限的筛选。正如本项目的名称（**REST**ful web service **SEC**urity）所暗示的那样，`restsec`只应当应用于`restful`方式的web应用，如果是展示网页的web应用，建议使用[apache-shiro](http://shiro.apache.org/)或[spring-security](http://projects.spring.io/spring-security/)。

依赖
---

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-restsec</artifactId>
    <version>1.0.0</version>
</dependency>
```

核心概念
---

#### 1) restsec何时进行认证与授权？
正因为`restful`规范的要求，所有的请求无状态的。所以，对每一次请求都要进行认证和授权的检查。

#### 2) AccessToken是什么？
请参考[AccessToken](https://github.com/yingzhuo/springboot-side/blob/master/springboot-side-restsec/src/main/java/com/github/yingzhuo/springboot/side/restsec/core/AccessToken.java)。`AccessToken`为是一个标记型接口。它表示一个用于身份验证的数据。常见地，[UsernamePasswordAccessToken](https://github.com/yingzhuo/springboot-side/blob/master/springboot-side-restsec/src/main/java/com/github/yingzhuo/springboot/side/restsec/core/UsernamePasswordAccessToken.java)封装了用户名和密码两个信息，在最最常见的[Basic Authentication](https://zh.wikipedia.org/wiki/HTTP%E5%9F%BA%E6%9C%AC%E8%AE%A4%E8%AF%81)等认证方式中被广泛使用。如果程序员需要使用其他方式的`AccessToken`需要自行实现。

#### 3) AccessToken从哪里来的？
简单的回答，从HTTP请求的上下文中来。如[Basic Authentication](https://zh.wikipedia.org/wiki/HTTP%E5%9F%BA%E6%9C%AC%E8%AE%A4%E8%AF%81)中规定的，从`HttpServletRequest`的`Header`中获得。从请求的上下文中获得参数这一过程，称为`AccessToken解析`，它交由[AccessTokenParser](https://github.com/yingzhuo/springboot-side/blob/master/springboot-side-restsec/src/main/java/com/github/yingzhuo/springboot/side/restsec/core/AccessTokenParser.java)处理。

#### 4) UserLike是什么？
请参考[UserLike](https://github.com/yingzhuo/springboot-side/blob/master/springboot-side-restsec/src/main/java/com/github/yingzhuo/springboot/side/restsec/core/UserLike.java)。它是对用户这一概念的抽象。

#### 5) UserLike从哪里来？
请参考[UserLikeLoader](https://github.com/yingzhuo/springboot-side/blob/master/springboot-side-restsec/src/main/java/com/github/yingzhuo/springboot/side/restsec/core/UserLikeLoader.java)。它负责加载用户实体，这个组件应该有程序员实现。拼配置到`Spring`上下文中。实现方式当然由程序员自行决定，可以从RDB，Key-Value DB加载数据。

配置
---

`application.properties`

```
# 开启本插件 (默认为true)
springboot.side.restsec.enabled=true

# 核心Filter名称 (有默认值，可不配置)
springboot.side.restsec.filter-name=RestsecFilter

# 核心Filter URL pattern (可配置多个)
springboot.side.restsec.url-patterns[0]=/*

# URL pattern exclude (可配置多个) (Ant-Style)
springboot.side.restsec.skip-patterns[0]=/beans
springboot.side.restsec.skip-patterns[1]=/**/*.js
springboot.side.restsec.skip-patterns[2]=/**/*.css

# 插件模式 可分为general和mock模式
# mock模式仅用户调试
springboot.side.restsec.mode=general
```

```java
@Configuration
public class SpringConfigRestsec {
    
    @Bean
    public AccessTokenParser accessTokenParser() {
    	  // 自己实现AccessTokenParser
        return new MyAccessTokenParser();
    }
    
    @Bean
    public UserLikeLoader userLikeLoader() {
    	  // 自己实现UserLikeLoader
        return new MyUserLikeLoader();
    }
}
```

> **注意:** 如果不自己实现`AccessTokenParser`，`restsec`将采用`Basic Authentication`验证。

mock模式
---

仅用于测试，在此模式下，无论请求上下文如何。都将认证为可配置的`UserLike`。

`application.properties`

```
springboot.side.restsec.enabled=true
springboot.side.restsec.mode=mock
springboot.side.restsec.mock.username=yingzhuo
springboot.side.restsec.mock.id=1234
springboot.side.restsec.mock.roles[0]=ROLE_USER
springboot.side.restsec.mock.roles[1]=ROLE_ADMIN
springboot.side.restsec.mock.permissions[0]=PERMISSION_ACCESS_PRODUCT
springboot.side.restsec.mock.expired=false
springboot.side.restsec.mock.locked=false
```

与SpringMVC的集成
---

```java
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

	// 访问方法者必须是经认证的
    @RequiresAuthentication
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String m1() {
        return "1";
    }

	 // 访问方法者必须是未经认证的
    @RequiresGuest
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String m2() {
        return "2";
    }
	
	// 访问者必须有指定的角色 (AND)
    @RequiresRoles(logical = Logical.AND, value = {"ROLE_USER", "ROLE_ADMIN"})      
    @RequestMapping(value = "/3", method = RequestMethod.DELETE)
    public String m3() {
        return "3";
    }

	// 访问者必须有指定的权限 (OR)
    @RequiresPermissions(logical = Logical.OR, value = {"PER_ACCESS_DB", "PER_ACCESS_REDIS"})
    @RequestMapping(value = "/4", method = RequestMethod.PUT)
    public String m4() {
        return "3";
    }
}
```

事件监听器
---

程序员可以配置事件监听器

```java
@Configuration
public class SpringConfigRestsec {
    
    @Bean
    public RestsecEventListener restsecEventListener() {
        return new MyRestsecEventListener();
    }
}
```

> **注意:**  在mock模式下事件监听器不会起作用