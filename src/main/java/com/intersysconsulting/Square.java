package com.intersysconsulting;

import lombok.Value;
import lombok.experimental.Wither;

@Value
public class Square implements Shape {
    @Wither float p1;
    @Wither float p2;
    @Wither float p3;
    @Wither float p4;

    @Override
    public void draw() {
        // draw this square
    }
}
