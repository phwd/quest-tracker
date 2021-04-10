package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(11)
/* renamed from: X.1i5  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09481i5 implements AnonymousClass1i6 {
    public static final byte[] A03 = {-1, -39};
    @VisibleForTesting
    public final AnonymousClass0WB<ByteBuffer> A00;
    public final AnonymousClass0VL A01;
    @Nullable
    public final AnonymousClass1iD A02;

    public static BitmapFactory.Options A00(AnonymousClass0PZ r4, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = r4.A03;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(r4.A0A(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (X.AnonymousClass1iD.A00(r11.inPreferredConfig) == false) goto L_0x002b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00b6 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private X.AbstractC00820Ju<android.graphics.Bitmap> A01(java.io.InputStream r10, android.graphics.BitmapFactory.Options r11, @javax.annotation.Nullable android.graphics.Rect r12, @javax.annotation.Nullable android.graphics.ColorSpace r13) {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC09481i5.A01(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):X.0Ju");
    }

    public int A02(int i, int i2, BitmapFactory.Options options) {
        return i * i2 * C01110Pz.A00(options.inPreferredConfig);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:24:0x0059 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v12 */
    @Override // X.AnonymousClass1i6
    public final AbstractC00820Ju<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(AnonymousClass0PZ r6, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace) {
        InputStream inputStream;
        AnonymousClass0Oj r1 = r6.A07;
        boolean z = true;
        if ((r1 == AnonymousClass0Oi.A05 || r1 == AnonymousClass0Oi.A01) && r6.A0A == null) {
            AbstractC00820Ju<AnonymousClass0JV> r0 = r6.A0B;
            if (r0 != null) {
                AnonymousClass0JV A06 = r0.A06();
                if (!(A06.read(i - 2) == -1 && A06.read(i - 1) == -39)) {
                    z = false;
                }
            } else {
                throw null;
            }
        }
        BitmapFactory.Options A002 = A00(r6, config);
        InputStream A0A = r6.A0A();
        if (A0A != null) {
            if (r6.A09() > i) {
                A0A = new AnonymousClass1OO(A0A, i);
            }
            if (!z) {
                inputStream = new AnonymousClass1ON(A0A, A03);
            } else {
                inputStream = A0A;
            }
            Bitmap.Config config2 = A002.inPreferredConfig;
            Bitmap.Config config3 = Bitmap.Config.ARGB_8888;
            AbstractC00820Ju<Bitmap> r12 = 0;
            if (config2 != config3) {
                r12 = 1;
            }
            try {
                r12 = A01(inputStream, A002, rect, colorSpace);
            } catch (RuntimeException e) {
                if (r12 != 0) {
                    r12 = decodeJPEGFromEncodedImageWithColorSpace(r6, config3, rect, i, colorSpace);
                } else {
                    throw e;
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    throw th;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    throw th;
                }
            }
            try {
                inputStream.close();
                return r12;
            } catch (IOException e3) {
                e3.printStackTrace();
                return r12;
            }
        } else {
            throw null;
        }
    }

    public AbstractC09481i5(AnonymousClass0VL r4, int i, AnonymousClass0WB r6) {
        AnonymousClass1iD r0;
        if (Build.VERSION.SDK_INT >= 26) {
            r0 = new AnonymousClass1iD();
        } else {
            r0 = null;
        }
        this.A02 = r0;
        this.A01 = r4;
        this.A00 = r6;
        for (int i2 = 0; i2 < i; i2++) {
            this.A00.A8z(ByteBuffer.allocate(16384));
        }
    }

    @Override // X.AnonymousClass1i6
    public final AbstractC00820Ju<Bitmap> decodeFromEncodedImageWithColorSpace(AnonymousClass0PZ r5, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        BitmapFactory.Options A002 = A00(r5, config);
        boolean z = false;
        if (A002.inPreferredConfig != Bitmap.Config.ARGB_8888) {
            z = true;
        }
        try {
            return A01(r5.A0A(), A002, rect, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(r5, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e;
        }
    }
}
