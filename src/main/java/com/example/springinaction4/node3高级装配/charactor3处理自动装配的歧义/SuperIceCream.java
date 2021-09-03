package com.example.springinaction4.node3高级装配.charactor3处理自动装配的歧义;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/3 17:25
 * @Description:
 */
@Component
@Qualifier("wby")
@Primary
@Super
public class SuperIceCream implements Dessert{
    Dessert dessert;
    @Autowired
    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
