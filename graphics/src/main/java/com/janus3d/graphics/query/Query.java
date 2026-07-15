/**
 * @author KingoSawada
 */
package com.janus3d.graphics.query;

import com.janus3d.core.IBind;
import com.janus3d.core.IDispose;

import static org.lwjgl.opengl.GL15.*;

public class Query implements IBind, IDispose {
    private final int id;
    private final QueryType type;

    public Query(QueryType type) {
        this.type = type;
        this.id = glGenQueries();
    }

    public boolean isReady() {
        return glGetQueryObjecti(id, GL_QUERY_RESULT_AVAILABLE) == GL_TRUE;
    }

    public int getResult() {
        return glGetQueryObjecti(id, GL_QUERY_RESULT);
    }

    @Override
    public void bind() {
        glBeginQuery(type.getId(), id);
    }

    @Override
    public void unbind() {
        glEndQuery(type.getId());
    }

    @Override
    public void dispose() {
        glDeleteQueries(id);
    }
}
