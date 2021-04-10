package X;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLException;

/* renamed from: X.11e  reason: invalid class name */
public final class AnonymousClass11e {
    public static int A00(byte[] bArr) throws AnonymousClass11f {
        if (bArr.length == 3) {
            return (bArr[2] & 255) | 0 | ((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8);
        }
        throw new AnonymousClass11f((byte) 80, new SSLException("Invalid argument. Byte array is null or length != 3"));
    }

    public static boolean A04(byte[] bArr, byte[] bArr2) {
        int length;
        if (bArr == null || bArr2 == null || (length = bArr.length) != bArr2.length) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < length; i++) {
            boolean z2 = false;
            if (bArr[i] == bArr2[i]) {
                z2 = true;
            }
            z &= z2;
        }
        return z;
    }

    public static int A01(byte[] bArr) throws AnonymousClass11f {
        if (bArr == null || bArr.length != 2) {
            throw new AnonymousClass11f((byte) 80, new SSLException("Invalid argument. Byte array is null or length != 2"));
        }
        return (bArr[1] & 255) | 0 | ((bArr[0] & 255) << 8);
    }

    public static long A02(byte[] bArr) throws AnonymousClass11f {
        if (bArr != null && bArr.length == 4) {
            return (((long) (bArr[0] & 255)) << 24) | (((long) (bArr[1] & 255)) << 16) | (((long) (bArr[2] & 255)) << 8) | ((long) (bArr[3] & 255));
        }
        throw new AnonymousClass11f((byte) 80, new SSLException("Invalid argument. byte array is null or length != 4"));
    }

    public static String A03(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        Formatter formatter = new Formatter();
        for (byte b : bArr) {
            formatter.format("%02x ", Byte.valueOf(b));
        }
        return formatter.toString();
    }

    public static byte[] A05(int i) throws AnonymousClass11f {
        if (i < 0 || i >= 16777216) {
            throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A02("Invalid argument. The supplied int value = ", i, " does not fit in 3 bytes.")));
        }
        return new byte[]{(byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] A06(int i) throws AnonymousClass11f {
        if (i < 0 || i >= 65536) {
            throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A02("Invalid argument. The supplied int value = ", i, " does not fit in 2 bytes.")));
        }
        return new byte[]{(byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] A07(String str, int i, String str2) throws AnonymousClass11f {
        try {
            return (str + "#" + i + "#" + str2).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }

    public static byte[] A08(String str, byte[] bArr, int i) throws AnonymousClass11f {
        String str2;
        byte b = 80;
        if (bArr != null) {
            int length = bArr.length;
            try {
                byte[] bytes = AnonymousClass006.A05("tls13 ", str).getBytes("UTF-8");
                int length2 = bytes.length;
                ByteBuffer allocate = ByteBuffer.allocate(length2 + 1 + 2 + length + 1);
                allocate.put(A06(i));
                short s = (short) length2;
                if (s < 0 || s >= 256) {
                    b = 80;
                    str2 = AnonymousClass006.A02("Invalid argument. Short val = ", s, " cannot fit in single byte");
                } else {
                    allocate.put((byte) (s & 255));
                    allocate.put(bytes);
                    allocate.put((byte) length);
                    allocate.put(bArr);
                    return allocate.array();
                }
            } catch (UnsupportedEncodingException e) {
                throw new AnonymousClass11f((byte) 80, new SSLException(e));
            }
        } else {
            str2 = "Context cannot be null when generating info";
        }
        throw new AnonymousClass11f(b, new SSLException(str2));
    }

    public static byte[] A09(byte[] bArr, byte[] bArr2, String str) throws AnonymousClass11f {
        try {
            String replace = str.replace("-", "");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, AnonymousClass006.A05("Hmac", replace));
            Mac instance = Mac.getInstance(AnonymousClass006.A05("Hmac", replace));
            instance.init(secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (NoSuchAlgorithmException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A07("Hmac", str, " not found"), e));
        } catch (InvalidKeyException e2) {
            throw new AnonymousClass11f((byte) 80, new SSLException("Invalid key", e2));
        }
    }
}
