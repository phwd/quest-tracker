package X;

import java.io.IOException;

/* renamed from: X.0dh  reason: invalid class name */
public final class AnonymousClass0dh<T> extends AnonymousClass13Y<T> {
    public AnonymousClass13Y<T> A00;
    public final AnonymousClass13N A01;
    public final AnonymousClass13Z A02;
    public final AnonymousClass14H<T> A03;
    public final AnonymousClass13Q<T> A04;
    public final AnonymousClass0dh<T>.GsonContextImpl A05 = new C01340dj(this);

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw new X.AnonymousClass0eR(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        throw new X.AnonymousClass0eV(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        throw new X.AnonymousClass0eR(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        r3 = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028 A[ExcHandler: NumberFormatException (r1v5 'e' java.lang.NumberFormatException A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f A[ExcHandler: IOException (r1v4 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036 A[ExcHandler: 14M (r1v3 'e' X.14M A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0047 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0049  */
    @Override // X.AnonymousClass13Y
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T A02(X.AnonymousClass14I r6) throws java.io.IOException {
        /*
            r5 = this;
            X.13Q<T> r4 = r5.A04
            if (r4 != 0) goto L_0x0019
            X.13Y<T> r0 = r5.A00
            if (r0 != 0) goto L_0x0014
            X.13N r2 = r5.A01
            X.13Z r1 = r5.A02
            X.14H<T> r0 = r5.A03
            X.13Y r0 = r2.A02(r1, r0)
            r5.A00 = r0
        L_0x0014:
            java.lang.Object r0 = r0.A02(r6)
            return r0
        L_0x0019:
            r6.A0G()     // Catch:{ EOFException -> 0x003d, 14M -> 0x0036, IOException -> 0x002f, NumberFormatException -> 0x0028 }
            r3 = 0
            X.13Y<X.13R> r0 = X.AnonymousClass14E.A0H     // Catch:{ EOFException -> 0x0026, 14M -> 0x0036, IOException -> 0x002f, NumberFormatException -> 0x0028 }
            java.lang.Object r2 = r0.A02(r6)     // Catch:{ EOFException -> 0x0026, 14M -> 0x0036, IOException -> 0x002f, NumberFormatException -> 0x0028 }
            X.13R r2 = (X.AnonymousClass13R) r2     // Catch:{ EOFException -> 0x0026, 14M -> 0x0036, IOException -> 0x002f, NumberFormatException -> 0x0028 }
            goto L_0x0043
        L_0x0026:
            r1 = move-exception
            goto L_0x003f
        L_0x0028:
            r1 = move-exception
            X.0eR r0 = new X.0eR
            r0.<init>(r1)
            throw r0
        L_0x002f:
            r1 = move-exception
            X.0eV r0 = new X.0eV
            r0.<init>(r1)
            throw r0
        L_0x0036:
            r1 = move-exception
            X.0eR r0 = new X.0eR
            r0.<init>(r1)
            throw r0
        L_0x003d:
            r1 = move-exception
            r3 = 1
        L_0x003f:
            if (r3 == 0) goto L_0x0054
            X.0eU r2 = X.AnonymousClass0eU.A00
        L_0x0043:
            boolean r0 = r2 instanceof X.AnonymousClass0eU
            if (r0 == 0) goto L_0x0049
            r0 = 0
            return r0
        L_0x0049:
            X.14H<T> r0 = r5.A03
            java.lang.reflect.Type r1 = r0.A02
            X.0dh<T>$GsonContextImpl r0 = r5.A05
            java.lang.Object r0 = r4.deserialize(r2, r1, r0)
            return r0
        L_0x0054:
            X.0eR r0 = new X.0eR
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0dh.A02(X.14I):java.lang.Object");
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, T t) throws IOException {
        AnonymousClass13Y<T> r0 = this.A00;
        if (r0 == null) {
            r0 = this.A01.A02(this.A02, this.A03);
            this.A00 = r0;
        }
        r0.A03(r4, t);
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/gson/JsonSerializer<TT;>;LX/13Q<TT;>;LX/13N;LX/14H<TT;>;LX/13Z;)V */
    public AnonymousClass0dh(AnonymousClass13Q r2, AnonymousClass13N r3, AnonymousClass14H r4, AnonymousClass13Z r5) {
        this.A04 = r2;
        this.A01 = r3;
        this.A03 = r4;
        this.A02 = r5;
    }
}
