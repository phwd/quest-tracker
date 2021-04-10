package X;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;
import com.squareup.okhttp.internal.framed.Http2;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qe  reason: invalid class name and case insensitive filesystem */
public final class C09951qe {
    public static final AnonymousClass0KB<ByteBuffer> A00 = new AnonymousClass0KB<>(12);

    public static int A00(Bitmap.Config config) {
        switch (C10351sa.A00[config.ordinal()]) {
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

    public static AnonymousClass1sC A01(InputStream inputStream) {
        if (inputStream != null) {
            AnonymousClass0KB<ByteBuffer> r6 = A00;
            ByteBuffer A002 = r6.A00();
            if (A002 == null) {
                A002 = ByteBuffer.allocate(Http2.INITIAL_MAX_FRAME_SIZE);
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = A002.array();
                ColorSpace colorSpace = null;
                BitmapFactory.decodeStream(inputStream, null, options);
                if (Build.VERSION.SDK_INT >= 26) {
                    colorSpace = options.outColorSpace;
                }
                return new AnonymousClass1sC(options.outWidth, options.outHeight, colorSpace);
            } finally {
                r6.A01(A002);
            }
        } else {
            throw null;
        }
    }
}
