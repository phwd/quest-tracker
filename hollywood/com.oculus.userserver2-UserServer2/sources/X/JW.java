package X;

import javax.annotation.Nullable;

public interface JW {
    void handleUncaughtException(Thread thread, Throwable th, @Nullable JS js);
}
