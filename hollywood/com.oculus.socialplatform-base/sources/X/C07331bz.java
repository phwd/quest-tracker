package X;

import androidx.annotation.NonNull;

/* renamed from: X.1bz  reason: invalid class name and case insensitive filesystem */
public class C07331bz<Data> implements AbstractC07051bX<Data> {
    public final AbstractC07341c1<Data> A00;
    public final byte[] A01;

    @Override // X.AbstractC07051bX
    public final void A26() {
    }

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<Data> A3h() {
        return this.A00.A3h();
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r3, @NonNull AnonymousClass1Ry<? super Data> r4) {
        r4.A6x(this.A00.A2H(this.A01));
    }

    public C07331bz(byte[] bArr, AbstractC07341c1<Data> r2) {
        this.A01 = bArr;
        this.A00 = r2;
    }
}
