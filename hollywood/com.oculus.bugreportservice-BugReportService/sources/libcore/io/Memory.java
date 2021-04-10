package libcore.io;

public final class Memory {
    public static native void memmove(Object obj, int i, Object obj2, int i2, long j);

    public static native byte peekByte(long j);

    public static native void peekByteArray(long j, byte[] bArr, int i, int i2);

    public static native void peekCharArray(long j, char[] cArr, int i, int i2, boolean z);

    public static native void peekIntArray(long j, int[] iArr, int i, int i2, boolean z);

    private static native int peekIntNative(long j);

    public static native void peekLongArray(long j, long[] jArr, int i, int i2, boolean z);

    private static native long peekLongNative(long j);

    public static native void peekShortArray(long j, short[] sArr, int i, int i2, boolean z);

    private static native short peekShortNative(long j);

    public static native void pokeByte(long j, byte b);

    public static native void pokeByteArray(long j, byte[] bArr, int i, int i2);

    private static native void pokeShortNative(long j, short s);

    public static native void unsafeBulkGet(Object obj, int i, int i2, byte[] bArr, int i3, int i4, boolean z);

    public static int peekInt(long j, boolean z) {
        int peekIntNative = peekIntNative(j);
        return z ? Integer.reverseBytes(peekIntNative) : peekIntNative;
    }

    public static long peekLong(long j, boolean z) {
        long peekLongNative = peekLongNative(j);
        return z ? Long.reverseBytes(peekLongNative) : peekLongNative;
    }

    public static short peekShort(long j, boolean z) {
        short peekShortNative = peekShortNative(j);
        return z ? Short.reverseBytes(peekShortNative) : peekShortNative;
    }

    public static void pokeShort(long j, short s, boolean z) {
        if (z) {
            s = Short.reverseBytes(s);
        }
        pokeShortNative(j, s);
    }
}
