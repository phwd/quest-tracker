package X;

import java.io.ByteArrayOutputStream;

/* renamed from: X.1nd  reason: invalid class name */
public class AnonymousClass1nd extends ByteArrayOutputStream {
    public final /* synthetic */ AnonymousClass1nc A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1nd(AnonymousClass1nc r1, int i) {
        super(i);
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r5.buf[r4] != 13) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r5 = this;
            int r2 = r5.count
            if (r2 <= 0) goto L_0x000e
            byte[] r0 = r5.buf
            int r4 = r2 + -1
            byte r1 = r0[r4]
            r0 = 13
            if (r1 == r0) goto L_0x000f
        L_0x000e:
            r4 = r2
        L_0x000f:
            byte[] r3 = r5.buf     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            r2 = 0
            X.1nc r0 = r5.A00     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            java.nio.charset.Charset r0 = r0.A04     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            java.lang.String r1 = r0.name()     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            r0.<init>(r3, r2, r4, r1)     // Catch:{ UnsupportedEncodingException -> 0x0020 }
            return r0
        L_0x0020:
            r1 = move-exception
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1nd.toString():java.lang.String");
    }
}
