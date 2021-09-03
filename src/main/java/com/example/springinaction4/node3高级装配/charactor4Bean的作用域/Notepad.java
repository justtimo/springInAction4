package com.example.springinaction4.node3高级装配.charactor4Bean的作用域;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 默认情况下,Spring应用上下文中所有的bean都是单例的
 * 多数情况下,单例是理想方案.初始化和垃圾回收对象实例所带来的成本只留给一些小规模任务,这些任务重,让对象保持无状态并且在应用中反复重用这些对象并不合理.
 * 但是,有些类是易变的,他们会保持一些状态,因此重用是不安全的
 *
 * Spring定义了多种作用域:
 *      1.单例:整个应用中,只创建bean的一个实例
 *      2.原型:每次注入或者通过Spring应用上下文获取的时候,都会创建一个新的bean实例
 *      3.会话:在web应用中,每个会话都创建一个实例
 *      4.请求:在web应用中,每个请求创建一个bean实例
 * 要选择其他作用域,可以使用@Scope注解,他可以和@Component或者@Bean一起使用
 * 这里使用了ConfigurableBeanFactory的常量设置原型作用域,当然也可以使用@Scope("prototype"),但是使用常量更加安全且不容易出错
 *
 * 如果想在Java配置类中设置作用域,可以组合使用@Bean和@Scope.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Notepad {
  // the details of this class are inconsequential to this example
}

class NotepadConfig{
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Notepad notepad() {
        return new Notepad();
    }
}

/**
 * 在web中,以购物车为例,使用单例会导致所有的用户都向同一个购物车添加商品;如果是原型,会导致应用的另一个地方就不可用,因为在这里注入的是另一个原型作用域的购物车
 * 所以使用session作用域是合适的选择,他与给定的用户关联性最大
 * 每个会话都会创建一个实例,但是在当前会话相关的操作中,这个bean实际上是单例的
 *
 * proxyMode属性解决了将会话或请求作用域的bean注入到单例bean中所遇到的问题
 */
@Scope(value = "session",proxyMode = ScopedProxyMode.INTERFACES)
class ShoppingCart{

}
