package com.intersysconsulting;

import lombok.Value;
import lombok.experimental.Wither;

@Value
public class Triangle implements Shape {
    @Wither Point p1;
    @Wither Point p2;
    @Wither Point p3;

    @Override
    public void draw() {
        // Draw this triangle
    }
}