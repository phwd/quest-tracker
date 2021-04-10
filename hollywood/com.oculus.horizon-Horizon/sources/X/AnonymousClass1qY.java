package X;

import javax.annotation.Nullable;

/* renamed from: X.1qY  reason: invalid class name */
public final class AnonymousClass1qY implements AnonymousClass1s3 {
    public final int A00;
    public final boolean A01;

    @Override // X.AnonymousClass1s3
    public final String getIdentifier() {
        return "SimpleImageTranscoder";
    }

    @Override // X.AnonymousClass1s3
    public final boolean canResize(AnonymousClass1qQ r3, @Nullable AnonymousClass1pN r4, @Nullable AnonymousClass1p9 r5) {
        if (!this.A01 || AnonymousClass1rR.A00(r5, r3, this.A00) <= 1) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1s3
    public final boolean canTranscode(AnonymousClass1tL r3) {
        if (r3 == C10191ri.A03 || r3 == C10191ri.A05) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ae A[SYNTHETIC, Splitter:B:44:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b1 A[Catch:{ OutOfMemoryError -> 0x00e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e1 A[Catch:{ OutOfMemoryError -> 0x00e8 }] */
    @Override // X.AnonymousClass1s3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C10371se transcode(X.AnonymousClass1qQ r16, java.io.OutputStream r17, @javax.annotation.Nullable X.AnonymousClass1pN r18, @javax.annotation.Nullable X.AnonymousClass1p9 r19, @javax.annotation.Nullable X.AnonymousClass1tL r20, @javax.annotation.Nullable java.lang.Integer r21) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qY.transcode(X.1qQ, java.io.OutputStream, X.1pN, X.1p9, X.1tL, java.lang.Integer):X.1se");
    }

    public AnonymousClass1qY(boolean z, int i) {
        this.A01 = z;
        this.A00 = i;
    }
}
