package X;

import com.facebook.FacebookSdk;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0Vq  reason: invalid class name */
public final class AnonymousClass0Vq implements Executor {
    @GuardedBy("this")
    public boolean A00 = false;
    public final Queue<AnonymousClass0Vp> A01 = new ConcurrentLinkedQueue();
    public final Executor A02;

    public static void A01(AnonymousClass0Vq r2) {
        AnonymousClass0Vp poll;
        synchronized (r2) {
            if (!r2.A00 && (poll = r2.A01.poll()) != null) {
                r2.A00 = true;
                r2.A02.execute(poll);
            }
        }
    }

    public static AnonymousClass0Vq A00() {
        if (AnonymousClass0Vm.A02 == null) {
            synchronized (AnonymousClass0Vm.class) {
                if (AnonymousClass0Vm.A02 == null) {
                    AnonymousClass0Vm.A02 = new ThreadPoolExecutor(5, (int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE, 1, TimeUnit.SECONDS, AnonymousClass0Vm.A00, AnonymousClass0Vm.A01);
                }
            }
        }
        return new AnonymousClass0Vq(new AnonymousClass0Vo(AnonymousClass0Vm.A02));
    }

    public final void execute(Runnable runnable) {
        this.A01.add(new AnonymousClass0Vp(this, runnable));
        A01(this);
    }

    public AnonymousClass0Vq(AnonymousClass0Vo r2) {
        this.A02 = r2.A00;
    }
}
