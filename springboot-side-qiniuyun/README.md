# springboot-side-qiniuyun

[七牛云](http://www.qiniu.com/)是中国一家著名的存储服务提供商。本项目封装了七牛云官方提供的JavaSDK，旨在帮助程序员能迅速在自己的Spring应用中集成七牛云的存储服务。

依赖
---

```
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>springboot-side-qiniuyun</artifactId>
    <version>1.0.0</version>
</dependency>
```

配置
---

```properties
# 开关 默认为关闭
springboot.side.qiniuyun.enabled=true

# 可选择两种模式 general: 普通模式，mock: 虚操作模式
springboot.side.qiniuyun.mode=general

# 七牛云bucket
springboot.side.qiniuyun.bucket=your_bucket

# 七牛云access_key
springboot.side.qiniuyun.access-key=your_access_key

# 七牛云secret_key
springboot.side.qiniuyun.secret-key=your_secret_key

# 文件访问URL前缀
springboot.side.qiniuyun.url-prefix=htt://host:port/
```

**Note:** bucket, access-key, secret-key等意义请参考七牛官方文档。

注入
---

```java
@Service
public class MyService {
	
	@Autowired
	private QiniuyunManager manager;
}
```

参考文档
---

* [七牛云官方文档](http://developer.qiniu.com/)
