
package com.janus3d.core;

import org.joml.*;
import org.joml.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Temporarily here, later i think i move it to the engine package
 */
public class Transform {

    private Transform parent;
    private final List<Transform> childrens;

    private final Vector3f localPosition;
    private final Vector3f globalPosition;
    private final Quaternionf localRotation;
    private final Vector3f localScale;

    private final Matrix4f localMatrix;
    private final Matrix4f globalMatrix;

    private boolean isDirty = true;

    public Transform() {
        this(new Vector3f(0, 0, 0), new Quaternionf(), new Vector3f(1f));
    }

    public Transform(Vector3f localPosition, Quaternionf localRotation, Vector3f localScale) {
        this.childrens = new ArrayList<>();

        this.localPosition = localPosition;
        this.globalPosition = new Vector3f();
        this.localRotation = localRotation;
        this.localScale = localScale;

        this.localMatrix = new Matrix4f().identity();
        this.globalMatrix = new Matrix4f().identity();
    }

    public void setLocalPosition(Vector3f value) {
        setLocalPosition(value.x, value.y, value.z);
    }

    public void setLocalPosition(float x, float y, float z) {
        forceDirty();

        localPosition.set(x, y, z);
    }

    public void setLocalScale(Vector3f value) {
        setLocalScale(value.x, value.y, value.z);
    }

    public void setLocalScale(float x, float y, float z) {
        forceDirty();

        localScale.set(x, y, z);
    }

    public Vector3f getEulerRotationRad() {
        return localRotation.getEulerAnglesYXZ(new Vector3f());
    }

    public Vector3f getEulerRotationDeg() {
        var euler = getEulerRotationRad();

        euler.x = org.joml.Math.toDegrees(euler.x);
        euler.y = org.joml.Math.toDegrees(euler.y);
        euler.z = Math.toDegrees(euler.z);

        return euler;
    }

    public void setRotationFromEulerRad(float x, float y, float z) {
        forceDirty();

        localRotation.identity().rotateXYZ(x, y, z);
    }

    public void setRotationFromEulerDeg(Vector3f val) {
        setRotationFromEulerDeg(val.x, val.y, val.z);
    }

    public void setRotationFromEulerDeg(float x, float y, float z) {
        setRotationFromEulerRad(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));
    }

    public void rotateEulerDeg(float x, float y, float z) {
        rotateEulerRad(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));
    }

    public void rotateEulerRad(float x, float y, float z) {
        forceDirty();

        localRotation.rotateXYZ(x, y, z);
    }

    public Transform from(Matrix4f matrix) {
        forceDirty();

        matrix.getTranslation(localPosition);
        matrix.getNormalizedRotation(localRotation);
        matrix.getScale(localScale);

        return this;
    }

    public void setParent(Transform transform) {
        forceDirty();

        if (parent != null) {
            parent.childrens.remove(this);
        }

        parent = transform;

        if (transform != null) {
            transform.childrens.add(this);
        }

    }

    // TODO: Replace with automatic checks in getMatrix instead of this
    public void forceDirty() {
        isDirty = true;

        for (var child : childrens) {
            child.forceDirty();
        }
    }


    private boolean shouldUpdate() {
        if (isDirty) {
            return true;
        }

        if (parent != null) {
            return parent.shouldUpdate();
        }

        return false;
    }

    private void updateChildrens() {
        if (!childrens.isEmpty()) {
            for (var children : childrens) {
                children.updateMatrix();
            }
        }
    }

    private void updateMatrix() {
        isDirty = false;

        localMatrix.translation(localPosition).rotate(localRotation).scale(localScale);

        if (parent == null) {
            globalPosition.set(localPosition);
            updateChildrens();
            return;
        }

        var parentMatrix = parent.getMatrix();
        globalMatrix.identity().set(parentMatrix).mul(localMatrix);

        updateChildrens();

        new Vector4f(localPosition, 1).mul(parentMatrix).xyz(globalPosition);
    }

    public Matrix4f getMatrix() {
        if (shouldUpdate()) {
            updateMatrix();
        }

        if (parent == null) {
            return localMatrix;
        }

        return globalMatrix;
    }

    public Vector3f getGlobalPosition() {
        if (shouldUpdate()) {
            updateMatrix();
        }

        return globalPosition;
    }

    public Vector3f forward() {
        if (shouldUpdate()) {
            updateMatrix();
        }

        // TODO: cache vector to reduce GC pressure
        return localMatrix.positiveZ(new Vector3f());
    }

    public Vector3f left() {
        if (shouldUpdate()) {
            updateMatrix();
        }

        // TODO: cache vector to reduce GC pressure
        return localMatrix.positiveX(new Vector3f());
    }

    public Vector3f up() {
        if (shouldUpdate()) {
            updateMatrix();
        }

        // TODO: cache vector to reduce GC pressure
        return localMatrix.positiveY(new Vector3f());
    }

    public Transform getParent() {
        return parent;
    }

    public List<Transform> getChildrens() {
        return childrens;
    }

    public Vector3f getLocalPosition() {
        return localPosition;
    }

    public Quaternionf getLocalRotation() {
        return localRotation;
    }

    public Vector3f getLocalScale() {
        return localScale;
    }
}
