package com.facebook.imagepipeline.platform;

import X.AnonymousClass0KU;
import X.AnonymousClass1qa;
import X.AnonymousClass1rP;
import X.C10021qp;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@TargetApi(19)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder {
    public final AnonymousClass1rP A00;

    @DoNotStrip
    public KitKatPurgeableDecoder(AnonymousClass1rP r1) {
        this.A00 = r1;
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> r6, BitmapFactory.Options options) {
        C10021qp A04 = r6.A04();
        int A02 = A04.A02();
        AnonymousClass1rP r2 = this.A00;
        AnonymousClass1qa A01 = AnonymousClass1qa.A01(r2.A01.get(A02), r2.A00);
        try {
            byte[] bArr = (byte[]) A01.A04();
            A04.A03(0, bArr, 0, A02);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, A02, options);
            AnonymousClass0KU.A02(decodeByteArray, "BitmapFactory returned null");
            A01.close();
            return decodeByteArray;
        } catch (Throwable th) {
            if (A01 != null) {
                A01.close();
            }
            throw th;
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeJPEGByteArrayAsPurgeable(AnonymousClass1qa<PooledByteBuffer> r9, int i, BitmapFactory.Options options) {
        byte[] bArr;
        C10021qp A04 = r9.A04();
        boolean z = true;
        if (!(i >= 2 && A04.A01(i - 2) == -1 && A04.A01(i - 1) == -39)) {
            z = false;
        }
        if (z) {
            bArr = null;
        } else {
            bArr = DalvikPurgeableDecoder.EOI;
        }
        C10021qp A042 = r9.A04();
        boolean z2 = false;
        if (i <= A042.A02()) {
            z2 = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z2));
        AnonymousClass1rP r2 = this.A00;
        int i2 = i + 2;
        AnonymousClass1qa A01 = AnonymousClass1qa.A01(r2.A01.get(i2), r2.A00);
        try {
            byte[] bArr2 = (byte[]) A01.A04();
            A042.A03(0, bArr2, 0, i);
            if (bArr != null) {
                bArr2[i] = -1;
                bArr2[i + 1] = -39;
                i = i2;
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, i, options);
            AnonymousClass0KU.A02(decodeByteArray, "BitmapFactory returned null");
            return decodeByteArray;
        } finally {
            if (A01 != null) {
                A01.close();
            }
        }
    }
}
