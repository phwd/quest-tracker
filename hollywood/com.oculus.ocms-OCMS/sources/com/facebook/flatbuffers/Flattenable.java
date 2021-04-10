package com.facebook.flatbuffers;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public interface Flattenable {

    public interface VirtualFlattenable {
        int getFlatBufferType();
    }

    public interface VirtualFlattenableResolver {
        public static final short INVALID_FLATTENABLE_TYPE = -1;

        int getVirtualFlattenableType(Flattenable flattenable);

        @Nullable
        Flattenable resolveVirtualFlattenableType(int i);
    }

    int flattenToBuffer(FlatBufferBuilder flatBufferBuilder);

    void initFromFlatBuffer(ByteBuffer byteBuffer, int i);

    void initFromMutableFlatBuffer(MutableFlatBuffer mutableFlatBuffer, int i);
}
