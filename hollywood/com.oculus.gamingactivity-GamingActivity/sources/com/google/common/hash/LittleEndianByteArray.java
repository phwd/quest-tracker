package com.google.common.hash;

import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class LittleEndianByteArray {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final LittleEndianBytes byteArray;

    private enum JavaLittleEndianBytes implements LittleEndianBytes {
        INSTANCE {
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] source, int offset) {
                return Longs.fromBytes(source[offset + 7], source[offset + 6], source[offset + 5], source[offset + 4], source[offset + 3], source[offset + 2], source[offset + 1], source[offset]);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] sink, int offset, long value) {
                long mask = 255;
                for (int i = 0; i < 8; i++) {
                    sink[offset + i] = (byte) ((int) ((value & mask) >> (i * 8)));
                    mask <<= 8;
                }
            }
        }
    }

    private interface LittleEndianBytes {
        long getLongLittleEndian(byte[] bArr, int i);

        void putLongLittleEndian(byte[] bArr, int i, long j);
    }

    static {
        boolean z;
        if (!LittleEndianByteArray.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        LittleEndianBytes theGetter = JavaLittleEndianBytes.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                theGetter = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? UnsafeByteArray.UNSAFE_LITTLE_ENDIAN : UnsafeByteArray.UNSAFE_BIG_ENDIAN;
            }
        } catch (Throwable th) {
        }
        byteArray = theGetter;
    }

    static long load64(byte[] input, int offset) {
        if ($assertionsDisabled || input.length >= offset + 8) {
            return byteArray.getLongLittleEndian(input, offset);
        }
        throw new AssertionError();
    }

    static long load64Safely(byte[] input, int offset, int length) {
        long result = 0;
        int limit = Math.min(length, 8);
        for (int i = 0; i < limit; i++) {
            result |= (((long) input[offset + i]) & 255) << (i * 8);
        }
        return result;
    }

    static void store64(byte[] sink, int offset, long value) {
        if ($assertionsDisabled || (offset >= 0 && offset + 8 <= sink.length)) {
            byteArray.putLongLittleEndian(sink, offset, value);
            return;
        }
        throw new AssertionError();
    }

    static int load32(byte[] source, int offset) {
        return (source[offset] & UnsignedBytes.MAX_VALUE) | ((source[offset + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((source[offset + 2] & UnsignedBytes.MAX_VALUE) << 16) | ((source[offset + 3] & UnsignedBytes.MAX_VALUE) << 24);
    }

    static boolean usingUnsafe() {
        return byteArray instanceof UnsafeByteArray;
    }

    private enum UnsafeByteArray implements LittleEndianBytes {
        UNSAFE_LITTLE_ENDIAN {
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] array, int offset) {
                return UnsafeByteArray.theUnsafe.getLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] array, int offset, long value) {
                UnsafeByteArray.theUnsafe.putLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), value);
            }
        },
        UNSAFE_BIG_ENDIAN {
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] array, int offset) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET)));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] array, int offset, long value) {
                UnsafeByteArray.theUnsafe.putLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), Long.reverseBytes(value));
            }
        };
        
        private static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
        private static final Unsafe theUnsafe = getUnsafe();

        static {
            if (theUnsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        private static Unsafe getUnsafe() {
            try {
                return Unsafe.getUnsafe();
            } catch (SecurityException e) {
                try {
                    return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                        /* class com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.AnonymousClass3 */

                        @Override // java.security.PrivilegedExceptionAction
                        public Unsafe run() throws Exception {
                            Field[] declaredFields = Unsafe.class.getDeclaredFields();
                            for (Field f : declaredFields) {
                                f.setAccessible(true);
                                Object x = f.get(null);
                                if (Unsafe.class.isInstance(x)) {
                                    return (Unsafe) Unsafe.class.cast(x);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            }
        }
    }

    private LittleEndianByteArray() {
    }
}
