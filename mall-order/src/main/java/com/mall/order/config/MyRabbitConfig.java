package com.mall.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;



/**
 * 订单模块订单服务RabbitMQ配置类
 * @author littlecheung
 */
@Configuration
public class MyRabbitConfig {


    /**
     * 延迟队列（死信队列）
     *
     * @return
     */@Bean
    public Queue orderDelayQueue() {
        /*
            String name,  队列名字
            boolean durable,  是否持久化
            boolean exclusive,  是否排他
            boolean autoDelete, 是否自动删除
            Map<String, Object> arguments, 自定义属性
         */
        HashMap<String, Object> arguments = new HashMap<>();
        //设置死信路由
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        //设置死信路由键
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        //消息过期时间设置为1分钟
        arguments.put("x-message-ttl", 60000);
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
        return queue;
    }

    /**
     * 普通队列
     *
     * @return
     */
    @Bean
    public Queue orderReleaseQueue() {

        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    /**
     * 交换机
     *
     * @return
     */
    @Bean
    public Exchange orderEventExchange() {

        return new TopicExchange("order-event-exchange", true, false);
    }


    /**
     * 与死信队列绑定
     *
     * @return
     */
    @Bean
    public Binding orderCreateBinding() {
        /*
         * String destination, 目的地（队列名或者交换机名字）
         * DestinationType destinationType, 目的地类型（Queue、Exchange）
         * String exchange, 交换机
         * String routingKey, 路由键
         * Map<String, Object> arguments, 自定义属性
         * */
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",
                null);
    }


    /**
     * 与普通队列绑定
     *
     * @return
     */
    @Bean
    public Binding orderReleaseBinding() {

        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",
                null);
    }


    /**
     * 订单释放直接和库存释放进行绑定
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding() {

        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.other.#",
                null);
    }


    /**
     * 商品秒杀队列
     * @return
     */
    @Bean
    public Queue orderSecKillOrderQueue() {

        Queue queue = new Queue("order.seckill.order.queue", true, false, false);
        return queue;
    }


    /**
     * 与秒杀队列进行绑定
     * @return
     */
    @Bean
    public Binding orderSecKillOrderQueueBinding() {

        Binding binding = new Binding(
                "order.seckill.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.seckill.order",
                null);
        return binding;
    }
}
