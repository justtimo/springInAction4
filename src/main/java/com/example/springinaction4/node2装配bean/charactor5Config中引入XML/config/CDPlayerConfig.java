package com.example.springinaction4.node2装配bean.charactor5Config中引入XML.config;

import com.example.springinaction4.node2装配bean.charactor1组件扫描.CompactDisc;
import com.example.springinaction4.node2装配bean.charactor2自动化装配.CDPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 假设将两个Config拆分,如何引入依赖的另一个config??
 * 使用@Import(CDCOnfig.class)注解
 * 或者创建一个更高级的类,将两个config组合在一起: 例如ComplexConfig.java
 *
 * 但是如果想引入XML配置呢?
 * 使用@ImportResource注解,这样两个bean---配置在config中的CDPlayer和XML中的BlankDisc都会被加载到spring容器中
 */
@Configuration
@Import(CDCOnfig.class)
@ImportResource("classPath:spring/knight.xml")
public class CDPlayerConfig{

    @Bean
    public CDPlayer cDPlayer1(CompactDisc compactDisc){
        return new CDPlayer(compactDisc);
    }
    @Bean
    public CDPlayer cDPlayer2(CompactDisc compactDisc){
        CDPlayer cdPlayer = new CDPlayer(compactDisc);
        cdPlayer.setCd(compactDisc);
        return cdPlayer;
    }
}
