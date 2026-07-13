package com.janus3d.graphics.mesh;

import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_SHORT;

public enum MeshElementType {
    UInt(GL_UNSIGNED_INT),
    UShort(GL_UNSIGNED_SHORT);

    final int id;


    MeshElementType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}