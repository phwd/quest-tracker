package com.facebook.flatbuffers.flatteners;

import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class ClassFlattener implements Flattener<Class> {
    public static final ClassFlattener INSTANCE = new ClassFlattener();

    public int flattenToBuffer(Class cls, FlatBufferBuilder flatBufferBuilder) {
        int createStringReference = flatBufferBuilder.createStringReference(cls.getName());
        flatBufferBuilder.startObject(1);
        flatBufferBuilder.addReference(0, createStringReference);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    @Nullable
    public Class initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        try {
            return Class.forName(FlatBuffer.resolveStringReference(byteBuffer, i, 0));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
