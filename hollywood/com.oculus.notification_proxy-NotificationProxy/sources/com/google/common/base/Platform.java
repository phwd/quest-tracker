package com.google.common.base;

final class Platform {
    static long systemNanoTime() {
        return System.nanoTime();
    }
}
