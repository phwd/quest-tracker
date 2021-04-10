package X;

import android.os.Handler;
import android.os.Looper;

/* renamed from: X.1eh  reason: invalid class name and case insensitive filesystem */
public final class C08471eh {
    public boolean A00;
    public final Handler A01 = new Handler(Looper.getMainLooper(), new C08501ek());

    public final synchronized void A00(AnonymousClass1fR<?> r3, boolean z) {
        if (this.A00 || z) {
            this.A01.obtainMessage(1, r3).sendToTarget();
        } else {
            this.A00 = true;
            r3.A8u();
            this.A00 = false;
        }
    }
}
