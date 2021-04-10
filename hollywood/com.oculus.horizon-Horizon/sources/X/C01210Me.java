package X;

/* renamed from: X.0Me  reason: invalid class name and case insensitive filesystem */
public final class C01210Me extends AbstractC08210wB {
    public final AnonymousClass0wX A00;
    public final AnonymousClass0Lw A01;

    @Override // X.AbstractC08210wB
    public final long A00() {
        String A002 = this.A00.A00("Content-Length");
        if (A002 == null) {
            return -1;
        }
        try {
            return Long.parseLong(A002);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // X.AbstractC08210wB
    public final C08370wR A02() {
        String A002 = this.A00.A00("Content-Type");
        if (A002 != null) {
            return C08370wR.A00(A002);
        }
        return null;
    }

    public C01210Me(AnonymousClass0wX r1, AnonymousClass0Lw r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC08210wB
    public final AnonymousClass0Lw A03() {
        return this.A01;
    }
}
