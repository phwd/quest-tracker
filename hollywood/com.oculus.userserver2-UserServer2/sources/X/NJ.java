package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class NJ {
    @Nullable
    public static File A00;
    public static final ThreadLocal<Boolean> A01 = new ThreadLocal<>();
    public static final Object A02 = new Object();

    public static void A00(File file) {
        A01.set(Boolean.TRUE);
        A00 = file;
        try {
            file.getCanonicalPath();
            synchronized (g6.class) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        } catch (IOException e) {
            Mi.A02("Nightwatch", "Error starting watcher", e);
        }
    }
}
