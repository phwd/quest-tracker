package X;

import androidx.annotation.NonNull;

/* renamed from: X.1dU  reason: invalid class name */
public final class AnonymousClass1dU implements AnonymousClass1fR<byte[]> {
    public final byte[] A00;

    @Override // X.AnonymousClass1fR
    public final void A8u() {
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<byte[]> A4o() {
        return byte[].class;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass1fR
    @NonNull
    public final byte[] get() {
        return this.A00;
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return this.A00.length;
    }

    public AnonymousClass1dU(byte[] bArr) {
        AnonymousClass1S2.A00(bArr);
        this.A00 = bArr;
    }
}
