package com.example.springinaction4.node3高级装配.charactor3处理自动装配的歧义;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/3 16:57
 * @Description:
 */
@Component
public class Cookies implements Dessert{
    Dessert dessert;
    @Autowired
    @Qualifier("wby1")
    public void setDessert( Dessert dessert) {
        this.dessert = dessert;
    }
}
