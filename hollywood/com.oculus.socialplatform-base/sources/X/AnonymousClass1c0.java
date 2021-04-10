package X;

import androidx.annotation.NonNull;

/* renamed from: X.1c0  reason: invalid class name */
public final class AnonymousClass1c0<Data> implements AbstractC07011bT<byte[], Data> {
    public final AbstractC07341c1<Data> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull byte[] bArr, int i, int i2, @NonNull AnonymousClass1cO r7) {
        byte[] bArr2 = bArr;
        return new C07091bb(new AnonymousClass1S3(bArr2), new C07331bz(bArr2, this.A00));
    }

    public AnonymousClass1c0(AbstractC07341c1<Data> r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull byte[] bArr) {
        return true;
    }
}
