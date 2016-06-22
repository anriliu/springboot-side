# springboot-side-core

本项目包含了一些通用的工具或Spring组件。

依赖
---

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

日志组件
---

本组件为对`slf4j`的微封装，可在运行时指定日志输出级别。

```java
import org.springframework.boot.logging.LogLevel;

public interface LoggerBean {

    public void log(LogLevel level, String format, Object... args);

    public void log(String format, Object... args);
}
```

配置

```properties
# 开启开关 默认为打开
springboot.side.logger-bean.enabled=true

# 指定“logger name”
springboot.side.logger-bean.name=com.mycompany.myapp

# 指定默认的日志级别
springboot.side.logger-bean.default-level=debug
```

注入

```java
@Service
public class MyService {

	@Autowired
	private LoggerBean logger;
}
```

Profile相关
---

我们知道，在启动Spring时，可以使用一个多个Profile。如果有一些Profiles是互斥的，那么他们不应该同时被指定。<br>
这个组件帮助程序员在Spring启动时，检查是否使用了互斥的Profile，如果配置错误Spring启动将会失败。

```properties
# 开启开关 默认为打开
springboot.side.profile-validator.enabled=true

# 互斥的组别1 dev,prod,docker之中的不能同时出现两个或以上
springboot.side.profile-validator.mutual-exclusion-map.group1=dev,prod,docker

# 互斥的组别2 http,https不能同时出现两个或以上
springboot.side.profile-validator.mutual-exclusion-map.group2=http,https
```

`ProfileUtils`是一个简单的工具，它包含一组静态方法，供程序员使用。

```java
// 获取当前被激活的Profile
public static List<String> getActiveProfiles() { ... }

// 获取当前被激活的Profile
public static Set<String> getActiveProfilesAsSet() { ... }

// 如果profile被激活，则执行闭包
public static void runIfPresent(String profile, CodeBlock codeBlock) { ... }

// 如果profile没有被激活，则执行闭包
public static void runIfAbsent(String profile, CodeBlock codeBlock) { ... }
```

HTTP请求日志组件
---

为了程序员方便调试，添加一个`javax.servlet.Filter`用户记录每一个请求的信息。

```properties
# 开启开关 默认为关闭
springboot.side.request-logging-filter.enabled=true

# Ant风格指定不起作用的请求
springboot.side.request-logging-filter.excludes=/**/*.js,/**/*.css,/**/*.ico

# Filter指定过滤的URL
springboot.side.request-logging-filter.url-patterns[0]=/*
springboot.side.request-logging-filter.url-patterns[1]=/security/*
```

~~简易人机验证码生成组件~~
---

添加一个`javax.servlet.http.HttpServletRequest`来生成人机验证码，并将人机验证码保存到`Session`。

```java
# 开启开关 默认为关闭
springboot.side.captcha-servlet.enabled=true

# 验证码图片宽度
springboot.side.captcha-servlet.width=100

# 验证码图片高度
springboot.side.captcha-servlet.height=18

# session attribute name
springboot.side.captcha-servlet.session-attribute-name=my-session-attribute-name

# url mapping(s)
springboot.side.captcha-servlet.url-mappings[0]=/captcha
springboot.side.captcha-servlet.url-mappings[1]=/captcha.jpg
```