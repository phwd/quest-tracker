package com.facebook.imagepipeline.platform;

import X.AnonymousClass1qa;
import X.C01180Ma;
import X.C10021qp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import com.facebook.infer.annotation.Nullsafe;
import java.lang.reflect.Method;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    public static Method A00;

    /* JADX WARN: Incorrect return type in method signature: (LX/1qa<Lcom/facebook/common/memory/PooledByteBuffer;>;I[BLandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap; */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0098, code lost:
        if (r10 == null) goto L_0x009d;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A00(X.AnonymousClass1qa r10, int r11, @javax.annotation.Nullable byte[] r12) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.GingerbreadPurgeableDecoder.A00(X.1qa, int, byte[]):void");
    }

    @DoNotStrip
    public GingerbreadPurgeableDecoder() {
        if (!C01180Ma.A00) {
            try {
                Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
            } catch (Throwable unused) {
            }
            C01180Ma.A00 = true;
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> r3, BitmapFactory.Options options) {
        A00(r3, r3.A04().A02(), null);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeJPEGByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> r5, int i, BitmapFactory.Options options) {
        byte[] bArr;
        C10021qp A04 = r5.A04();
        if (i >= 2 && A04.A01(i - 2) == -1 && A04.A01(i - 1) == -39) {
            bArr = null;
        } else {
            bArr = DalvikPurgeableDecoder.EOI;
        }
        A00(r5, i, bArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
