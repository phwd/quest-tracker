package X;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1xT  reason: invalid class name */
public final class AnonymousClass1xT extends AbstractC12411xQ {
    public final AtomicBoolean A00 = new AtomicBoolean();
    public final AnonymousClass1xU A01;
    public final AnonymousClass1xY A02;
    public final AnonymousClass1xR A03;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (this.A00.compareAndSet(false, true)) {
            this.A01.dispose();
            AnonymousClass1xR r5 = this.A03;
            AnonymousClass1xY r4 = this.A02;
            r4.A00 = System.nanoTime() + r5.A00;
            r5.A02.offer(r4);
        }
    }

    public AnonymousClass1xT(AnonymousClass1xR r4) {
        AnonymousClass1xY r1;
        this.A03 = r4;
        this.A01 = new AnonymousClass1xU();
        AnonymousClass1xU r2 = r4.A01;
        if (!r2.A01) {
            while (true) {
                ConcurrentLinkedQueue<AnonymousClass1xY> concurrentLinkedQueue = r4.A02;
                if (concurrentLinkedQueue.isEmpty()) {
                    r1 = new AnonymousClass1xY(r4.A05);
                    r2.A1D(r1);
                    break;
                }
                r1 = concurrentLinkedQueue.poll();
                if (r1 != null) {
                    break;
                }
            }
        } else {
            r1 = C12421xS.A06;
        }
        this.A02 = r1;
    }
}
