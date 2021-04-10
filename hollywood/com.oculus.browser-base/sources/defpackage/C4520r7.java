package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.telephony.TelephonyManager;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;

/* renamed from: r7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4520r7 {
    public static boolean a(Context context, Intent intent, int i, String str, Executor executor, ServiceConnection serviceConnection) {
        return context.bindIsolatedService(intent, i, str, executor, serviceConnection);
    }

    public static void b(TelephonyManager telephonyManager, Callback callback) {
        telephonyManager.requestCellInfoUpdate(AbstractC2032cb.f9616a, new C4350q7(callback));
    }

    public static void c(Context context, ServiceConnection serviceConnection, int i, int i2) {
        context.updateServiceGroup(serviceConnection, i, i2);
    }
}
