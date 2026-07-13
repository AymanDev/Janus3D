package com.janus3d.core;

import org.joml.Vector3f;

public class AABB {
    private Vector3f min;
    private Vector3f max;

    public AABB() {
        min = new Vector3f();
        max = new Vector3f();
    }

    public AABB(Vector3f min, Vector3f max) {
        this.min = min;
        this.max = max;
    }

    public AABB(AABB source) {
        min = new Vector3f(source.getMin());
        max = new Vector3f(source.getMax());
    }

    public void set(AABB source) {
        min.set(source.getMin());
        max.set(source.getMax());
    }

    public void moveToGlobal(Vector3f center) {
        min.add(center);
        max.add(center);
    }

    public Vector3f getMin() {
        return min;
    }

    public AABB setMin(Vector3f min) {
        this.min = min;
        return this;
    }

    public Vector3f getMax() {
        return max;
    }

    public AABB setMax(Vector3f max) {
        this.max = max;
        return this;
    }

    @Override
    public String toString() {
        return "AABB{" + "min=" + min.x + ";" + min.y + ";" + min.z + ", max=" + max.x + ";" + max.y + ";" + max.z + '}';
    }
}
