package X;

public final class e0 {
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

    public final void A02(e3... e3VarArr) {
        if (this.A03) {
            int length = e3VarArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = e3VarArr[i].A00;
            }
            A00(strArr);
            return;
        }
        throw new IllegalStateException("no cipher suites for cleartext connections");
    }

    public final void A03(EnumC0356dd... ddVarArr) {
        if (this.A03) {
            int length = ddVarArr.length;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = ddVarArr[i].javaName;
            }
            A01(strArr);
            return;
        }
        throw new IllegalStateException("no TLS versions for cleartext connections");
    }

    public e0(C0376dz dzVar) {
        this.A03 = dzVar.A01;
        this.A00 = dzVar.A02;
        this.A01 = dzVar.A03;
        this.A02 = dzVar.A00;
    }

    public e0(boolean z) {
        this.A03 = z;
    }
}
