package com.oculus.updater.credentialsmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.auth.device.DeviceAuthTokenSubscriber;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriberModule;
import com.oculus.auth.handler.AuthHandlerModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.common.build.BuildConfig;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.ossdk.inject.OsSdkModule;
import com.oculus.updater.credentialsmanager.CredentialsManagerModule;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies
@ApplicationScoped
public class OSUpdateCredentialsManager implements CredentialsManager, DeviceAuthTokenSubscriber {
    private static final long FETCH_TOKEN_RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(30);
    private static final String TAG = "OSUpdateCredentialsManager";
    private static volatile OSUpdateCredentialsManager _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    @Nullable
    private Credentials mCredentials;
    @Nullable
    private String mDeviceAccessToken;
    private String mDeviceId;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @AutoGeneratedAccessMethod
    public static final OSUpdateCredentialsManager _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OSUpdateCredentialsManager) UL.factorymap.get(CredentialsManagerModule.UL_id._UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OSUpdateCredentialsManager _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_INSTANCE == null) {
            synchronized (OSUpdateCredentialsManager.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_INSTANCE = new OSUpdateCredentialsManager(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_INSTANCE;
    }

    @Inject
    public OSUpdateCredentialsManager(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(3, injectorLike);
        setUserIdFromSettings();
        this.mDeviceAccessToken = ((DeviceAuthTokenStore) FbInjector.lazyInstance(2, NoOpDeviceAuthTokenSubscriberModule.UL_id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAuthToken();
        setCredentialsAndRunLoginHandlers();
        fetchDeviceAuthToken();
        ((SettingsManager) FbInjector.lazyInstance(1, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).registerSettingsObserver("device_ent_id", new SettingsObserverCallback() {
            /* class com.oculus.updater.credentialsmanager.OSUpdateCredentialsManager.AnonymousClass1 */

            public void onSettingChange(String str) {
                OSUpdateCredentialsManager.this.setUserIdFromSettings();
                OSUpdateCredentialsManager.this.setCredentialsAndRunLoginHandlers();
            }
        }, this.mHandler);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchDeviceAuthToken() {
        if (this.mDeviceAccessToken == null) {
            ((DeviceAuthTokenStore) FbInjector.lazyInstance(2, NoOpDeviceAuthTokenSubscriberModule.UL_id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID, this._UL_mInjectionContext)).fetchToken().thenRun((Runnable) new Runnable() {
                /* class com.oculus.updater.credentialsmanager.OSUpdateCredentialsManager.AnonymousClass2 */

                public void run() {
                    if (TextUtils.isEmpty(OSUpdateCredentialsManager.this.mDeviceAccessToken)) {
                        OSUpdateCredentialsManager.this.mHandler.postDelayed(new Runnable() {
                            /* class com.oculus.updater.credentialsmanager.OSUpdateCredentialsManager.AnonymousClass2.AnonymousClass1 */

                            public void run() {
                                OSUpdateCredentialsManager.this.fetchDeviceAuthToken();
                            }
                        }, OSUpdateCredentialsManager.FETCH_TOKEN_RETRY_INTERVAL);
                    }
                }
            });
        }
    }

    @Override // com.oculus.auth.device.DeviceAuthTokenSubscriber
    public void onTokenRefresh(Context context, String str) {
        BLog.d(TAG, "onTokenRefresh called");
        this.mDeviceAccessToken = str;
        setCredentialsAndRunLoginHandlers();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserIdFromSettings() {
        this.mDeviceId = ((SettingsManager) FbInjector.lazyInstance(1, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getString("device_ent_id", BuildConfig.PROVIDER_SUFFIX);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCredentialsAndRunLoginHandlers() {
        if (!(TextUtils.isEmpty(this.mDeviceId) || TextUtils.isEmpty(this.mDeviceAccessToken))) {
            this.mCredentials = new Credentials(this.mDeviceId, this.mDeviceAccessToken);
            for (LoginHandler loginHandler : (Set) FbInjector.lazyInstance(0, AuthHandlerModule.UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID, this._UL_mInjectionContext)) {
                BLog.d(TAG, "Executing login handler: %s", loginHandler.getClass().getSimpleName());
                loginHandler.afterLoginAsync();
            }
        }
    }

    @Override // com.oculus.auth.credentials.CredentialsManager
    @Nullable
    public Credentials getCredentials() {
        return this.mCredentials;
    }
}
