package X;

import android.os.Looper;

/* renamed from: X.1aG  reason: invalid class name */
public class AnonymousClass1aG implements AbstractC09211aF {
    @Override // X.AbstractC09211aF
    public final void A2R(AnonymousClass1aA r3) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StringBuilder sb = new StringBuilder("Event bus ");
            sb.append(r3);
            sb.append(" accessed from non-main thread ");
            sb.append(Looper.myLooper());
            throw new IllegalStateException(sb.toString());
        }
    }
}
