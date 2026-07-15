package com.janus3d.graphics.animation;

import java.util.List;

public record AnimationData(String name, double duration, List<AnimatedFrame> frames) {
}
