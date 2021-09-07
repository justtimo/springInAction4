package com.example.springinaction4.第一部分Spring核心.node1Spring之旅;

import com.example.springinaction4.第一部分Spring核心.node1Spring之旅.node1Spring之旅.charactor1简化开发.BraveKnight;
import com.example.springinaction4.第一部分Spring核心.node1Spring之旅.node1Spring之旅.charactor1简化开发.Minstrel;
import com.example.springinaction4.第一部分Spring核心.node1Spring之旅.node1Spring之旅.charactor1简化开发.Quest;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class BraveKnightTest {

  @Test
  public void knightShouldEmbarkOnQuest() {
    Quest mockQuest = mock(Quest.class);
    Minstrel minstrel = mock(Minstrel.class);
    BraveKnight knight = new BraveKnight(mockQuest,minstrel);
    knight.embarkOnQuest();
    verify(mockQuest, times(1)).embark();
  }

}
