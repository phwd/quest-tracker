package X;

import java.util.ArrayDeque;

/* renamed from: X.0q5  reason: invalid class name */
public class AnonymousClass0q5 implements AnonymousClass0HZ {
    public final int A00;
    public final AnonymousClass0HZ A01;
    public final /* synthetic */ C00890Hd A02;

    public AnonymousClass0q5(C00890Hd r1, int i, AnonymousClass0HZ r3) {
        this.A02 = r1;
        this.A00 = i;
        this.A01 = r3;
    }

    @Override // X.AnonymousClass0HZ
    public final void A65() {
        Runnable poll;
        C00890Hd r2 = this.A02;
        synchronized (r2) {
            AnonymousClass0Hc r1 = r2.A01.get(this.A00);
            if (r1 != null) {
                r1.A00 = null;
                r1.A00 = null;
                ArrayDeque<Runnable> arrayDeque = r1.A01;
                if (!(arrayDeque == null || (poll = arrayDeque.poll()) == null)) {
                    poll.run();
                }
            }
            this.A01.A65();
        }
    }

    @Override // X.AnonymousClass0HZ
    public final void A7D(boolean z) {
        this.A01.A7D(z);
    }
}
