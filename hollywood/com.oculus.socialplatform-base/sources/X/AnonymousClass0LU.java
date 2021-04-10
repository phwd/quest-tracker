package X;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import com.facebook.infer.annotation.Nullsafe;
import java.io.UnsupportedEncodingException;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0LU  reason: invalid class name */
public final class AnonymousClass0LU {
    public static boolean A00;
    public static final boolean A01;
    public static final byte[] A02 = A01("WEBP");
    public static final byte[] A03 = A01("RIFF");
    public static final byte[] A04 = A01("VP8L");
    public static final byte[] A05 = A01("VP8X");
    public static final byte[] A06 = A01("VP8 ");

    static {
        boolean z;
        if (Build.VERSION.SDK_INT == 17) {
            byte[] decode = Base64.decode("UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==", 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            if (!(options.outHeight == 1 && options.outWidth == 1)) {
                z = false;
                A01 = z;
            }
        }
        z = true;
        A01 = z;
    }

    public static boolean A00(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2 != null) {
            int length = bArr2.length;
            if (length + i <= bArr.length) {
                for (int i2 = 0; i2 < length; i2++) {
                    if (bArr[i2 + i] == bArr2[i2]) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static byte[] A01(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }
}
