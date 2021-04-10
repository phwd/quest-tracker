package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.RandomAccess;

public final class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(EMPTY_BYTE_ARRAY);
    static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    public interface BooleanList extends ProtobufList<Boolean> {
        /* Return type fixed from 'com.google.protobuf.Internal$BooleanList' to match base method */
        @Override // com.google.protobuf.Internal.ProtobufList
        ProtobufList<Boolean> mutableCopyWithCapacity(int i);
    }

    public interface DoubleList extends ProtobufList<Double> {
        /* Return type fixed from 'com.google.protobuf.Internal$DoubleList' to match base method */
        @Override // com.google.protobuf.Internal.ProtobufList
        ProtobufList<Double> mutableCopyWithCapacity(int i);
    }

    public interface EnumLite {
    }

    public interface EnumLiteMap<T extends EnumLite> {
    }

    public interface FloatList extends ProtobufList<Float> {
        /* Return type fixed from 'com.google.protobuf.Internal$FloatList' to match base method */
        @Override // com.google.protobuf.Internal.ProtobufList
        ProtobufList<Float> mutableCopyWithCapacity(int i);
    }

    public interface IntList extends ProtobufList<Integer> {
        /* Return type fixed from 'com.google.protobuf.Internal$IntList' to match base method */
        @Override // com.google.protobuf.Internal.ProtobufList
        ProtobufList<Integer> mutableCopyWithCapacity(int i);
    }

    public interface LongList extends ProtobufList<Long> {
        /* Return type fixed from 'com.google.protobuf.Internal$LongList' to match base method */
        @Override // com.google.protobuf.Internal.ProtobufList
        ProtobufList<Long> mutableCopyWithCapacity(int i);
    }

    public interface ProtobufList<E> extends List<E>, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList<E> mutableCopyWithCapacity(int i);
    }

    public static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static int partialHash(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }
}
