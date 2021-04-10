package oculus.internal;

import android.app.IActivityManager;
import android.content.ComponentName;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Singleton;

public class PanelAppUtils {
    private static final Singleton<IActivityManager> IActivityManagerSingleton = new Singleton<IActivityManager>() {
        /* class oculus.internal.PanelAppUtils.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public IActivityManager create() {
            return IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));
        }
    };
    private static final String TAG = PanelAppUtils.class.getSimpleName();

    public static boolean setIsForegroundPanelService(boolean value, ComponentName component) {
        try {
            ((IActivityManager) IActivityManagerSingleton.get()).setIsForegroundPanelService(value, component);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException: ", e);
            return false;
        }
    }
}
