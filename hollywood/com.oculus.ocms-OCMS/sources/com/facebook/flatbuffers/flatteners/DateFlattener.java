package com.facebook.flatbuffers.flatteners;

import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;
import java.util.Date;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DateFlattener implements Flattener<Date> {
    public static final DateFlattener INSTANCE = new DateFlattener();

    public int flattenToBuffer(Date date, FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(1);
        flatBufferBuilder.addLong(0, date.getTime(), 0);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    public Date initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        return new Date(FlatBuffer.getLong(byteBuffer, i, 0, 0));
    }
}
