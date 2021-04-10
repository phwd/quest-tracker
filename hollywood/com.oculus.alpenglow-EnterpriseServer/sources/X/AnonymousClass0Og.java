package X;

import java.io.IOException;

/* renamed from: X.0Og  reason: invalid class name */
public class AnonymousClass0Og implements AbstractC04550h0 {
    public final /* synthetic */ AnonymousClass0Of A00;
    public final /* synthetic */ AbstractC04550h0 A01;

    public AnonymousClass0Og(AnonymousClass0Of r1, AbstractC04550h0 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
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

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r5, long j) throws IOException {
        AnonymousClass0Of r3 = this.A00;
        r3.enter();
        try {
            long read = this.A01.read(r5, j);
            r3.exit(true);
            return read;
        } catch (IOException e) {
            throw r3.exit(e);
        } catch (Throwable th) {
            r3.exit(false);
            throw th;
        }
    }

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A00;
    }

    public final String toString() {
        return "AsyncTimeout.source(" + this.A01 + ")";
    }
}
