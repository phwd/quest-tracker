package X;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: X.8j  reason: invalid class name */
public class AnonymousClass8j extends Dv {
    public final /* synthetic */ Socket A00;

    public AnonymousClass8j(Socket socket) {
        this.A00 = socket;
    }

    @Override // X.Dv
    public final IOException newTimeoutException(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.Dv
    public final void timedOut() {
        try {
            this.A00.close();
        } catch (Exception e) {
            Logger logger = WL.A00;
            Level level = Level.WARNING;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to close timed out socket ");
            sb.append(this.A00);
            logger.log(level, sb.toString(), (Throwable) e);
        } catch (AssertionError e2) {
            if (e2.getCause() == null || e2.getMessage() == null || !e2.getMessage().contains("getsockname failed")) {
                throw e2;
            }
            Logger logger2 = WL.A00;
            Level level2 = Level.WARNING;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to close timed out socket ");
            sb2.append(this.A00);
            logger2.log(level2, sb2.toString(), (Throwable) e2);
        }
    }
}
