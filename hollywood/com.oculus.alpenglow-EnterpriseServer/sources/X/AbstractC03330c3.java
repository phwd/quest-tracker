package X;

/* renamed from: X.0c3  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03330c3 extends AnonymousClass0H7 {
    public int A00;
    public int A01 = 0;
    public String A02;
    public AnonymousClass08S[] A03 = null;

    public boolean A03() {
        return false;
    }

    public AnonymousClass08S[] getPathData() {
        return this.A03;
    }

    public String getPathName() {
        return this.A02;
    }

    public void setPathData(AnonymousClass08S[] r7) {
        AnonymousClass08S[] r4;
        int length;
        int length2;
        AnonymousClass08S[] r5 = this.A03;
        if (!(r5 == null || r7 == null || (length = r5.length) != (length2 = r7.length))) {
            for (int i = 0; i < length; i++) {
                if (r5[i].A00 == r7[i].A00 && r5[i].A01.length == r7[i].A01.length) {
                }
            }
            for (int i2 = 0; i2 < length2; i2++) {
                r5[i2].A00 = r7[i2].A00;
                for (int i3 = 0; i3 < r7[i2].A01.length; i3++) {
                    r5[i2].A01[i3] = r7[i2].A01[i3];
                }
            }
            return;
        }
        if (r7 == null) {
            r4 = null;
        } else {
            int length3 = r7.length;
            r4 = new AnonymousClass08S[length3];
            for (int i4 = 0; i4 < length3; i4++) {
                r4[i4] = new AnonymousClass08S(r7[i4]);
            }
        }
        this.A03 = r4;
    }

    public AbstractC03330c3() {
    }

    public AbstractC03330c3(AbstractC03330c3 r7) {
        AnonymousClass08S[] r4;
        this.A02 = r7.A02;
        this.A00 = r7.A00;
        AnonymousClass08S[] r5 = r7.A03;
        if (r5 == null) {
            r4 = null;
        } else {
            int length = r5.length;
            r4 = new AnonymousClass08S[length];
            for (int i = 0; i < length; i++) {
                r4[i] = new AnonymousClass08S(r5[i]);
            }
        }
        this.A03 = r4;
    }
}
