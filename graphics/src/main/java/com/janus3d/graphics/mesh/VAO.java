/**
 * @author KingoSawada
 */
package com.janus3d.graphics.mesh;

import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;
import com.janus3d.graphics.GLState;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VAO implements IDispose, IBind {
    private int id;
    private final List<Integer> attributes;

    public VAO() {
        id = glGenVertexArrays();
        attributes = new ArrayList<>();
    }

    @Override
    public void bind() {
        GLState.bindVertexArray(id);
    }

    @Override
    public void unbind() {
        GLState.bindVertexArray(0);
    }

    public void setAttribPointer(int index, int size) {
        setAttribPointer(index, size, VAOType.Float);
    }

    public void setAttribPointer(int index, int size, VAOType type) {
        setAttribPointer(index, size, type, false, 0, 0);
    }

    public void setAttribPointer(int index, int size, VAOType type, int stride, int pointer) {
        setAttribPointer(index, size, type, false, stride, pointer);
    }

    public void setAttribPointer(
            int index, int size, VAOType type, boolean normalized, int stride, int pointer) {
        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, size, type.getId(), normalized, stride, pointer);

        attributes.add(index);
    }

    public void setAttribIPointer(int index, int size, VAOType type, int stride, int pointer) {
        glEnableVertexAttribArray(index);
        glVertexAttribIPointer(index, size, type.getId(), stride, pointer);

        attributes.add(index);
    }

    public void bindAttributes() {
        attributes.forEach(GL20::glEnableVertexAttribArray);
    }

    public void unbindAttributes() {
        attributes.forEach(GL20::glDisableVertexAttribArray);
    }

    @Override
    public void dispose() {
        glDeleteVertexArrays(id);
        id = 0;
    }
}
