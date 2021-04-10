package com.facebook.flatbuffers.flatteners;

import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BooleanFlattener implements Flattener<Boolean> {
    public static final BooleanFlattener INSTANCE = new BooleanFlattener();

    public int flattenToBuffer(Boolean bool, FlatBufferBuilder flatBufferBuilder) {
        boolean z;
        flatBufferBuilder.startObject(1);
        if (bool == null) {
            z = false;
        } else {
            z = bool.booleanValue();
        }
        flatBufferBuilder.addBoolean(0, z);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    public Boolean initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        return Boolean.valueOf(FlatBuffer.getBoolean(byteBuffer, i, 0, false));
    }
}
