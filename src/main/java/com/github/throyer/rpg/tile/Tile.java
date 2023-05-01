package com.github.throyer.rpg.tile;

import com.github.throyer.rpg.utils.Resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
  private BufferedImage image;
  public Boolean collision = false;

  public BufferedImage getImage() {
    return image;
  }
  
  public Boolean hasCollision() {
    return collision;
  }

  public Tile(String path) {
    try {
      this.image = Resources.tile(path);
    } catch (IOException | NullPointerException exception) {
      exception.printStackTrace();
    }
  }
}
