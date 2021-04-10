package X;

import android.content.Context;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0Ww  reason: invalid class name and case insensitive filesystem */
public final class C01710Ww {
    public final AtomicLong A00 = new AtomicLong();
    public final AtomicLong A01 = new AtomicLong();
    public final AtomicLong A02 = new AtomicLong();
    public final AtomicLong A03 = new AtomicLong();
    public final AtomicLong A04 = new AtomicLong();
    public final Context A05;
    public final RealtimeSinceBootClock A06;
    public volatile String A07;

    public final AnonymousClass0Wv A00(boolean z) {
        AtomicLong atomicLong = this.A01;
        long j = atomicLong.get();
        AtomicLong atomicLong2 = this.A00;
        AnonymousClass0Wv r4 = new AnonymousClass0Wv(this.A07, this.A03.get() - this.A04.get(), j - atomicLong2.get(), atomicLong.get() - this.A02.get());
        if (z) {
            atomicLong2.set(0);
            atomicLong.set(0);
        }
        return r4;
    }

    public C01710Ww(Context context, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A05 = context;
        this.A06 = realtimeSinceBootClock;
    }
}
