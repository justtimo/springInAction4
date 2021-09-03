package com.example.springinaction4.node2装配bean.charactor1组件扫描;
import org.springframework.stereotype.Component;

/**
 * @Component: 该类作为组件类,并且Spring要为这个类创建bean.这样就不需要显式的配置SgtPeppers bean.
 * 不过组件扫描默认是不开启的,需要显式的配置Spring,让他寻找带有@Component注解的类,并为其创建bean
 *
 * Spring上下文会自动为所有bean设置ID,ID为类名首字母的手写
 *    可以使用value属性,为bean设置指定的ID
 * 另种设置ID的方式是使用Java依赖注入规范所提供的@Named注解为bean设置ID
 */
@Component(value = "wby")
/*@Named(value = "wby")*/
public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";
  private String artist = "The Beatles";

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }

}
