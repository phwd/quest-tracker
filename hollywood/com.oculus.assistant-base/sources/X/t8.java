package X;

import java.io.IOException;

public final class t8 implements AbstractC0609cs {
    public final /* synthetic */ t7 A00;
    public final /* synthetic */ AbstractC0609cs A01;

    public t8(t7 t7Var, AbstractC0609cs csVar) {
        this.A00 = t7Var;
        this.A01 = csVar;
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r5, long j) {
        t7 t7Var = this.A00;
        t7Var.A08();
        try {
            long A4c = this.A01.A4c(r5, j);
            t7Var.A09(true);
            return A4c;
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

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        try {
            this.A01.close();
            this.A00.A09(true);
        } catch (IOException e) {
            t7 t7Var = this.A00;
            if (t7Var.A0A()) {
                throw t7Var.A07(e);
            }
            throw e;
        } catch (Throwable th) {
            this.A00.A09(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.source(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A00;
    }
}
