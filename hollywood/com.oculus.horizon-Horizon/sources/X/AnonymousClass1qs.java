package X;

import android.graphics.Bitmap;

/* renamed from: X.1qs  reason: invalid class name */
public final class AnonymousClass1qs implements AnonymousClass1tN {
    public final AnonymousClass1tN A00 = new AnonymousClass1r5(this);
    public final AbstractC10231rm A01;

    public final AnonymousClass1qH A00(AnonymousClass1qQ r6, AnonymousClass1r8 r7) {
        AnonymousClass1qa<Bitmap> decodeFromEncodedImageWithColorSpace = this.A01.decodeFromEncodedImageWithColorSpace(r6, r7.A02, null, null);
        try {
            AnonymousClass1tA r3 = AnonymousClass1tA.A03;
            AnonymousClass1qQ.A05(r6);
            int i = r6.A02;
            AnonymousClass1qQ.A05(r6);
            return new AnonymousClass1qH(decodeFromEncodedImageWithColorSpace, r3, i, r6.A00);
        } finally {
            decodeFromEncodedImageWithColorSpace.close();
        }
    }

    public AnonymousClass1qs(AbstractC10231rm r2) {
        this.A01 = r2;
    }

    @Override // X.AnonymousClass1tN
    public final AnonymousClass1q1 A2B(AnonymousClass1qQ r3, int i, AnonymousClass1tA r5, AnonymousClass1r8 r6) {
        AnonymousClass1qQ.A05(r3);
        AnonymousClass1tL r1 = r3.A07;
        if (r1 == null || r1 == AnonymousClass1tL.A01) {
            r3.A07 = C10031qq.A00(r3.A08());
        }
        return this.A00.A2B(r3, i, r5, r6);
    }
}
