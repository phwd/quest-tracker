package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1HN  reason: invalid class name */
public final class AnonymousClass1HN implements AbstractC01080Pu {
    public final int A00;

    @Override // X.AbstractC01080Pu
    public final AbstractC01070Pt createImageTranscoder(AnonymousClass0Oj r3, boolean z) {
        return new AnonymousClass1i9(z, this.A00);
    }

    public AnonymousClass1HN(int i) {
        this.A00 = i;
    }
}
