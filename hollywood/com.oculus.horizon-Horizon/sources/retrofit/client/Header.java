package retrofit.client;

import X.AnonymousClass006;

public final class Header {
    public final String name;
    public final String value;

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r1.equals(r5.name) == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r3 = 1
            if (r4 == r5) goto L_0x0034
            r2 = 0
            if (r5 == 0) goto L_0x001e
            java.lang.Class r1 = r4.getClass()
            java.lang.Class r0 = r5.getClass()
            if (r1 != r0) goto L_0x001e
            retrofit.client.Header r5 = (retrofit.client.Header) r5
            java.lang.String r1 = r4.name
            if (r1 == 0) goto L_0x001f
            java.lang.String r0 = r5.name
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0024
        L_0x001e:
            return r2
        L_0x001f:
            java.lang.String r0 = r5.name
            if (r0 == 0) goto L_0x0024
            return r2
        L_0x0024:
            java.lang.String r1 = r4.value
            java.lang.String r0 = r5.value
            if (r1 == 0) goto L_0x0031
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0034
            return r2
        L_0x0031:
            if (r0 == 0) goto L_0x0034
            return r2
        L_0x0034:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.client.Header.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i;
        String str = this.name;
        int i2 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        String str2 = this.value;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return i3 + i2;
    }

    public String toString() {
        String str = this.name;
        if (str == null) {
            str = "";
        }
        String str2 = this.value;
        if (str2 == null) {
            str2 = "";
        }
        return AnonymousClass006.A07(str, ": ", str2);
    }

    public Header(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
