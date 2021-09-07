package com.example.springinaction4.第一部分Spring核心.node3高级装配.charactor3处理自动装配的歧义;

/**
 * 分别由三个类实现了该接口,并将Setter方法使用了@autowaire注解,并且都使用了@Component注解(组件臊面,能够发现它们并创建为SPring上下文中的bean)
 * 但是当试图自动装备setDessert()方法时,由于没有唯一且无歧义的可选值,所以报错.
 * 解决方案:
 *      1.将某个bean设置为首选(primary) 例如:IceCream.java
 *      2.使用限定符(qualifier)
 * 这样帮助Spring将可选范围的bean缩小到只有一个
 */
public interface Dessert {
}
class Test{
    public static void main(String[] args) {
        Cake cake = new Cake();
        cake.setDessert(new Cookies());
    }
}



