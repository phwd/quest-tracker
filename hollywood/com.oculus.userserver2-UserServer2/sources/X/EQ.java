package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;

public final class EQ implements WG {
    public boolean A00;
    public final Dl A01;
    public final /* synthetic */ EN A02;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public final synchronized void close() throws IOException {
        if (!this.A00) {
            this.A00 = true;
            EN en = this.A02;
            en.A01.A44("0\r\n\r\n");
            EN.A00(this.A01);
            en.A00 = 3;
        }
    }

    @Override // X.WG, java.io.Flushable
    public final synchronized void flush() throws IOException {
        if (!this.A00) {
            this.A02.A01.flush();
        }
    }

    public EQ(EN en) {
        this.A02 = en;
        this.A01 = new Dl(en.A01.timeout());
    }

    @Override // X.WG
    public final void write(AnonymousClass8k r4, long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            Du du = this.A02.A01;
            du.A40(j);
            du.A44(HttpRequestMultipart.LINE_FEED);
            du.write(r4, j);
            du.A44(HttpRequestMultipart.LINE_FEED);
        }
    }

    @Override // X.WG
    public final WE timeout() {
        return this.A01;
    }
}
