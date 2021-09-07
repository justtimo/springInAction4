package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor5Config中引入XML.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/1 17:36
 * @Description:
 */
@Configuration
@Import({CDCOnfig.class,CDPlayerConfig.class})
public class ComplexConfig {
}
