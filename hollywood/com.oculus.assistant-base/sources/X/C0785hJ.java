package X;

import com.google.common.base.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.hJ  reason: case insensitive filesystem */
public final class C0785hJ implements AbstractC00869q {
    public final ScheduledExecutorService A00 = Executors.newSingleThreadScheduledExecutor();
    public final AtomicLong A01 = new AtomicLong(0);

    @Override // X.AbstractC00869q
    public final void A3u() {
    }

    public static long A00(Optional optional) {
        long currentMonotonicTimestamp = C00879r.A00.A00.currentMonotonicTimestamp();
        if (optional.isPresent()) {
            return currentMonotonicTimestamp - ((Number) optional.get()).longValue();
        }
        return currentMonotonicTimestamp;
    }

    public final void A01() {
        hG hGVar = C00879r.A00;
        hGVar.A05(EnumC00909u.RESULT_DELIVERED);
        this.A01.set(hGVar.A00.currentMonotonicTimestamp());
        this.A00.schedule(new RunnableC00939x(this), 2, TimeUnit.SECONDS);
    }

    public final void A03(Optional optional) {
        this.A01.set(0);
        hG hGVar = C00879r.A00;
        EnumC00909u r0 = EnumC00909u.PREPARING_RESPONSE;
        hGVar.A00.markerPoint(hGVar.A02, r0.getPointName(), A00(optional), TimeUnit.MILLISECONDS);
    }

    @Override // X.AbstractC00869q
    public final void A4F() {
        this.A01.set(0);
    }

    public final void A02(Optional optional) {
        long A002 = A00(optional);
        hG hGVar = C00879r.A00;
        hGVar.A00.markerPoint(hGVar.A02, EnumC00909u.DELIVERING_RESPONSE.getPointName(), A002, TimeUnit.MILLISECONDS);
        hGVar.A09(2, A002);
    }
}
