package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0pY  reason: invalid class name */
public final class AnonymousClass0pY implements AnonymousClass0LA {
    @Override // X.AnonymousClass0LA
    public final long now() {
        return SystemClock.currentThreadTimeMillis();
    }
}
