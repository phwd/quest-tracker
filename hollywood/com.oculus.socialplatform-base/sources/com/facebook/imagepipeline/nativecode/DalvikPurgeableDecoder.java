package com.facebook.imagepipeline.nativecode;

import X.AbstractC00820Ju;
import X.AnonymousClass0JV;
import X.AnonymousClass0PQ;
import X.AnonymousClass0PZ;
import X.AnonymousClass0lD;
import X.AnonymousClass1i6;
import X.AnonymousClass1iE;
import X.AnonymousClass1iK;
import X.AnonymousClass1iN;
import X.C00770Im;
import X.C01110Pz;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Locale;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public abstract class DalvikPurgeableDecoder implements AnonymousClass1i6 {
    public static final byte[] EOI = {-1, -39};
    public final AnonymousClass1iK mUnpooledBitmapsCounter;

    @DoNotStrip
    public static native void nativePinBitmap(Bitmap bitmap);

    public abstract Bitmap decodeByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> v, BitmapFactory.Options options);

    public abstract Bitmap decodeJPEGByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> v, int i, BitmapFactory.Options options);

    static {
        AnonymousClass0lD.A01("imagepipeline");
    }

    @Override // X.AnonymousClass1i6
    public AbstractC00820Ju<Bitmap> decodeFromEncodedImageWithColorSpace(AnonymousClass0PZ r4, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace) {
        int i = r4.A03;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            AnonymousClass1iE.A00(options, colorSpace);
        }
        AbstractC00820Ju<AnonymousClass0JV> A00 = AbstractC00820Ju.A00(r4.A0B);
        if (A00 != null) {
            try {
                return pinBitmap(decodeByteArrayAsPurgeable(A00, options));
            } finally {
                AbstractC00820Ju.A03(A00);
            }
        } else {
            throw null;
        }
    }

    @Override // X.AnonymousClass1i6
    public AbstractC00820Ju<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(AnonymousClass0PZ r4, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace) {
        int i2 = r4.A03;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i2;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            AnonymousClass1iE.A00(options, colorSpace);
        }
        AbstractC00820Ju<AnonymousClass0JV> A00 = AbstractC00820Ju.A00(r4.A0B);
        if (A00 != null) {
            try {
                return pinBitmap(decodeJPEGByteArrayAsPurgeable(A00, i, options));
            } finally {
                AbstractC00820Ju.A03(A00);
            }
        } else {
            throw null;
        }
    }

    public AbstractC00820Ju<Bitmap> pinBitmap(Bitmap bitmap) {
        boolean z;
        int i;
        long j;
        int i2;
        int i3;
        if (bitmap != null) {
            try {
                nativePinBitmap(bitmap);
                AnonymousClass1iK r6 = this.mUnpooledBitmapsCounter;
                synchronized (r6) {
                    int A01 = C01110Pz.A01(bitmap);
                    int i4 = r6.A00;
                    if (i4 < r6.A02) {
                        long j2 = r6.A01 + ((long) A01);
                        if (j2 <= ((long) r6.A03)) {
                            z = true;
                            r6.A00 = i4 + 1;
                            r6.A01 = j2;
                        }
                    }
                    z = false;
                }
                if (z) {
                    return AbstractC00820Ju.A01(bitmap, this.mUnpooledBitmapsCounter.A04);
                }
                int A012 = C01110Pz.A01(bitmap);
                bitmap.recycle();
                Locale locale = Locale.US;
                Integer valueOf = Integer.valueOf(A012);
                AnonymousClass1iK r1 = this.mUnpooledBitmapsCounter;
                synchronized (r1) {
                    i = r1.A00;
                }
                Integer valueOf2 = Integer.valueOf(i);
                AnonymousClass1iK r2 = this.mUnpooledBitmapsCounter;
                synchronized (r2) {
                    j = r2.A01;
                }
                Long valueOf3 = Long.valueOf(j);
                AnonymousClass1iK r12 = this.mUnpooledBitmapsCounter;
                synchronized (r12) {
                    i2 = r12.A02;
                }
                Integer valueOf4 = Integer.valueOf(i2);
                AnonymousClass1iK r13 = this.mUnpooledBitmapsCounter;
                synchronized (r13) {
                    i3 = r13.A03;
                }
                throw new AnonymousClass0PQ(String.format(locale, "Attempted to pin a bitmap of size %d bytes. The current pool count is %d, the current pool size is %d bytes. The current pool max count is %d, the current pool max size is %d bytes.", valueOf, valueOf2, valueOf3, valueOf4, Integer.valueOf(i3)));
            } catch (Exception e) {
                bitmap.recycle();
                C00770Im.A00(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw null;
        }
    }

    public DalvikPurgeableDecoder() {
        if (AnonymousClass1iN.A01 == null) {
            synchronized (AnonymousClass1iN.class) {
                if (AnonymousClass1iN.A01 == null) {
                    AnonymousClass1iN.A01 = new AnonymousClass1iK(AnonymousClass1iN.A00);
                }
            }
        }
        this.mUnpooledBitmapsCounter = AnonymousClass1iN.A01;
    }
}
