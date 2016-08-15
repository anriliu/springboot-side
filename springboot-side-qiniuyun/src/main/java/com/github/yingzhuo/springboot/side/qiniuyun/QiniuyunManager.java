package com.github.yingzhuo.springboot.side.qiniuyun;

import com.github.yingzhuo.springboot.side.qiniuyun.exception.QiniuyunIOException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface QiniuyunManager {

    /**
     * 上传文件 (公开方式)
     *
     * @param data 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     */
    public String upload(byte[] data, String key, boolean overwrite);

    /**
     * 上传文件 (公开方式)
     *
     * @param data 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     * @throws QiniuyunIOException 操作失败抛出
     */
    public String upload(InputStream data, String key, boolean overwrite);

    /**
     * 上传文件 (公开方式)
     *
     * @param data 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     * @throws QiniuyunIOException 操作失败抛出
     */
    public String upload(MultipartFile data, String key, boolean overwrite);

    /**
     * 上传文件 (公开方式)
     *
     * @param urlData 数据
     * @param key 数据key
     * @param overwrite 是否为覆盖上传 true时表示覆盖方式上传
     * @return 上传成功后资源URL
     */
    public String upload(String urlData, String key, boolean overwrite);

    /**
     * 资源重命名
     *
     * @param fromKey 原资源Key
     * @param toKey 新资源Key
     * @return 新的资源URL
     * @throws QiniuyunIOException 操作失败抛出
     */
    public String rename(String fromKey, String toKey);

    /**
     * 查看资源key是否存在
     *
     * @param key 待查看的资源
     * @return true时表示资源key存在
     * @throws QiniuyunIOException 操作失败时抛出
     */
    public boolean exists(String key);

    /**
     * 删除资源
     *
     * @param key 资源key
     * @throws QiniuyunIOException 操作失败时抛出
     */
    public void delete(String key);

    /**
     * 删除资源
     *
     * @param url 资源url
     * @throws QiniuyunIOException 操作失败时抛出
     */
    public void deleteByUrl(String url);

}
