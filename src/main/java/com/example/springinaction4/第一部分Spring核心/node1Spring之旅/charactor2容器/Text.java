package com.example.springinaction4.第一部分Spring核心.node1Spring之旅.charactor2容器;

/**
 * 在基于Spring的应用中，你的应用对象生存于Spring容器（container）中。
 * Spring容器负责创建对象，装配它们，配置它们并管理它们的整个生命周期，从生存到死亡（在这里，可能就是new到finalize （））。
 *
 * 下一章,你将了解如何配置Spring,从而让他知道如何创建,配置和组装哪些对象.
 * 但首先要了解容纳对象的容器,这有助于理解对象如何被管理.
 *
 * 容器是Spring框架的核心。Spring容器使用DI管理构成应用的组件，它会创建相互协作的组件之间的关联。
 * Spring容器并不是只有一个。Spring自带了多个容器实现，可以归为两种不同的类型:
 *      bean工厂（由org.springframework. beans. factory.eanFactory接口定义）
 *          是最简单的容器，提供基本的DI支持。
 *      应用上下文（由org.springframework.context.ApplicationContext接口定义）
 *          基于BeanFactory构建.并提供应用框架级别的服务，例如从属性文件解析文本信息以及发布应用事件给感兴趣的事件监听者。
 *
 * 虽然我们可以在bean工厂和应用上下文之间任选一种，但bean工厂对大多数应用来说往往太低级了，因此，应用上下文要比bean工厂更受欢迎。
 * 我们会把精力集中在应用上下文的使用上，不再浪费时间讨论bean工厂。
 *
 * Spring自带了多种类型的应用上下文。下面罗列的几个是你最有可能遇到的。
 *      ●AnnotationConfigApplicationContext∶从一个或多个基于Java的配置类中加载 Spring应用上下文。
 *      ·AnnotationConfigWebApplicationContext∶从一个或多个基于Java的配置类中加载Spring Web应用上下文。
 *      ●Class PathXmlApplicationContext∶从类路径下的一个或多个XML配置文件中加载上下文定义，把应用上下文的定义文件作为类资源。
 *      ●FileSystemXmlapplicationcontext∶从文件系统下的一个或多个XML配置文件中加载上下文定义。
 *      ●XmlWebApplicationContext∶从Web应用下的一个或多个XML配置文件中加载上下文定义。
 *
 *      当在第8章讨论基于Web的Spring应用时，我们会对AnnotationConfigWeb-ApplicationContext和XmlWebApplicationContext进行更详细的讨论。
 *      现在我们先简单地使用FileSystemxmlApplicationContext从文件系统中加载应用上下文或者使用Class PathXmlApplicationContext从类路径中加载应用上下文。
 *      无论是从文件系统中装载应用上下文还是从类路径下装载应用上下文，捋bean加载到bean工厂的过程都是相似的。
 *      例如，如下代码展示了如何加载一个 FileSystemXmlApplicationContext:
 *          ApplicationContext context = newFileSystemXmlApplicationContext ("c: /knight .xml");
 *      类似地，你可以使用ClassPathXmlApplicationContext从应用的类路径下加载应用上下文∶
 *          ApplicationContext context = new ClassPathXmlApplicationContext {"knight.xml");
 *
 * 使用FileSystemXmlApplicationContext和使用ClassPathXmlApp-licationContext的区别在于∶
 *      FileSystemxmlApplicationContext在指定的文件系统路径下查找knightxml文件;
 *      ClassPathxmlApplicationContext是在所有的类路径（包含JAR文件）下查找 knight.xml文件
 *
 * 如果你想从Java配置中加载应用上下文，那么可以使用AnnotationConfig- ApplicationContext:
 *      ApplicationContext context = new AnnotationConfigApplicationContext(com.springinaction.knights.config,KnightConfig.class};
 * 在这里没有指定加载Spring应用上下文所需的XML文件，AnnotationConfig- ApplicationContext通过一个配置类加载bean。
 * 应用上下文准备就绪之后，我们就可以调用上下文的getBean（）方法从Spring容器中获取 bean。
 *
 * bean的生命周期
 * 在传统的Java应用中，bean的生命周期很简单。使用Java关键字new进行bean实例化，然后该 bean就可以使用了。一旦该bean不再被使用，则由Java自动进行垃圾回收。
 * 相比之下，Spring容器中的bean的生命周期就显得相对复杂多了。正确理解Spring bean的生命周期非常重要，因为你或许要利用Spring提供的扩展点来自定义bean的创建过程。
 */
public class Text {
}
