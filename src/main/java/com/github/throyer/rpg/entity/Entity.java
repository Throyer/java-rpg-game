package com.github.throyer.rpg.entity;

import com.github.throyer.rpg.utils.Direction;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public abstract class Entity {
  protected Integer x = 0;
  protected Integer y = 0;
  protected Integer speed = 0;
  
  protected BufferedImage imageUpOne;
  protected BufferedImage imageUpTwo;

  protected BufferedImage imageDownOne;
  protected BufferedImage imageDownTwo;

  protected BufferedImage imageLeftOne;
  protected BufferedImage imageLeftTwo;

  protected BufferedImage imageRightOne;
  protected BufferedImage imageRightTwo;

  protected Direction direction;
  
  protected Integer spriteCounter = 0;
  protected Integer spriteNumber = 1;
  
  public abstract void update();
  public abstract void draw(Graphics2D graphics2D);
}
