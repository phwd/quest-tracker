package X;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* renamed from: X.0HS  reason: invalid class name */
public class AnonymousClass0HS extends AnonymousClass0Of {
    public final /* synthetic */ C04760hS A00;

    public AnonymousClass0HS(C04760hS r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Of
    public final IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.AnonymousClass0Of
    public final void timedOut() {
        C04760hS r3 = this.A00;
        EnumC04880hs r2 = EnumC04880hs.CANCEL;
        if (C04760hS.A00(r3, r2)) {
            r3.A07.A03(r3.A06, r2);
        }
    }

    public final void A00() throws IOException {
        if (exit()) {
            throw newTimeoutException(null);
        }
    }
}
