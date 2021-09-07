package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor3处理自动装配的歧义;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Primary 无法将可选方案限定到唯一的一个无歧义性的bean中,他只能标识一个优先的可先方案.
 * 而Spring限定符能够在所有可选的bena上进行缩小范围的操作,达到最终只有一个bena满足规定的条件.
 * 如果将所有的限定符都用上后依然存在歧义性,那么你可以使用更多的限定符继续缩小范围
 *
 * @Qualifier 注解是使用限定符的主要方式,可以与@Autowired和@Inject协同使用,在注入的时候指定想要注入进去的是哪个bean
 * 为@Qualifier设置的参数就是要注入的bean的ID,所有使用@Component注解声明的类bean的ID是首字母小写
 * 基于默认的beanID作为限定符会有一些问题:如果修改了类名,就无法匹配限定符的参数.自动装配会失败
 *
 * 使用自定义限定符
 *      我们可以为bean设置自己的限定符,而不是仅仅依赖默认bean ID.
 *      @Component 与 @Qualifier组合使用,这样"wby"限定符就分配给了IceCream bean,因为没有耦合类名,所以可以随意修改类名而不会导致自动装配失败
 *
 *      注意:使用JAva配置显示定义bean的时候,@Qualifier也可以与@Bean注解一起使用 见IceCreamConfig
 *
 * 使用自定义的限定符注解
 *      如果有两个bean,都带有@Primary和@Qualifier("wby")的时候,又会有歧义性问题:IceCream,java 和 SuperIceCream.java
 *      解决办法可以是在 注入点 和 bean定义 的地方同时再添加另外一个@Qualifier注解.
 *      这样就需要添加更多的限定符,但是java不允许在同一个条目上重复出现相同类型的多个注解,编译器会报错
 *      使用自定义的限定符注解:见Super.annotion
 *      通过在Soper注解类上使用@Qualifier,它就具有了@Qualifier注解的特性.他也就成为了限定符注解
 *      最终,在注入点(此例子中的setDessert()方法)我们可以使用限定符注解进行任意组合,从而将范围缩小到只有一个符合规定的bean
 *      相对于原始的@Qualifier并借助String类型来指定限定符,自定义的注解也更为类型安全.
 *
 *      为了创建自定义的条件化注解,我们创建了一个新的注解并在这个注解上添加了@Conditional
 *      为了创建自定义的限定符注解,我们创建了一个新的注解并在这个主街上添加了@Qualifier
 *      这种技术可以应用到很多Spring注解上,从而能够将他们组合在一起形成特定目标的自定义注解
 */
@Component
@Primary
public class Cake implements Dessert{
    Dessert dessert;
    @Autowired
    /*@Qualifier("iceCream")*/
    @Qualifier("wby")
    /*@Qualifier("super")*/
    @Super
    public void setDessert( Dessert dessert) {
        this.dessert = dessert;
    }
}
