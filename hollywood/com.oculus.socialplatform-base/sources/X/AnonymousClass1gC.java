package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* renamed from: X.1gC  reason: invalid class name */
public final class AnonymousClass1gC {
    public static final C07491cP<Boolean> A05 = C07491cP.A00("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);
    public static final C07491cP<AnonymousClass1gj> A06 = C07491cP.A00("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", AnonymousClass1gj.DEFAULT);
    @Deprecated
    public static final C07491cP<AbstractC09081gb> A07 = AbstractC09081gb.A00;
    public static final C07491cP<Boolean> A08 = C07491cP.A00("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final C07491cP<AnonymousClass1Dw> A09 = C07491cP.A00("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace", AnonymousClass1Dw.SRGB);
    public static final AnonymousClass1h7 A0A = new AnonymousClass1hB();
    public static final Queue<BitmapFactory.Options> A0B = new ArrayDeque(0);
    public static final Set<String> A0C = Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));
    public static final Set<ImageHeaderParser$ImageType> A0D = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser$ImageType.JPEG, ImageHeaderParser$ImageType.PNG_A, ImageHeaderParser$ImageType.PNG));
    public final DisplayMetrics A00;
    public final AbstractC07941di A01;
    public final AnonymousClass1hX A02;
    public final AnonymousClass1gK A03;
    public final List<AbstractC08251eH> A04;

    public static void A03(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = null;
            options.outColorSpace = null;
            options.outConfig = null;
        }
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    public static Bitmap A00(AbstractC07611cj r7, BitmapFactory.Options options, AnonymousClass1h7 r9, AbstractC07941di r10) throws IOException {
        if (!options.inJustDecodeBounds) {
            r9.A7S();
            r7.AAV();
        }
        int i = options.outWidth;
        int i2 = options.outHeight;
        String str = options.outMimeType;
        Lock lock = AnonymousClass1gF.A02;
        lock.lock();
        try {
            return r7.A2X(options);
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder("Exception decoding bitmap, outWidth: ");
            sb.append(i);
            sb.append(", outHeight: ");
            sb.append(i2);
            sb.append(", outMimeType: ");
            sb.append(str);
            sb.append(", inBitmap: ");
            sb.append(A02(options.inBitmap));
            IOException iOException = new IOException(sb.toString(), e);
            Bitmap bitmap = options.inBitmap;
            if (bitmap != null) {
                try {
                    r10.A8l(bitmap);
                    options.inBitmap = null;
                    return A00(r7, options, r9, r10);
                } catch (IOException unused) {
                    throw iOException;
                }
            } else {
                throw iOException;
            }
        } finally {
            lock.unlock();
        }
    }

    @Nullable
    @TargetApi(19)
    public static String A02(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        String A042 = AnonymousClass006.A04(" (", bitmap.getAllocationByteCount(), ")");
        StringBuilder sb = new StringBuilder("[");
        sb.append(bitmap.getWidth());
        sb.append("x");
        sb.append(bitmap.getHeight());
        sb.append("] ");
        sb.append(bitmap.getConfig());
        sb.append(A042);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r0 == null) goto L_0x0010;
     */
    @android.annotation.TargetApi(26)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A04(android.graphics.BitmapFactory.Options r2, X.AbstractC07941di r3, int r4, int r5) {
        /*
            int r1 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r1 < r0) goto L_0x0010
            android.graphics.Bitmap$Config r1 = r2.inPreferredConfig
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.HARDWARE
            if (r1 == r0) goto L_0x0018
            android.graphics.Bitmap$Config r0 = r2.outConfig
            if (r0 != 0) goto L_0x0012
        L_0x0010:
            android.graphics.Bitmap$Config r0 = r2.inPreferredConfig
        L_0x0012:
            android.graphics.Bitmap r0 = r3.A3n(r4, r5, r0)
            r2.inBitmap = r0
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1gC.A04(android.graphics.BitmapFactory$Options, X.1di, int, int):void");
    }

    public AnonymousClass1gC(List<AbstractC08251eH> list, DisplayMetrics displayMetrics, AbstractC07941di r5, ArrayPool arrayPool) {
        if (AnonymousClass1gK.A06 == null) {
            synchronized (AnonymousClass1gK.class) {
                if (AnonymousClass1gK.A06 == null) {
                    AnonymousClass1gK.A06 = new AnonymousClass1gK();
                }
            }
        }
        this.A03 = AnonymousClass1gK.A06;
        this.A04 = list;
        AnonymousClass1S2.A00(displayMetrics);
        this.A00 = displayMetrics;
        AnonymousClass1S2.A00(r5);
        this.A01 = r5;
        AnonymousClass1S2.A00(arrayPool);
        this.A02 = arrayPool;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1cj;IILX/1cO;LX/1h7;)LX/1fR<Landroid/graphics/Bitmap;>; */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006e, code lost:
        if (((java.lang.Boolean) r45.A00(r1)).booleanValue() == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c4, code lost:
        if (r5 == 270) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d4, code lost:
        if (r5 == 270) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f2, code lost:
        if (r5 == 270) goto L_0x00f4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x028b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass1fR A01(X.AnonymousClass1gC r41, X.AbstractC07611cj r42, int r43, int r44, X.AnonymousClass1cO r45, X.AnonymousClass1h7 r46) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1062
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1gC.A01(X.1gC, X.1cj, int, int, X.1cO, X.1h7):X.1fR");
    }
}
