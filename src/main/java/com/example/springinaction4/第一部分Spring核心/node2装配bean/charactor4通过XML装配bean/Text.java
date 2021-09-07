package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor4通过XML装配bean;

/**
 * 见resources/node2/soundsystem.xml
 *
 * 借助SPring Tool Suit可以快捷的创建XML配置文件,并选择可用的配置命名空间
 *
 * 声明bean:  使用<bean id class></bean>
 * 注意,使用XML你不再负责创建SgtPeppers实例,Spring将会调用SgtPeppers的默认构造器来创建bean.
 * 另外,XML中bean的类型以字符串形式设置在了class属性中.
 *
 * Spring XML配置中,只有一种方式声明bean: 使用<bean>标签并指定class属性
 * Spring XML声明DI是,有两种方式:
 *      <constructor-arg >标签
 *      使用Spring3.0引入的c-命名空间
 * 两者的却别:
 *      <constructor-arg >更加冗余
 *      <constructor-arg >有些可以做到的事情,-c命名空间做不到
 *
 * <constructor-arg >标签会告诉Spring将一个ID为compact的bean引用传递到CDPlayer的构造器中
 * 使用c:cd-ref="compact"会更加简洁.
 *      c:命名空间前缀
 *      cd:构造器的参数名字
 *      -ref:命名约定,告诉SPring在装配的是一个bean引用
 *      compact:bean的名字
 * 还可以使用c:_0-ref="compact":
 *      0:参数的索引,因为XML中不允许数字作为第一个字符所以加上了_
 *      多个参数时,使用参数索引替代参数名字是好的选择:即便在构建的时候移除了调试标志,参数依然会保持相同的顺序
 *
 * 选择构造器注入还是属性注入呢?
 *      作为一个通用规则,强依赖使用构造器注入,而对可选性的依赖使用属性注入.
 * 按照这个规则,collections.BlankDisc中,名称演唱者和磁道列表是强依赖的,因此构造器注入是正确的方案.
 * <property></property>元素为属性的Setter方法提供的功能与<constructor-arg></constructor-arg>为构造器提供的功能是一样的.
 *
 * 与之类似的,<property></property>标签也有p-命名空间
 * p:cd-ref="compactDisc"
 *      p:命名空间前桌
 *      cd:属性名
 *      -ref:命名约定,告诉Spring要进行装配的是引用,而不是字面量
 *      compactDisc:所注入的bean的ID
 *
 * 注意,不能使用p-命名空间装配集合,没有便利的方式使用p-命名空间指定一个值(或者bean引用)的列表,但是我们可以使用Spring util-命名空间来简化这些操作
 *      更多util命名空间的元素,可以查看表2.1
 *
 *
 */
public class Text {
}
