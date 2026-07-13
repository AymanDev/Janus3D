package com.janus3d.graphics.mesh;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL33.GL_INT_2_10_10_10_REV;

public enum VAOType {
        Float(GL_FLOAT),
        Int(GL_INT),
        Int2_10_10_10_Rev(GL_INT_2_10_10_10_REV),
        UInt(GL_UNSIGNED_INT),
        UByte(GL_UNSIGNED_BYTE),
        UShort(GL_UNSIGNED_SHORT),
        ;

        final int id;

        VAOType(int id) {
            this.id = id;
        }

        public int getId() {
                return id;
        }
}