package com.oculus.config.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.aidl.IGkService;
import com.oculus.config.ConfigController;
import com.oculus.config.ConfigModule;
import com.oculus.config.gatekeeper.DynamicGatekeeperRegistry;
import com.oculus.config.updater.ConfigUpdater;
import com.oculus.util.thread.ThreadUtils;
import java.util.concurrent.TimeUnit;

public class GkService extends Service {
    public static final long MAX_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(30);
    public static final String TAG = "GkService";
    public final IGkService.Stub mBinder = new IGkService.Stub() {
        /* class com.oculus.config.service.GkService.AnonymousClass1 */

        @Override // com.oculus.aidl.IGkService
        public void clearGatekeeperOverride(String str) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            GkService.this.mConfigUpdater.clearGatekeeperOverride(str);
        }

        @Override // com.oculus.aidl.IGkService
        public boolean deregisterGatekeeper(String str) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            return GkService.this.mDynamicGatekeeperRegistry.mGatekeeperSet.remove(str);
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeeper(String str) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            ThreadUtils.A03("must not be run on the ui thread");
            if (!GkService.this.mConfigController.isGatekeeperRegistered(str)) {
                return false;
            }
            fetchGatekeepers(GkService.MAX_TIMEOUT_MS);
            return GkService.this.mConfigController.getGatekeeper(str, false).booleanValue();
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeepers(long j) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            ThreadUtils.A03("must not be run on the ui thread");
            if (j <= 0) {
                AnonymousClass0NO.A08(GkService.TAG, "invalid timeout");
                return false;
            }
            try {
                return GkService.this.mConfigUpdater.fetchAsync().A0M(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                AnonymousClass0NO.A0B(GkService.TAG, "fetchGatekeepersSync interrupted.", e);
                return false;
            }
        }

        @Override // com.oculus.aidl.IGkService
        public void fetchGatekeepersAsync() {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            GkService.this.mConfigUpdater.fetchAsync();
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeper(String str) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            return getGatekeeperDef(str, false);
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeperDef(String str, boolean z) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            if (GkService.this.mConfigController.isGatekeeperRegistered(str)) {
                return GkService.this.mConfigController.getGatekeeper(str, z).booleanValue();
            }
            return z;
        }

        @Override // com.oculus.aidl.IGkService
        public void overrideGatekeeper(String str, boolean z) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            GkService.this.mConfigUpdater.setGatekeeperOverride(str, z);
        }

        @Override // com.oculus.aidl.IGkService
        public boolean registerGatekeeper(String str) {
            GkService.this.mCallerVerifier.enforceTrustedCaller();
            return GkService.this.mDynamicGatekeeperRegistry.mGatekeeperSet.add(str);
        }
    };
    @Inject
    @Eager
    public GkServiceCallerVerifier mCallerVerifier;
    @Inject
    @Eager
    public ConfigController mConfigController;
    @Inject
    @Eager
    public ConfigUpdater mConfigUpdater;
    @Inject
    @Eager
    public DynamicGatekeeperRegistry mDynamicGatekeeperRegistry;
    @Inject
    @Eager
    public ThreadUtils mThreadUtils;

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public static final void _UL_injectMe(Context context, GkService gkService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), gkService);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, GkService gkService) {
        gkService.mThreadUtils = ThreadUtils.A01(r1);
        gkService.mConfigUpdater = ConfigUpdater._UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_ACCESS_METHOD(r1);
        gkService.mConfigController = ConfigController._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_ACCESS_METHOD(r1);
        gkService.mDynamicGatekeeperRegistry = ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_ACCESS_METHOD(r1);
        gkService.mCallerVerifier = GkServiceCallerVerifier._UL__ULSEP_com_oculus_config_service_GkServiceCallerVerifier_ULSEP_ACCESS_METHOD(r1);
    }

    public static /* synthetic */ String access$400() {
        return TAG;
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
