package com.facebook.imagepipeline.platform;

import X.AbstractC00820Ju;
import X.AnonymousClass0JV;
import X.C00740Ii;
import X.C09491iJ;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@TargetApi(19)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder {
    public final C09491iJ A00;

    @DoNotStrip
    public KitKatPurgeableDecoder(C09491iJ r1) {
        this.A00 = r1;
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> r6, BitmapFactory.Options options) {
        AnonymousClass0JV A06 = r6.A06();
        int size = A06.size();
        C09491iJ r2 = this.A00;
        AbstractC00820Ju A01 = AbstractC00820Ju.A01(r2.A01.get(size), r2.A00);
        try {
            byte[] bArr = (byte[]) A01.A06();
            A06.read(0, bArr, 0, size);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, size, options);
            C00740Ii.A02(decodeByteArray, "BitmapFactory returned null");
            return decodeByteArray;
        } finally {
            AbstractC00820Ju.A03(A01);
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    public final Bitmap decodeJPEGByteArrayAsPurgeable(AbstractC00820Ju<AnonymousClass0JV> r9, int i, BitmapFactory.Options options) {
        byte[] bArr;
        AnonymousClass0JV A06 = r9.A06();
        boolean z = true;
        if (!(i >= 2 && A06.read(i - 2) == -1 && A06.read(i - 1) == -39)) {
            z = false;
        }
        if (z) {
            bArr = null;
        } else {
            bArr = DalvikPurgeableDecoder.EOI;
        }
        AnonymousClass0JV A062 = r9.A06();
        boolean z2 = false;
        if (i <= A062.size()) {
            z2 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z2));
        C09491iJ r2 = this.A00;
        int i2 = i + 2;
        AbstractC00820Ju A01 = AbstractC00820Ju.A01(r2.A01.get(i2), r2.A00);
        try {
            byte[] bArr2 = (byte[]) A01.A06();
            A062.read(0, bArr2, 0, i);
            if (bArr != null) {
                bArr2[i] = -1;
                bArr2[i + 1] = -39;
                i = i2;
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, i, options);
            C00740Ii.A02(decodeByteArray, "BitmapFactory returned null");
            return decodeByteArray;
        } finally {
            AbstractC00820Ju.A03(A01);
        }
    }
}
