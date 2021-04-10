package X;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0K0  reason: invalid class name */
public final class AnonymousClass0K0 {
    public static AnonymousClass0K0 A07;
    public static final long A08 = TimeUnit.MINUTES.toMillis(2);
    @GuardedBy("lock")
    public long A00;
    public final Lock A01 = new ReentrantLock();
    @Nullable
    public volatile StatFs A02 = null;
    @Nullable
    public volatile StatFs A03 = null;
    @Nullable
    public volatile File A04;
    @Nullable
    public volatile File A05;
    public volatile boolean A06 = false;

    @Nullable
    public static StatFs A00(@Nullable StatFs statFs, @Nullable File file) {
        if (file != null && file.exists()) {
            if (statFs == null) {
                try {
                    return new StatFs(file.getAbsolutePath());
                } catch (IllegalArgumentException unused) {
                } catch (Throwable th) {
                    C00770Im.A00(th);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                statFs.restat(file.getAbsolutePath());
                return statFs;
            }
        }
        return null;
    }

    public static void A01(AnonymousClass0K0 r2) {
        if (!r2.A06) {
            Lock lock = r2.A01;
            lock.lock();
            try {
                if (!r2.A06) {
                    r2.A05 = Environment.getDataDirectory();
                    r2.A04 = Environment.getExternalStorageDirectory();
                    A02(r2);
                    r2.A06 = true;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @GuardedBy("lock")
    public static void A02(AnonymousClass0K0 r2) {
        r2.A03 = A00(r2.A03, r2.A05);
        r2.A02 = A00(r2.A02, r2.A04);
        r2.A00 = SystemClock.uptimeMillis();
    }
}
