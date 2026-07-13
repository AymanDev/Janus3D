/**
 * @author KingoSawada
 */
package com.janus3d.graphics.texture;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL12.GL_TEXTURE_3D;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL30.GL_TEXTURE_2D_ARRAY;

public enum TextureTarget {
    Tex2D(GL_TEXTURE_2D),
    Tex2DArray(GL_TEXTURE_2D_ARRAY),
    Tex3D(GL_TEXTURE_3D),
    TexCubeMap(GL_TEXTURE_CUBE_MAP),
    ;

    private final int id;

    TextureTarget(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
