/**
 * @author KingoSawada
 */
package com.janus3d.graphics.mesh;

import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;
import com.janus3d.graphics.buffer.BufferObject;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;
import static org.lwjgl.opengl.GL43.glMultiDrawElementsIndirect;

public class Mesh implements IDispose, IBind {

    private String name;
    private int idxCount;
    protected final VAO vao;
    protected final List<BufferObject> bufferObjectList;
    protected final MeshElementType type;

    protected MeshRenderMode renderMode;

    public Mesh(int idxCount, MeshElementType type, MeshRenderMode renderMode) {
        this.idxCount = idxCount;
        this.type = type;
        this.renderMode = renderMode;
        this.name = "Unnamed mesh";

        bufferObjectList = new ArrayList<>();

        vao = new VAO();
        vao.bind();
    }

    public void render() {
        glDrawElements(renderMode.getId(), idxCount, type.getId(), 0);
    }

    public void renderInstanced(int count) {
        glDrawElementsInstanced(renderMode.getId(), idxCount, type.getId(), 0, count);
    }

    public void renderIndirect(int count) {
        glMultiDrawElementsIndirect(renderMode.getId(), type.getId(), 0, count, 0);
    }

    @Override
    public void bind() {
        vao.bind();
    }

    @Override
    public void unbind() {
        vao.unbind();
    }

    @Override
    public void dispose() {
        for (var buf : bufferObjectList) {
            buf.dispose();
        }

        vao.dispose();
    }
}
