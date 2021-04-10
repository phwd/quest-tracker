package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0pD  reason: invalid class name and case insensitive filesystem */
public final class C04090pD implements AnonymousClass0K4 {
    @Override // X.AnonymousClass0K4
    public final long now() {
        return SystemClock.currentThreadTimeMillis();
    }
}
