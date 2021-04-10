package com.facebook.flatbuffers.flatteners;

import android.graphics.RectF;
import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RectFFlattener implements Flattener<RectF> {
    public static final RectFFlattener INSTANCE = new RectFFlattener();

    public int flattenToBuffer(RectF rectF, FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(4);
        flatBufferBuilder.addFloat(0, rectF.left, 0.0f);
        flatBufferBuilder.addFloat(1, rectF.top, 0.0f);
        flatBufferBuilder.addFloat(2, rectF.right, 0.0f);
        flatBufferBuilder.addFloat(3, rectF.bottom, 0.0f);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    public RectF initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        RectF rectF = new RectF();
        rectF.left = FlatBuffer.getFloat(byteBuffer, i, 0, 0.0f);
        rectF.top = FlatBuffer.getFloat(byteBuffer, i, 1, 0.0f);
        rectF.right = FlatBuffer.getFloat(byteBuffer, i, 2, 0.0f);
        rectF.bottom = FlatBuffer.getFloat(byteBuffer, i, 3, 0.0f);
        return rectF;
    }
}
