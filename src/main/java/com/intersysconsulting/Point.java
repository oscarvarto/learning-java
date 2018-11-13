package com.intersysconsulting;

import lombok.Value;
import lombok.experimental.Wither;

@Value
public class Point {
    @Wither float X;
    @Wither float Y;
}