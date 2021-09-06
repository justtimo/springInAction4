package com.example.springinaction4.node3高级装配.charactor5运行时值注入;


import org.springframework.beans.factory.annotation.Value;

public class BlankDisc {
  @Value("${disc.title}")
  private final String title;
  private final String artist;

  public BlankDisc(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

}
