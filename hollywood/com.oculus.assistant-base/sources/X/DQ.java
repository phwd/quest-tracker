package X;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class DQ {
    public DQ A00;
    public DR A01;
    public C0848js A02;
    public boolean A03;
    public AtomicInteger A04 = new AtomicInteger(0);

    public final void A02() {
        DQ dq = this.A00;
        if (dq != null) {
            StringBuilder sb = new StringBuilder("Already added to ");
            sb.append(dq);
            throw new IllegalStateException(sb.toString());
        }
    }

    public final void A03(DR dr) {
        int incrementAndGet = this.A04.incrementAndGet();
        if (incrementAndGet == 1) {
            this.A01 = dr;
            if (!this.A03) {
                this.A03 = true;
                return;
            }
            throw new IllegalStateException("Internal bug, expected object to be immutable");
        }
        throw new IllegalStateException(AnonymousClass08.A00("Acquired object with non-zero initial refCount current = ", incrementAndGet));
    }
}
