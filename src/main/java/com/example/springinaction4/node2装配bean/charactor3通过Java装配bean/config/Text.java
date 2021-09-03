package com.example.springinaction4.node2装配bean.charactor3通过Java装配bean.config;

import com.example.springinaction4.node2装配bean.charactor1组件扫描.CompactDisc;
import com.example.springinaction4.node2装配bean.charactor1组件扫描.SgtPeppers;
import com.example.springinaction4.node2装配bean.charactor2自动化装配.CDPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * 我们看看Spring如何显示的装配bean
 * 多数场景下通过组件扫描+自动装配实现SPring的自动化配置是推荐的方式.
 * 但是想要将第三方库组件装配到应用中,你无法在他的类上添加@Component和@Autowaire,此时就不能使用自动化配置了
 *
 * 显示配置有两种方案: JavaConfig 和 XML
 * JavaConfig更类型安全,强大且容易重构
 * JavaConfig是配置代码,意味着他不应该包含业务逻辑,同时也不应该侵入业务代码中.尽管不是必需的
 *
 * 创建Config类的关键在于@Configuration注解,该注解表名这个类是配置类,该类应该包含在Spring应用上下文中如何创建bean的细节
 *
 * 尽管我们可以使用组件扫描和显示配置,但本节中,我们更关注显示配置,因此移除了@ComponentScan.
 *
 * 要在Config中声明bean,需要编写一个方法,并添加@Bean注解.
 * @Bean 注解告诉Spring这个方法将会返回一个对象,该对象要注册为Spring上下文中的bean.默认情况下,bean的ID与方法名相同,可以通过name属性指定bean ID
 *
 * 还可以做些疯狂的事情,比如在一组CD中随机选择一个CompactDisc来播放:randomCompactDisc()
 *
 * 现在我们想要创建CDPlayer bean,CDPlayer依赖于CompactDisc.如何装配到一起呢?
 * 在JavaConfig中最简单的方式就是引用创建bean的方法: cDPlayer()
 * 看起来,CompactDisc是通过sgtPeppers()方法得到的,但并非如此.因为sgtPeppers()方法上有@Bean注解,Spring将会拦截所有对他的调用,并确保直接返回该方法
 *      所创建的bean,而不是每次都对其进行实际的调用.
 * 比如在引入一个CDPlayer anotherCDPlayer(),他和之前的bean一样.假如sgtPeppers()的调用像其他方法调用一样的话,那么每个CDPlayer都会有自己特有的SgtPeppers
 *      实例.如果在现实生活中,这么做是有意义的,因为两台播放器不能使用同一张CD光盘.但是在软件领域,我们完全可以将同一个SgtPeppers实例注入到任意数量的其他bean中.
 *      默认情况下,Spring中的bean都是单例的,我们没必要为第二个播放器创建完全相同的SgtPeppers实例.
 *      所以,Spring会拦截对SgtPeppers()的调用,并确保返回的是Spring所创建的bean,也就是Spring本身在调用sgtPeppers()时所创建的CompactDisc bean,因此,
 *      两个播放器会得到相同的SgtPeppers实例
 *
 * 还有另一种方式可以实现装配依赖:cDPlayer1().Spring调用cDPlayer1()创建CDPlayer时,会自动装配一个CompactDisc到方法中.借助这种技术,cDPlayer1()也能够将
 *      CompactDisc注入到CDPlayer的构造器中,而且不用明确指定CompactDisc的@Bean方法
 *
 * 还可以采用Setter的方式注入CompactDisc: cDPlayer2()
 *
 * 带有@Bean注解的方法可以采用任何必要的Java功能来产生bean实例.
 */
@Configuration
class CDPlayerConfig1111 {
    @Bean(name = "wby")
    public CompactDisc sgtPeppers(){
        return new SgtPeppers();
    }

    @Bean
    public CompactDisc randomCompactDisc() {
        Random random = new Random();
        int i = random.nextInt(5);
        if (i==0){
            return new SgtPeppers();
        }else if (i == 1) {
            return new SgtPeppers();
        }else {
            return new SgtPeppers();
        }
    }

    @Bean
    public CDPlayer cDPlayer(){
        return new CDPlayer(sgtPeppers());
    }
    @Bean
    public CDPlayer anotherCDPlayer(){
        return new CDPlayer(sgtPeppers());
    }
    @Bean
    public CDPlayer cDPlayer1(CompactDisc compactDisc111){
        return new CDPlayer(compactDisc111);
    }
    @Bean
    public CDPlayer cDPlayer2(CompactDisc compactDisc111){
        CDPlayer cdPlayer = new CDPlayer(compactDisc111);
        cdPlayer.setCd(compactDisc111);
        return cdPlayer;
    }
}
public class Text {
}
