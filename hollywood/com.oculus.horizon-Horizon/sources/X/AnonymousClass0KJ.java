package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0KJ  reason: invalid class name */
public final class AnonymousClass0KJ {
    @VisibleForTesting
    public static final Logger A00 = Logger.getLogger(AnonymousClass0KJ.class.getName());

    public static void A00(@Nullable Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                A00.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e);
            }
        }
    }

    public static void A01(@Nullable InputStream inputStream) {
        try {
            A00(inputStream);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
