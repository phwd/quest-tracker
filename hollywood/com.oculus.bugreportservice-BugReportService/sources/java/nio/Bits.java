package java.nio;

import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
public class Bits {
    private static final ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
    private static int pageSize = -1;
    private static boolean unalignedKnown = false;
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static byte char0(char c) {
        return (byte) c;
    }

    private static byte char1(char c) {
        return (byte) (c >> '\b');
    }

    private static char makeChar(byte b, byte b2) {
        return (char) ((b << 8) | (b2 & 255));
    }

    private static int makeInt(byte b, byte b2, byte b3, byte b4) {
        return (b << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    private static long makeLong(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((((long) b2) & 255) << 48) | (((long) b) << 56) | ((((long) b3) & 255) << 40) | ((((long) b4) & 255) << 32) | ((((long) b5) & 255) << 24) | ((((long) b6) & 255) << 16) | ((((long) b7) & 255) << 8) | (((long) b8) & 255);
    }

    private static short makeShort(byte b, byte b2) {
        return (short) ((b << 8) | (b2 & 255));
    }

    static char getCharL(ByteBuffer byteBuffer, int i) {
        return makeChar(byteBuffer._get(i + 1), byteBuffer._get(i));
    }

    static char getCharB(ByteBuffer byteBuffer, int i) {
        return makeChar(byteBuffer._get(i), byteBuffer._get(i + 1));
    }

    static char getChar(ByteBuffer byteBuffer, int i, boolean z) {
        return z ? getCharB(byteBuffer, i) : getCharL(byteBuffer, i);
    }

    static void putCharL(ByteBuffer byteBuffer, int i, char c) {
        byteBuffer._put(i, char0(c));
        byteBuffer._put(i + 1, char1(c));
    }

    static void putCharB(ByteBuffer byteBuffer, int i, char c) {
        byteBuffer._put(i, char1(c));
        byteBuffer._put(i + 1, char0(c));
    }

    static void putChar(ByteBuffer byteBuffer, int i, char c, boolean z) {
        if (z) {
            putCharB(byteBuffer, i, c);
        } else {
            putCharL(byteBuffer, i, c);
        }
    }

    static short getShortL(ByteBuffer byteBuffer, int i) {
        return makeShort(byteBuffer._get(i + 1), byteBuffer._get(i));
    }

    static short getShortB(ByteBuffer byteBuffer, int i) {
        return makeShort(byteBuffer._get(i), byteBuffer._get(i + 1));
    }

    static short getShort(ByteBuffer byteBuffer, int i, boolean z) {
        return z ? getShortB(byteBuffer, i) : getShortL(byteBuffer, i);
    }

    static int getIntL(ByteBuffer byteBuffer, int i) {
        return makeInt(byteBuffer._get(i + 3), byteBuffer._get(i + 2), byteBuffer._get(i + 1), byteBuffer._get(i));
    }

    static int getIntB(ByteBuffer byteBuffer, int i) {
        return makeInt(byteBuffer._get(i), byteBuffer._get(i + 1), byteBuffer._get(i + 2), byteBuffer._get(i + 3));
    }

    static int getInt(ByteBuffer byteBuffer, int i, boolean z) {
        return z ? getIntB(byteBuffer, i) : getIntL(byteBuffer, i);
    }

    static long getLongL(ByteBuffer byteBuffer, int i) {
        return makeLong(byteBuffer._get(i + 7), byteBuffer._get(i + 6), byteBuffer._get(i + 5), byteBuffer._get(i + 4), byteBuffer._get(i + 3), byteBuffer._get(i + 2), byteBuffer._get(i + 1), byteBuffer._get(i));
    }

    static long getLongB(ByteBuffer byteBuffer, int i) {
        return makeLong(byteBuffer._get(i), byteBuffer._get(i + 1), byteBuffer._get(i + 2), byteBuffer._get(i + 3), byteBuffer._get(i + 4), byteBuffer._get(i + 5), byteBuffer._get(i + 6), byteBuffer._get(i + 7));
    }

    static long getLong(ByteBuffer byteBuffer, int i, boolean z) {
        return z ? getLongB(byteBuffer, i) : getLongL(byteBuffer, i);
    }

    static ByteOrder byteOrder() {
        return byteOrder;
    }
}
