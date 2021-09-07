package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor2条件花的bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

  /**
   * 假如希望bean只有在应用类路径下包含特定库才创建;或者某个bean只有在特定的bean声明后才创建,Spring4之前很难实现这种级别的条件化设置.
   *
   * Spring4引入了@Conditional注解,他可以被用在@Bean注解的方法上:如果给定的条件为true则创建,否则这个bean会被忽略
   *
   * 例如,我们希望只有设置了magic环境属性的时候,Spring才实例化这个类
   * @Conditional(MagicExistsCondition.class) 中指明了条件-----即MagicExistsCondition,@Conditional将会通过Condition接口进行条件比较:
   *    设置给@Conditional的类可以是任意实现了Condition接口的类型.
   * @return
   */
  @Bean
  @Conditional(MagicExistsCondition.class)
  public MagicBean magicBean() {
    return new MagicBean();
  }

}
