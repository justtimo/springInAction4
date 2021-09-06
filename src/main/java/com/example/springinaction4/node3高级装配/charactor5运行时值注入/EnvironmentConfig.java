package com.example.springinaction4.node3高级装配.charactor5运行时值注入;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

/**
 * 为了避免硬编码,让某些值在运行时再确定,Spring提供了两种在运行时求值的方法:
 *    1.属性占位符
 *    2.Spring表达式语言(SPEL)
 * 两种方式用法类似,但目的和行为有差别
 */

/**
 * 注入外部的值
 *  Spring中,处理外部值最简单的方式就是:  声明属性源 ,通过SPring的Environment来检索属性
 *
 */
@Configuration
@PropertySource("classpath:/com/soundsystem/app.properties")//声明属性源
public class EnvironmentConfig {

  @Autowired
  Environment env;

  @Bean
  public BlankDisc blankDisc() {
    return new BlankDisc(
        env.getProperty("disc.title"),//检索属性值
        env.getProperty("disc.artist"));
  }
}
/**
 * blankDisc()中会创建一个BlankDisc实例,他的构造器参数是从属性文件中获取的,而这是通过getProperty()方法实现的
 *
 * 深入了解Spring的Environment
 *  Spring的getProperty()方法还有四个重载形式:
 *    String getProperty(String key)
 *    String getProperty(String key, String defaultValue);指定属性不存在时,会使用一个默认值
 *    假设你想要获取的值表示连接池中所维持的连接数量,如果使用上面的两个重载方法,则还需要转换为Integer类型,而使用下面两个重载方法,则方便多了
 *    <T> T getProperty(String key, Class<T> targetType);
 *    <T> T getProperty(String key, Class<T> targetType, T defaultValue);
 */
@Configuration
@PropertySource("classpath:/com/soundsystem/app.properties")
class Test{
  @Autowired
  Environment env;

  int connect;
  int count;
  public void setConnect(int connect) {
    this.connect = env.getProperty("db.connction.connect",Integer.class,30);
  }
  public void setCount(int count){
    boolean isExsit = env.containsProperty("db.connction.count");
    //返回激活profile名称的数组
    String[] activeProfiles = env.getActiveProfiles();
    //返回默认profile名称的数组
    String[] defaultProfiles = env.getDefaultProfiles();
    //如果Environment支持给定profile的话,就返回true
    boolean acceptsProfiles = env.acceptsProfiles(Profiles.of("dev"));
    this.count = env.getRequiredProperty("db.connction.count",Integer.class);
  }
}
/**
 * Environment还提供了几个与属性相关的方法,如果在使用getProperty()时没有指定默认值,并且这个属性没有定义的话,获取到的值是null.
 * 如果你希望这个属性必须要定义,那么可以使用getRequiredProperty(),如果db.connction.count没有定义,则会抛出异常
 * 如果想检查一下某个属性是否存在的话,可以使用containsProperty()
 * 如果想将属性解析为类的话,可以使用getPropertyAsClass()
 * 此外,Spring还提供了方法检查哪些profile处于激活状态
 *    getActiveProfiles()
 *    getDefaultProfiles()
 *    acceptsProfiles()
 */


/**
 * 解析属性占位符
 *  在Spring的装配中,占位符的形式为"${....}"包装的属性名称,作为例子,我们在XML可以使用如下方式解析
 *
 *  如果我们使用的是组件扫描和自动装配来创建和初始化应用组件的话,那么就没有指定占位符的配置文件或类了.可以使用@Value注解
 */
class AnnotionValue{
  @Value("${disc.title}")
  private int test1;
  private int test2;
}
/**
 * 为了使用占位符，我们必须要配置一个PropertyPlaceholderConfigurer bean 或 PropertySourcesPlaceholderConfigurer bean。
 * 从Spring3.1开始，推荐使用PropertySourcesPlaceholderConfigurer，因为它能够基于Spring Environment及其属性源来解析占位符。
 * 下例是使用Java 配置类的方式
 */
class PropertySourcesPlaceholder{
  @Bean
  public static PropertySourcesPlaceholder propertySourcesPlaceholder(){
    return new PropertySourcesPlaceholder();
  }
}
/**
 * XML方式
 *  <context:property-placeholder></context:property-placeholder>
 *
 * 解析外部属性能够将值的处理推迟到运行时,但是他的关注点在于根据名称解析来自于Spring Environment和属性源的属性
 * 而Spring表达式语言提供了一种更通用的方式在运行时计算所要注入的值
 */

/**
 * 使用Spring表达式语言进行装配
 *    Spring3引入了Spring表达式语言（Spring Expression Language，SpEL），它能够以一种强大和简洁的方式捋值装配到bean属性和构造器参数中，在这个过程中所使用的表达式会在运行时计算得到值。
 *      使用SpEL，你可以实现超乎想象的装配效果，这是使用其他的装配技术难以做到的（甚至是不可能的）。
 *    SpEL拥有很多特性， 包括∶
 *      ●使用bean的ID来引用bean;
 *      ●调用方法和访问对象的属性;
 *      ●对值进行算术、关系和逻辑运算;
 *      ● 正则表达式匹配;
 *      ●集合操作。
 *    在本书后面的内容中你可以看到，SpEL能够用在依赖注入以外的其他地方。
 *        例如， Spring Security支持使用SpEL表达式定义安全限制规则。
 *        另外，如果你在Spring MVC应用中使用 Thymeleaf模板作为视图的话，那么这些模板可以使用SpEL表达式引用模型数据。
 *    作为起步，我们看几个SpEL表达式的样例，以及如何捋其注入到bean中。然后我们会深入学习一些SpEL的基础表达式，它们能够组合起来形成更为强大的表达式。
 *    SpEL样例
 *      SpEL是一种非常灵活的表达式语言，所以在本书中不可能面面俱到地介绍它的各种用法。
 *      但是我们可以展示几个基本的例子， 这些例子会激发你的灵感，有助于你编写自己的表达式。
 *      需要了解的第一件事情就是SpEL表达式要放到"#{... }"之中，这与属性占位符有些类似，属性占位符需要放到"$（... }"之中。
 *      下面所展现的可能是最简单的SpEL表达式了∶
 *          #{1}
 *          除去"#{...}"标记之后，剩下的就是SpEL表达式体了，也就是一个数字常量。
 *          这个表达式的计算结果就是数字1，这恐怕并不会让你感到丝毫惊讶。
 *      当然，在实际的应用程序中，我们可能会使用更加有意思的表达式，如∶
 *          #{T(System).currentTimeMillis ()}
 *          它的最终结果是计算表达式的那一刻当前时间的毫秒数。
 *          T()表达式会将java.lang.System视为Java中对应的类型，因此可以调用其static修饰的currentTimeMillis()方法。
 *      SpEL表达式也可以引用其他的bean或其他bean的属性。例如，如下的表达式会计算得到ID为sgtPeppers的bean的artist属性∶
 *          #{sgtPeppers.artist}
 *      我们还可以通过systemProperties对象引用系统属性∶
 *          #{systemProperties ['disc.title'] }
 *      这只是SpEL的几个基础样例。让我们看一下在bean装配的时候如何使用这些表达式。
 *        如果通过组件扫描创建bean的话，在注入属性和构造器参数时，我们可以使用@value注解，这与之前看到的属性占位符非常类似。
 *        不过，在这里我们所使用的不是占位符表达式，而是 SpEL表达式。例如，下面的样例展现了BlankDisc，它会从系统属性中获取专辑名称和艺术家的名字∶
 *          public BlankDisc(
 *            @value("#{systemProperties['disc.title']}") String title,
 *            @Value("#{systemProperties['disc.artist']〕") String artist)
 *            {
 *                this.title = title;
 *                this.artist = artist;
 *            }
 *       在XML配置中，你可以捋SpEL表达式传入<property>或<constructor-arg>的value属性中，或者捋其作为p-命名空间或c-命名空间条目的值。
 *       例如，在如下BlankDisc bean的 XML声明中，构造器参数就是通过SpEL表达式设置的∶
 *          <bean
 *              id=" sgtPeppers"
 *              class=" goundsystem.BlankDisc"
 *              c:_title=*#{systemProperties['disc.title'] )"
 *              c:_artist='#{systemProperties ['disc.artist']}"
 *          />
 *      我们已经看过了几个简单的样例，也学习了如何捋SpEL解析得到的值注入到lbean中，那现在就来继续学习一下SpEL所支持的基础表达式吧。
 *          表示字面值我们在前面已经看到了一个使用SpEL来表示整数字面量的样例。它实际上还可以用来表示浮点数、String值以及Boolean值。
 *          下面的SpEL表达式样例所表示的就是浮点值∶
 *              #{3.14159}
 *          数值还可以使用科学记数法的方式进行表示。如下面的表达式计算得到的值就是98，700∶
 *              #{9.87E4}
 *          SpEL表达式也可以用来计算String类型的字面值，如∶
 *              #{'ello'}
 *          最后，字面值true和false的计算结果就是它们对应的Boolean类型的值。例如∶
 *              #{false}
 *      在SpEL中使用字面值其实没有太大的意思，毕竟将整型属性设置为1，或者捋Boolean属性设置为false时，我们并不需要使用SpEL。我承认在SpEL表达式中，只包含字面值情况并没有太大的用处。
 *      但需要记住的一点是，更有意思的SpEL表达式是由更简单的表达式组成的，因此了解在SpEL中如何使用字面量还是很有用处的。当组合更为复杂的表达式时，你迟早会用到它们。
 *  引用bean、属性和方法
 *      SpEL所能做的另外一件基础的事情就是通过ID引用其他的bean。
 *      例如，你可以使用SpEL捋一个bean装配到另外一个bean的属性中，此时要使用bean ID作为SpEL表达式（在本例中，也就是sgtPeppers）∶
 *          #{sgtPeppers}
 *      现在，假设我们想在一个表达式中引用sgtPeppers的artist属性∶
 *          #{sgtPeppers.artist)
 *      表达式主体的第一部分引用了一个ID为sgtPeppers的bean，分割符之后是对artist属性的引用。
 *      除了引用bean的属性，我们还可以调用bean上的方法。例如，假设有另外一个bean，它的ID为artistSelector，我们可以在SpEL表达式中按照如下的方式来调用bean的 selectArtist （）方法∶
 *          #{artistSelector.selectArtist()}
 *      对于被调用方法的返回值来说，我们同样可以调用它的方法。例如，如果selectArtist（）方法返回的是一个string，那么可以调用toUpperCase（）捋整个艺术家的名字改为大写字母形式∶
 *          #{artistseloctor.selectArtist().toUpperCase()}
 *      如果selectArtist（）的返回值不是nul1的话， 这没有什么问题。为了避免出现 NullPointerException，我们可以使用类型安全的运算符∶
 *          #{artistselector.selectArtist() ?.toUpperCase()})
 *      与之前只是使用点号来访问toUpperCase（）方法不同，现在我们使用了"?."运算符。这个运算符能够在访问它右边的内容之前，确保它所对应的元素不是nu11。
 *      所以，如果selectArtist（）的返回值是nul1的话，那么SpEL将不会调用toUpperCase（）方法。表达式的返回值会是null。
 *  在表达式中使用类型
 *      如果要在SpEL中访间类作用域的方法和常量的话，要依赖工（）这个关键的运算符。例如，为了在SpEL中表达Java的Math类，需要按照如下的方式使用T()运算符:
 *          T(java.lang.Math)
 *          这里所示的T（）运算符的结果会是一个class对象，代表了java.lang.Math。
 *      如果需要的话.我们甚至可以捋其装配到一个class类型的bean属性中。但是T（）运算符的真正价值在于它能够访问目标类型的静态方法和常量。例如，假如你需要捋PI值装配到bean属性中。如下的SpEL就能完成该任务∶
 *          T(java.lang.Math) .PI
 *      与之类似，我们可以调用T（）运算符所得到类型的静态方法。我们已经看到了通过T（） 调用system.currentTimeMillis（）。如下的这个样例会计算得到一个0到1之间的随机数∶
 *          T(java.1ang.Math).random()
 *  SpEL运算符
 *        算术运算
 *            +、-、*、/、%、^
 *        比较运算
 *            <、>、==、<=、>=、lt、gt、eq、le、ge
 *        逻辑运算
 *            and 、or 、not 、|
 *        条件运算
 *            ?: (ternary)、?: (E1vis)
 *        正则表达式
 *            matches
 *    作为使用上述运算符的一个简单样例， 我们看一下下面这个SpEL表达式∶
 *          #{2 * T(java.1ang.Math) .PI * circle.radius}
 *          这不仅是使用SpEL中乘法运算符（*）的绝佳样例，它也为你展现了如何特简单的表达式组合为更为复杂的表达式。
 *          在这里PI的值乘以2，然后再乘以radius属性的值，这个属性来源于ID为circle的bean。
 *          实际上，它计算了circle bean中所定义圆的周长。
 *    类似地，你还可以在表达式中使用乘方运算符（^）来计算圆的面积;
 *       #(T(java.lang.Math) .PI · circle.radius ^ 2〕
 *       "^"是用于乘方计算的运算符。在本例中， 我们使用它来计算圆半径的平方。
 *   当使用String类型的值时，"+"运算符执行的是连接操作，与在Java中是一样的∶
 *      #(disc.title +'by '+ disc.artist)
 *   SpEL同时还提供了比较运算符，用来在表达式中对值进行对比。注意在表3.1中，比较运算符有两种形式:符号形式和文本形式。在大多数情况下，符号运算符与对应的文本运算符作用是相同的，使用哪一种形式均可以。
 *   例如，要比较两个数字是不是相等，可以使用双等号运算符（==）∶
 *      #{counter.total == 100}
 *      或者，也可以使用文本型的eg运算符∶
 *      #(counter.total eq 100}
 *      两种方式的结果都是一样的。表达式的计算结果是个Boolean值∶如果counter.total等于 100的话，为true，否则为false。
 *  SpEL还提供了三元运算符（ternary），它与Java中的三元运算符非常类似。例如.如下的表达式会判断如果scoreboard.score>1000的话，计算结果为String类型的"Winner !"，否则的话，结果为Loser∶
 *      #{scoreboard.score > 100 ? "Winner!" : "Loser"}
 *      三元运算符的一个常见场景就是检查nul1值，并用一个默认值来替代null。例如，如下的表达式会判断disc.title的值是不是nul1，如果是nul1的话，那么表达式的计算结果就会是"Rattle and Hum"∶
 *       #(disc.title ?: 'Rattle and Hum'}
 *
 * 计算正则表达式
 *    当处理文本时，有时检查文本是否匹配某种模式是非常有用的。SpEL通过matches运算符支持表达式中的模式匹配。matches运算符对String类型的文本（作为左边参数）应用正则表达式（作为右边参数）。
 *    matches的运算结果会返回一个Boolean类型的值∶如果与正则表达式相匹配，则返回true;否则返回false。为了进一步解释matches运算符，假设我们想判断一个字符串是否包含有效的邮件地址。
 *    在这个场景下，我们可以使用matches运算符，如下所示∶
 *        #{admin.email matches'[a-zA-Z0-9._$+-]+@[a-zA-Z0-9.-]+\\.com'}
*         探寻正则表达式语法的秘密超出了本书的范围， 同时我们也应该意识到这里的正则表达式还不足够健壮来涵盖所有的场景。但对于演示matches运算符的用法，这已经足够了。
 * 计算集合
 *    SpEL中最令人惊奇的一些技巧是与集合和数组相关的。最简单的事情可能就是引用列表中的一个元素了∶
 *        #{jukebox.songs [4].title}
 *        这个表达式会计算songs集合中第五个（基于零开始）元素的title属性，这个集合来源于ID为jukebox bean。
 *        为了让这个表达式更丰富一些，假设我们要从jukebox中随机选择一首歌∶
 *            #{jukebox.songs [T(java.lang.Math).random() *jukebox.songs.size() ].title}
 *            "[]"运算符用来从集合或数组中按照索引获取元素， 实际上，它还可以从String中获取一个字符。比如∶
 *            #{'This is a test'[3]}
 *            这个表达式引用了string中的第四个（基于雾开始）字符，也就是"s"。
 *        SpEL还提供了查询运算符（.?[]），它会用来对集合进行过滤，得到集合的一个子集。作为阐述的样例，假设你希望得到jukebox中artist属性为Aerosmith的所有歌曲。
 *        如下的表达式就使用查询运算符得到了Aerosmith的所有歌曲∶
 *            #{jukebox.song8.?[artist eq 'Aerosmith'] }
 *            可以看到，选择运算符在它的方括号中接受另一个表达式。当SpEL迭代歌曲列表的时候. 会对歌曲集合中的每一个条目计算这个表达式。如果表达式的计算结果为true的话，那么条目会放到新的集合中。
 *            否则的话，它就不会放到新集合中。在本例中，内部的表达式会检查歌曲的 artist属性是不是等于Aerosmith。
 *        SpEL还提供了另外两个查询运算符∶".^[]"和".$[]"，它们分别用来在集合中查询第一个匹配项和最后一个匹配项。例如，考虑下面的表达式，它会查找列表中第一个artist属性为Aerosmith的歌曲∶
 *            #(〔jukebox.songs.^[artist eq 'Aerosmith']}
 *        最后，SpEL还提供了投影运算符（.![]），它会从集合的每个成员中选择特定的属性放到另外—个集合中。作为样例，假设我们不想要歌曲对象的集合，而是所有歌曲名称的集合。
 *        如下的表达式会特title属性投影到一个新的string类型的集合中∶
 *            #(jukebx.songs.![title])
 *            实际上，投影操作可以与其他任意的SpEL运算符一起使用。比如.我们可以使用如下的表达式获得Aerosmith所有歌曲的名称列表∶
 *            #{jukebox.songs.? [artist eq 'Aerosmith'].! [title] )
 *        我们所介绍的只是SpEL功能的一个皮毛。在本书中还有更多的机会继续介绍SpEL， 尤其是在定义安全规则的时候。
 *        我们有一个提示。在动态注入值到Spring bean时， SpEL是一种很便利和强大的方式。我们有时会忍不住编写很复杂的表达式。
 *        但需要注意的是，不要让你的表达式太智能。你的表达式越智能，对它的测试就越重要。
 *        SpEL毕竟只是 String类型的值， 可能测试起来很困难。鉴于这一点，我建议尽可能让表达式保持简洁，这样测试不会是什么大问题。
 */
























