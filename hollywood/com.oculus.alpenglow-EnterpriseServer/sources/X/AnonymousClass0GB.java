package X;

import androidx.room.ColumnInfo;

/* renamed from: X.0GB  reason: invalid class name */
public final class AnonymousClass0GB {
    @ColumnInfo.SQLiteTypeAffinity
    public final int A00;
    public final int A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final boolean A05;
    public final int A06;

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        if (r4 != 0) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r7) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0GB.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode = ((this.A03.hashCode() * 31) + this.A00) * 31;
        int i = 1237;
        if (this.A05) {
            i = 1231;
        }
        return ((hashCode + i) * 31) + this.A01;
    }

    public final String toString() {
        return "Column{name='" + this.A03 + '\'' + ", type='" + this.A04 + '\'' + ", affinity='" + this.A00 + '\'' + ", notNull=" + this.A05 + ", primaryKeyPosition=" + this.A01 + ", defaultValue='" + this.A02 + '\'' + '}';
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        if (r0 != false) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0GB(java.lang.String r3, java.lang.String r4, boolean r5, int r6, java.lang.String r7, int r8) {
        /*
            r2 = this;
            r2.<init>()
            r2.A03 = r3
            r2.A04 = r4
            r2.A05 = r5
            r2.A01 = r6
            if (r4 == 0) goto L_0x0060
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r1 = r4.toUpperCase(r0)
            java.lang.String r0 = "INT"
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x0023
            r1 = 3
        L_0x001c:
            r2.A00 = r1
            r2.A02 = r7
            r2.A06 = r8
            return
        L_0x0023:
            java.lang.String r0 = "CHAR"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x005e
            java.lang.String r0 = "CLOB"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x005e
            java.lang.String r0 = "TEXT"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x005e
            java.lang.String r0 = "BLOB"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x0060
            java.lang.String r0 = "REAL"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x005c
            java.lang.String r0 = "FLOA"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L_0x005c
            java.lang.String r0 = "DOUB"
            boolean r0 = r1.contains(r0)
            r1 = 1
            if (r0 == 0) goto L_0x001c
        L_0x005c:
            r1 = 4
            goto L_0x001c
        L_0x005e:
            r1 = 2
            goto L_0x001c
        L_0x0060:
            r1 = 5
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0GB.<init>(java.lang.String, java.lang.String, boolean, int, java.lang.String, int):void");
    }
}
