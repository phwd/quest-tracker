package com.oculus.alpenglow.induction;

import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.C04910hv;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import androidx.annotation.Nullable;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.constants.ForegroundServiceNotificationIds;
import com.oculus.alpenglow.lifecycle.StartupListener;
import com.oculus.alpenglow.util.ForegroundNotificationManager;
import com.oculus.managed.ManagedMode;
import java.util.Set;
import java.util.function.BiConsumer;

public class InductionService extends Service {
    public static final String TAG = "EnterpriseServer.InductionService";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Nullable
    public final IBinder onBind(Intent intent) {
        return null;
    }

    public final /* synthetic */ void A01(Device device, Throwable th) {
        if (th != null) {
            AnonymousClass0NK.A04(TAG, "performInduction complete with exception, killing process", th);
            stopSelf();
            Process.killProcess(Process.myPid());
        } else if (((ManagedMode) AnonymousClass0Lh.A03(4, 86, this._UL_mInjectionContext)).isEnterpriseModeEnabled) {
            try {
                Intent intent = new Intent(getApplicationContext(), Class.forName(getPackageName().concat(".enterpriseserver.EnterpriseServer")));
                if (Build.VERSION.SDK_INT >= 26) {
                    C04910hv.A00().A02().A01(intent, getApplicationContext());
                } else {
                    C04910hv.A00().A02().A00(intent, getApplicationContext());
                }
            } catch (ClassNotFoundException unused) {
            }
            for (StartupListener startupListener : (Set) AnonymousClass0Lh.A03(2, 26, this._UL_mInjectionContext)) {
                try {
                    startupListener.A6b(getApplicationContext());
                } catch (Exception e) {
                    AnonymousClass0NK.A04(TAG, "startup listener failed", e);
                }
            }
            ConfigurationStore configurationStore = (ConfigurationStore) AnonymousClass0Lh.A03(1, 97, this._UL_mInjectionContext);
            configurationStore.A02(configurationStore.mData.device, device);
        }
        stopSelf();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        startForeground(ForegroundServiceNotificationIds.ENTERPRISE_SERVICE_FOREGROUND_ID, ((ForegroundNotificationManager) AnonymousClass0Lh.A03(3, 106, this._UL_mInjectionContext)).A00(getApplicationContext()));
        ((Induction) AnonymousClass0Lh.A03(0, 129, this._UL_mInjectionContext)).A00().whenComplete((BiConsumer<? super Device, ? super Throwable>) new BiConsumer() {
            /* class com.oculus.alpenglow.induction.$$Lambda$InductionService$bTGkA09nYnydhw_zMwU5Za2w0g2 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                InductionService.this.A01((Device) obj, (Throwable) obj2);
            }
        });
        return 1;
    }

    public static void A00(Context context) {
        C04910hv.A00().A02().A01(new Intent(context, InductionService.class), context);
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(5, AnonymousClass0Lh.get(this));
    }
}
