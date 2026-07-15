package com.janus3d.graphics.framebuffer;

import com.janus3d.graphics.texture.Texture2D;

public record FramebufferAttachment(FramebufferAttachmentType type, Texture2D texture) {
    }