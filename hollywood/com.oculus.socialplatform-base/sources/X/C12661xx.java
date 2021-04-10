package X;

import android.os.Handler;

/* renamed from: X.1xx  reason: invalid class name and case insensitive filesystem */
public final class C12661xx extends AbstractC12411xQ {
    public final Handler A00;
    public volatile boolean A01;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A01 = true;
        this.A00.removeCallbacksAndMessages(this);
    }

    public C12661xx(Handler handler) {
        this.A00 = handler;
    }
}
