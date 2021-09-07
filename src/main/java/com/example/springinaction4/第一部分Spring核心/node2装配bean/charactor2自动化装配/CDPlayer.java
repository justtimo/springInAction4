package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor2自动化装配;

import com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自动装配: 让Spring自动满足bean依赖的一种方法,在满足依赖的过程中,会在Spring应用上下文中寻找匹配某个bean需求的其他bean.
 */
@Component
public class CDPlayer implements MediaPlayer {
  private CompactDisc cd;

  /**
   * 在构造器上使用@Autowired,表名Spring创建CDPlayer bean的时候,会通过构造器来实例化并且传入一个可设置给CompactDisc类型的bean
   * @Autowired 注解不仅可以用在构造器上,还可以用在属性的Setter方法上
   * 实际上,@Autowired可以用在类的任何方法上,例如insertCD()
   *
   * 无论是构造器还是Setter()还是其他方法,Spring都会尝试满足方法参数上声明的依赖: 假如有且只有一个bean匹配依赖需求的话,这个bean将会被装配进来.
   * 如果没有匹配的bean,将会抛出异常.为了避免异常出现,你可以使用required = false属性,当没有匹配的bena,Spring将会让这个bean出于未装配状态.
   * 但是,这么做需要谨慎对待,如果你没有进行null检查的话,将会抛出空指针异常.
   * 如果有多个bean满足依赖关系的话,Spring将抛出异常,表明没有明确指定要选择哪个bean进行自动装配
   *
   * 注解@Autowired 还可以用Java依赖注入规范中的@Inject注解.多数情况下,他们可以互相替换,尽管他们有细微的差别
   * @param cd
   */
  @Autowired(required = false)
  public CDPlayer(CompactDisc cd) {
    this.cd = cd;
  }

  @Autowired
  public void setCd(CompactDisc cd) {
    this.cd = cd;
  }

  @Autowired
  public void insertCD(CompactDisc cd) {
    this.cd = cd;
  }

  public void play() {
    cd.play();
  }

}
