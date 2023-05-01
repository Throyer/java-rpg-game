package com.github.throyer.rpg.main;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;
import static java.util.Map.of;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class KeyHandler implements KeyListener {
    private static final Map<Integer, Boolean> directions = new HashMap<>(of(
      VK_W, false,
      VK_S, false,
      VK_A, false,
      VK_D, false
    ));

    public Boolean up() {
      return directions.get(VK_W);
    }

    public Boolean down() {
        return directions.get(VK_S);
    }

    public Boolean left() {
        return directions.get(VK_A);
    }

    public Boolean right() {
        return directions.get(VK_D);
    }

    @Override
    public void keyTyped(KeyEvent event) { }

    @Override
    public void keyPressed(KeyEvent event) {
      var code = Integer.valueOf(event.getKeyCode());
      if (directions.containsKey(code)) {
        directions.put(code, true);
      }
    }

    @Override
    public void keyReleased(KeyEvent event) {
      var code = Integer.valueOf(event.getKeyCode());
      if (directions.containsKey(code)) {
        directions.put(code, false);
      }
    }
}
