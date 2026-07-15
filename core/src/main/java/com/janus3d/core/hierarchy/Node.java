
package com.janus3d.core.hierarchy;

import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> childrens;
    private final List<Integer> meshIndexes;
    private final String name;
    private final Node parent;
    private final Matrix4f transformation;

    public Node(String name, Node parent, Matrix4f transformation) {
        this.name = name;
        this.parent = parent;
        this.transformation = transformation;
        this.childrens = new ArrayList<>();
        this.meshIndexes = new ArrayList<>();
    }

    public void addMesh(int idx) {
        meshIndexes.add(idx);
    }

    public void addChild(Node node) {
        childrens.add(node);
    }

    public List<Node> getChildrens() {
        return childrens;
    }

    public List<Integer> getMeshIndexes() {
        return meshIndexes;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public Matrix4f getTransformation() {
        return transformation;
    }
}
