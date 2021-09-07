package com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor5注入AspectJ切面;

/**
 * 使用AspectJ实现表演的评论员
 *
 * 虽然Spring AOP能够满足许多应用的切面需求，但是与AspectJ相比，Spring AOP是一个功能比较弱的AOP解决方案。AspectI提供了Spring AOP所不能支持的许多类型的切点。
 *
 * 例如，当我们需要在创建对象时应用通知.构造器切点就非常方便。不像某些其他面向对象语言中的构造器，Java构造器不同于其他的正常方法。这使得Spring基于代理的AOP无法把通知应用于对象的创建过程。
 *
 * 对于大部分功能来讲，Aspect切面与Spring是相互独立的。虽然它们可以织入到任意的Java应用中，这也包括了Spring应用，但是在应用AspectJ切面时几乎不会涉及到Spring。
 *
 * 但是精心设计且有意义的切面很可能依赖其他类来完成它们的工作。如果在执行通知时，切面依赖于一个或多个类，我们可以在切面内部实例化这些协作的对象。但更好的方式是，我们可 以借助Spring的依赖注入把bean装配进AspectJ切面中。
 *
 * 为了演示，我们为上面的演出创建一个新切面。具体来讲， 我们以切面的方式创建一个评论员的角色，他会观看演出并且会在演出之后提供一些批评意见。下面的criticAspect就是一个这样的切面
 */
public class CriticAspect {
    public CriticAspect() {
    }
    /*pointcut performance() :execution(* perform(..))*/

}
