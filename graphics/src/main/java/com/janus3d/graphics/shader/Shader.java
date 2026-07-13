package com.janus3d.graphics.shader;

import com.janus3d.core.IDispose;
import com.janus3d.graphics.GLState;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader<D extends IShaderDefine> implements IDispose {

    private int programId;
    private String log = "";

    private int vertex;
    private int fragment;

    private final HashMap<String, Uniform> uniforms;
    protected final List<D> defines;


    public Shader(List<D> defines, String vtxCode, String fragCode) {
        uniforms = new HashMap<>();
        this.defines = defines;

        this.vertex = compileShader(ShaderType.Vertex, vtxCode);
        this.fragment = compileShader(ShaderType.Fragment, fragCode);

        programId = create();

        try {
            link();
        } catch (IllegalStateException e) {
            dispose();
        }
    }

    public Shader(String vtxCode, String fragCode) {
        this(List.of(), vtxCode, fragCode);
    }

    public abstract void fetchUniforms();

    private static int create() {
        return glCreateProgram();
    }

    private void link() throws IllegalStateException {
        if (!isValid()) {
            throw new IllegalStateException("Shader program is not created!");
        }

        uniforms.clear();

        attach();
        glLinkProgram(programId);

        int status = glGetProgrami(programId, GL_LINK_STATUS);
        int length = glGetProgrami(programId, GL_INFO_LOG_LENGTH);
        String err = glGetProgramInfoLog(programId, length);

        if (!err.isEmpty()) {
            log = err + "\n" + log;
        }

        log = log.trim();

        if (status == GL_FALSE) {
            throw new IllegalStateException(log.isEmpty() ? "Could not link shader program" : log);
        }

        bind();

        fetchUniforms();
    }

    private int compileShader(ShaderType type, String source) throws IllegalStateException {
        int shader = glCreateShader(type.getId());

        if (shader == 0) {
            throw new IllegalStateException("Shader was not created!");
        }

        var result = new StringBuilder();

        for (var line : source.split("\n", -1)) {
            result.append(line).append("\n");

            if (line.trim().startsWith("#version")) {
                result.append("\n");

                for (var d : defines) {
                    var fullDefineLine = String.format("#define %s 1", d.getDefine());

                    result.append(fullDefineLine).append("\n");
                }
            }
        }

        var shaderSource = result.toString();

        glShaderSource(shader, shaderSource);
        glCompileShader(shader);

        int status = glGetShaderi(shader, GL_COMPILE_STATUS);
        int length = glGetShaderi(shader, GL_INFO_LOG_LENGTH);
        String name = type.getName();
        String err = glGetShaderInfoLog(shader, length);

        log = "";

        if (!err.isEmpty()) {
            log += "Shader: " + getClass().getSimpleName() + status + "\n" + "Shader Defines:\n" + defines + "\ncompile log:\n" + err + "\n";
        }

        if (status == GL_FALSE) {
            var msg = !log.isEmpty() ? log : "Could not compile " + name + "\nDefines:\n" + defines;
            throw new IllegalStateException(String.format("Shader of type: %s did not compile: ", type) + msg);
        }


        return shader;
    }

    private void attach() {
        if (vertex != 0) {
            glAttachShader(programId, vertex);
        }

        if (fragment != 0) {
            glAttachShader(programId, fragment);
        }
    }

    public void bind() {
        if (!isValid()) {
            return;
        }

        GLState.useProgram(programId);
    }


    public Uniform getUniform(String name) {
        return uniforms.get(name);
    }

    protected void createUniform(String name) throws NoSuchElementException {
        var location = glGetUniformLocation(programId, name);
        var uniform = new Uniform(location, name);

        if (location < 0) {
            throw new NoSuchElementException(
                    String.format(
                            "Could not find uniform [%s] in shader [%s] with id [%d]",
                            name, getClass().getName(), programId));
        }

        uniforms.put(name, uniform);
    }

    public boolean isValid() {
        return programId != 0;
    }

    @Override
    public void dispose() {
        if (vertex != 0) {
            glDetachShader(programId, vertex);
            glDeleteShader(vertex);
            vertex = 0;
        }

        if (fragment != 0) {
            glDetachShader(programId, fragment);
            glDeleteShader(fragment);
            fragment = 0;
        }

        if (programId != 0) {
            glDeleteProgram(programId);
            programId = 0;
        }
    }
}