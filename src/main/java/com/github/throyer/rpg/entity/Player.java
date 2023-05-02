package com.github.throyer.rpg.entity;

import static com.github.throyer.rpg.utils.Direction.DOWN;
import static com.github.throyer.rpg.utils.Direction.LEFT;
import static com.github.throyer.rpg.utils.Direction.RIGHT;
import static com.github.throyer.rpg.utils.Direction.UP;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.stream.Stream;

import com.github.throyer.rpg.main.Game;
import com.github.throyer.rpg.main.KeyHandler;
import com.github.throyer.rpg.utils.Resources;
import lombok.Getter;

public class Player extends Entity {
  private final Game game;
  private final KeyHandler keys;
  
  @Getter
  private final Integer screenX;
  
  @Getter
  private final Integer screenY;

  public Player(Game game, KeyHandler keys) {
    this.game = game;
    this.keys = keys;

    this.screenX = game.screenWidth / 2 - (game.tileSize / 2);
    this.screenY = game.screenHeight / 2 - (game.tileSize / 2);

    this.setDefaultValues();
    this.readImages();
  }

  private void setDefaultValues() {
    this.worldX = game.tileSize * 23;
    this.worldY = game.tileSize * 21;
    
    this.speed = 4;
    this.direction = DOWN;
  }

  private void readImages() {
    try {
      this.imageUpOne = Resources.sprite("player/boy_up_1.png");
      this.imageUpTwo = Resources.sprite("player/boy_up_2.png");
      this.imageDownOne = Resources.sprite("player/boy_down_1.png");
      this.imageDownTwo = Resources.sprite("player/boy_down_2.png");
      this.imageLeftOne = Resources.sprite("player/boy_left_1.png");
      this.imageLeftTwo = Resources.sprite("player/boy_left_2.png");
      this.imageRightOne = Resources.sprite("player/boy_right_1.png");
      this.imageRightTwo = Resources.sprite("player/boy_right_2.png");
    } catch (IOException | NullPointerException exception) {
      exception.printStackTrace();
    }
  }

  private void moveUp() {
    this.direction = UP;
    this.worldY -= this.speed;
  }

  private void moveDown() {
    this.direction = DOWN;
    this.worldY += this.speed;
  }

  private void moveLeft() {
    this.direction = LEFT;
    this.worldX -= this.speed;
  }

  private void moveRight() {
    this.direction = RIGHT;
    this.worldX += this.speed;
  }

  private void move() {
    var directions = Stream.of(
      keys.up(),
      keys.down(),
      keys.left(),
      keys.right()
    );

    if (directions.anyMatch(direction -> direction)) {
      if (keys.up()) {
        moveUp();
      }

      if (keys.down()) {
        moveDown();
      }

      if (keys.left()) {
        moveLeft();
      }

      if (keys.right()) {
        moveRight();
      }

      this.spriteCounter++;

      if (this.spriteCounter > 15) {
        if (this.spriteNumber == 1) {
          this.spriteNumber = 2;
        } else if (this.spriteNumber == 2) {
          this.spriteNumber = 1;
        }

        this.spriteCounter = 0;
      }
    }
  }

  @Override
  public void update() {
    move();
  }

  private void animateMovement(Graphics2D graphics2D) {
    BufferedImage image = null;

    switch (direction) {
      case UP -> {
        if (this.spriteNumber == 1) {
          image = imageUpOne;
        }

        if (this.spriteNumber == 2) {
          image = imageUpTwo;
        }
      }
      case DOWN -> {
        if (this.spriteNumber == 1) {
          image = imageDownOne;
        }

        if (this.spriteNumber == 2) {
          image = imageDownTwo;
        }
      }
      case LEFT -> {
        if (this.spriteNumber == 1) {
          image = imageLeftOne;
        }

        if (this.spriteNumber == 2) {
          image = imageLeftTwo;
        }
      }
      case RIGHT -> {
        if (this.spriteNumber == 1) {
          image = imageRightOne;
        }

        if (this.spriteNumber == 2) {
          image = imageRightTwo;
        }
      }
    }

    graphics2D.drawImage(image, this.screenX, this.screenY, game.tileSize, game.tileSize, null);
  }

  @Override
  public void draw(Graphics2D graphics2D) {
    animateMovement(graphics2D);
  }
}
