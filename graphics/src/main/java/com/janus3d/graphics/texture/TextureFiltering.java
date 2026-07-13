package com.janus3d.graphics.texture;

import static org.lwjgl.opengl.GL11.*;

public enum TextureFiltering {
  Nearest(GL_NEAREST),
  Linear(GL_LINEAR),
  LinearMipMapLinear(GL_LINEAR_MIPMAP_LINEAR),
  ;

  private final int id;

  TextureFiltering(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
