package com.yxj.gulimall.product;


import com.aliyun.oss.OSSClient;

import com.yxj.gulimall.product.service.BrandService;
import com.yxj.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author 从前慢
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Autowired
    OSSClient ossClient;

    @Autowired
    CategoryService categoryService;



    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    RedissonClient redissonClient;
    @Test
    public void tesRedisson() {
        System.out.println(redissonClient);
    }

    @Test
    public void teststringRedisTemplate() {
        // hello world
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        // 保存
        ops.set("hello","world_" + UUID.randomUUID().toString());
        // 查询
        String hello = ops.get("hello");
        System.out.println("之前保存的数据是："  + hello);
    }
    @Test
    public void testUpload() throws FileNotFoundException {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
//       // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "LTAI4GJvYzX1sXmLhdH7ydHW";
//        String accessKeySecret = "gQiFRD3Y2EnWmjPkDUxqxHlTQzPE6Q";
//
//       // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

         // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\YAOXINJIA\\Desktop\\soft.png");
        ossClient.putObject("gulimall-yaoxinjia", "soft.png", inputStream);

       // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("上传完成...");

    }

    @Test
    public void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(226L);
        log.info("完整路径: {}", Arrays.asList(catelogPath));
    }

}
