package X;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* renamed from: X.99  reason: invalid class name */
public class AnonymousClass99 extends KK {
    public final /* synthetic */ C0335d3 A00;

    public AnonymousClass99(C0335d3 d3Var) {
        this.A00 = d3Var;
    }

    @Override // X.KK
    public final IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.KK
    public final void timedOut() {
        C0335d3 d3Var = this.A00;
        dD dDVar = dD.CANCEL;
        if (C0335d3.A00(d3Var, dDVar)) {
            d3Var.A07.A03(d3Var.A06, dDVar);
        }
    }

    public final void A00() throws IOException {
        if (exit()) {
            throw newTimeoutException(null);
        }
    }
}
