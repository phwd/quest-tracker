package X;

import android.app.ActivityManager;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jD  reason: invalid class name and case insensitive filesystem */
public final class C09661jD implements AbstractC00750Ik<AnonymousClass0PF> {
    public static final long A01 = TimeUnit.MINUTES.toMillis(5);
    public final ActivityManager A00;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC00750Ik
    public final AnonymousClass0PF get() {
        int i;
        int min = Math.min(this.A00.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            i = 4194304;
        } else {
            i = 6291456;
            if (min >= 67108864) {
                i = min >> 2;
            }
        }
        return new AnonymousClass0PF(i, 256, Integer.MAX_VALUE, Integer.MAX_VALUE, A01);
    }

    public C09661jD(ActivityManager activityManager) {
        this.A00 = activityManager;
    }
}
