package X;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: X.0HQ  reason: invalid class name */
public class AnonymousClass0HQ extends AnonymousClass0Of {
    public final /* synthetic */ Socket A00;

    public AnonymousClass0HQ(Socket socket) {
        this.A00 = socket;
    }

    @Override // X.AnonymousClass0Of
    public final IOException newTimeoutException(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // X.AnonymousClass0Of
    public final void timedOut() {
        try {
            this.A00.close();
        } catch (Exception e) {
            Logger logger = C04600h6.A00;
            Level level = Level.WARNING;
            logger.log(level, "Failed to close timed out socket " + this.A00, (Throwable) e);
        } catch (AssertionError e2) {
            if (e2.getCause() == null || e2.getMessage() == null || !e2.getMessage().contains("getsockname failed")) {
                throw e2;
            }
            Logger logger2 = C04600h6.A00;
            Level level2 = Level.WARNING;
            logger2.log(level2, "Failed to close timed out socket " + this.A00, (Throwable) e2);
        }
    }
}
