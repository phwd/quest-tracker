package X;

import java.io.IOException;

public class Dw implements WF {
    public final /* synthetic */ Dv A00;
    public final /* synthetic */ WF A01;

    public Dw(Dv dv, WF wf) {
        this.A00 = dv;
        this.A01 = wf;
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            this.A01.close();
            this.A00.exit(true);
        } catch (IOException e) {
            throw this.A00.exit(e);
        } catch (Throwable th) {
            this.A00.exit(false);
            throw th;
        }
    }

    @Override // X.WF
    public final long read(AnonymousClass8k r5, long j) throws IOException {
        Dv dv = this.A00;
        dv.enter();
        try {
            long read = this.A01.read(r5, j);
            dv.exit(true);
            return read;
        } catch (IOException e) {
            throw dv.exit(e);
        } catch (Throwable th) {
            dv.exit(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.source(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A00;
    }
}
