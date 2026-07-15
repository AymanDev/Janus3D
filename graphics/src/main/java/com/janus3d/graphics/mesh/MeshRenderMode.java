
package com.janus3d.graphics.mesh;

import static org.lwjgl.opengl.GL11.*;

public enum MeshRenderMode {
    Triangles(GL_TRIANGLES),
    Quads(GL_QUADS),
    Lines(GL_LINES);

    final int id;

    MeshRenderMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
