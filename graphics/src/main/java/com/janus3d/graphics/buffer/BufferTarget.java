
package com.janus3d.graphics.buffer;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL40.GL_DRAW_INDIRECT_BUFFER;
import static org.lwjgl.opengl.GL43.GL_SHADER_STORAGE_BUFFER;

public enum BufferTarget {
    ArrayBuffer(GL_ARRAY_BUFFER),
    ArrayElementBuffer(GL_ELEMENT_ARRAY_BUFFER),
    DrawIndirect(GL_DRAW_INDIRECT_BUFFER),
    ShaderStorage(GL_SHADER_STORAGE_BUFFER);

    private final int id;

    BufferTarget(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
