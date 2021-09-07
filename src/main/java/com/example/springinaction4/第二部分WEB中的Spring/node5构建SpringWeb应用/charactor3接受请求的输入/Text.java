package com.example.springinaction4.第二部分WEB中的Spring.node5构建SpringWeb应用.charactor3接受请求的输入;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * SpringMVC 允许以多种方式将客户端中的数据传输到控制器的处理方法中,包括:
 *      1. 查询参数
 *          1.1 分页
 *              RequestParam注解,defualValue属性:请求中参数不存在,则使用默认值.
 *              RequestParam(defaultValue="20") long max:虽然defaultValue是String的,但是当绑定到方法参数时,会转为Long型
 *      2. 表单参数
 *          2.1 重定向
 *              当InternalResourceViewResolver看到视图格式中的"redirect∶"前缀时，它就知道要捋其解析为重定向的规则，而不是视图的名称。
 *              在本例中，它捋会重定向到用户基本信息的页面。例如，如果spitter.username属性的值为"jbauer"，那么视图捋会重定向到"/spitte/jbauer"。
 *              需要注意的是，除了"redirect∶"，InternalResourceViewResolver还能识别"forward∶"前缀。当它发现视图格式中以"forward∶"作为前缀时，
 *              请求捋会前往（forward）指定的URL路径，而不再是重定向。
 *          2.2 表单参数校验
 *              JAVA校验API:只需要确保类路径下包含JAVA校验API的实现即可,例如: Hibernate Validator
 *              API列表:
 *                  AssertFalse,AssertTrue:注解的元素必须是BOOlean类型,值为False或者True
 *                  DecimalMax,DecimalMin: 注解的元素必须是数字,值要小于等于(min 大于等于)给定的BigDecimalString值
 *                  Digits: 必须是数字,且值必须有指定的位数
 *                  Future: 必须是一个将来的日期
 *                  Max,Min: 元素必须是数字,且要小于等于(min 大于等于)给定的值
 *                  NotNull,Null: 必须不能为空(必须为空)
 *                  Past: 必须是过去的日期
 *                  Pattern: 匹配给定的正则表达式
 *                  Size: 必须是String,数组,集合,并且长度要符合给定的范围
 *              注解@Valid: 告知Spring,确保这个对象满足校验限制
 *      3. 路径变量
 *          3.1 在理想情况下，要识别的资源（Spittle）应该通过URL路径进行标示，而不是通过查询参数。对"/spittles/12345"发起GET请求要优于对"/spittles/show?spittle id=12345"发起请求。
 *              前者能够识别出要查询的资源，而后者描述的是带有参数的一个操作———本质上是通过HTTP发起的RPC。
 *              RequestMapping(value="/{spittelId}")
 *              public String spittel(@PathVariable("spittelId") Long spittelId){}
 *              注解@PathVariable: 不管占位符部分的值是什么,都会传递到处理器方法的spittelId参数中
 *              如果方法参数名与占位符名称相同,则可以省略@PathVaribel中的value属性
 */
public class Text {
    @RequestMapping("/{spittelId}")
    public String spittel(@PathVariable("spittelId") Long spittelId){
        return "redirect:/spittel";
    }

    @RequestMapping("/valid")
    public void valid(@Valid Spitter spitter){ }
}
class Spitter{
    @NotNull
    @Size(min=5, max=16)
    private String username;

    @NotNull
    @Size(min=5, max=25)
    private String password;

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    @NotNull
    @Email
    private String email;
}
