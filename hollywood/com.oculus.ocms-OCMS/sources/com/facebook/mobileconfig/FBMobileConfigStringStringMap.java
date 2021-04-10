package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMobileConfigStringStringMap extends Table {
    public static FBMobileConfigStringStringMap getRootAsFBMobileConfigStringStringMap(ByteBuffer byteBuffer) {
        return getRootAsFBMobileConfigStringStringMap(byteBuffer, new FBMobileConfigStringStringMap());
    }

    public static FBMobileConfigStringStringMap getRootAsFBMobileConfigStringStringMap(ByteBuffer byteBuffer, FBMobileConfigStringStringMap fBMobileConfigStringStringMap) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fBMobileConfigStringStringMap.__init(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public FBMobileConfigStringStringMap __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
        return this;
    }

    @Nullable
    public String keys(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int keysLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public String values(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int valuesLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public static int createFBMobileConfigStringStringMap(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startObject(2);
        addValues(flatBufferBuilder, i2);
        addKeys(flatBufferBuilder, i);
        return endFBMobileConfigStringStringMap(flatBufferBuilder);
    }

    public static void startFBMobileConfigStringStringMap(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(2);
    }

    public static void addKeys(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createKeysVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startKeysVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addValues(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createValuesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startValuesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endFBMobileConfigStringStringMap(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
