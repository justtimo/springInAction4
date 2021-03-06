package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor6小结;

/**
 * 我们在本章介绍了许多背景知识，在第2章所介绍的基本bean装配基础之上，又学习了一些强大的高级装配技巧。
 * 首先，我们学习了Spring profile，它解决了Spring bean要跨各种部署环境的通用问题。在运行时，通过捋环境相关的bean与当前激活的profile进行匹配， Spring能够让相同的部署单元跨多种环境运行，而不需要进行重新构建。
 *
 * Profile bean是在运行时条件化创建bean的一种方式，但是Spring4提供了一种更为通用的方式，通过这种方式能够声明某些bean的创建与否要依赖于给定条件的输出结果。
 * 结合使用@Conditional注解和Spring Condition接口的实现，能够为开发人员提供一种强大和灵活的机制，实现条件化地创建bean。
 *
 * 我们还看了两种解决自动装配歧义性的方法∶首选bean以及限定符。尽管捋某个bean设置为首选bean是很简单的，但这种方式也有其局限性，所以我们讨论了如何特一组可选的自动装配 bean，
 * 借助限定符捋其范围缩小到只有一个符合条件的bean。除此之外，我们还看到了如何创建自定义的限定符注解，这些限定符描述了bean的特性。尽管大多数的Spring bean都是以单例的方式创建的，但有的时候其他的创建策略更为合适。 .
 *
 * Spring能够让bean以单例、原型、请求作用域或会话作用域的方式来创建。在声明请求作用域或会话作用域的bean的时候，我们还学习了如何创建作用域代理，它分为基于类的代理和基于接口的代理的两种方式。
 *
 * 最后，我们学习了Spring表达式语言，它能够在运行时计算要注入到bean属性中的值。
 */
public class Text {
}
