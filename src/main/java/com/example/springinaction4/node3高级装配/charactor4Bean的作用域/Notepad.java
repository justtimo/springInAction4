package com.example.springinaction4.node3高级装配.charactor4Bean的作用域;

import org.springframework.beans.factory.annotation.Autowired;
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
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
class ShoppingCart{

}
/**
 * 描述proxyMode之前,我们先看看他解决什么样的问题
 * 假设我们要将ShoppingCart bean注入到单例StoreService bean的Setter方法中
 */
@Component
class StoreService{
    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart){

    }
}
/**
 * 因为StoreService是单例的bean,会在Spring应用上下文加载的时候创建.当他创建时,Spring会试图将ShoppingCart注入到setShoppingCart()中.
 *      但是ShoppingCart是会话作用域的,此时并不存在,直到某个用户进入系统创建了会话之后,才会出现ShoppingCart实例
 * 另外,系统中会有多个ShoppingCart,我们希望StoreService处理购物车功能时,使用的ShoppingCart恰好是当前会话对应的那个.
 * Spring不会将实际的ShoppingCart bean注入到StoreService中,Spring会注入一个到ShoppingCart bean的代理.这个代理会暴露与ShoppingCart相同的
 *      方法,所以StoreService会认为他就是一个购物车.但是当StoreService调用ShoppingCart的方法时,代理会对其进行懒解析并将调用委托给会话作用域内的
 *      真正的ShoppingCart bean
 * 现在再来看proxyMode属性,他被设置为INTERFACES,表名代理要实现ShoppingCart接口,并将调用委托给实现bean
 * 如果ShoppingCart是接口而不是类,这是最理想的.但如果ShoppingCart是一个具体的类,Spring就无法创建基于接口的代理了,此时他会使用基于CGlib来生成基于
 *      类的代理.所以,如果bean类型是具体类的话,我们要使用proxyMode = ScopedProxyMode.TARGET_CLASS
 * 请求作用域的bean也有相同的装配问题,因此他也是使用作用于代理的方式进行注入
 */









