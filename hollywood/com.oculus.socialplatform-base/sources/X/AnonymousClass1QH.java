package X;

import android.content.Context;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1QH  reason: invalid class name */
public final class AnonymousClass1QH {
    public final AtomicLong A00 = new AtomicLong();
    public final AtomicLong A01 = new AtomicLong();
    public final AtomicLong A02 = new AtomicLong();
    public final AtomicLong A03 = new AtomicLong();
    public final Context A04;
    public final RealtimeSinceBootClock A05;
    public final AtomicLong A06 = new AtomicLong();
    public volatile String A07;

    public final AnonymousClass1Q7 A00() {
        AtomicLong atomicLong = this.A01;
        long j = atomicLong.get();
        AtomicLong atomicLong2 = this.A00;
        AnonymousClass1Q7 r4 = new AnonymousClass1Q7(null, this.A03.get() - this.A06.get(), j - atomicLong2.get(), atomicLong.get() - this.A02.get());
        atomicLong2.set(0);
        atomicLong.set(0);
        return r4;
    }

    public AnonymousClass1QH(Context context, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A04 = context;
        this.A05 = realtimeSinceBootClock;
    }
}
