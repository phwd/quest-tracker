package X;

import androidx.annotation.VisibleForTesting;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
public final class Fj {
    public static final long A00 = TimeUnit.DAYS.toMillis(1);
    public static final long A01 = TimeUnit.HOURS.toMillis(1);

    public static long A00() {
        long currentTimeMillis;
        synchronized (Fj.class) {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis / A00;
    }

    public static long A01() {
        long currentTimeMillis;
        synchronized (Fj.class) {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis / A01;
    }
}
