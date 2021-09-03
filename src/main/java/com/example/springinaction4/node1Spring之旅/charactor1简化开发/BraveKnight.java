package com.example.springinaction4.node1Spring之旅.charactor1简化开发;

public class BraveKnight implements Knight {

  private Quest quest;
  //AOP应用:在探险前后,诗人会记录并赞颂骑士
  //但是:骑士真的要管理诗人吗?每个人不是应该只做自己的事情吗?如果有骑士不需要诗人呢?如果诗人为null呢?
  //所以将诗人抽象为切面,见minstrel.xml
  private Minstrel minstrel;

  public BraveKnight(Quest quest,Minstrel minstrel) {
    //Quest被当做参数注入进来,注意与DamselRescuingKnight的区别
    //构造器注入是spring 依赖注入的方式之一
    this.quest = quest;
    this.minstrel = minstrel;
  }

  public void embarkOnQuest() {
    minstrel.singBeforeQuest();
    quest.embark();
    minstrel.singAfterQuest();
  }

}
