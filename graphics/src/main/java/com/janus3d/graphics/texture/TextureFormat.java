/**
 * @author KingoSawada
 */
package com.janus3d.graphics.texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public enum TextureFormat {
    Rgba(GL_RGBA),
    Rgba16F(GL_RGBA16F),
    Rgba32F(GL_RGBA32F),
    Rgb(GL_RGB),
    Rgb16F(GL_RGB16F),
    Red(GL_RED),
    Depth(GL_DEPTH_COMPONENT),
    Depth32F(GL_DEPTH_COMPONENT32F);


    private final int id;

    TextureFormat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
