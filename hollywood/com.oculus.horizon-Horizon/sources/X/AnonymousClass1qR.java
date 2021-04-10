package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.memory.PooledByteBuffer;
import com.squareup.okhttp.internal.framed.Http2;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(11)
/* renamed from: X.1qR  reason: invalid class name */
public abstract class AnonymousClass1qR implements AbstractC10231rm {
    public static final byte[] A03 = {-1, -39};
    @VisibleForTesting
    public final AnonymousClass0KB<ByteBuffer> A00;
    public final AnonymousClass0Ox A01;
    @Nullable
    public final C10451sr A02;

    public static BitmapFactory.Options A00(AnonymousClass1qQ r4, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = r4.A03;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(r4.A08(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008b, code lost:
        if (r1 == null) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0090, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (X.C10451sr.A00(r12.inPreferredConfig) == false) goto L_0x002b;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009e A[Catch:{ all -> 0x009f }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private X.AnonymousClass1qa<android.graphics.Bitmap> A01(java.io.InputStream r11, android.graphics.BitmapFactory.Options r12, @javax.annotation.Nullable android.graphics.Rect r13, @javax.annotation.Nullable android.graphics.ColorSpace r14) {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qR.A01(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):X.1qa");
    }

    public int A02(int i, int i2, BitmapFactory.Options options) {
        return i * i2 * C09951qe.A00(options.inPreferredConfig);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:24:0x0059 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v12 */
    @Override // X.AbstractC10231rm
    public final AnonymousClass1qa<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(AnonymousClass1qQ r6, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace) {
        InputStream inputStream;
        AnonymousClass1tL r1 = r6.A07;
        boolean z = true;
        if ((r1 == C10191ri.A05 || r1 == C10191ri.A01) && r6.A0A == null) {
            AnonymousClass1qa<PooledByteBuffer> r0 = r6.A0B;
            if (r0 != null) {
                C10021qp A04 = r0.A04();
                if (!(A04.A01(i - 2) == -1 && A04.A01(i - 1) == -39)) {
                    z = false;
                }
            } else {
                throw null;
            }
        }
        BitmapFactory.Options A002 = A00(r6, config);
        InputStream A08 = r6.A08();
        if (A08 != null) {
            if (r6.A07() > i) {
                A08 = new AnonymousClass1mF(A08, i);
            }
            if (!z) {
                inputStream = new AnonymousClass1mG(A08, A03);
            } else {
                inputStream = A08;
            }
            Bitmap.Config config2 = A002.inPreferredConfig;
            Bitmap.Config config3 = Bitmap.Config.ARGB_8888;
            AnonymousClass1qa<Bitmap> r12 = 0;
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

    public AnonymousClass1qR(AnonymousClass0Ox r4, int i, AnonymousClass0KB r6) {
        C10451sr r0;
        if (Build.VERSION.SDK_INT >= 26) {
            r0 = new C10451sr();
        } else {
            r0 = null;
        }
        this.A02 = r0;
        this.A01 = r4;
        this.A00 = r6;
        for (int i2 = 0; i2 < i; i2++) {
            this.A00.A01(ByteBuffer.allocate(Http2.INITIAL_MAX_FRAME_SIZE));
        }
    }

    @Override // X.AbstractC10231rm
    public final AnonymousClass1qa<Bitmap> decodeFromEncodedImageWithColorSpace(AnonymousClass1qQ r5, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        BitmapFactory.Options A002 = A00(r5, config);
        boolean z = false;
        if (A002.inPreferredConfig != Bitmap.Config.ARGB_8888) {
            z = true;
        }
        try {
            return A01(r5.A08(), A002, rect, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(r5, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e;
        }
    }
}
