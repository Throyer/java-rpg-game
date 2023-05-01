package com.github.throyer.rpg.main;

import lombok.extern.log4j.Log4j2;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

@Log4j2
public class Main {

  public static void main(String[] args) {
    log.info("initializing GUI");
    
    var window = new JFrame("generic RPG");

    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setResizable(false);

    var game = new Game();

    window.add(game);
    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    game.start();
  }
}
