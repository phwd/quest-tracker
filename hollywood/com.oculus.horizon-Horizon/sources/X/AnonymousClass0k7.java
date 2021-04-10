package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.SparseArray;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;
import javax.annotation.Nonnull;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0k7  reason: invalid class name */
public abstract class AnonymousClass0k7 extends AnonymousClass0Vk {
    public static final String TAG = "FbnsCallbackReceiver";
    public final Class<? extends AbstractIntentServiceC02350aK> mIntentServiceClass;

    public AnonymousClass0k7(@Nonnull Class<? extends AbstractIntentServiceC02350aK> cls) {
        this.mIntentServiceClass = cls;
    }

    public final void onReceive(Context context, Intent intent) {
        intent.getAction();
        if (intent.getAction() != null) {
            intent.setClass(context, this.mIntentServiceClass);
            SparseArray<PowerManager.WakeLock> sparseArray = AnonymousClass0Vk.mActiveWakeLocks;
            synchronized (sparseArray) {
                int i = AnonymousClass0Vk.mNextId;
                int i2 = i + 1;
                AnonymousClass0Vk.mNextId = i2;
                if (i2 <= 0) {
                    AnonymousClass0Vk.mNextId = 1;
                }
                intent.putExtra(AnonymousClass0Vk.EXTRA_WAKE_LOCK_ID, i);
                ComponentName startService = context.startService(intent);
                if (startService == null) {
                    AnonymousClass0NO.A0E(TAG, "service %s does not exist", this.mIntentServiceClass.getClass().getCanonicalName());
                    return;
                }
                PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, AnonymousClass006.A05("wake:", startService.flattenToShortString()));
                newWakeLock.setReferenceCounted(false);
                newWakeLock.acquire(MobileConfigAppAwareAccessorDecorator.MS_IN_ONE_MINUTE);
                sparseArray.put(i, newWakeLock);
            }
        }
    }
}
