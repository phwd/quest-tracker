package X;

import java.io.IOException;

/* renamed from: X.Sy  reason: case insensitive filesystem */
public final class C0163Sy<T> extends AbstractC0131Ob<T> {
    public AbstractC0131Ob<T> A00;
    public final HY A01;
    public final AbstractC0132Os A02;
    public final lQ<T> A03;
    public final Lj<T> A04;
    public final OO<T> A05;
    public final C0163Sy<T>.GsonContextImpl A06 = new T0(this);

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw new X.U4(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        throw new X.U0(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028 A[ExcHandler: IOException (r1v4 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f A[ExcHandler: pz | NumberFormatException (r1v3 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:7:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0042  */
    @Override // X.AbstractC0131Ob
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T A02(X.lk r6) throws java.io.IOException {
        /*
            r5 = this;
            X.Lj<T> r4 = r5.A04
            if (r4 != 0) goto L_0x0019
            X.Ob<T> r0 = r5.A00
            if (r0 != 0) goto L_0x0014
            X.HY r2 = r5.A01
            X.Os r1 = r5.A02
            X.lQ<T> r0 = r5.A03
            X.Ob r0 = r2.A03(r1, r0)
            r5.A00 = r0
        L_0x0014:
            java.lang.Object r0 = r0.A02(r6)
            return r0
        L_0x0019:
            r6.A0G()     // Catch:{ EOFException -> 0x0036, pz | NumberFormatException -> 0x002f, IOException -> 0x0028 }
            r3 = 0
            X.Ob<X.M4> r0 = X.C0433hz.A0H     // Catch:{ EOFException -> 0x0026, pz | NumberFormatException -> 0x002f, IOException -> 0x0028, pz | NumberFormatException -> 0x002f }
            java.lang.Object r2 = r0.A02(r6)     // Catch:{ EOFException -> 0x0026, pz | NumberFormatException -> 0x002f, IOException -> 0x0028, pz | NumberFormatException -> 0x002f }
            X.M4 r2 = (X.M4) r2     // Catch:{ EOFException -> 0x0026, pz | NumberFormatException -> 0x002f, IOException -> 0x0028, pz | NumberFormatException -> 0x002f }
            goto L_0x003c
        L_0x0026:
            r1 = move-exception
            goto L_0x0038
        L_0x0028:
            r1 = move-exception
            X.U4 r0 = new X.U4
            r0.<init>(r1)
            throw r0
        L_0x002f:
            r1 = move-exception
            X.U0 r0 = new X.U0
            r0.<init>(r1)
            throw r0
        L_0x0036:
            r1 = move-exception
            r3 = 1
        L_0x0038:
            if (r3 == 0) goto L_0x004d
            X.U3 r2 = X.U3.A00
        L_0x003c:
            boolean r0 = r2 instanceof X.U3
            if (r0 == 0) goto L_0x0042
            r0 = 0
            return r0
        L_0x0042:
            X.lQ<T> r0 = r5.A03
            java.lang.reflect.Type r1 = r0.type
            X.Sy<T>$GsonContextImpl r0 = r5.A06
            java.lang.Object r0 = r4.A1p(r2, r1, r0)
            return r0
        L_0x004d:
            X.U0 r0 = new X.U0
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0163Sy.A02(X.lk):java.lang.Object");
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T t) throws IOException {
        AbstractC0131Ob<T> ob;
        OO<T> oo = this.A05;
        if (oo == null) {
            ob = this.A00;
            if (ob == null) {
                ob = this.A01.A03(this.A02, this.A03);
                this.A00 = ob;
            }
        } else if (t == null) {
            mmVar.A0B();
            return;
        } else {
            t = (T) oo.A4v(t, this.A03.type, this.A06);
            ob = (AbstractC0131Ob<T>) C0433hz.A0H;
        }
        ob.A03(mmVar, t);
    }

    public C0163Sy(OO<T> oo, Lj<T> lj, HY hy, lQ<T> lQVar, AbstractC0132Os os) {
        this.A05 = oo;
        this.A04 = lj;
        this.A01 = hy;
        this.A03 = lQVar;
        this.A02 = os;
    }
}
