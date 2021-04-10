package com.facebook.imagepipeline.platform;

import X.AbstractC00820Ju;
import X.AnonymousClass0JV;
import X.AnonymousClass0LU;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import com.facebook.infer.annotation.Nullsafe;
import java.lang.reflect.Method;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    public static Method A00;

    /* JADX WARN: Incorrect return type in method signature: (LX/0Ju<LX/0JV;>;I[BLandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap; */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0089, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008a, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x008d, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A00(X.AbstractC00820Ju r10, int r11, @javax.annotation.Nullable byte[] r12) {
        /*
        // Method dump skipped, instructions count: 178
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.GingerbreadPurgeableDecoder.A00(X.0Ju, int, byte[]):void");
    }

    @DoNotStrip
    public GingerbreadPurgeableDecoder() {
        if (!AnonymousClass0LU.A00) {
            try {
                Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
            } catch (Throwable unused) {
            }
            AnonymousClass0LU.A00 = true;
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> r3, BitmapFactory.Options options) {
        A00(r3, r3.A06().size(), null);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeJPEGByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> r5, int i, BitmapFactory.Options options) {
        byte[] bArr;
        AnonymousClass0JV A06 = r5.A06();
        if (i >= 2 && A06.read(i - 2) == -1 && A06.read(i - 1) == -39) {
            bArr = null;
        } else {
            bArr = DalvikPurgeableDecoder.EOI;
        }
        A00(r5, i, bArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
