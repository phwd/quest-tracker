package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0bp  reason: invalid class name and case insensitive filesystem */
public final class C03210bp implements AnonymousClass0LG {
    @Override // X.AnonymousClass0LG
    public final long now() {
        return SystemClock.currentThreadTimeMillis();
    }
}
