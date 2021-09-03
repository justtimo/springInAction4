package com.example.springinaction4.node1Spring之旅.charactor1简化开发.config;

import com.example.springinaction4.node1Spring之旅.charactor1简化开发.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;


@Configuration
public class KnightConfig {

  @Bean
  public Knight knight() {
    return new BraveKnight(quest(),minstrel());
  }

  @Bean
  public Quest quest() {
    return new SlayDragonQuest(stream());
  }

  @Bean
  public PrintStream stream() {
    return new FakePrintStream();
  }

  @Bean
  public Minstrel minstrel() {
      return new Minstrel(stream());
  }

}
