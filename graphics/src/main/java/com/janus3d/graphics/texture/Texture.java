/**
 * @author KingoSawada
 */
package com.janus3d.graphics.texture;


import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;
import com.janus3d.graphics.GLState;

import static org.lwjgl.opengl.GL11.*;

public class Texture implements IDispose, IBind {

    protected final TextureTarget target;

    protected TextureFormat internalFormat = TextureFormat.Rgba;
    protected TextureFormat format = TextureFormat.Rgba;
    protected TextureType type = TextureType.UByte;
    protected TextureWrap wrap = TextureWrap.Repeat;

    protected int id;

    protected TextureFiltering minFilter = TextureFiltering.LinearMipMapLinear;
    protected TextureFiltering magFilter = TextureFiltering.Linear;

    public Texture(TextureTarget target) {
        this.target = target;
    }

    public void create() {
        id = glGenTextures();
    }

    @Override
    public void bind() {
        glBindTexture(target.getId(), id);
    }

    @Override
    public void unbind() {
        glBindTexture(target.getId(), 0);
    }

    @Override
    public void dispose() {
        glDeleteTextures(id);
    }

    public int getId() {
        return id;
    }

    public TextureTarget getTarget() {
        return target;
    }

    public TextureFormat getInternalFormat() {
        return internalFormat;
    }

    public Texture setInternalFormat(TextureFormat internalFormat) {
        this.internalFormat = internalFormat;
        return this;
    }

    public TextureFormat getFormat() {
        return format;
    }

    public Texture setFormat(TextureFormat format) {
        this.format = format;
        return this;
    }

    public TextureType getType() {
        return type;
    }

    public Texture setType(TextureType type) {
        this.type = type;
        return this;
    }

    public TextureWrap getWrap() {
        return wrap;
    }

    public Texture setWrap(TextureWrap wrap) {
        this.wrap = wrap;
        return this;
    }

    public TextureFiltering getMinFilter() {
        return minFilter;
    }

    public Texture setMinFilter(TextureFiltering minFilter) {
        this.minFilter = minFilter;
        return this;
    }

    public TextureFiltering getMagFilter() {
        return magFilter;
    }

    public Texture setMagFilter(TextureFiltering magFilter) {
        this.magFilter = magFilter;
        return this;
    }
}
