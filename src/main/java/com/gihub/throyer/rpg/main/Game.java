package com.gihub.throyer.rpg.main;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3;
  final int tileSize = originalTileSize * scale; // 48x48 tile
  final int maxScreenColumn = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenColumn; // 768 px
  final int screenHeight = tileSize * maxScreenRow; // 576 px

  // FPS
  int FPS = 60;

  private Thread thread;
  private final KeyHandler directions = new KeyHandler();

  // player default position
  private int playerX = 100;
  private int playerY = 100;
  private int playerSpeed = 4;

  public Game() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(directions);
    this.setFocusable(true);
  }

  public void start() {
    this.thread = new Thread(this);
    thread.start();
  }

  public void update() {
    if (directions.up()) {
      playerY -= playerSpeed;
    }

    if (directions.down()) {
      playerY += playerSpeed;
    }

    if (directions.left()) {
      playerX -= playerSpeed;
    }

    if (directions.right()) {
      playerX += playerSpeed;
    }
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    var graphics2D = (Graphics2D) graphics;

    graphics2D.setColor(WHITE);

    graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
    graphics2D.dispose();
  }
  
  @Override
  public void run() {
    Loop.start(FPS, thread, () -> {
      update();
      repaint();
    });
  }
}
