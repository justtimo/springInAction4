package com.example.springinaction4.node2装配bean.charactor1组件扫描;

/**
 * 创建应用对象之间协作关系的行为通常称为"装配",这也是依赖注入DI的本质
 * Spring中装配bean有多种方式.主要提供了三种方式:
 *      在XML中显示配置
 *      在java中显示配置
 *      隐式的bean发现机制和自动装配
 * 配置风格可以相互搭配.,即便如此,还是推荐使用自动配置机制.显示配置越少越好,当必需显示配置时,推荐类型安全且比XML更加强大的JavaConfig
 *
 * Spring从两个角度实现自动化装配:
 *      组件扫描:Spring会自动发现应用上下文中所创建的bean
 *      自动装配:Spring自动满足bean之间的依赖
 * 组件扫描和自动装配组合在一起就能发挥出强大的威力,能将显示配置降到最低.
 *
 * 下面的例子演示了音响系统
 */
public class Text {
}
