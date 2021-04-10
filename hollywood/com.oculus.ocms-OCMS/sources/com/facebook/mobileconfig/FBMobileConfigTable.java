package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FBMobileConfigTable extends Table {
    public static FBMobileConfigTable getRootAsFBMobileConfigTable(ByteBuffer byteBuffer) {
        return getRootAsFBMobileConfigTable(byteBuffer, new FBMobileConfigTable());
    }

    public static FBMobileConfigTable getRootAsFBMobileConfigTable(ByteBuffer byteBuffer, FBMobileConfigTable fBMobileConfigTable) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fBMobileConfigTable.__init(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public FBMobileConfigTable __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
        return this;
    }

    @Nullable
    public String schemaHash() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    @Nullable
    public ByteBuffer schemaHashAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public byte bools(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1));
        }
        return 0;
    }

    public int boolsLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer boolsAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public int boolsMeta(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int boolsMetaLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer boolsMetaAsByteBuffer() {
        return __vector_as_bytebuffer(8, 4);
    }

    public long longs(int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getLong(__vector(__offset) + (i * 8));
        }
        return 0;
    }

    public int longsLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer longsAsByteBuffer() {
        return __vector_as_bytebuffer(10, 8);
    }

    public int longsMeta(int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int longsMetaLength() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer longsMetaAsByteBuffer() {
        return __vector_as_bytebuffer(12, 4);
    }

    public int ints(int i) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int intsLength() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer intsAsByteBuffer() {
        return __vector_as_bytebuffer(14, 4);
    }

    public int intsMeta(int i) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int intsMetaLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer intsMetaAsByteBuffer() {
        return __vector_as_bytebuffer(16, 4);
    }

    public double doubles(int i) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getDouble(__vector(__offset) + (i * 8));
        }
        return 0.0d;
    }

    public int doublesLength() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer doublesAsByteBuffer() {
        return __vector_as_bytebuffer(18, 8);
    }

    public int doublesMeta(int i) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int doublesMetaLength() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer doublesMetaAsByteBuffer() {
        return __vector_as_bytebuffer(20, 4);
    }

    @Nullable
    public String strings(int i) {
        int __offset = __offset(22);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int stringsLength() {
        int __offset = __offset(22);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int stringsMeta(int i) {
        int __offset = __offset(24);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int stringsMetaLength() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer stringsMetaAsByteBuffer() {
        return __vector_as_bytebuffer(24, 4);
    }

    @Nullable
    public String loggingIds(int i) {
        int __offset = __offset(26);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int loggingIdsLength() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int magic() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    @Nullable
    public FBMobileConfigEPInfo emergencyPushInfo(int i) {
        return emergencyPushInfo(new FBMobileConfigEPInfo(), i);
    }

    @Nullable
    public FBMobileConfigEPInfo emergencyPushInfo(FBMobileConfigEPInfo fBMobileConfigEPInfo, int i) {
        int __offset = __offset(32);
        if (__offset != 0) {
            return fBMobileConfigEPInfo.__init(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int emergencyPushInfoLength() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int magic2() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public long requestTimestamp() {
        int __offset = __offset(36);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0;
    }

    @Nullable
    public FBMobileConfigStringStringMap stringStringMap(int i) {
        return stringStringMap(new FBMobileConfigStringStringMap(), i);
    }

    @Nullable
    public FBMobileConfigStringStringMap stringStringMap(FBMobileConfigStringStringMap fBMobileConfigStringStringMap, int i) {
        int __offset = __offset(38);
        if (__offset != 0) {
            return fBMobileConfigStringStringMap.__init(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int stringStringMapLength() {
        int __offset = __offset(38);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public int stringStringMapMeta(int i) {
        int __offset = __offset(40);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int stringStringMapMetaLength() {
        int __offset = __offset(40);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    @Nullable
    public ByteBuffer stringStringMapMetaAsByteBuffer() {
        return __vector_as_bytebuffer(40, 4);
    }

    public static int createFBMobileConfigTable(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j, int i16, int i17) {
        flatBufferBuilder.startObject(19);
        addRequestTimestamp(flatBufferBuilder, j);
        addStringStringMapMeta(flatBufferBuilder, i17);
        addStringStringMap(flatBufferBuilder, i16);
        addMagic2(flatBufferBuilder, i15);
        addEmergencyPushInfo(flatBufferBuilder, i14);
        addMagic(flatBufferBuilder, i13);
        addLoggingIds(flatBufferBuilder, i12);
        addStringsMeta(flatBufferBuilder, i11);
        addStrings(flatBufferBuilder, i10);
        addDoublesMeta(flatBufferBuilder, i9);
        addDoubles(flatBufferBuilder, i8);
        addIntsMeta(flatBufferBuilder, i7);
        addInts(flatBufferBuilder, i6);
        addLongsMeta(flatBufferBuilder, i5);
        addLongs(flatBufferBuilder, i4);
        addBoolsMeta(flatBufferBuilder, i3);
        addBools(flatBufferBuilder, i2);
        addSchemaHash(flatBufferBuilder, i);
        return endFBMobileConfigTable(flatBufferBuilder);
    }

    public static void startFBMobileConfigTable(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(19);
    }

    public static void addSchemaHash(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addBools(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createBoolsVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        flatBufferBuilder.startVector(1, bArr.length, 1);
        for (int length = bArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addByte(bArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startBoolsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static void addBoolsMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createBoolsMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startBoolsMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLongs(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createLongsVector(FlatBufferBuilder flatBufferBuilder, long[] jArr) {
        flatBufferBuilder.startVector(8, jArr.length, 8);
        for (int length = jArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addLong(jArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startLongsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(8, i, 8);
    }

    public static void addLongsMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static int createLongsMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startLongsMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addInts(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static int createIntsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startIntsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addIntsMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static int createIntsMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startIntsMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addDoubles(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static int createDoublesVector(FlatBufferBuilder flatBufferBuilder, double[] dArr) {
        flatBufferBuilder.startVector(8, dArr.length, 8);
        for (int length = dArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addDouble(dArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startDoublesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(8, i, 8);
    }

    public static void addDoublesMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static int createDoublesMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startDoublesMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addStrings(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(9, i, 0);
    }

    public static int createStringsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startStringsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addStringsMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(10, i, 0);
    }

    public static int createStringsMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startStringsMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLoggingIds(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(11, i, 0);
    }

    public static int createLoggingIdsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startLoggingIdsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addMagic(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(12, i, 0);
    }

    public static void addEmergencyPushInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(14, i, 0);
    }

    public static int createEmergencyPushInfoVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEmergencyPushInfoVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addMagic2(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(15, i, 0);
    }

    public static void addRequestTimestamp(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(16, j, 0);
    }

    public static void addStringStringMap(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(17, i, 0);
    }

    public static int createStringStringMapVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startStringStringMapVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addStringStringMapMeta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(18, i, 0);
    }

    public static int createStringStringMapMetaVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startStringStringMapMetaVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endFBMobileConfigTable(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }

    public static void finishFBMobileConfigTableBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finish(i);
    }
}
