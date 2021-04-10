package X;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* renamed from: X.8l  reason: invalid class name */
public class AnonymousClass8l extends Dv {
    public final /* synthetic */ C0156Wh A00;

    public AnonymousClass8l(C0156Wh wh) {
        this.A00 = wh;
    }

    @Override // X.Dv
    public final IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.Dv
    public final void timedOut() {
        C0156Wh wh = this.A00;
        EnumC0166Wr wr = EnumC0166Wr.CANCEL;
        if (C0156Wh.A00(wh, wr)) {
            wh.A07.A03(wh.A06, wr);
        }
    }

    public final void A00() throws IOException {
        if (exit()) {
            throw newTimeoutException(null);
        }
    }
}
