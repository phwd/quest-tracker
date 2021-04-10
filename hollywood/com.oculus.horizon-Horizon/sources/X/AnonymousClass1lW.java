package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lW  reason: invalid class name */
public final class AnonymousClass1lW implements AnonymousClass1lX {
    public final int A00;

    @Override // X.AnonymousClass1lX
    public final AnonymousClass1s3 createImageTranscoder(AnonymousClass1tL r3, boolean z) {
        return new AnonymousClass1qY(z, this.A00);
    }

    public AnonymousClass1lW(int i) {
        this.A00 = i;
    }
}
