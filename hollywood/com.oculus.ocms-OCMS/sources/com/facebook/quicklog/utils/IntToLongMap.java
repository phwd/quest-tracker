package com.facebook.quicklog.utils;

public interface IntToLongMap {
    long get(int i, long j);

    int indexOfKey(int i);

    int keyAt(int i);

    void put(int i, long j);

    int size();

    long valueAt(int i);
}
