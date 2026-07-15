
package com.janus3d.graphics;

import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class GLState {
    private static int lastVertexArray = -1;

    public static void bindVertexArray(int id) {
        if (id == lastVertexArray) {
            return;
        }

        glBindVertexArray(id);
        lastVertexArray = id;
    }

    private static int lastProgram = -1;

    public static void useProgram(int id) {
        if (id == lastProgram) {
            return;
        }

        glUseProgram(id);
        lastProgram = id;
    }
}
