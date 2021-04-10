package X;

import android.graphics.Bitmap;

/* renamed from: X.1jX  reason: invalid class name */
public class AnonymousClass1jX implements AnonymousClass0PW {
    public final /* synthetic */ C09691jW A00;

    @Override // X.AnonymousClass0PW
    public final AnonymousClass0VM A2W(AnonymousClass0PZ r10, int i, C03410mW r12, AnonymousClass0PI r13) {
        AnonymousClass0PZ.A06(r10);
        AnonymousClass0Oj r1 = r10.A07;
        if (r1 == AnonymousClass0Oi.A05) {
            AbstractC00820Ju<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = this.A00.A01.decodeJPEGFromEncodedImageWithColorSpace(r10, r13.A02, null, i, null);
            try {
                AnonymousClass0PZ.A06(r10);
                int i2 = r10.A02;
                AnonymousClass0PZ.A06(r10);
                return new C002305g(decodeJPEGFromEncodedImageWithColorSpace, r12, i2, r10.A00);
            } finally {
                decodeJPEGFromEncodedImageWithColorSpace.close();
            }
        } else if (r1 == AnonymousClass0Oi.A02) {
            C09691jW r2 = this.A00;
            AnonymousClass0PZ.A06(r10);
            if (r10.A05 != -1) {
                AnonymousClass0PZ.A06(r10);
                if (r10.A01 != -1) {
                    return r2.A00(r10, r13);
                }
            }
            throw new AnonymousClass1jY("image width or height is incorrect", r10);
        } else if (r1 == AnonymousClass0Oi.A07) {
            throw new NullPointerException("decode");
        } else if (r1 != AnonymousClass0Oj.A01) {
            return this.A00.A00(r10, r13);
        } else {
            throw new AnonymousClass1jY("unknown image format", r10);
        }
    }

    public AnonymousClass1jX(C09691jW r1) {
        this.A00 = r1;
    }
}
