package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: X.1f7  reason: invalid class name and case insensitive filesystem */
public final class C08691f7 {
    public AbstractC08671f5 A00;
    public final ReferenceQueue<C08701f8<?>> A01 = new ReferenceQueue<>();
    @VisibleForTesting
    public final Map<AbstractC06491aL, C08761fF> A02 = new HashMap();
    public final Executor A03;
    public volatile boolean A04;
    @Nullable
    public volatile AbstractC09021fu A05;

    public static final void A00(@NonNull C08691f7 r5, C08761fF r6) {
        synchronized (r5) {
            Map<AbstractC06491aL, C08761fF> map = r5.A02;
            AbstractC06491aL r4 = r6.A01;
            map.remove(r4);
            if (r6.A02 && r6.A00 != null) {
                AnonymousClass1fR<?> r1 = r6.A00;
                AbstractC08671f5 r52 = r5.A00;
                r52.A01(r4, new C08701f8<>(r1, true, false, r4, r52));
            }
        }
    }

    public final synchronized void A01(AbstractC06491aL r4, C08701f8<?> r5) {
        C08761fF put = this.A02.put(r4, new C08761fF(r4, r5, this.A01, false));
        if (put != null) {
            put.A00 = null;
            put.clear();
        }
    }

    public C08691f7() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactoryC08361eS());
        this.A03 = newSingleThreadExecutor;
        newSingleThreadExecutor.execute(new AnonymousClass1fK(this));
    }
}
