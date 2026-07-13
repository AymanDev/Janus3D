package com.janus3d.graphics.shader;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;

public class Uniform {

    private final int id;
    private final String name;

    public Uniform(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isValid() {
        return id >= 0;
    }

    public void set(FloatBuffer buffer, int count) {
        if (!isValid()) {
            return;
        }

        switch (count) {
            case 1:
                glUniform1fv(id, buffer);
                break;
            case 2:
                glUniform2fv(id, buffer);
                break;
            case 3:
                glUniform3fv(id, buffer);
                break;
            case 4:
                glUniform4fv(id, buffer);
                break;
            case 16:
                glUniformMatrix4fv(id, false, buffer);
                break;

            default:
                throw new RuntimeException(String.format("Shader Uniforms float buffer isn't within range of 1>=%s<=4 or %s == 16", count, count));
        }
    }

    public void set(Matrix4f value) {
        if (!isValid()) {
            return;
        }

        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocFloat(16);
            value.get(buffer);

            glUniformMatrix4fv(id, false, buffer);
        }
    }

    public void setMatrix4fList(List<Matrix4f> value) {
        if (!isValid()) {
            return;
        }
        var length = value != null ? value.size() : 0;
        var buffer = MemoryUtil.memAllocFloat(16 * length);

        for (int i = 0; i < length; i++) {
            value.get(i).get(16 * i, buffer);
        }

        glUniformMatrix4fv(id, false, buffer);

        MemoryUtil.memFree(buffer);
    }

    public void set(Matrix4f[] value) {
        if (!isValid()) {
            return;
        }

        try (MemoryStack stack = MemoryStack.stackPush()) {
            var length = value != null ? value.length : 0;
            FloatBuffer buffer = stack.mallocFloat(16 * length);

            for (int i = 0; i < length; i++) {
                value[i].get(16 * i, buffer);
            }

            glUniformMatrix4fv(id, false, buffer);
        }
    }

    public void set(float value) {
        if (!isValid()) {
            return;
        }

        glUniform1f(id, value);
    }

    public void set1f(FloatBuffer value) {
        if (!isValid()) {
            return;
        }

        glUniform1fv(id, value);
    }

    public void set3f(FloatBuffer value) {
        if (!isValid()) {
            return;
        }

        glUniform3fv(id, value);
    }

    public void set(Vector4f value) {
        if (!isValid()) {
            return;
        }

        glUniform4f(id, value.x, value.y, value.z, value.w);
    }

    public void set(Vector3f value) {
        if (!isValid()) {
            return;
        }

        glUniform3f(id, value.x, value.y, value.z);
    }

    public void set(List<Vector3f> value) {
        if (!isValid()) {
            return;
        }

        var buffer = MemoryUtil.memAllocFloat(value.size() * 3);

        for (var vec3 : value) {
            buffer.put(vec3.x);
            buffer.put(vec3.y);
            buffer.put(vec3.z);
        }

        glUniform3fv(id, buffer);

        MemoryUtil.memFree(buffer);
    }

    public void set(Vector2f value) {
        if (!isValid()) {
            return;
        }

        glUniform2f(id, value.x, value.y);
    }

    public void set(int value) {
        if (!isValid()) {
            return;
        }

        glUniform1i(id, value);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}