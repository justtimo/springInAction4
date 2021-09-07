package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor3处理自动装配的歧义;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 遇到歧义性时,Spring首选带有@Primary的bean
 * @Primary 可以和 @Component组合使用在组件扫描的bean上,也是与@Bean组合使用在Java配置的bean声明中
 *
 * 如果有两个首选的Dessert bean:Cake和IceCream,就需要使用限定符
 */
@Component
@Qualifier("wby")
@Primary
public class IceCream implements Dessert{
    Dessert dessert;
    @Autowired
    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
@Configuration
class IceCreamConfig{
    @Bean
    @Primary
    @Qualifier("wby1")
    public IceCream iceCream(){
        return new IceCream();
    }
}
