package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.Mu;
import X.QC;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.UserHandle;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.sdk_logging.SDKLoggingInterface;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class PresenceReporter {
    public static final String EVENT_NAME = "moonlight_sdk";
    public static final String LOGGING_PACKAGE_NAME = "com.oculus.horizon";
    public static final String LOGGING_SERVICE_NAME = "com.oculus.horizon.service.SDKLoggingService";
    public static final String TAG = "PresenceReporter";
    public QC _UL_mInjectionContext;
    public final ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.oculus.unifiedtelemetry.unifiedlogging.PresenceReporter.AnonymousClass1 */

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PresenceReporter.this.mServiceInterface = SDKLoggingInterface.Stub.asInterface(iBinder);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            PresenceReporter.A01(PresenceReporter.this);
        }
    };
    @Nullable
    public SDKLoggingInterface mServiceInterface = null;
    public final UserHandle mUserHandle;

    public static synchronized void A00(PresenceReporter presenceReporter) {
        synchronized (presenceReporter) {
            if (presenceReporter.mServiceInterface == null) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oculus.horizon", LOGGING_SERVICE_NAME));
                try {
                    ((Context) AbstractC0096Hu.A03(0, 3, presenceReporter._UL_mInjectionContext)).bindServiceAsUser(intent, presenceReporter.mServiceConnection, 1, presenceReporter.mUserHandle);
                } catch (SecurityException e) {
                    Mu.A02(TAG, "does not have permission to access the service", e);
                }
            }
        }
    }

    public static synchronized void A01(PresenceReporter presenceReporter) {
        synchronized (presenceReporter) {
            presenceReporter.mServiceInterface = null;
            ((Context) AbstractC0096Hu.A03(0, 3, presenceReporter._UL_mInjectionContext)).unbindService(presenceReporter.mServiceConnection);
            A00(presenceReporter);
        }
    }

    @Inject
    public PresenceReporter(AbstractC0247Xu xu, @Assisted UserHandle userHandle) {
        this._UL_mInjectionContext = new QC(1, xu);
        this.mUserHandle = userHandle;
        A00(this);
    }
}
