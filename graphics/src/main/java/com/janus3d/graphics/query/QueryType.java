package com.janus3d.graphics.query;

import static org.lwjgl.opengl.GL15.GL_SAMPLES_PASSED;
import static org.lwjgl.opengl.GL33.GL_ANY_SAMPLES_PASSED;
import static org.lwjgl.opengl.GL43.GL_ANY_SAMPLES_PASSED_CONSERVATIVE;

public enum QueryType {
        SamplesPassed(GL_SAMPLES_PASSED),
        AnySamplesPassed(GL_ANY_SAMPLES_PASSED),
        AnySamplesPassedConservative(GL_ANY_SAMPLES_PASSED_CONSERVATIVE),
        ;

        private final int id;

        QueryType(int id) {
            this.id = id;
        }

    public int getId() {
        return id;
    }
}