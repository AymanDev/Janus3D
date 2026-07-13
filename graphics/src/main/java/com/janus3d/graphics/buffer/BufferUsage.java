package com.janus3d.graphics.buffer;

import static org.lwjgl.opengl.GL15.*;

public enum BufferUsage {
    StaticDraw(GL_STATIC_DRAW),
    StreamDraw(GL_STREAM_DRAW),
    DynamicDraw(GL_DYNAMIC_DRAW);

    private final int id;

    BufferUsage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
