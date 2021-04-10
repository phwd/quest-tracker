package X;

/* renamed from: X.Xe  reason: case insensitive filesystem */
public final class C0179Xe {
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

    public final void A02(C0182Xh... xhArr) {
        if (this.A03) {
            int length = xhArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = xhArr[i].A00;
            }
            A00(strArr);
            return;
        }
        throw new IllegalStateException("no cipher suites for cleartext connections");
    }

    public final void A03(XH... xhArr) {
        if (this.A03) {
            int length = xhArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = xhArr[i].javaName;
            }
            A01(strArr);
            return;
        }
        throw new IllegalStateException("no TLS versions for cleartext connections");
    }

    public C0179Xe(C0178Xd xd) {
        this.A03 = xd.A01;
        this.A00 = xd.A02;
        this.A01 = xd.A03;
        this.A02 = xd.A00;
    }

    public C0179Xe(boolean z) {
        this.A03 = z;
    }
}
