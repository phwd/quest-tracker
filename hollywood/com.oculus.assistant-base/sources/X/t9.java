package X;

import java.io.IOException;

public final class t9 implements AbstractC0608cr {
    public final /* synthetic */ t7 A00;
    public final /* synthetic */ AbstractC0608cr A01;

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r9, long j) {
        long j2 = j;
        C0611cu.A00(r9.A00, 0, j2);
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                C0606cp cpVar = r9.A01;
                while (true) {
                    j3 += (long) (cpVar.A00 - cpVar.A01);
                    if (j3 < j2) {
                        cpVar = cpVar.A02;
                        if (j3 >= 65536) {
                            break;
                        }
                    } else {
                        j3 = j2;
                        break;
                    }
                }
                t7 t7Var = this.A00;
                t7Var.A08();
                try {
                    this.A01.A5k(r9, j3);
                    j2 -= j3;
                    t7Var.A09(true);
                } catch (IOException e) {
                    if (t7Var.A0A()) {
                        throw t7Var.A07(e);
                    }
                    throw e;
                } catch (Throwable th) {
                    t7Var.A09(false);
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    public t9(t7 t7Var, AbstractC0608cr crVar) {
        this.A00 = t7Var;
        this.A01 = crVar;
    }

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable
    public final void close() {
        t7 t7Var = this.A00;
        t7Var.A08();
        try {
            this.A01.close();
            t7Var.A09(true);
        } catch (IOException e) {
            if (t7Var.A0A()) {
                throw t7Var.A07(e);
            }
            throw e;
        } catch (Throwable th) {
            t7Var.A09(false);
            throw th;
        }
    }

    @Override // X.AbstractC0608cr, java.io.Flushable
    public final void flush() {
        t7 t7Var = this.A00;
        t7Var.A08();
        try {
            this.A01.flush();
            t7Var.A09(true);
        } catch (IOException e) {
            if (t7Var.A0A()) {
                throw t7Var.A07(e);
            }
            throw e;
        } catch (Throwable th) {
            t7Var.A09(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.sink(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A00;
    }
}
