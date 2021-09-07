package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor4通过XML装配bean;

import com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc;

/**
 *之前的DI都是类型装配:将对象的引用装配到依赖于他们的其他对象中
 *
 * 如果需要一个字面量值来配置对象,该怎么做呢?
 * 见: ConstructorArgValueTest-context.xml
 * <constructor-arg>的value属性,通过给该属性赋值,表名以给定字面量的值注入到构造其中
 * 也可以使用c命名空间的方式实现
 */
public class BlankDisc implements CompactDisc {

  private String title;
  private String artist;

  public BlankDisc(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }

}
