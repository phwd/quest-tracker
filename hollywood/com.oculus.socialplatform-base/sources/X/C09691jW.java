package X;

import android.graphics.Bitmap;

/* renamed from: X.1jW  reason: invalid class name and case insensitive filesystem */
public final class C09691jW implements AnonymousClass0PW {
    public final AnonymousClass0PW A00 = new AnonymousClass1jX(this);
    public final AnonymousClass1i6 A01;

    public final C002305g A00(AnonymousClass0PZ r6, AnonymousClass0PI r7) {
        AbstractC00820Ju<Bitmap> decodeFromEncodedImageWithColorSpace = this.A01.decodeFromEncodedImageWithColorSpace(r6, r7.A02, null, null);
        try {
            C03410mW r3 = C03410mW.A03;
            AnonymousClass0PZ.A06(r6);
            int i = r6.A02;
            AnonymousClass0PZ.A06(r6);
            return new C002305g(decodeFromEncodedImageWithColorSpace, r3, i, r6.A00);
        } finally {
            decodeFromEncodedImageWithColorSpace.close();
        }
    }

    public C09691jW(AnonymousClass1i6 r2) {
        this.A01 = r2;
    }

    @Override // X.AnonymousClass0PW
    public final AnonymousClass0VM A2W(AnonymousClass0PZ r3, int i, C03410mW r5, AnonymousClass0PI r6) {
        AnonymousClass0PZ.A06(r3);
        AnonymousClass0Oj r1 = r3.A07;
        if (r1 == null || r1 == AnonymousClass0Oj.A01) {
            r3.A07 = C00990Ok.A00(r3.A0A());
        }
        return this.A00.A2W(r3, i, r5, r6);
    }
}
