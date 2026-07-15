package com.janus3d.graphics.animation;

public class Animation {

    private final int offset;
    private final double duration;
    private final int frames;
    private final String name;

    public Animation(int offset, AnimationData data) {
        this.offset = offset;
        this.name = data.name();
        this.duration = data.duration();
        this.frames = data.frames().size();
    }

    public int getOffset() {
        return offset;
    }

    public double getDuration() {
        return duration;
    }

    public int getFrames() {
        return frames;
    }

    public String getName() {
        return name;
    }
}