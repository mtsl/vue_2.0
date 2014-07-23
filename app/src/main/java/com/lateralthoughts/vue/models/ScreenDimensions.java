package com.lateralthoughts.vue.models;

import com.googlecode.objectify.annotation.Index;

import lombok.Getter;
import lombok.Setter;

public class ScreenDimensions {
    @Getter @Setter public int mScreenWidth;
    @Getter @Setter public int mScreenHeight;
}
