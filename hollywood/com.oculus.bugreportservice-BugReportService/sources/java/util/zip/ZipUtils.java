package java.util.zip;

import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public class ZipUtils {
    public static final FileTime winTimeToFileTime(long j) {
        return FileTime.from((j / 10) - 11644473600000000L, TimeUnit.MICROSECONDS);
    }

    public static final FileTime unixTimeToFileTime(long j) {
        return FileTime.from(j, TimeUnit.SECONDS);
    }

    private static long dosToJavaTime(long j) {
        return new Date((int) (((j >> 25) & 127) + 80), (int) (((j >> 21) & 15) - 1), (int) ((j >> 16) & 31), (int) ((j >> 11) & 31), (int) ((j >> 5) & 63), (int) ((j << 1) & 62)).getTime();
    }

    public static long extendedDosToJavaTime(long j) {
        return dosToJavaTime(j) + (j >> 32);
    }

    public static final int get16(byte[] bArr, int i) {
        return (Byte.toUnsignedInt(bArr[i + 1]) << 8) | Byte.toUnsignedInt(bArr[i]);
    }

    public static final long get32(byte[] bArr, int i) {
        return ((((long) get16(bArr, i + 2)) << 16) | ((long) get16(bArr, i))) & 4294967295L;
    }

    public static final long get64(byte[] bArr, int i) {
        return (get32(bArr, i + 4) << 32) | get32(bArr, i);
    }
}
