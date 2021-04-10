package java.util;

public class IllformedLocaleException extends RuntimeException {
    private static final long serialVersionUID = -5245986824925681401L;
    private int _errIdx;

    public IllformedLocaleException() {
        this._errIdx = -1;
    }

    public IllformedLocaleException(String message) {
        super(message);
        this._errIdx = -1;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IllformedLocaleException(java.lang.String r4, int r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            if (r5 >= 0) goto L_0x000d
            java.lang.String r1 = ""
            goto L_0x0023
        L_0x000d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " [at index "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = "]"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0023:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            r0 = -1
            r3._errIdx = r0
            r3._errIdx = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.IllformedLocaleException.<init>(java.lang.String, int):void");
    }

    public int getErrorIndex() {
        return this._errIdx;
    }
}
