package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* renamed from: X.1g9  reason: invalid class name */
public final class AnonymousClass1g9 extends AbstractC08591et<AnonymousClass1gA> implements AbstractC08111dz {
    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<AnonymousClass1gA> A4o() {
        return AnonymousClass1gA.class;
    }

    @Override // X.AnonymousClass1fR
    public final void A8u() {
        AnonymousClass1hX r0;
        AnonymousClass1hX r02;
        AnonymousClass1hX r03;
        AnonymousClass1gA r1 = (AnonymousClass1gA) this.A00;
        r1.stop();
        r1.A03 = true;
        AnonymousClass1g5 r3 = r1.A09.A00;
        r3.A0G.clear();
        Bitmap bitmap = r3.A03;
        if (bitmap != null) {
            r3.A0F.A8l(bitmap);
            r3.A03 = null;
        }
        r3.A09 = false;
        AnonymousClass1gV r12 = r3.A05;
        if (r12 != null) {
            r3.A0D.clear(r12);
            r3.A05 = null;
        }
        AnonymousClass1gV r13 = r3.A06;
        if (r13 != null) {
            r3.A0D.clear(r13);
            r3.A06 = null;
        }
        AnonymousClass1gV r14 = r3.A07;
        if (r14 != null) {
            r3.A0D.clear(r14);
            r3.A07 = null;
        }
        AnonymousClass1gD r2 = r3.A0E;
        r2.A07 = null;
        byte[] bArr = r2.A0C;
        if (!(bArr == null || (r03 = r2.A0I.A01) == null)) {
            r03.A05(bArr);
        }
        int[] iArr = r2.A0G;
        if (!(iArr == null || (r02 = r2.A0I.A01) == null)) {
            r02.A05(iArr);
        }
        Bitmap bitmap2 = r2.A06;
        if (bitmap2 != null) {
            r2.A0I.A00.A8l(bitmap2);
        }
        r2.A06 = null;
        r2.A09 = null;
        r2.A08 = null;
        byte[] bArr2 = r2.A0B;
        if (!(bArr2 == null || (r0 = r2.A0I.A01) == null)) {
            r0.A05(bArr2);
        }
        r3.A08 = true;
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        AnonymousClass1g5 r3 = ((AnonymousClass1gA) this.A00).A09.A00;
        AnonymousClass1gD r2 = r3.A0E;
        return r2.A09.limit() + r2.A0C.length + (r2.A0G.length << 2) + r3.A00;
    }

    public AnonymousClass1g9(AnonymousClass1gA r1) {
        super(r1);
    }
}
