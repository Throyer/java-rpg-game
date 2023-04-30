package com.gihub.throyer.rpg.main;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
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
