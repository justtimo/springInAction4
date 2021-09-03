package com.example.springinaction4.node2装配bean.charactor5Config中引入XML.config;

import com.example.springinaction4.node2装配bean.charactor1组件扫描.CompactDisc;
import com.example.springinaction4.node2装配bean.charactor1组件扫描.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/1 17:33
 * @Description:
 */
@Configuration
public class CDCOnfig {
    @Bean(name = "wby")
    public CompactDisc sgtPeppers(){
        return new SgtPeppers();
    }

    @Bean
    public CompactDisc randomCompactDisc() {
        Random random = new Random();
        int i = random.nextInt(5);
        if (i==0){
            return new SgtPeppers();
        }else if (i == 1) {
            return new SgtPeppers();
        }else {
            return new SgtPeppers();
        }
    }
}
