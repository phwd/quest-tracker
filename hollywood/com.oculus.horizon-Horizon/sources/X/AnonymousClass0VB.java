package X;

import java.io.IOException;

/* renamed from: X.0VB  reason: invalid class name */
public final class AnonymousClass0VB<T> extends AnonymousClass0yl<T> {
    public AnonymousClass0yl<T> A00;
    public final C08780ya A01;
    public final AbstractC08860ym A02;
    public final C09110zQ<T> A03;
    public final AbstractC08810yd<T> A04;
    public final AnonymousClass0VB<T>.GsonContextImpl A05 = new AnonymousClass0VZ(this);

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw new X.AnonymousClass0c9(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        throw new X.C03080c5(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r3 = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028 A[ExcHandler: IOException (r1v4 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f A[ExcHandler: 0zV | NumberFormatException (r1v3 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0042  */
    @Override // X.AnonymousClass0yl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T A02(X.C09120zR r6) throws java.io.IOException {
        /*
            r5 = this;
            X.0yd<T> r4 = r5.A04
            if (r4 != 0) goto L_0x0019
            X.0yl<T> r0 = r5.A00
            if (r0 != 0) goto L_0x0014
            X.0ya r2 = r5.A01
            X.0ym r1 = r5.A02
            X.0zQ<T> r0 = r5.A03
            X.0yl r0 = r2.A03(r1, r0)
            r5.A00 = r0
        L_0x0014:
            java.lang.Object r0 = r0.A02(r6)
            return r0
        L_0x0019:
            r6.A0G()     // Catch:{ EOFException -> 0x0036, 0zV | NumberFormatException -> 0x002f, IOException -> 0x0028 }
            r3 = 0
            X.0yl<X.0ye> r0 = X.C09080zN.A0H     // Catch:{ EOFException -> 0x0026, 0zV | NumberFormatException -> 0x002f, IOException -> 0x0028, 0zV | NumberFormatException -> 0x002f }
            java.lang.Object r2 = r0.A02(r6)     // Catch:{ EOFException -> 0x0026, 0zV | NumberFormatException -> 0x002f, IOException -> 0x0028, 0zV | NumberFormatException -> 0x002f }
            X.0ye r2 = (X.AbstractC08820ye) r2     // Catch:{ EOFException -> 0x0026, 0zV | NumberFormatException -> 0x002f, IOException -> 0x0028, 0zV | NumberFormatException -> 0x002f }
            goto L_0x003c
        L_0x0026:
            r1 = move-exception
            goto L_0x0038
        L_0x0028:
            r1 = move-exception
            X.0c9 r0 = new X.0c9
            r0.<init>(r1)
            throw r0
        L_0x002f:
            r1 = move-exception
            X.0c5 r0 = new X.0c5
            r0.<init>(r1)
            throw r0
        L_0x0036:
            r1 = move-exception
            r3 = 1
        L_0x0038:
            if (r3 == 0) goto L_0x004d
            X.0c8 r2 = X.AnonymousClass0c8.A00
        L_0x003c:
            boolean r0 = r2 instanceof X.AnonymousClass0c8
            if (r0 == 0) goto L_0x0042
            r0 = 0
            return r0
        L_0x0042:
            X.0zQ<T> r0 = r5.A03
            java.lang.reflect.Type r1 = r0.A02
            X.0VB<T>$GsonContextImpl r0 = r5.A05
            java.lang.Object r0 = r4.A2F(r2, r1, r0)
            return r0
        L_0x004d:
            X.0c5 r0 = new X.0c5
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0VB.A02(X.0zR):java.lang.Object");
    }

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, T t) throws IOException {
        AnonymousClass0yl<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A01.A03(this.A02, this.A03);
            this.A00 = r0;
        }
        r0.A03(r4, t);
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/gson/JsonSerializer<TT;>;LX/0yd<TT;>;LX/0ya;LX/0zQ<TT;>;LX/0ym;)V */
    public AnonymousClass0VB(AbstractC08810yd r2, C08780ya r3, C09110zQ r4, AbstractC08860ym r5) {
        this.A04 = r2;
        this.A01 = r3;
        this.A03 = r4;
        this.A02 = r5;
    }
}
