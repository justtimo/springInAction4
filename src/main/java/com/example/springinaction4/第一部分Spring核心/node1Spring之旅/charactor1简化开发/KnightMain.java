package com.example.springinaction4.第一部分Spring核心.node1Spring之旅.charactor1简化开发;

import org.springframework.context.support.
                   ClassPathXmlApplicationContext;

public class KnightMain {

  public static void main(String[] args) throws Exception {
    /**
     * Spring通过应用上下文（Application Context）装载bean的定义并把它们组装起来。Spring应用上下文全权负责对象的创建和组装。
     * Spring自带了多种应用上下文的实现，它们之间主要的区别仅仅在于如何加载配置。
     *
     * 因为knights.xml中的bean是使用XML文件进行配置的，所以选择ClassPathXmlApplicationContext作为应用上下文相对是比较合适的。
     * 该类加载位于应用程序类路径下的一个或多个XML配置文件。
     */
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext(
            "META-INF/spring/knight.xml");
    Knight knight = context.getBean(Knight.class);
    knight.embarkOnQuest();
    context.close();
  }

}
