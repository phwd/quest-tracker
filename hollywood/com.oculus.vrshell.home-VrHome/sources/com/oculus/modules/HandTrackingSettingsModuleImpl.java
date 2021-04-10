package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.oculus.modules.codegen.HandTrackingSettingsModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.secure.trustedapp.CallerInfoHelper;

public class HandTrackingSettingsModuleImpl extends HandTrackingSettingsModule {
    private static final String ENABLE_HAND_TRACKING_ACTION = "enable_hand_tracking";
    private static final String HAND_TRACKING_ENABLED_KEY = "hand_tracking_enabled";
    private static final String HAND_TRACKING_OPT_IN_KEY = "hand_tracking_opt_in";
    private static final String SYSTEM_UTILITIES_PACKAGE_NAME = "com.oculus.systemutilities";
    private static final String TAG = HandTrackingSettingsModuleImpl.class.getSimpleName();
    private Context mContext;
    private SettingsManager mSettingsManager = new SettingsManager();
    private SettingsObserverCallback mSettingsObserverCallback;

    public HandTrackingSettingsModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.HandTrackingSettingsModule
    public void enableHandTrackingImpl() {
        Intent intent = new Intent(ENABLE_HAND_TRACKING_ACTION);
        intent.setPackage("com.oculus.systemutilities");
        CallerInfoHelper.attachCallerInfo(intent, this.mContext, TAG + ":enableHandTrackingImpl()");
        this.mContext.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.HandTrackingSettingsModule
    public void startListeningForSettingsChangesImpl(Resolver<Boolean> resolver) {
        if (this.mSettingsObserverCallback == null) {
            this.mSettingsObserverCallback = new SettingsObserverCallback() {
                /* class com.oculus.modules.HandTrackingSettingsModuleImpl.AnonymousClass1 */

                @Override // com.oculus.os.SettingsObserverCallback
                public void onSettingChange(String settingName) {
                    HandTrackingSettingsModuleImpl.this.updateHandTrackingSettings();
                }
            };
        }
        Handler handler = new Handler(this.mContext.getMainLooper());
        this.mSettingsManager.registerSettingsObserver("hand_tracking_opt_in", this.mSettingsObserverCallback, handler);
        this.mSettingsManager.registerSettingsObserver("hand_tracking_enabled", this.mSettingsObserverCallback, handler);
        resolver.resolve(true);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.HandTrackingSettingsModule
    public void stopListeningForSettingsChangesImpl(Resolver<Boolean> resolver) {
        if (this.mSettingsObserverCallback == null) {
            resolver.resolve(false);
            return;
        }
        this.mSettingsManager.unregisterSettingsObserver("hand_tracking_opt_in", this.mSettingsObserverCallback);
        this.mSettingsManager.unregisterSettingsObserver("hand_tracking_enabled", this.mSettingsObserverCallback);
        resolver.resolve(true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateHandTrackingSettings() {
        HandTrackingSettingsModule.HandTrackingSettingsUpdate settingsUpdate = new HandTrackingSettingsModule.HandTrackingSettingsUpdate();
        settingsUpdate.optedIn = this.mSettingsManager.getBoolean("hand_tracking_opt_in", false);
        settingsUpdate.enabled = this.mSettingsManager.getBoolean("hand_tracking_enabled", false);
        emitOnHandTrackingSettingsUpdated(settingsUpdate);
    }
}
