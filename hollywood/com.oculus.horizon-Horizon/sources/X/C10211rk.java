package X;

import android.app.ActivityManager;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rk  reason: invalid class name and case insensitive filesystem */
public final class C10211rk implements AnonymousClass0KW<C10411sn> {
    public static final long A01 = TimeUnit.MINUTES.toMillis(5);
    public final ActivityManager A00;

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0KW
    public final C10411sn get() {
        int i;
        int min = Math.min(this.A00.getMemoryClass() * ApkUpdateInfoContract.UPDATE_TYPE_FULL, Integer.MAX_VALUE);
        if (min < 33554432) {
            i = 4194304;
        } else {
            i = 6291456;
            if (min >= 67108864) {
                i = min >> 2;
            }
        }
        return new C10411sn(i, 256, Integer.MAX_VALUE, Integer.MAX_VALUE, A01);
    }

    public C10211rk(ActivityManager activityManager) {
        this.A00 = activityManager;
    }
}
