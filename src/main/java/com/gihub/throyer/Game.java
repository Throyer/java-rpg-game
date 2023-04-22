package com.gihub.throyer;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game extends Canvas implements Runnable {

    /**
     * Altura
     */
    private static final Integer WIDTH = 240;

    /**
     * Largura
     */
    private static final Integer HEIGHT = 160;

    private static final Integer SCALE = 3;

    private static JFrame frame;

    private Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame = new JFrame("Generic game window");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void run() {

    }

    public static void start(String[] args) {
        new Game();
    }
}
