package com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点;


/**
 * 通过切点来选择连接点
 *      正如之前所提过的，切点用于准确定位应该在什么地方应用切面的通知。通知和切点是切面的最基本元素。因此，了解如何编写切点非常重要。在Spring AOP中，要使用AspectJ的切点表达式语言来定义切点。
 *      如果你已经很熟悉AspectJ.那么在Spring中定义切点就感觉非常自然。但是如果你一点都不了解AspectJ的话，本小节我们捋快速介绍一下如何编写AspectUJ风格的切点。
 *      关于Spring AOP的AspectJ切点，最重要的一点就是Spring仅支持Aspect切点指示器（pointcutdesignator）的一个子集。
 *      让我们回顾下，Spring是基于代理的，而某些切点表达式是与基于代理的AOP无关的。表4.1列出了Spring AOP所支持的AspectJ切点指示器。
 *
 *      在Spring中尝试使用AspectJ其他指示器时，将会抛出IllegalArgument-Exception异常。当我们查看如上所展示的这些Spring支持的指示器时，注意只有execution指示器是实际执行匹配的，
 *      而其他的指示器都是用来限制匹配的。这说明execution指示器是我们在编写切点定义时最主要使用的指示器。在此基础上，我们使用其他指示器来限制所匹配的切点。
 *
 * 编写切点
 *      为了阐述Spring中的切面，我们需要有个主题来定义切面的切点。为此，我们定义一个Performance接口∶
 */
public interface Performance {
    public void performance();
}
/**
 * execution (* concert.Performance.perform(..) )
 * execution:方法执行时触发
 * *:返回任意类型
 * concert.Performance:方法所属的类
 * perform:方法
 * (..):使用任意参数
 * * concert.Performance.perform(..): 指定的方法
 *
 * 我们使用execution（）指示器选择Performance的perform（）方法。
 * 方法表达式以"*"号开始，表明了我们不关心方法返回值的类型。
 * 然后，我们指定了全限定类名和方法名。
 * 对于方法参数列表，我们使用两个点号（..）表明切点要选择任意的perform（）方法，无论该方法的入参是什么。
 *
 * 现在假设我们需要配置的切点仅匹配concert包。在此场景下，可以使用within（）指示器来限制匹配，
 *      execution(* concert.Performance.perform(..)) && within (concert. *))
 * execution(* concert.Performance.perform(..)): 执行Performance.performance()方法
 * &&: 与（and）操作
 * && within (concert. *): 当concert包下的任意类的方法被调用时
 *
 * 请注意我们使用了"&s"操作符把execution（）和within（）指示器连接在一起形成与（and）关系（切点必须匹配所有的指示器）。
 * 类似地，我们可以使用"||"操作符来标识或（or）关系，而使用"!"操作符来标识非（not）操作。
 * 因为"&"在XML中有特殊含义， 所以在Spring的XML配置里面描述切点时，我们可以使用and来代替"&&"。
 * 同样， or和not可以分别用来代替"||"和"!"。
 */

/**
 * 在切点中选择bean
 *      除了表4.1所列的指示器外，Spring还引入了一个新的bean（）指示器，它允许我们在切点表达式中使用bean的ID来标识bean。bean （）使用bean ID或bean名称作为参数来限制切点只匹配特定的bean。
 *
 *      例如，考虑如下的切点∶
 *              execution (* concert.Performance.perform() ) and bean ( 'woodstock' )
 *      在这里，我们希望在执行Performance的perform（）方法时应用通知，但限定bean的ID为woodstock。
 *      在某些场景下，限定切点为指定的bean或许很有意义，但我们还可以使用非操作为除了特定ID以外的其他bean应用通知∶
 *              execution(* concert.Performance.perform( )) and !bean('woodstock")
 *      在此场景下，切面的通知会被编织到所有ID不为woodstock的bean中。
 *
 * 现在，我们已经讲解了编写切点的基础知识，让我们再了解一下如何编写通知和使用这些切点声明切面。
 */




















