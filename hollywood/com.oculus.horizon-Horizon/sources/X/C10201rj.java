package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rj  reason: invalid class name and case insensitive filesystem */
public final class C10201rj implements AnonymousClass0KW<C10411sn> {
    public static final long A00 = TimeUnit.MINUTES.toMillis(5);

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0KW
    public final C10411sn get() {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            i = ApkUpdateInfoContract.UPDATE_TYPE_FULL;
        } else {
            i = 4194304;
            if (min < 33554432) {
                i = ApkUpdateInfoContract.UPDATE_TYPE_PATCH;
            }
        }
        return new C10411sn(i, Integer.MAX_VALUE, i, i >> 3, A00);
    }
}
