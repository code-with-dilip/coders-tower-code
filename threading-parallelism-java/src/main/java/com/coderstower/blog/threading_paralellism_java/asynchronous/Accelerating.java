package com.coderstower.blog.threading_paralellism_java.asynchronous;

public class Accelerating implements Runnable {
  @Override
  public void run() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
  }
}
