package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import androidx.annotation.Nullable;
import com.facebook.crudolib.prefs.LightSharedPreferences;
import com.facebook.infer.annotation.NullsafeStrict;
import java.io.File;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@NullsafeStrict
@ThreadSafe
/* renamed from: X.0of  reason: invalid class name and case insensitive filesystem */
public final class C03850of {
    public static final Object A0B = new Object();
    public static final Handler A0C = new Handler(Looper.getMainLooper());
    @Nullable
    public Throwable A00;
    public final int A01;
    public final AnonymousClass0Lo A02;
    public final Object A03 = new Object();
    public final Thread A04;
    @GuardedBy("mCacheLock")
    public final Map<String, Object> A05;
    @GuardedBy("this")
    public final Map<String, Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler>> A06;
    public final CountDownLatch A07;
    public final AtomicBoolean A08 = new AtomicBoolean(false);
    public final Executor A09;
    public volatile boolean A0A = false;

    public C03850of(File file, Executor executor) {
        this.A02 = new AnonymousClass0Lo(file);
        this.A05 = new HashMap();
        this.A06 = new HashMap();
        if (executor != null) {
            this.A09 = executor;
            this.A01 = 1;
            this.A07 = new CountDownLatch(1);
            Thread thread = new Thread(new AnonymousClass0Ll(this), AnonymousClass006.A07("LSP-", file.getName()));
            this.A04 = thread;
            thread.start();
            return;
        }
        throw null;
    }

    public static void A00(C03850of r3) {
        int priority;
        if (!r3.A0A) {
            Trace.beginSection("LightSharedPreferences.waitIfNotLoaded");
            while (!r3.A0A) {
                synchronized (r3) {
                    Thread thread = r3.A04;
                    if (thread.getState() != Thread.State.TERMINATED && (priority = Thread.currentThread().getPriority()) > thread.getPriority()) {
                        thread.setPriority(priority);
                    }
                }
                try {
                    r3.A07.await();
                    break;
                } catch (InterruptedException unused) {
                }
            }
            Trace.endSection();
        }
    }
}
