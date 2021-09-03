package com.example.springinaction4.node1Spring之旅.charactor1简化开发;

public class DamselRescuingKnight implements Knight {

  private RescueDamselQuest quest;

  public DamselRescuingKnight() {
    //与RescueDamselQuest紧耦合
    this.quest = new RescueDamselQuest();
  }

  public void embarkOnQuest() {
    quest.embark();
  }

}
