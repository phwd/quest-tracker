package com.facebook.quicklog.utils;

public interface ProcessProxy {
    long getElapsedCpuTime();

    Thread getMainThread();

    int getThreadPriority(int i);

    int myTid();
}
