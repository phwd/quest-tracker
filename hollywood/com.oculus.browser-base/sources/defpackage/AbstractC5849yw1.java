package defpackage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.SparseArray;

@Deprecated
/* renamed from: yw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5849yw1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public static final SparseArray f11711a = new SparseArray();
    public static int b = 1;

    public static ComponentName a(Context context, Intent intent) {
        SparseArray sparseArray = f11711a;
        synchronized (sparseArray) {
            int i = b;
            int i2 = i + 1;
            b = i2;
            if (i2 <= 0) {
                b = 1;
            }
            intent.putExtra("androidx.contentpager.content.wakelockid", i);
            intent.putExtra("android.support.content.wakelockid", i);
            ComponentName startService = context.startService(intent);
            if (startService == null) {
                return null;
            }
            PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "androidx.core:wake:" + startService.flattenToShortString());
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire(60000);
            sparseArray.put(i, newWakeLock);
            return startService;
        }
    }
}
