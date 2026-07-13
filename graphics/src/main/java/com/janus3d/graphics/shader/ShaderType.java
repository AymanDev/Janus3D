package com.janus3d.graphics.shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public enum ShaderType {
  Vertex(GL_VERTEX_SHADER, "VERTEX_SHADER"),
  Fragment(GL_FRAGMENT_SHADER, "FRAGMENT_SHADER"),
  BOTH(-1, "SHADER");

  private final int id;
  private final String name;

  ShaderType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
