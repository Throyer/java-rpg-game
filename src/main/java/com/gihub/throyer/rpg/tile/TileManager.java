package com.gihub.throyer.rpg.tile;

import static com.gihub.throyer.rpg.utils.Resources.map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.gihub.throyer.rpg.main.Game;

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
    try {
      var reader = new BufferedReader(new InputStreamReader(map(path)));

      var column = 0;
      var row = 0;
      
      while (column < game.maxScreenColumn && row < game.maxScreenRow) {
        var line = reader.readLine();
        while (column < game.maxScreenColumn) {
          var numbers = line.split(" ");
          var number = Integer.parseInt(numbers[column]);
          
          map[column][row] = number;
          column++;
        }
        
        if (column == game.maxScreenColumn) {
          column = 0;
          row++;
        }
      }
      reader.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  
  public void draw(Graphics2D graphics2D) {
    var column = 0;
    var row = 0;
    
    var x = 0;
    var y = 0;
    
    while (column < game.maxScreenColumn && row < game.maxScreenRow) {
      
      var tile = tiles.get(map[column][row]);
      
      graphics2D.drawImage(tile.getImage(), x, y, game.tileSize, game.tileSize, null);
      
      column++;
      x+= game.tileSize;
      
      if (column == game.maxScreenColumn) {
        column = 0;
        x = 0;
        row++;
        y+= game.tileSize;
      }
    }
  }
}
