package com.janus3d.graphics.framebuffer;

import static org.lwjgl.opengl.GL30.*;

public enum FramebufferAttachmentType {
        ColorAttachment0(GL_COLOR_ATTACHMENT0),
        ColorAttachment1(GL_COLOR_ATTACHMENT1),
        DepthAttachment(GL_DEPTH_ATTACHMENT),
        ;
        private final int id;

        FramebufferAttachmentType(int id) {
            this.id = id;
        }

    public int getId() {
        return id;
    }
}