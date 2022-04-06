package com.yxj.gulimall.search;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void contextLoads() {
        System.out.println(client);
        User user = new User();
        user.setAge(11);
        user.setName("xiaoming");
        System.out.println(JSON.toJSONString(user));
    }

}


@Data
class User {
    private int age;
    private String name;
}
