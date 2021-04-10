package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMobileConfigEPInfo extends Table {
    public static FBMobileConfigEPInfo getRootAsFBMobileConfigEPInfo(ByteBuffer byteBuffer) {
        return getRootAsFBMobileConfigEPInfo(byteBuffer, new FBMobileConfigEPInfo());
    }

    public static FBMobileConfigEPInfo getRootAsFBMobileConfigEPInfo(ByteBuffer byteBuffer, FBMobileConfigEPInfo fBMobileConfigEPInfo) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fBMobileConfigEPInfo.__init(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public FBMobileConfigEPInfo __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
        return this;
    }

    public int configIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int version() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createFBMobileConfigEPInfo(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startObject(2);
        addVersion(flatBufferBuilder, i2);
        addConfigIndex(flatBufferBuilder, i);
        return endFBMobileConfigEPInfo(flatBufferBuilder);
    }

    public static void startFBMobileConfigEPInfo(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(2);
    }

    public static void addConfigIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endFBMobileConfigEPInfo(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
