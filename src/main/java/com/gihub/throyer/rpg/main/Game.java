package com.gihub.throyer.rpg.main;

import static java.awt.Color.BLACK;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.gihub.throyer.rpg.entity.Player;
import com.gihub.throyer.rpg.tile.TileManager;

public class Game extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3;
  public final int tileSize = originalTileSize * scale; // 48x48 tile
  public final int maxScreenColumn = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenColumn; // 768 px
  public final int screenHeight = tileSize * maxScreenRow; // 576 px

  // FPS
  int FPS = 60;
  
  private TileManager tileManager = new TileManager(this);
  private Thread thread;
  private final KeyHandler directions = new KeyHandler();
  private final Player player = new Player(this, directions);
  
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
    player.update();
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    var graphics2D = (Graphics2D) graphics;
    
    tileManager.draw(graphics2D);
    player.draw(graphics2D);

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
