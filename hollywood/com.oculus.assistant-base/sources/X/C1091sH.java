package X;

import android.os.Handler;

/* renamed from: X.sH  reason: case insensitive filesystem */
public final class C1091sH implements AbstractC0316Qn {
    public final /* synthetic */ C0319Qs A00;

    public C1091sH(C0319Qs qs) {
        this.A00 = qs;
    }

    @Override // X.AbstractC0316Qn
    public final void A3t(boolean z) {
        Handler handler = this.A00.A05;
        handler.sendMessage(handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
