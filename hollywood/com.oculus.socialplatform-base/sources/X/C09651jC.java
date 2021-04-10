package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jC  reason: invalid class name and case insensitive filesystem */
public final class C09651jC implements AbstractC00750Ik<AnonymousClass0PF> {
    public static final long A00 = TimeUnit.MINUTES.toMillis(5);

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC00750Ik
    public final AnonymousClass0PF get() {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            i = 1048576;
        } else {
            i = 4194304;
            if (min < 33554432) {
                i = ApkUpdateInfoContract.UPDATE_TYPE_PATCH;
            }
        }
        return new AnonymousClass0PF(i, Integer.MAX_VALUE, i, i >> 3, A00);
    }
}
