package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.SparseArray;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.fbns.FbnsService;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ya  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09050ya extends AbstractC09060yb {
    public static final String TAG = "FbnsCallbackReceiver";
    public final Class<? extends AnonymousClass0uh> mIntentServiceClass = FbnsService.class;

    public final void onReceive(Context context, Intent intent) {
        intent.getAction();
        if (intent.getAction() != null) {
            intent.setClass(context, this.mIntentServiceClass);
            SparseArray<PowerManager.WakeLock> sparseArray = AbstractC09060yb.mActiveWakeLocks;
            synchronized (sparseArray) {
                int i = AbstractC09060yb.mNextId;
                int i2 = i + 1;
                AbstractC09060yb.mNextId = i2;
                if (i2 <= 0) {
                    AbstractC09060yb.mNextId = 1;
                }
                intent.putExtra(AbstractC09060yb.EXTRA_WAKE_LOCK_ID, i);
                ComponentName startService = context.startService(intent);
                if (startService == null) {
                    AnonymousClass0NK.A06(TAG, "service %s does not exist", this.mIntentServiceClass.getClass().getCanonicalName());
                    return;
                }
                PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, AnonymousClass006.A05("wake:", startService.flattenToShortString()));
                newWakeLock.setReferenceCounted(false);
                newWakeLock.acquire(60000);
                sparseArray.put(i, newWakeLock);
            }
        }
    }
}
