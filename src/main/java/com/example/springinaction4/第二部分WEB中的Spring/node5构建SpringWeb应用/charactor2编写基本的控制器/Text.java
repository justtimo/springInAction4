package com.example.springinaction4.第二部分WEB中的Spring.node5构建SpringWeb应用.charactor2编写基本的控制器;

import com.example.springinaction4.第二部分WEB中的Spring.node5构建SpringWeb应用.charactor1SpringMVC起步.bean.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/7 17:30
 * @Description:
 */
public class Text {
}
/**
 * 编写基本的控制器
 *
 * 在Spring MVC中，控制器只是方法上添加了@RequestMapping注解的类，这个注解声明了它们所要处理的请求。
 *
 * 开始的时候，我们尽可能简单，假设控制器类要处理对""的请求， 并渲染应用的首页
 */
@Controller                 //声明为一个控制器//
class HomeControllerTWO {
    @RequestMapping(value = "/" ,method = GET)       //处理对"/"的GET请求
    public String home(Model model) {
        return "home";                  //视图名为"home"
    }
}
/**
 * 你可能注意到的第一件事情就是HomeController带有@Controller注解。很显然这个注解是用来声明控制器的，但实际上这个注解对Spring MVC本身的影响并不大。
 *
 * HomeController是一个构造型（stereotype）的注解，它基于@Component注解。在这里，它的目的就是辅助实现组件扫描。因为HomeContro11er带有aContro11er注解，
 * 因此组件扫描器会自动找到HomeContro1ler，并特其声明为Spring应用上下文中的一个bean。
 *
 * 其实，你也可以让HomeController带有@Component注解，它所实现的效果是一样的，但是在表意性上可能会差一些，无法确定HomeContro1ler是什么组件类型。
 *
 * HomeController唯一的一个方法，也就是home （）方法，带有@RequestMapping注解。它的value属性指定了这个方法所要处理的请求路径，method属性细化了它所处理的HTTP方法。
 * 在本例中，当收到对"/"的HTTP GET请求时，就会调用home （）方法。
 *
 * 你可以看到，home （）方法其实并没有做太多的事情∶它返回了一个String类型的"home"。这个String特会被Spring MVC解读为要渲染的视图名称。DispatcherServlet会要求视图解析器捋这个逻辑名称解析为实际的视图。
 *
 * 鉴于我们配置InternalResourceViewResolver的方式，视图名"home"哥会解析为"/WEB-INF/views/home.jsp"路径的JSP。现在，我们会让Spittr应用的首页相当简单，如下所示。
 * Spittr应用的首页，定义为一个简单的JSP: 见 home.jsp
 */

/**
 * 测试控制器
 *  见 :HomeControllerTest
 */


/**
 * 定义类级别的请求处理
 *
 * 拆分RequestMapping,将其路径映射部分分放到类级别上
 */
@Controller
@RequestMapping("/")
class HomeControllerThree {
    @RequestMapping(method = GET)
    public String home(Model model) {
        return "home";
    }
}
/**
 * 在这个新版本的HomeController中，路径现在被转移到类级别的@RequestMapping上，而HTTP方法依然映射在方法级别上。当控制器在类级别上添加@RequestMapping注解时，这个注解会应用到控制器的所有处理器方法上。
 * 处理器方法上的@RequestMapping注解会对类级别上的@RequestMapping的声明进行补充。
 *
 * 就HomeController而言，这里只有一个控制器方法。与类级别的@Request-Mapping合并之后，这个方法的@RequestMapping表明home （）捋会处理对"/"路径的GET请求。
 *
 * 换言之， 我们其实没有改变任何功能.只是捋一些代码换了个地方，但是HomeController所做的事情和以前是一样的。因为我们现在有了测试，所以可以确保在这个过程中，没有对原有的功能造成破坏。
 *
 * 当我们在修改@RequestMapping时，还可以对HomeController做另外一个变更。@RequestMapping的value属性能够接受一个String类型的数组。到目前为止，我们给它设置的都是一个string类型的"/"。
 * 但是，我们还可以特它映射到对"/homepage"的请求，只需特类级别的@RequestMapping改为如下所示∶
 */
@RequestMapping({"/", "/homepage"})
class HomeControllerFour{
    @RequestMapping(method = GET)
    public String home(Model model) {
        return "home";
    }
}
/**
 * 现在，HomeController的home （）方法能够映射到对"/"和"/homepage"的GET请求。
 */

/**
 * 传递模型数据到视图中
 *
 *
 * 到现在为止，就编写超级简单的控制器来说，HomeController已经是一个不错的样例了。但是大多数的控制器并不是这么简单。在Spittr应用中，我们需要有一个页面展现最近提交的 Spittle列表。
 * 因此，我们需要一个新的方法来处理这个页面。
 *
 * 首先，需要定义一个数据访问的Repository。为了实现解耦以及避免陷入数据库访问的细节之中，我们捋Repository定义为一个接口，并在稍后实现它（第10章中）。
 * 此时，我们只需要一个能够获取Spittle列表的Repository，如下所示的spittleRepository功能已经足够了∶
 */
interface SpittleRepositoryOne {
    List<Spittle> findSpittles(long max, int count);

}
/**
 * findSpittles（）方法接受两个参数。其中max参数代表所返回的spittle中，Spittle ID属性的最大值，而count参数表明要返回多少个Spittle对象。为了获得最新的20个Spittle对象，我们可以这样调用findSpittles（）
 *      List<Spittle> recent=spittleRepositoryOne.findSpittles(20,20)
 */
/**
 * 就大部分内容来看，Spittle就是一个基本的POJO数据对象——没有什么复杂的。唯一要注意的是，我们使用Apache CommonLang包来实现equals（）和hashCode（）方法。
 * 这些方法除了常规的作用以外，当我们为控制器的处理器方法编写测试时，它们也是有用的。
 *
 * 既然我们说到了测试，那么我们继续讨论这个话题并为新的控制器方法编写测试。如下的程序清单使用Spring的MockMvc来断言新的处理器方法中你所期望的行为。
 * 见SpittleControllerTest.shouldShowPagedSpittles()
 */
/**
 * 这个测试首先会创建spittleRepository接口的mock实现，这个实现会从它的findSpittles（）方法中返回20个Spittle对象。然后，它捋这个Repository注入到一个新的spittleController实例中，
 * 然后创建MockMvc并使用这个控制器。
 * 需要注意的是，与HomeController不同，这个测试在MockMvc构造器上调用了setSingleView（）。这样的话，mock框架就不用解析控制器中的视图名了。在很多场景中，其实没有必要这样做。
 * 但是对干这个控制器方法，视图名与请求路径是非常相似的.这样按照默认的视图解析规则时，MockMvc就会发生失败，因为无法区分视图路择和控制器的路择。
 * 在这个测试中，构建InternalResourceView时所设置的实际路径是无关紧要的，但我们捋其设置为与InternalResourceViewResolver配置一致。
 *
 * 这个测试对"/spittes"发起GET请求，然后断言视图的名称为spittles并且模型中包含名为spittleList的属性， 在spittleList中包含预期的内容。
 *
 * 当然，如果此时运行测试的话，它特会失败。它不是运行失败，而是在编译的时候就会失败。这是因为我们还没有编写SpittleController。现在，我们创建SpittleController，
 * 让它满足程序清单5.9的预期。如下的spittleController实现捋会满足以上测试的要求。
 */
@Controller
@RequestMapping("/spittles")
class SpittleControllerOne {
    private SpittleRepositoryOne spittleRepository;
    /**
     * 注入SpittleRepositoryOne bean
     * @param spittleRepositoryOne
     */
    @Autowired
    public SpittleControllerOne(SpittleRepositoryOne spittleRepositoryOne) {
        this.spittleRepository = spittleRepositoryOne;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String spittles(Model model){
        //将spittle添加到模型中
        model.addAllAttributes(spittleRepository.findSpittles(200,20));
        //返回视图名
        return "splittes";
    }

}
/**
 * 我们可以看到SspittleController有一个构造器，这个构造器使用了@Autowired注解，用来注入SpittleRepository。这个SpittleRepository随后又用在spittles（）方法中，用来获取最新的spittle列表。
 *
 * 需要注意的是，我们在spittles（）方法中给定了一个Mode1作为参数。这样，spittles（）方法就能特Repository中获取到的Spittle列表填充到模型中。
 * Mode1实际上就是一个 Map（也就是key-value对的集合），它会传递给视图，这样数据就能渲染到客户端了。当调用addAttribute（）方法并且不指定key的时候，那么key会根据值的对象类型推断确定。
 * 在本例中，因为它是一个List<Spittle>，因此，键特会推断为spittleList。
 *
 * spittles（）方法所做的最后一件事是返回spittles作为视图的名字， 这个视图会渲染模型。
 *
 * 如果你希望显式声明模型的key的话，那也尽可以进行指定。例如，下面这个版本的 spittles（）方法与程序清单5.10中的方法作用是一样的∶
 *      model.addAllAttributes("spittleList",spittleRepository.findSpittles(200,20));
 *
 * 如果你希望使用非Spring类型的话，那么可以用java.uti1.Map来代替Model。下面这个版本的spittles（）方法与之前的版本在功能上是一样的∶
 *      public String spittles(Map model){}
 *
 * 既然我们现在提到了各种可替代的方案，那下面还有另外一种方式来编写spittles（）方法∶
 *      public List spittles(){
 *         return spittleRepository.findSpittles(200,20);
 *     }
 *
 * 这个版本与其他的版本有些差别。它并没有返回视图名称，也没有显式地设定模型，这个方法返向的是spitt1e列表。当处理器方法像这样返回对象或集合时，这个值会放到模型中，
 * 模型的key会根据其类型推断得出（在本例中，也就是spittleList）。
 *
 * 而逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针对"/spittles"的GET请求，因此视图的名称特会是spittles（去掉开头的斜线）。
 *
 * 不管你选择哪种方式来编写spittles（）方法，所达成的结果都是相同的。模型中会存储一—个Sspittle列表， key为spittleList，然后这个列表会发送到名为spittles的视图中。
 * 按照我们配置InternalResourceViewResolver的方式，视图的JSP将会是"/WEB- INF/views/spittls.jsp"。
 *
 * 现在，数据已经放到了模型中，在JSP中该如何访问它呢?实际上，当视图是JSP的时候， 模型数据会作为请求属性放到请求（request）之中。
 * 因此，在spittles.jsp文件中可以使用 JSTL（JavaServer Pages Standard Tag Library）的<c∶forEach>标签渲染spittle列表
 */

/**
 * 尽管SpittleController很简单，但是它依然比HomeController更进一步了。不过，SpittleController和HomeController都没有处理任何形式的输入。
 * 现在，让我们扩展spittleController，让它从客户端接受一些输入。
 */
