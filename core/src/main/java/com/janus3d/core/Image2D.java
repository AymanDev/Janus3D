package com.janus3d.core;

import java.nio.ByteBuffer;

public abstract class Image2D implements IDispose {

    protected int width;
    protected int height;
    protected ByteBuffer buffer;

    protected Image2D(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    @Override
    public abstract void dispose();
}
