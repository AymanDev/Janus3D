
package com.janus3d.graphics.buffer;

import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL45.glCreateBuffers;

public class BufferObject implements IDispose, IBind {

    protected int id;
    private final BufferTarget target;
    private final BufferUsage usage;

    public BufferObject(BufferTarget target, BufferUsage usage) {
        this.id = glCreateBuffers();
        this.target = target;
        this.usage = usage;
    }

    @Override
    public void bind() {
        glBindBuffer(target.getId(), id);
    }

    @Override
    public void unbind() {
        glBindBuffer(target.getId(), 0);
    }

    @Override
    public void dispose() {
        glDeleteBuffers(id);

        id = 0;
    }

    public int getId() {
        return id;
    }

    public BufferTarget getTarget() {
        return target;
    }

    public BufferUsage getUsage() {
        return usage;
    }
}
