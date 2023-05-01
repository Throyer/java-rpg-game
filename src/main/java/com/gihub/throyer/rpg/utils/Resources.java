package com.gihub.throyer.rpg.utils;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Resources {
  private Resources() { }
  
  public static final String SPRITES_PATH = "/sprites";
  public static final String TILES_PATH = "/tiles";
  public static final String MAPS_PATH = "/maps";
  
  public static BufferedImage sprite(String path) throws IOException, NullPointerException {
    return ImageIO.read(requireNonNull(Resources.class.getResourceAsStream(format("%s/%s", SPRITES_PATH, path))));
  }

  public static BufferedImage tile(String path) throws IOException, NullPointerException {
    return ImageIO.read(requireNonNull(Resources.class.getResourceAsStream(format("%s/%s", TILES_PATH, path))));
  }

  public static InputStream map(String path) throws NullPointerException {
    return requireNonNull(Resources.class.getResourceAsStream(format("%s/%s", MAPS_PATH, path)));
  }
}
