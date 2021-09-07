package com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor4通过XML装配bean.collections;

import com.example.springinaction4.第一部分Spring核心.node2装配bean.charactor1组件扫描.CompactDisc;

import java.util.List;

/**
 * 装配集合: 这是<constructor-arg></constructor-arg>能做到,但是c命名空间无法做到的
 *
 *
 */
public class BlankDisc implements CompactDisc {
  //名称
  private String title;
  //演唱者
  private String artist;
  //磁道列表
  private List<String> tracks;

  public BlankDisc(String title, String artist, List<String> tracks) {
    this.title = title;
    this.artist = artist;
    this.tracks = tracks;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
    for (String track : tracks) {
      System.out.println("-Track: " + track);
    }
  }

}
