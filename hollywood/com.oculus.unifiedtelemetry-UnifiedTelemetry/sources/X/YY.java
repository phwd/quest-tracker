package X;

import java.util.ArrayDeque;

public class YY implements HA {
    public final int A00;
    public final HA A01;
    public final /* synthetic */ HE A02;

    public YY(HE he, int i, HA ha) {
        this.A02 = he;
        this.A00 = i;
        this.A01 = ha;
    }

    @Override // X.HA
    public final void A3h() {
        Runnable poll;
        HE he = this.A02;
        synchronized (he) {
            HD hd = he.A01.get(this.A00);
            if (hd != null) {
                hd.A00 = null;
                hd.A00 = null;
                ArrayDeque<Runnable> arrayDeque = hd.A01;
                if (!(arrayDeque == null || (poll = arrayDeque.poll()) == null)) {
                    poll.run();
                }
            }
            this.A01.A3h();
        }
    }

    @Override // X.HA
    public final void A46(boolean z) {
        this.A01.A46(z);
    }
}
