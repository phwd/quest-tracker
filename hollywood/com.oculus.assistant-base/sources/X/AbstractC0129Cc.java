package X;

/* renamed from: X.Cc  reason: case insensitive filesystem */
public abstract class AbstractC0129Cc implements Comparable {
    public int A00;
    public String A01;
    public final AbstractC0129Cc A02;
    public final String A03;

    private final String A00() {
        String str = this.A01;
        if (str == null) {
            AbstractC0129Cc cc = this.A02;
            String str2 = this.A03;
            if (cc == null || cc.A00() == null) {
                str = str2;
            } else {
                str = cc.A00();
                if (str2 != null) {
                    str = str.concat(str2);
                }
            }
            this.A01 = str;
        }
        return str;
    }

    public final AbstractC0129Cc A01(String str) {
        if (!(this instanceof ZW)) {
            boolean z = ((C0913og) this) instanceof ZW;
            if (z || z) {
                return new ZW(this, str, false);
            }
            return new C0913og(this, str);
        }
        ZW zw = (ZW) this;
        return new ZW(zw, str, zw.A00);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        if (r4.A03 == null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(X.AbstractC0129Cc r4) {
        /*
            r3 = this;
            java.lang.String r0 = r3.A01
            r2 = 1
            if (r0 != 0) goto L_0x002c
            java.lang.String r0 = r4.A01
            if (r0 != 0) goto L_0x002c
            X.Cc r1 = r3.A02
            if (r1 != 0) goto L_0x0023
            X.Cc r0 = r4.A02
            if (r0 != 0) goto L_0x002c
        L_0x0011:
            java.lang.String r1 = r3.A03
            if (r1 != 0) goto L_0x001a
            java.lang.String r0 = r4.A03
            if (r0 != 0) goto L_0x002c
        L_0x0019:
            return r2
        L_0x001a:
            java.lang.String r0 = r4.A03
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x002c
            return r2
        L_0x0023:
            X.Cc r0 = r4.A02
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x002c
            goto L_0x0011
        L_0x002c:
            java.lang.String r0 = r3.A00()
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = r4.A00()
            if (r0 == 0) goto L_0x0019
            r2 = 0
            return r2
        L_0x003a:
            java.lang.String r1 = r3.A00()
            java.lang.String r0 = r4.A00()
            boolean r2 = r1.equals(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC0129Cc.A02(X.Cc):boolean");
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return A00().compareTo(((AbstractC0129Cc) obj).A00());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return A02((AbstractC0129Cc) obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode() {
        /*
            r5 = this;
            int r1 = r5.A00
            if (r1 != 0) goto L_0x0029
            java.lang.String r4 = r5.A01
            r3 = 0
            if (r4 != 0) goto L_0x0015
            X.Cc r0 = r5.A02
            if (r0 == 0) goto L_0x0025
            int r1 = r0.hashCode()
        L_0x0011:
            java.lang.String r4 = r5.A03
            if (r4 == 0) goto L_0x0027
        L_0x0015:
            int r2 = r4.length()
        L_0x0019:
            if (r3 >= r2) goto L_0x0027
            int r1 = r1 * 31
            char r0 = r4.charAt(r3)
            int r1 = r1 + r0
            int r3 = r3 + 1
            goto L_0x0019
        L_0x0025:
            r1 = 0
            goto L_0x0011
        L_0x0027:
            r5.A00 = r1
        L_0x0029:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC0129Cc.hashCode():int");
    }

    public final String toString() {
        return A00();
    }

    public AbstractC0129Cc(AbstractC0129Cc cc, String str) {
        boolean z = true;
        CT.A00(cc != null);
        CT.A00(str == null ? false : z);
        this.A02 = cc;
        this.A03 = str;
    }

    public AbstractC0129Cc() {
        this.A02 = null;
        this.A03 = "/";
        this.A01 = "/";
    }
}
