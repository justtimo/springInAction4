package com.example.springinaction4.node1Spring之旅.charactor3Spring概览;

/**
 * Spring核心容器:Beans,Core,Context,Expression,ContextSupport
 *      容器是Spring框架最核心的部分，它管理着Spring应用中bean的创建、配置和管理。在该模块中，包括了Spring bean工厂，它为Spring提供了DI的功能。
 *      基于bean工厂，我们还会发现有多种 Spring应用上下文的实现，每一种都提供了配置Spring的不同方式。
 *      除了bean工厂和应用上下文，该模块也提供了许多企业服务，例如E-mail、JNDI访问、EJB集成和调度。所有的Spring模块都构建于核心容器之上。当你配置应用时，其实你隐式地使用了这些类。
 *      贯穿本书， 我们都会涉及到核心模块，在第2章中我们捋会深入探讨Spring的DI.
 * Spring的AOP模块:AOP,Aspects
 *      在AOP模块中，Spring对面向切面编程提供了丰富的支持。这个模块是Spring应用系统中开发切面的基础。
 *      与DI一样，AOP可以帮助应用对象解耦。
 *      借助于AOP，可以捋逼布系统的关注点（例如事务和安全）从它们所应用的对象中解耦出来。
 *      Spring的JDBC和DAO（Data Access Obiect）模块抽象了这些样板式代码，使我们的数据库代码变得简单明了，还可以避免因为关闭数据库资源失败而引发的问题。以后我们再也不需要解释那些隐晦专有的SQL错误信息了!
 *
 *      对于那些更喜欢ORM（Object-Relational Mapping）工具而不愿意直接使用JDBC的开发者，Spring提供了ORM模块。Spring的ORM模块建立在对DAO的支持之上，并为多个ORM框架提供了一种构建DAO的简便方式。
 *      Spring没有尝试去创建自己的ORM解决方案， 而是对许多流行的ORM框架进行了集成，包括Hibernate、Java Persisternce API、Java Data Object和iBATIS SQL Maps。
 *      Spring的事务管理支持所有的ORM框架以及JDBC。在第10章讨论Spring数据访问时，你会看到Spring基于模板的JDBC抽象层能够极大地简化 JDBC代码。
 *
 *      本模块同样包含了在JMS（Java Message Service）之上构建的Spring抽象层，它会使用消息以异步的方式与其他应用集成。
 *      从Spring 3.0开始，本模块还包含对象到XML映射的特性，它最初是 Spring Web Service项目的一部分。
 *      除此之外，本模块会使用Spring AOP模块为Spring应用中的对象提供事务管理服务。
 * Web与远程调用:Web,WEbSeverlet,Web portlet,WebSocket
 *      MVC（Model-View-Controller）模式是一种普逼被接受的构建Web应用的方法，它可以帮助用户特界面逻辑与应用逻辑分离。
 *      Java从来不缺少MVC框架，Apache的Struts、JSF、WebWork和 Tapestry都是可选的最流行的MVC框架。
 *      虽然Spring能够与多种流行的MVC框架进行集成，但它的Web和远程调用模块自带了一个强大的MVC框架，有助于在Web层提升应用的松耦合水平。
 *      Spring远程调用功能集成了RMI（Remote Method Invocation）、Hessian、Burlap、JAX-WS，同时Spring还自带了一个远程调用框架∶HTTP invoker。Spring还提供了暴露和使用REST API的良好支持。
 *
 * Instrumentation:Instrument,Instrument Tomcat
 *      Spring的Instrumentation模块提供了为JVM添加代理（agent）的功能。
 *      具体来讲，它为Tomcat提供了一个织入代理， 能够为Tomcat传递类文件，就像这些文件是被类加载器加载的一样。
 * 测试:Test
 *      鉴于开发者自测的重要性， Spring提供了测试模块以致力于Spring应用的测试。通过该模块，你会发现Spring为使用JNDI、Servlet和Portlet编写单元测试提供了一系列的mock
 */

/**
 *  Spring Portfolio
 *          当谈论Spring时，其实它远远超出我们的想象。事实上，Spring远不是Spring框架所下载的那些。如果仅仅停留在核心的Spring框架层面我们捋错过Spring Portfolio所提供的巨额财富。
 *          整个Spring Portfolio包括多个构建于核心Spring框架之上的框架和类库。概括地讲，整个Spring Portfolio几乎为每一个领域的Java开发都提供了Spring编程模型。
 *          或许需要几卷书才能覆盖Spring Portfolio所提供的所有内容， 这也远远超出了本书的范围。不过，我们会介绍Spring Portfolio中的一些项目，同样，我们捋体验一下核心框架之外的另一番风景。
 *
 *          Spring Web Flow
 *              Spring Web Flow建立于Spring MVC框架之上，它为基于流程的会话式Web应用（可以想一下购物车或者向导功能）提供了支持。
 *              我们将在第8章讨论更多关于Spring Web Flow的内容。
 *          Spring Web Service
 *                  虽然核心的Spring框架提供了捋Spring bean以声明的方式发布为Web Service的功能，但是这些服务是基于一个具有争议性的架构（拙劣的契约后置模型）之上而构建的。
 *                  这些服务的契约由 bean的接口来决定。Spring Web Service提供了契约优先的Web Service模型，服务的实现都是为了满足服务的契约而编写的。
 *                  本书不会再探讨Spring Web Service，但是你可以浏览站点http//docs.springio/spring- ws/site/来了解更多关于Spring Web Service的信息。
 *          Spring Security
 *                  安全对于许多应用都是一个非常关键的切面。利用Spring AOP， Spring Security为Spring应用提供了声明式的安全机制。
 *                  你特会在第9章看到如何为应用的Web层添加Spring Securitv功能。同时，我们还会在第14章重新回到Spring Security的话题，学习如何保护方法调用。
 *                  你可以在主页http∶//projects.spring io/spring-security/上获得关于Spring Security的更多信息。
 *          SpringIntegration
 *                  许多企业级应用都需要与其他应用进行交互。Spring Integration提供了多种通用应用集成模式的Spring声明式风格实现。
 *                  我们不会在本书覆盖Spring Integration的内容，但是如果你想了解更多关于Spring Integration的信息，
 *          Spring Batch
 *
 */
public class Text {
}
