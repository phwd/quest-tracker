package X;

/* renamed from: X.1qY  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11071qY extends AbstractC11161qj {
    public int A00;
    public int A01 = 0;
    public String A02;
    public AnonymousClass05A[] A03 = null;

    public AnonymousClass05A[] getPathData() {
        return this.A03;
    }

    public String getPathName() {
        return this.A02;
    }

    public void setPathData(AnonymousClass05A[] r6) {
        AnonymousClass05A[] r4 = this.A03;
        if (!AnonymousClass05B.A00(r4, r6)) {
            this.A03 = AnonymousClass05B.A02(r6);
            return;
        }
        for (int i = 0; i < r6.length; i++) {
            r4[i].A00 = r6[i].A00;
            for (int i2 = 0; i2 < r6[i].A01.length; i2++) {
                r4[i].A01[i2] = r6[i].A01[i2];
            }
        }
    }

    public AbstractC11071qY() {
    }

    public AbstractC11071qY(AbstractC11071qY r2) {
        this.A02 = r2.A02;
        this.A00 = r2.A00;
        this.A03 = AnonymousClass05B.A02(r2.A03);
    }
}
