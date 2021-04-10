package X;

/* renamed from: X.0wg  reason: invalid class name and case insensitive filesystem */
public final class C08500wg {
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

    public final void A02(C08530wj... r5) {
        if (this.A03) {
            int length = r5.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = r5[i].A00;
            }
            A00(strArr);
            return;
        }
        throw new IllegalStateException("no cipher suites for cleartext connections");
    }

    public final void A03(EnumC08190w9... r5) {
        if (this.A03) {
            int length = r5.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = r5[i].javaName;
            }
            A01(strArr);
            return;
        }
        throw new IllegalStateException("no TLS versions for cleartext connections");
    }

    public C08500wg(C08490wf r2) {
        this.A03 = r2.A01;
        this.A00 = r2.A02;
        this.A01 = r2.A03;
        this.A02 = r2.A00;
    }

    public C08500wg(boolean z) {
        this.A03 = z;
    }
}
