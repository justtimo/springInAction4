package com.example.springinaction4.第一部分Spring核心.node1Spring之旅.charactor1简化开发;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FakePrintStream extends PrintStream {

  private static StringBuffer printBuffer = new StringBuffer();


  public FakePrintStream() {
    super(new ByteArrayOutputStream());
  }

  @Override
  public void println(String string) {
    printBuffer.append(string).append("\n");
  }

  public String getPrintedString() {
    return printBuffer.toString();
  }

  public void clear() {
    printBuffer = new StringBuffer();
  }
}
