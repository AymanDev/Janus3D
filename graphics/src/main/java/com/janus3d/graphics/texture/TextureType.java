
package com.janus3d.graphics.texture;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;

public enum TextureType {
    UByte(GL_UNSIGNED_BYTE),
    Float(GL_FLOAT);

    private final int id;

    TextureType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
