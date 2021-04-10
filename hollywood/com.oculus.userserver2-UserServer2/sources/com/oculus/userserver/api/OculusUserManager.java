package com.oculus.userserver.api;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.oculus.userserver.managerservice.IOculusUserManager;
import java.util.concurrent.CountDownLatch;

@TargetApi(19)
public class OculusUserManager implements AutoCloseable {
    public static final String SERVICE_IMPL_CLS = "com.oculus.userserver.managerservice.OculusUserManagerService";
    public static final String SERVICE_IMPL_PKG = "com.oculus.userserver2";
    public static final String TAG = "OculusUserManager";
    public final BlockingServiceConnection mConnection;
    public final Context mContext;

    public class BlockingServiceConnection implements ServiceConnection {
        public final CountDownLatch mLatch;
        public volatile IOculusUserManager mService;
        public final /* synthetic */ OculusUserManager this$0;

        /* renamed from: com.oculus.userserver.api.OculusUserManager$BlockingServiceConnection$1  reason: invalid class name */
        public class AnonymousClass1 implements IBinder.DeathRecipient {
            public final /* synthetic */ BlockingServiceConnection this$1;

            public final void binderDied() {
                Log.e(OculusUserManager.TAG, "Remote service died", null);
            }
        }
    }
}
