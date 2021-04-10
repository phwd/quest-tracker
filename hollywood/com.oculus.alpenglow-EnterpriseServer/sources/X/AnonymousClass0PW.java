package X;

/* renamed from: X.0PW  reason: invalid class name */
public final class AnonymousClass0PW extends AbstractC05650kC {
    public final C06020lp A00;
    public final AnonymousClass0Od A01;

    @Override // X.AbstractC05650kC
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

    @Override // X.AbstractC05650kC
    public final C05820lT A01() {
        String A002 = this.A00.A00("Content-Type");
        if (A002 != null) {
            return C05820lT.A00(A002);
        }
        return null;
    }

    @Override // X.AbstractC05650kC
    public final AnonymousClass0Od A02() {
        return this.A01;
    }

    public AnonymousClass0PW(C06020lp r1, AnonymousClass0Od r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
