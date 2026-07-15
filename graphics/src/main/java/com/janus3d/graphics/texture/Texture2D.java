
package com.janus3d.graphics.texture;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture2D extends Texture {

    private int width;
    private int height;

    public Texture2D() {
        super(TextureTarget.Tex2D);
    }

    public void uploadTexture(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;

        glTexImage2D(target.getId(), 0, internalFormat.getId(), width, height, 0, format.getId(), type.getId(), buffer);
        glTexParameteri(target.getId(), GL_TEXTURE_MIN_FILTER, minFilter.getId());
        glTexParameteri(target.getId(), GL_TEXTURE_MAG_FILTER, magFilter.getId());
        glTexParameteri(target.getId(), GL_TEXTURE_WRAP_S, wrap.getId());
        glTexParameteri(target.getId(), GL_TEXTURE_WRAP_T, wrap.getId());
    }

    public void generateMipmap(){
        glGenerateMipmap(target.getId());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
