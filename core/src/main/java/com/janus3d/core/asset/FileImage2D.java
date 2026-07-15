package com.janus3d.core.asset;

import com.janus3d.core.Image2D;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class FileImage2D extends Image2D {

    private String path;

    public FileImage2D(String path) {
        super(-1, -1, null);
        this.path = path;
    }

    public void load() {
        try (var stack = MemoryStack.stackPush()) {
            var w = stack.mallocInt(1);
            var h = stack.mallocInt(1);
            var channels = stack.mallocInt(1);
            buffer = stbi_load(path, w, h, channels, 4);

            if (buffer == null) {
                return;
            }

            width = w.get();
            height = h.get();
        }
    }

    protected FileImage2D(int width, int height, ByteBuffer buffer) {
        super(width, height, buffer);
    }

    public boolean isLoaded() {
        return buffer != null;
    }

    @Override
    public void dispose() {
        stbi_image_free(buffer);
        width = -1;
        height = -1;
        buffer = null;
    }
}
