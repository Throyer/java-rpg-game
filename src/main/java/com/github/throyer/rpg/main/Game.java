package com.github.throyer.rpg.main;

import com.github.throyer.rpg.entity.Player;
import com.github.throyer.rpg.tile.TileManager;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;

@Log4j2
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
  int TARGET_FPS = 60;
  
  private final TileManager tileManager = new TileManager(this);
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
    log.info("starting game");
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
    Loop.start(TARGET_FPS, thread, () -> {
      update();
      repaint();
    });
  }
}
