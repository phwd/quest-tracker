package X;

/* renamed from: X.bX  reason: case insensitive filesystem */
public final class C0534bX {
    public String[] A00;
    public String[] A01;
    public boolean A02;
    public boolean A03;

    public final void A00(String... strArr) {
        if (!this.A03) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        } else if (strArr.length != 0) {
            this.A00 = (String[]) strArr.clone();
        } else {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
    }

    public final void A01(String... strArr) {
        if (!this.A03) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        } else if (strArr.length != 0) {
            this.A01 = (String[]) strArr.clone();
        } else {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
    }

    public C0534bX(C0535bY bYVar) {
        this.A03 = bYVar.A01;
        this.A00 = bYVar.A02;
        this.A01 = bYVar.A03;
        this.A02 = bYVar.A00;
    }

    public C0534bX(boolean z) {
        this.A03 = z;
    }
}
