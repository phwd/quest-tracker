package X;

/* renamed from: X.1B0  reason: invalid class name */
public final class AnonymousClass1B0 {
    public int A00;
    public int A01;
    public int A02;
    public Object A03;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r1 == r6.A01) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r3 = 1
            if (r5 == r6) goto L_0x0028
            boolean r0 = r6 instanceof X.AnonymousClass1B0
            r4 = 0
            if (r0 == 0) goto L_0x0044
            X.1B0 r6 = (X.AnonymousClass1B0) r6
            int r1 = r5.A00
            int r0 = r6.A00
            if (r1 != r0) goto L_0x0044
            r0 = 8
            if (r1 != r0) goto L_0x0029
            int r2 = r5.A01
            int r1 = r5.A02
            int r0 = r2 - r1
            int r0 = java.lang.Math.abs(r0)
            if (r0 != r3) goto L_0x0029
            int r0 = r6.A02
            if (r2 != r0) goto L_0x0029
            int r0 = r6.A01
            if (r1 != r0) goto L_0x0029
        L_0x0028:
            return r3
        L_0x0029:
            int r1 = r5.A01
            int r0 = r6.A01
            if (r1 != r0) goto L_0x0044
            int r1 = r5.A02
            int r0 = r6.A02
            if (r1 != r0) goto L_0x0044
            java.lang.Object r1 = r5.A03
            java.lang.Object r0 = r6.A03
            if (r1 == 0) goto L_0x0042
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0028
            return r4
        L_0x0042:
            if (r0 == 0) goto L_0x0028
        L_0x0044:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1B0.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return (((this.A00 * 31) + this.A02) * 31) + this.A01;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.A00;
        if (i == 1) {
            str = "add";
        } else if (i == 2) {
            str = "rm";
        } else if (i == 4) {
            str = "up";
        } else if (i != 8) {
            str = "??";
        } else {
            str = "mv";
        }
        sb.append(str);
        sb.append(",s:");
        sb.append(this.A02);
        sb.append("c:");
        sb.append(this.A01);
        sb.append(",p:");
        sb.append(this.A03);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass1B0(int i, int i2, int i3, Object obj) {
        this.A00 = i;
        this.A02 = i2;
        this.A01 = i3;
        this.A03 = obj;
    }
}
