package java.io;

public class FileNotFoundException extends IOException {
    private static final long serialVersionUID = -897856973823710492L;

    public FileNotFoundException() {
    }

    public FileNotFoundException(String s) {
        super(s);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private FileNotFoundException(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            if (r5 != 0) goto L_0x000d
            java.lang.String r1 = ""
            goto L_0x0023
        L_0x000d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " ("
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x0023:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.FileNotFoundException.<init>(java.lang.String, java.lang.String):void");
    }
}
