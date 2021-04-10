package X;

import java.io.IOException;

/* renamed from: X.8n  reason: invalid class name */
public class AnonymousClass8n extends ER {
    public boolean A00;
    public final /* synthetic */ EN A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass8n(EN en) {
        super(en);
        this.A01 = en;
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!super.A00) {
            if (!this.A00) {
                A00(false);
            }
            super.A00 = true;
        }
    }

    @Override // X.WF
    public final long read(AnonymousClass8k r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
        } else if (!super.A00) {
            if (!this.A00) {
                long read = this.A01.A04.read(r6, j);
                if (read != -1) {
                    return read;
                }
                this.A00 = true;
                A00(true);
            }
            return -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }
}
