package X;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.PowerManager;
import android.util.SparseArray;

/* renamed from: X.0yb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09060yb extends BroadcastReceiver {
    public static final String EXTRA_WAKE_LOCK_ID = "androidx.contentpager.content.wakelockid";
    public static final SparseArray<PowerManager.WakeLock> mActiveWakeLocks = new SparseArray<>();
    public static int mNextId = 1;

    public static void A00(Intent intent) {
        int intExtra = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        if (intExtra != 0) {
            SparseArray<PowerManager.WakeLock> sparseArray = mActiveWakeLocks;
            synchronized (sparseArray) {
                PowerManager.WakeLock wakeLock = sparseArray.get(intExtra);
                if (wakeLock != null) {
                    wakeLock.release();
                    sparseArray.remove(intExtra);
                } else {
                    AnonymousClass0NK.A07("WakefulBroadcastReceiver", "No active wake lock id #%s", Integer.valueOf(intExtra));
                }
            }
        }
    }
}
