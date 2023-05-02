package com.github.throyer.rpg.tile;

import com.github.throyer.rpg.main.Game;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static com.github.throyer.rpg.utils.Resources.map;
import static java.lang.Integer.parseInt;

@Log4j2
public class TileManager {
  private final Game game;
  private Map<Integer, Tile> tiles;
  private final Integer[][] map;

  public TileManager(Game game) {
    this.game = game;
    this.map = new Integer[game.maxWorldColumn][game.maxWorldRow];
    defineTiles();
    setMap("world01.txt");
  }
  
  private void defineTiles() {
    this.tiles = Map.of(
      0, new Tile("grass.png"),
      1, new Tile("wall.png"),
      2, new Tile("water.png"),
      3, new Tile("earth.png"),
      4, new Tile("tree.png"),
      5, new Tile("sand.png")
    );
  }
  
  
  public void setMap(String path) {
    log.info("set map to {}", path);
    try {
      var reader = new BufferedReader(new InputStreamReader(map(path)));

      for (int row = 0; row < game.maxWorldRow; row++) {
        var line = reader.readLine();
        var numbers = line.split(" ");        
        for (int column = 0; column < game.maxWorldColumn; column++) {
          var number = parseInt(numbers[column]);
          map[column][row] = number;
        }
      }
      
      reader.close();
      log.info("all tiles read successfully from {}", path);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  
  public void draw(Graphics2D graphics2D) {
    for (int column = 0; column < game.maxWorldColumn; column++) {
      for (int row = 0; row < game.maxWorldRow; row++) {
        var tile = tiles.get(map[column][row]);
        
        var worldX = column * game.tileSize;
        var worldY = row * game.tileSize;

        var player = game.player;
        
        var screenX = worldX - player.getWorldX() + player.getScreenX();
        var screenY = worldY - player.getWorldY() + player.getScreenY();
        
        if (
          worldX + game.tileSize > player.getWorldX() - player.getScreenX() &&
          worldX - game.tileSize < player.getWorldX() + player.getScreenX() &&
          worldY + game.tileSize > player.getWorldY() - player.getScreenY() &&
          worldY - game.tileSize < player.getWorldY() + player.getScreenY()
        ) {
          graphics2D.drawImage(tile.getImage(), screenX, screenY, game.tileSize, game.tileSize, null);
        }       
      }      
    }
  }
}
