package X;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Pz  reason: invalid class name and case insensitive filesystem */
public final class C01110Pz {
    public static final AnonymousClass0WB<ByteBuffer> A00 = new AnonymousClass0WB<>(12);

    public static int A00(Bitmap.Config config) {
        switch (C01100Py.A00[config.ordinal()]) {
            case 1:
            case 6:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    @SuppressLint({"NewApi"})
    public static int A01(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }

    public static AnonymousClass0Q2 A02(InputStream inputStream) {
        if (inputStream != null) {
            AnonymousClass0WB<ByteBuffer> r6 = A00;
            ByteBuffer A19 = r6.A19();
            if (A19 == null) {
                A19 = ByteBuffer.allocate(16384);
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = A19.array();
                ColorSpace colorSpace = null;
                BitmapFactory.decodeStream(inputStream, null, options);
                if (Build.VERSION.SDK_INT >= 26) {
                    colorSpace = options.outColorSpace;
                }
                return new AnonymousClass0Q2(options.outWidth, options.outHeight, colorSpace);
            } finally {
                r6.A8z(A19);
            }
        } else {
            throw null;
        }
    }
}
