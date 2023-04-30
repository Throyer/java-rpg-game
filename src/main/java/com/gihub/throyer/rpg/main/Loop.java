package com.gihub.throyer.rpg.main;

import static java.lang.System.nanoTime;
import static java.util.Objects.nonNull;

public class Loop {
  public static void start(Integer FPS, Thread thread, Runnable runnable) {    
    double drawInterval = (double) 1000000000 / FPS;
    double delta = 0;

    long lastTime = nanoTime();
    long currentTime = 0;
    long timer = 0;
    int drawCount = 0;

    while (nonNull(thread)) {
      currentTime = nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        runnable.run();
        delta--;
        drawCount++;
      }

      if (timer >= 1000000000) {
        System.out.printf("\rFPS: %s", drawCount);

        drawCount = 0;
        timer = 0;
      }
    }
  }
}
