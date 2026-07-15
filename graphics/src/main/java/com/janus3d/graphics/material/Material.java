package com.janus3d.graphics.material;

import com.janus3d.graphics.shader.Shader;

public abstract class Material<S extends Shader<?>> {

    protected S shader;
    private String name = "Material";

    public Material(S shader) {
        this.shader = shader;
    }

    public S getShader() {
        return shader;
    }

    public String getName() {
        return name;
    }

    public Material<S> setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isValid() {
        return shader.isValid();
    }
}
