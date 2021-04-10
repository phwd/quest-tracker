package com.facebook.flatbuffers;

import java.nio.ByteBuffer;

public interface Flattener<T> {
    int flattenToBuffer(T t, FlatBufferBuilder flatBufferBuilder);

    T initFromFlatBuffer(ByteBuffer byteBuffer, int i) throws InstantiationException, IllegalAccessException;
}
