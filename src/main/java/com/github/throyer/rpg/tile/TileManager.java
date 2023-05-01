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
  private Game game;
  private Map<Integer, Tile> tiles;
  private Integer[][] map;

  public TileManager(Game game) {
    this.game = game;
    this.map = new Integer[game.tileSize][game.tileSize];
    defineTiles();
    setMap("map_01.txt");
  }
  
  private void defineTiles() {
    this.tiles = Map.of(
      0, new Tile("grass.png"),
      1, new Tile("wall.png"),
      2, new Tile("water.png")
    );
  }
  
  
  public void setMap(String path) {
    log.info("set map to {}", path);
    try {
      var reader = new BufferedReader(new InputStreamReader(map(path)));

      for (int row = 0; row < game.maxScreenRow; row++) {
        var line = reader.readLine();
        var numbers = line.split(" ");        
        for (int column = 0; column < game.maxScreenColumn; column++) {
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
    var x = 0;
    var y = 0;
    
    for (int column = 0; column < game.maxScreenColumn; column++) {
      for (int row = 0; row < game.maxScreenRow; row++) {
        var tile = tiles.get(map[column][row]);        
        graphics2D.drawImage(tile.getImage(), x, y, game.tileSize, game.tileSize, null);
        y+= game.tileSize;
      }
      x+= game.tileSize;
      y = 0;
    }
  }
}
