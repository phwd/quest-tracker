package X;

import android.graphics.Bitmap;

/* renamed from: X.1r5  reason: invalid class name */
public class AnonymousClass1r5 implements AnonymousClass1tN {
    public final /* synthetic */ AnonymousClass1qs A00;

    @Override // X.AnonymousClass1tN
    public final AnonymousClass1q1 A2B(AnonymousClass1qQ r10, int i, AnonymousClass1tA r12, AnonymousClass1r8 r13) {
        String str;
        AnonymousClass1qQ.A05(r10);
        AnonymousClass1tL r1 = r10.A07;
        if (r1 == C10191ri.A05) {
            AnonymousClass1qa<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = this.A00.A01.decodeJPEGFromEncodedImageWithColorSpace(r10, r13.A02, null, i, null);
            try {
                AnonymousClass1qQ.A05(r10);
                int i2 = r10.A02;
                AnonymousClass1qQ.A05(r10);
                return new AnonymousClass1qH(decodeJPEGFromEncodedImageWithColorSpace, r12, i2, r10.A00);
            } finally {
                decodeJPEGFromEncodedImageWithColorSpace.close();
            }
        } else {
            if (r1 == C10191ri.A02) {
                AnonymousClass1qs r2 = this.A00;
                AnonymousClass1qQ.A05(r10);
                if (r10.A05 != -1) {
                    AnonymousClass1qQ.A05(r10);
                    if (r10.A01 != -1) {
                        return r2.A00(r10, r13);
                    }
                }
                str = "image width or height is incorrect";
            } else if (r1 == C10191ri.A07) {
                throw null;
            } else if (r1 != AnonymousClass1tL.A01) {
                return this.A00.A00(r10, r13);
            } else {
                str = "unknown image format";
            }
            throw new AnonymousClass1t5(str, r10);
        }
    }

    public AnonymousClass1r5(AnonymousClass1qs r1) {
        this.A00 = r1;
    }
}
