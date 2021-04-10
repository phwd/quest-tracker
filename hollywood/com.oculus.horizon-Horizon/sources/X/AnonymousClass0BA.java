package X;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* renamed from: X.0BA  reason: invalid class name */
public class AnonymousClass0BA extends AnonymousClass0Ly {
    public final /* synthetic */ C07900vZ A00;

    public AnonymousClass0BA(C07900vZ r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Ly
    public final IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.AnonymousClass0Ly
    public final void timedOut() {
        C07900vZ r3 = this.A00;
        EnumC08000vj r2 = EnumC08000vj.CANCEL;
        if (C07900vZ.A00(r3, r2)) {
            r3.A07.A03(r3.A06, r2);
        }
    }

    public final void A00() throws IOException {
        if (exit()) {
            throw newTimeoutException(null);
        }
    }
}
