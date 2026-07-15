/**
 * @author KingoSawada
 */
package com.janus3d.graphics.framebuffer;

import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;
import com.janus3d.graphics.texture.Texture2D;
import org.lwjgl.system.MemoryStack;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.*;

public class Framebuffer implements IDispose, IBind {

    private final int id;

    private final List<FramebufferAttachment> attachments;
    private final int width;
    private final int height;

    public Framebuffer(int width, int height) {
        this.width = width;
        this.height = height;

        attachments = new ArrayList<>();
        id = glGenFramebuffers();
    }

    public FramebufferAttachment createAttachment(FramebufferAttachmentType type) {
        var texture = new Texture2D();
        texture.create();

        return new FramebufferAttachment(type, texture);
    }

    public void addAttachment(FramebufferAttachment attachment) {
        attachment.texture().uploadTexture(width, height, null);
        glFramebufferTexture2D(GL_FRAMEBUFFER, attachment.type().getId(), GL_TEXTURE_2D, attachment.texture().getId(), 0);
        attachments.add(attachment);
    }

    public void build() {
        try (var stack = MemoryStack.stackPush()) {
            var buffer = stack.mallocInt(attachments.size());

            int i = 0;

            for (var fboAttachment : attachments) {
                if (fboAttachment.type() == FramebufferAttachmentType.DepthAttachment) {
                    continue;
                }


                buffer.put(i, fboAttachment.type().getId());
                i++;
            }

            glDrawBuffers(buffer);
        }


        var status = glCheckFramebufferStatus(GL_FRAMEBUFFER);

        if (status != GL_FRAMEBUFFER_COMPLETE) {
            throw new IllegalStateException(String.format("Could not create FrameBuffer. Status: %s; Size: %sX%s", status, width, height));
        }
    }


    @Override
    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id);

    }

    @Override
    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    @Override
    public void dispose() {
        for (var att : attachments) {
            att.texture().dispose();
        }

        glDeleteFramebuffers(id);
    }
}
