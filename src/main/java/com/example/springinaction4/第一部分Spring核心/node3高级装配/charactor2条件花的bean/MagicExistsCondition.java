package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor2条件花的bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicExistsCondition implements Condition {

  /**
   * 通过ConditionContext我们可以实现以下几点:
   *    1. 借助getRegistry()返回的BeanDefinitionRegistry检查bean定义
   *    2. 借助getBeanFactory()返回的ConfigurableListableBeanFactory检查bean是否存在,甚至探查bean的属性
   *    3. getEnvironment()返回的Environment检查环境变量是否存在以及他的值是什么
   *    4. 读取并探查getResourceLoader()返回的ResourceLoader所加载的资源
   *    5. 借助getClassLoader()返回的ClassLoader加载并检查类是否存在
   *
   * AnnotatedTypeMetadata
   *  能够让我们检查带有@bean注解的方法上还有什么其他注解,
   *  1. getAnnotations()能够判断带有@Bean注解的方法上是不是还有其他特定注解
   *  借助其他方法,可以检查@Bean注解方法上其他注解的属性
   *
   * 作为Spring4重构后的@profile,它是基于@Condition注解和Condition实现,作为使用@Condition注解和Condition实现的例子,我们看看@Profile是如何实现的
   *    @Target({ElementType.TYPE, ElementType.METHOD})
   *    @Retention(RetentionPolicy.RUNTIME)
   *    @Documented
   *    @Conditional(ProfileCondition.class)
   *    public @interface Profile {}
   * 注意,@Profile本身也使用了@Condition注解,并且引用了ProfileCondition作为Condition的实现.
   * 观察ProfileCondition可以发现,通过AnnotatedTypeMetadata得到用于@Profile注解的所有属性,借助该信息,他会检查value属性值,该属性包含了bean的
   * profile名称.然后通过ConditionContext得到的Environment的acceptsProfiles()方法检查该profile是否处于激活状态
   *
   * @param context
   * @param metadata
   * @return
   */
  @Override
  @Profile("dev")
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Environment env = context.getEnvironment();
    return env.containsProperty("magic");
  }
}
























