package X;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class AR {
    public int A00;
    public int A01 = 0;
    public int A02 = Integer.MAX_VALUE;
    public long A03 = 0;
    public Integer A04;
    public String A05;

    public final ThreadPoolExecutor A00() {
        int i;
        AV av;
        if (this.A05 != null) {
            boolean z = true;
            boolean z2 = false;
            if (this.A00 > 0) {
                z2 = true;
            }
            CT.A00(z2);
            boolean z3 = false;
            if (this.A02 > 0) {
                z3 = true;
            }
            CT.A00(z3);
            if (this.A03 < 0) {
                z = false;
            }
            CT.A00(z);
            Integer num = this.A04;
            int i2 = this.A00;
            if (num != null) {
                i = num.intValue();
                boolean z4 = false;
                if (i >= i2) {
                    z4 = true;
                }
                CT.A00(z4);
            } else {
                i = i2;
            }
            int i3 = this.A00;
            long j = this.A03;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(this.A02);
            String str = this.A05;
            int i4 = this.A01;
            if (i > 1) {
                av = new AV(new C0794hi(str, new AtomicInteger(1)), i4);
            } else {
                av = new AV(str, i4);
            }
            return new AQ(this, i3, i, j, timeUnit, linkedBlockingQueue, av);
        }
        throw null;
    }
}
