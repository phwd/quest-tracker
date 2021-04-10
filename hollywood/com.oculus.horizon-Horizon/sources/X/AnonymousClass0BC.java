package X;

import java.io.IOException;

/* renamed from: X.0BC  reason: invalid class name */
public class AnonymousClass0BC extends AbstractC01190Mc {
    public boolean A00;
    public final /* synthetic */ AnonymousClass0MU A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0BC(AnonymousClass0MU r1) {
        super(r1);
        this.A01 = r1;
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!super.A00) {
            if (!this.A00) {
                A00(false);
            }
            super.A00 = true;
        }
    }

    @Override // X.AbstractC07630v6
    public final long read(AnonymousClass0B3 r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
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
