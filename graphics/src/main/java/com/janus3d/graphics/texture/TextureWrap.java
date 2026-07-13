/**
 * @author KingoSawada
 */
package com.janus3d.graphics.texture;

import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

public enum TextureWrap {
    ClampToEdge(GL_CLAMP_TO_EDGE),
    ClampToBorder(GL_CLAMP_TO_BORDER),
    Repeat(GL_REPEAT);

    private final int id;

    TextureWrap(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
