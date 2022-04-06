package com.yxj.gulimall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *
 * @author yaoxinjia
 */
/**
 * 整合redis作为session存储
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableRedisHttpSession
/**
 * spring-session核心原理
 * 1、EnableRedisHttpSession 导入RedisHttpSessionConfiguration配置
 *   1)给容器中添加了一个组件 SessionRepository ==》RedisOperationsSessionRepository/RedisIndexedSessionRepository ==？redis操作session ,实现session的增删改查
 * 2、SessionRepositoryFilter==》Filter :session存储过滤器，每个请求过来必须经过filter
 *    1)创建的时候，就自动从容器中获取到了SessionRepository
 *    2）原始的request,response被包装，SessionRepositoryRequestWrapper SessionRepositoryResponseWrapper
 *    3）以后获取session,request.getSession()
 *      获取的都是SessionRepositoryRequestWrapper
 *     4)wrapperRequest.getSession() ==> SessionRepository中获取的
 *
 *     这个实现是通过装饰器模式实现的 ，实现代码：
 *     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 *         request.setAttribute(SESSION_REPOSITORY_ATTR, this.sessionRepository);
 *         SessionRepositoryFilter<S>.SessionRepositoryRequestWrapper wrappedRequest = new SessionRepositoryFilter.SessionRepositoryRequestWrapper(request, response);
 *         SessionRepositoryFilter.SessionRepositoryResponseWrapper wrappedResponse = new SessionRepositoryFilter.SessionRepositoryResponseWrapper(wrappedRequest, response);
 *
 *         try {
 *             filterChain.doFilter(wrappedRequest, wrappedResponse);
 *         } finally {
 *             wrappedRequest.commitSession();
 *         }
 *
 *     }
 *     自动延期：redis中的数据也是有过期时间的
 */
public class GulimailAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailAuthServerApplication.class,args);
    }
}
