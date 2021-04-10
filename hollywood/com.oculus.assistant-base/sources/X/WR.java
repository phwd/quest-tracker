package X;

import android.os.CountDownTimer;

public final class WR extends CountDownTimer {
    public final /* synthetic */ WS A00;

    public final void onFinish() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WR(WS ws) {
        super(20000, 7000);
        this.A00 = ws;
    }

    public final void onTick(long j) {
        if (j < 20000) {
            WS ws = this.A00;
            if (ws.A00) {
                ws.A00 = false;
                C0112Aq.A00().A01(new C1285wf());
                return;
            }
            C0112Aq.A00().A01(new C1284we(AnonymousClass09.A00, true));
        }
    }
}
