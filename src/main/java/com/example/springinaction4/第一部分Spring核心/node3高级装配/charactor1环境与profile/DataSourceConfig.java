package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor1环境与profile;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
  /**
   * 开发环境,嵌入式数据库
   * @return
   */
  @Bean(destroyMethod = "shutdown")
  @Profile("dev")
  public DataSource embeddedDataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:schema.sql")
        .addScript("classpath:test-data.sql")
        .build();
  }

  /**
   * 生产环境,从JNDI容器中获取一个DataSource
   * 通过JNDI获取DataSource可以让容器决定该如何创建这个DataSource,包括切换为容器管理的连接池.即便如此,JNDI管理的DataSource也更加适合生产环境
   * @return
   */
  @Bean
  @Profile("prod")
  public DataSource jndiDataSource() {
    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    jndiObjectFactoryBean.setJndiName("jdbc/myDS");
    jndiObjectFactoryBean.setResourceRef(true);
    jndiObjectFactoryBean.setProxyInterface(DataSource.class);
    return (DataSource) jndiObjectFactoryBean.getObject();
  }

  /**
   * QA环境中,你可以选择完全不同的DataSource配置,比如DBCP连接池,如下所示
   * @return
   */
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc;h2;tcp://dbserver/~/test");
    dataSource.setDriverClassName("org.h2 .Driver");
    dataSource.setUsername ("sa");
    dataSource.setPassword("password");
    dataSource.setInitialSize(20);
    dataSource.setMaxTotal(30); ;
    return dataSource;
  }
  /**
   * 以上三个方法虽然都生成javax.sql.DataSource,但是使用的策略完全不同
   * 这表现了不同的环境中某个bean会有所不同,使其在某种环境下成为更合适的选择:
   *    其中一种方式是使用单独的配置类(或XML)配置每个bean,然后再构建阶段确定将哪一个配置编译到可部署的应用中.
   * 但这会带来问题:需要为每种环境重新构建应用,当从QA阶段迁移到生产阶段时,重构可能会带来bug
   * 但是,SPring提供的解决方案并不需要重新构建
   */

  /**
   * 配置profile bean.
   * Spring不是在构建时决定,而是运行时决定.这样,同一个部署单元能够适用于所有环境,无需进行重新构建
   * 3.1中引入了profile bean,将所有不同的bean定义 整合到一个或多个profile中,将应用部署到每个环境时,要确保对应的profile出于激活状态
   *
   * 使用@Profile("dev")注解指定某个bean属于哪个profile
   * 该注解使用类级别上,会告诉SPring这个配置类中的bean只有在dev profile激活时才会被创建.
   *
   * 从3.2开始,@Profile注解也能用在方法级别上了!!!这样就能将不同bean的声明放在同一个配置类中了.
   *
   * 注意,没有指定profile的bena始终都会被创建,与激活哪个profile没有关系
   */

  /**
   * 也可以在XML配置profile.
   *    注意,使用的<beans></beans>标签  ,而不是<bean></bean>
   */

  /**
   * 如何激活profile
   *  需要依赖两个独立的属性:
   *      spring.profiles.active
   *      spring.profiles.default
   *  首先检查active属性的值,用来确定哪个profile被激活;
   *  如果没有设置active,则检查default的值
   *  如果都没设置,就没有激活的profile,因此只会创建没有定义在profile中的bean
   *
   * 有多种方式设置这两个属性:
   *    作为DispatcherServerlet的初始化参数
   *    作为JNDI的条目
   *    作为环境变量
   *    作为JVM的系统属性
   *    测试类上,使用@ActiveProfiles("dev")注解设置
   *
   * 作者喜欢使用作为DispatcherServerlet的初始化参数将spring.profiles.default设置为开发环境的profile,会在上下文中进行设置(兼顾到ContextLoaderListener)
   * 见node3/web.xml
   *
   * spring.profiles是复数形式:可以激活多个profile,使用逗号分割
   */
}










