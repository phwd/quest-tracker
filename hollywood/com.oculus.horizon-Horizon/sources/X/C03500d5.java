package X;

import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

@VisibleForTesting
/* renamed from: X.0d5  reason: invalid class name and case insensitive filesystem */
public final class C03500d5 implements AnonymousClass0u5 {
    public static final C03500d5 A00 = new C03500d5();

    @Override // X.AnonymousClass0u5
    public final void A9b(Closeable closeable, Throwable th, Throwable th2) {
        Logger logger = AnonymousClass0u4.A00;
        Level level = Level.WARNING;
        StringBuilder sb = new StringBuilder("Suppressing exception thrown when closing ");
        sb.append(closeable);
        logger.log(level, sb.toString(), th2);
    }
}
