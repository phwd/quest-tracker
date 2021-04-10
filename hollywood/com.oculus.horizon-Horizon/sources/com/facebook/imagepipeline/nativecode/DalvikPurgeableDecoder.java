package com.facebook.imagepipeline.nativecode;

import X.AbstractC10231rm;
import X.AnonymousClass1qQ;
import X.AnonymousClass1qa;
import X.AnonymousClass1rU;
import X.AnonymousClass1sW;
import X.C03250cX;
import X.C10361sd;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public abstract class DalvikPurgeableDecoder implements AbstractC10231rm {
    public static final byte[] EOI = {-1, -39};
    public final AnonymousClass1rU mUnpooledBitmapsCounter;

    @DoNotStrip
    public static native void nativePinBitmap(Bitmap bitmap);

    public abstract Bitmap decodeByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> v, BitmapFactory.Options options);

    public abstract Bitmap decodeJPEGByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> v, int i, BitmapFactory.Options options);

    static {
        C03250cX.A01("imagepipeline");
    }

    @Override // X.AbstractC10231rm
    public AnonymousClass1qa<Bitmap> decodeFromEncodedImageWithColorSpace(AnonymousClass1qQ r4, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        int i = r4.A03;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            C10361sd.A00(options, colorSpace);
        }
        AnonymousClass1qa<PooledByteBuffer> A00 = AnonymousClass1qa.A00(r4.A0B);
        if (A00 != null) {
            try {
                return pinBitmap(decodeByteArrayAsPurgeable(A00, options));
            } finally {
                A00.close();
            }
        } else {
            throw null;
        }
    }

    @Override // X.AbstractC10231rm
    public AnonymousClass1qa<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(AnonymousClass1qQ r4, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace) {
        int i2 = r4.A03;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i2;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            C10361sd.A00(options, colorSpace);
        }
        AnonymousClass1qa<PooledByteBuffer> A00 = AnonymousClass1qa.A00(r4.A0B);
        if (A00 != null) {
            try {
                return pinBitmap(decodeJPEGByteArrayAsPurgeable(A00, i, options));
            } finally {
                A00.close();
            }
        } else {
            throw null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2 = r8.getAllocationByteCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        r2 = r8.getByteCount();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.AnonymousClass1qa<android.graphics.Bitmap> pinBitmap(android.graphics.Bitmap r8) {
        /*
        // Method dump skipped, instructions count: 162
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder.pinBitmap(android.graphics.Bitmap):X.1qa");
    }

    public DalvikPurgeableDecoder() {
        if (AnonymousClass1sW.A01 == null) {
            synchronized (AnonymousClass1sW.class) {
                if (AnonymousClass1sW.A01 == null) {
                    AnonymousClass1sW.A01 = new AnonymousClass1rU(AnonymousClass1sW.A00);
                }
            }
        }
        this.mUnpooledBitmapsCounter = AnonymousClass1sW.A01;
    }
}
