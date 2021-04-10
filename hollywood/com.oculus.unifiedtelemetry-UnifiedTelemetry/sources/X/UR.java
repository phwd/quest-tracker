package X;

import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

@VisibleForTesting
public final class UR implements AnonymousClass9Y {
    public static final UR A00 = new UR();

    @Override // X.AnonymousClass9Y
    public final void A5S(Closeable closeable, Throwable th, Throwable th2) {
        Logger logger = AnonymousClass9X.A00;
        Level level = Level.WARNING;
        StringBuilder sb = new StringBuilder("Suppressing exception thrown when closing ");
        sb.append(closeable);
        logger.log(level, sb.toString(), th2);
    }
}
