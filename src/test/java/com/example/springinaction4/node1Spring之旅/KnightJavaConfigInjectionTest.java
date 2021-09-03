package com.example.springinaction4.node1Spring之旅;

import com.example.springinaction4.node1Spring之旅.charactor1简化开发.FakePrintStream;
import com.example.springinaction4.node1Spring之旅.charactor1简化开发.Knight;
import com.example.springinaction4.node1Spring之旅.charactor1简化开发.config.KnightConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= KnightConfig.class,loader=AnnotationConfigContextLoader.class)
public class KnightJavaConfigInjectionTest {

  @Autowired
  Knight knight;

  @Autowired
  FakePrintStream printStream;

  @After
  public void clearPrintStream() {
    printStream.clear();
  }

  @Test
  public void shouldInjectKnightWithSlayDragonQuest() {
    knight.embarkOnQuest();
    assertEquals(
        "Embarking on quest to slay the dragon!\n",
        printStream.getPrintedString());
  }

}
