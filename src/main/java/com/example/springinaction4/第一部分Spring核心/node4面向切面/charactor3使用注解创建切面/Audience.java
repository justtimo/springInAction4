package com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor3使用注解创建切面;

import com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc;
import com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor4通过XML装配bean.BlankDisc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用注解来创建切面是AspectJ 5所引入的关键特性。Aspect 5之前，编写AspectJ切面需要学习一种Java语言的扩展，但是AspectJ面向注解的模型可以非常简便地通过少量注解把任意类转变为切面。
 * 我们已经定义了Performance接口，它是切面中切点的目标对象。现在， 让我们使用AspecJ注解来定义切面。
 *
 * 定义切面
 *      如果一场演出没有观众的话，那不能称之为演出。对不对? 从演出的角度来看，观众是非常重要的，但是对演出本身的功能来讲， 它并不是核心， 这是一个单独的关注点。
 *      因此，捋观众定义为一个切面，并特其应用到演出上就是较为明智的做法。
 *      下面的例子 ,它定义了我们所需的一个切面: 观看演出的切面
 */
@Aspect
public class Audience {
    //表演之前
    @Before("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void silenceCellPhones(){
        System.out.println("silence cell phones");
    }
    //表演之前
    @Before("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void takeSeats(){
        System.out.println("take seats");
    }
    //表演之前
    @AfterReturning("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void applause(){
        System.out.println("Clap Clap Clap");
    }
    //表演之前
    @AfterThrowing("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void demandRefund(){
        System.out.println("demanding a refund");
    }
}
/**
 * Audience类使用@AspectJ注解进行了标注。该注解表明Audience不仅仅是一个POJO，还是一个切面。Audience类中的方法都使用注解来定义切面的具体行为。
 * Audience有四个方法. 定义了一个观众在观看演出时可能会做的事情。
 * 在演出之前，观众要就坐（takeSeats（））并特手机调至静音状态（silenceCel1Phones（））。
 * 如果演出很精彩的话，观众应该会鼓掌喝彩（applause（））。
 * 不过，如果演出没有达到观众预期的话，观众会要求退款（demandRefund （））。
 * 可以看到，这些方法都使用了通知注解来表明它们应该在什么时候调用。AspectJ提供了五个注解来定义通知:
 * @After :通知方法会在目标方法返回或抛出异常后调用
 * @AfterReturning: 通知方法会在目标方法返回后调用
 * @AfterThrowing: 通知方法会在目标方法抛出异常后调用
 * @Around: 通知方法会将目标方法封装起来
 * @Before: 通知方法会在目标方法调用之前执行
 *
 * Audience使用到了前面五个注解中的三个。takeSeats（）和silence CellPhones（）方法都用到了@Before注解，表明它们应该在演出开始之前调用。
 * applause （）方法使用了@AfterReturning注解，它会在演出成功返回后调用。demandRefund（）方法上添加了@AfterThrowing注解， 这表明它会在抛出异常以后执行。
 *
 * 你可能已经注意到了，所有的这些注解都给定了一个切点表达式作为它的值， 同时，这四个方法的切点表达式都是相同的。
 * 其实.它们可以设置成不同的切点表达式， 但是在这里，这个切点表达式就能满足所有通知方法的需求。
 * 让我们近距离看一下这个设置给通知注解的切点表达式，我们发现它会在Performance的perform （）方法执行时触发。
 * 相同的切点表达式我们重复了四逼，这可真不是什么光彩的事情。这样的重复让人感觉有些不对劲。如果我们只定义这个切点一次，然后每次需要的时候引用它，那么这会是一个很好的方案。
 * 幸好，我们完全可以这样做;@Pointcut注解能够在一个@AspectJ切面内定义可重用的切点。接下来的程序清单4.2展现了新的Audience，现在它使用了@Pointcut。
 */

@Aspect
class AudienceByPointCut {
    @Pointcut("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void performance(){}
    //表演之前
    @Before("performance()")
    public void silenceCellPhones(){
        System.out.println("silence cell phones");
    }
    //表演之前
    @Before("performance()")
    public void takeSeats(){
        System.out.println("take seats");
    }
    //表演之前
    @AfterReturning("performance()")
    public void applause(){
        System.out.println("Clap Clap Clap");
    }
    //表演之前
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("demanding a refund");
    }
}
/**
 * 在Audience中，performance （）方法使用了@Pointcut注解。为@Pointcut注解设置的值是一个切点表达式，就像之前在通知注解上所设置的那样。
 * 通过在performance （）方法上添加@Pointcut注解，我们实际上扩展了切点表达式语言，这样就可以在任何的切点表达式中使用performance（）了，如果不这样做的话，你需要在这些地方使用那个更长的切点表达式。
 * 我们现在把所有通知注解中的长表达式都替换成了performance （）。
 * performance （）方法的实际内容并不重要，在这里它实际上应该是空的。其实该方法本身只是一个标识， 供@Pointcut注解依附。
 *
 * 需要注意的是， 除了注解和没有实际操作的performance（）方法，Audience类依然是一个 POJO。我们能够像使用其他的Java类那样调用它的方法，它的方法也能够独立地进行单元测试，
 * 这与其他的Java类并没有什么区别。Audience只是一个Java类，只不过它通过注解表明会作为切面使用而已。
 *
 * 像其他的Java类一样，它可以装配为Spring中的bean∶
 */
@Configuration
@ComponentScan(" ")
class AudienceConfig{
    @Bean
    public Audience audience() {
        return new Audience();
    }
}
/**
 * 如果你就此止步的话，Audience只会是Spring容器中的一个bean。即便使用了AspectJ注解，但它并不会被视为切面，这些注解不会解析，也不会创建捋其转换为切面的代理。
 * 如果你使用JavaConfig的话，可以在配置类的类级别上通过使用EnableAspectJ- AutoProxy注解启用自动代理功能。程序清单4.3展现了如何在JavaConfig中启用自动代理。
 */
@Configuration
@ComponentScan(" ")
@EnableAspectJAutoProxy     //启用AspectJ自动代理
class ConcertConfig{
    @Bean
    public Audience audience() {    //声明Audience bean
        return new Audience();
    }
}

/**
 * 不管你是使用JavaConfig还是XML，Aspect自动代理都会为使用@Aspect注解的bean创建一个代理. 这个代理会围绕着所有该切面的切点所匹配的bean。
 * 在这种情况下，捋会为Concertbean创建一个代理，Audience类中的通知方法特会在perform（）调用前后执行。
 *
 * 我们需要记住的是，Spring的AspectJ自动代理仅仅使用@AspectJ作为创建切面的指导，切面依然是基于代理的。在本质上，它依然是Spring基于代理的切面。
 * 这一点非常重要，因为这意味着尽管使用的是@AspectJ注解，但我们仍然限于代理方法的调用。如果想利用AspectJ的所有能力，我们必须在运行时使用Aspect并且不依赖Spring来创建基于代理的切面。
 *
 * 到现在为止，我们的切面在定义时，使用了不同的通知方法来实现前置通知和后置通知。但是表4.2还提到了另外的一种通知∶环绕通知（around advice）。
 * 环绕通知与其他类型的通知有所不同，因此值得花点时间来介绍如何进行编写。
 */
/**
 * 环绕通知是最为强大的通知类型。它能够让你所编写的逻辑捋被通知的目标方法完全包装起来。实际上就像在一个通知方法中同时编写前置通知和后置通知。
 * 为了阐述环绕通知，我们重写Audience切面。这次，我们使用一个环绕通知来代替之前多个不同的前置通知和后置通知。
 */
@Aspect
class AudienceByAround {
    //定义命名的切点
    @Pointcut("execution(* com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance.performance(..))")
    public void performance(){}
    //表演之前
    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint jp){
        try {
            System.out.println("silence cell phones");
            System.out.println("take seats");
            jp.proceed();
            System.out.println("Clap Clap Clap");
        } catch (Throwable throwable) {
            System.out.println("demanding a refund");
            throwable.printStackTrace();
        }
    }
}
/**
 * 在这里，@Around注解表明watchPerformance（）方法会作为performance（）切点的环绕通知。在这个通知中，观众在演出之前会捋手机调至静音并就坐，演出结束后会鼓掌喝彩。
 * 像前面一样，如果演出失败的话，观众会要求退款。
 *
 * 可以看到，这个通知所达到的效果与之前的前置通知和后置通知是一样的。但是，现在它们位于同一个方法中，不像之前那样分散在四个不同的通知方法里面。
 *
 * 关于这个新的通知方法，你首先注意到的可能是它接受ProceedingJoinPoint作为参数。这个对象是必须要有的，因为你要在通知中通过它来调用被通知的方法。
 * 通知方法中可以做任何的事情，当要特控制权交给被通知的方法时，它需要调用ProceedingJoinPoint的 proceed （）方法。
 *
 * 需要注意的是，别忘记调用proceed（）方法。如果不调这个方法的话，那么你的通知实际上会阻塞对被通知方法的调用。有可能这就是你想要的效果，但更多的情况是你希望在某个点上执行被通知的方法。
 *
 * 有意思的是，你可以不调用proceed（）方法，从而阻塞对被通知方法的访问，与之类似，你也可以在通知中对它进行多次调用。要这样做的一个场景就是实现重试逻辑，也就是在被通知方法失败后，进行重复尝试。
 */

/**
 * 处理通知中的参数
 * 到目前为止，我们的切面都很简单，没有任何参数。唯一的例外是我们为环绕通知所编写的 watchPerformance（）示例方法中使用了ProceedingJoinPoint作为参数。
 * 除了环绕通知，我们编写的其他通知不需要关注传递给被通知方法的任意参数。这很正常，因为我们所通知的perform （）方法本身没有任何参数。
 *
 * 但是，如果切面所通知的方法确实有参数该怎么办呢? 切面能访问和使用传递给被通知方法的参数吗?
 *
 * 为了阐述这个问题，让我们重新看一下2.4.4小节中的BlankDisc样例。play（）方法会循环所有的磁道并调用playTrack （）方法。但是，我们也可以通过playTrack （）方法直接播放某一个磁道中的歌曲。
 *
 * 假设你想记录每个磁道被播放的次数。一种方法就是修改playTrack（）方法，直接在每次调用的时候记录这个数量。但是，记录磁道的播放次数与播放本身是不同的关注点，因此不应该属于playTrack （）方法。
 * 看起来，这应该是切面要完成的任务。
 *
 * 为了记录每个磁道所播放的次数， 我们创建了TrackCounter类，它是通知playTrack（）方法的一个切面。下面的程序清单展示了这个切面。
 */
@Aspect
class TrackCounter{
    private Map<Integer,Integer> trackCounts=new HashMap<Integer,Integer>();

    //通知playTrack的方法
    @Pointcut("execution(* com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc.play(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber){}

    //在播放前,为该磁道计数
    @Before("trackPlayed(trackNumber)")
    public void trackCount(int trackNumber){
        int playCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber,playCount+1);
    }

    public int getPlayCount(int trackNumber){
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
    }
}
/**
 * 像之前所创建的切面一样， 这个切面使用@Pointcut注解定义命名的切点，并使用@Before捋一个方法声明为前置通知。但是，这里的不同点在于切点还声明了要提供给通知方法的参数。
 * 图4.6捋切点表达式进行了分解，以展现参数是在什么地方指定的。
 * execution(* com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc.play(int)) && args(trackNumber)
 * *: 返回任意类型
 * com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc: 方法所属的类型
 * play: 方法
 * int: 接受int类型的参数
 * args(trackNumber): 指定参数
 *
 * 在图4.6中需要关注的是切点表达式中的args（trackNumber） 限定符。它表明传递给 playTrack （）方法的int类型参数也会传递到通知中去。参数的名称trackNumber也与切点方法签名中的参数相匹配。
 *
 * 这个参数会传递到通知方法中，这个通知方法是通过@Before注解和命名切点trackP1ayed（trackNumber）定义的。切点定义中的参数与切点方法中的参数名称是一样的，这样就完成了从命名切点到通知方法的参数转移。
 *
 * 现在，我们可以在Spring配置中捋BlankDisc和TrackCounter定义为bean，并启用AspectU自动代理，如程序清单4.7所示。
 */
@Configuration
@EnableAspectJAutoProxy
class TrackCounterConfig{
    //CompactDisc bean
    @Bean
    public CompactDisc sgtPeppers(){
        BlankDisc cd = new BlankDisc("1","2");
        cd.setTitle("123");
        cd.setArtist("123");
        ArrayList<String> tracks = new ArrayList<>();
        tracks.add("sgt");
        tracks.add("the bettal");
        tracks.add("lucy");
        /*cd.setTracks(tracks);*/
        return cd;
    }
    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }
}
/**
 * 到目前为止，在我们所使用的切面中，所包装的都是被通知对象的已有方法。但是，方法包装仅仅是切面所能实现的功能之一。让我们看一下如何通过编写切面，为被通知的对象引入全新的功能。
 */

/**
 * 通过注解引入新功能
 * 一些编程语言， 例如Ruby和Grooyy，有开放类的理念。它们可以不用直接修改对象或类的定义就能够为对象或类增加新的方法。不过，Java并不是动态语言。一旦类编译完成了，我们就很难再为该类添加新的功能了。
 *
 * 但是如果仔细想想，我们在本章中不是一直在使用切面这样做吗? 当然，我们还没有为对象增加任何新的方法，但是已经为对象拥有的方法添加了新功能。如果切面能够为现有的方法增加额外的功能，为什么不能为一个对象增加新的方法呢?
 *
 * 实际上，利用被称为引入的AOP概念，切面可以为Spring bean添加新方法。回顾一下，在Spring中，切面只是实现了它们所包装bean相同接口的代理。如果除了实现这些接口，代理也能暴露新接口的话， 会怎么样呢?
 * 那样的话，切面所通知的bean看起来像是实现了新的接口，即便底层实现类并没有实现这些接口也无所谓。图4.7展示了它们是如何工作
 *
 * 我们需要注意的是，当引入接口的方法被调用时，代理会把此调用委托给实现了新接口的某个其他对象。实际上，一个bean的实现被拆分到了多个类中。
 * 为了验证该主意能行得通，我们为示例中的所有的Performance实现引入下面的 Encoreable接口∶
 */
interface Encoreable{
    void performEncore();
}
class EncoreableImpl implements Encoreable{
    @Override
    public void performEncore() {
        System.out.println("111");
    }
}
/**
 * 暂且先不管Encoreable是不是一个真正存在的单词【】，我们需要有一种方式将这个接口应用到Performance实现中。我们现在假设你能够访问Performance的所有实现，并对其进行修改，让它们都实现Encoreable接口。
 * 但是，从设计的角度来看，这并不是最好的做法，并不是所有的Performance都是具有Encoreable特性的。另外一方面，有可能无法修改所有的Performance实现，当使用第三方实现并且没有源码的时候更是如此。
 *
 * 值得庆幸的是，借助于AOP的引入功能， 我们可以不必在设计上妥协或者侵入性地改变现有的实现。为了实现该功能，我们要创建一个新的切面∶
 */
@Aspect
class EncoreableIndutroducer{
    @DeclareParents(value = "com.example.springinaction4.第一部分Spring核心.node4面向切面.charactor2通过切点确定连接点.Performance",
                    defaultImpl = EncoreableImpl.class )
    public static Encoreable encoreable;
}
/**
 * 可以看到，EncoreableIntroducer是一个切面。但是，它与我们之前所创建的切面不同，它并没有提供前置、后置或环绕通知，而是通过@DeclareParents注解，将Encoreable接口引人到Performance bean中。
 *
 * @DeclareParents注解由三部分组成∶
 *      ●value属性指定了哪种类型的bean要引入该接口。在本例中，也就是所有实现Performance的类型。（标记符后面的加号表示是Performance的所有子类型，而不是Performance本身。）
 *      ●defaultImpl属性指定了为引入功能提供实现的类。在这里，我们指定的是DefaultEncoreable提供实现。
 *      ●@DeclareParents注解所标注的静态属性指明了要引入了接口。在这里，我们所引入的是Encoreable接口。
 * 和其他的切面一样，我们需要在Spring应用中将EncoreableIntroducer声明为一个bean∶
 *      <bean class="concert.EncoreableIntroducer" />
 * Spring的自动代理机制特会获取到它的声明，当Spring发现一个bean使用了（Aspect注解时， Spring就会创建一个代理， 然后捋调用委托给被代理的bean或被引I入的实现，这取决干调用的方法属于被代理的bean还是属于被引入的接口。
 *
 * 在Spring中，注解和自动代理提供了一种很便利的方式来创建切面。它非常简单，并且只涉及到最少的Spring配置。但是，面向注解的切面声明有一个明显的劣势;你必须能够为通知类添加注解。为了做到这一点，必须要有源码。
 *
 * 如果你没有源码的话，或者不想捋AspectU注解放到你的代码之中，Spring为切面提供了另外一种可选方案。让我们看一下如何在Spring XML配置文件中声明切面。
 */


















