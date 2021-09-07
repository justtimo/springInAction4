package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ComponentScan: 能够在Spring中启用组件扫描
 * 没有其他配置,@ComponentScan默认扫描与配置类相同的包及其子包
 * 但是如果想要将配置类放到单独的包中,使其与其他应用代码区分开来,那么默认的扫描就不能满足要求了.可以使用basePackages属性设置 或者使用 basePackageClasses属性
 *
 * 或者使用XML配置的方式soundsystem.xml
 */
@Configuration
@ComponentScan(basePackages = {"com.example.springinaction4", "com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.config"}/*,basePackageClasses = {CompactDisc.class, SgtPeppers.class}*/)
public class CDPlayerConfig {
}
