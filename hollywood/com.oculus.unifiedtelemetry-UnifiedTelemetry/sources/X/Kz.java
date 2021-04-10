package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;

public final class Kz implements AbstractC0313cc {
    public boolean A00;
    public final K4 A01;
    public final /* synthetic */ C0127Kx A02;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final synchronized void close() throws IOException {
        if (!this.A00) {
            this.A00 = true;
            C0127Kx kx = this.A02;
            kx.A01.A5y("0\r\n\r\n");
            C0127Kx.A00(this.A01);
            kx.A00 = 3;
        }
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final synchronized void flush() throws IOException {
        if (!this.A00) {
            this.A02.A01.flush();
        }
    }

    public Kz(C0127Kx kx) {
        this.A02 = kx;
        this.A01 = new K4(kx.A01.timeout());
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r4, long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            KJ kj = this.A02.A01;
            kj.A5s(j);
            kj.A5y(HttpRequestMultipart.LINE_FEED);
            kj.write(r4, j);
            kj.A5y(HttpRequestMultipart.LINE_FEED);
        }
    }

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A01;
    }
}
