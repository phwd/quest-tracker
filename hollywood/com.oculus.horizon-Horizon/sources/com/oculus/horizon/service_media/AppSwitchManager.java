package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import android.content.Context;
import android.os.Handler;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_ForegroundAppChecker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_InstantReplayHelper_ULSEP_BINDING_ID"})
public class AppSwitchManager {
    public static final String ENABLE_VIDEO_CAPTURE_KEY = "debug.oculus.enableVideoCapture";
    public static final String TAG = "AppSwitchManager";
    public AnonymousClass0QC _UL_mInjectionContext;
    public OVRMediaServiceManager mOVRMediaServiceManager;
    public String mPackageName;
    @Nullable
    public SettingsManager mSettingsManager;

    public interface AppSwitchListener {
        void onAppSwitch(String str);

        void onForegroundAppUpdate();
    }

    public class InstantReplaySettingObserver implements SettingsObserverCallback {
        public void onSettingChange(String str) {
            AppSwitchManager.this.mOVRMediaServiceManager.A0a(true);
        }

        public InstantReplaySettingObserver() {
        }
    }

    public final void A00(SettingsManager settingsManager, Context context) {
        this.mSettingsManager = settingsManager;
        settingsManager.registerSettingsObserver("instant_replay_enabled", new InstantReplaySettingObserver(), new Handler(context.getMainLooper()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        if (r9 != false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x015e, code lost:
        if (r8 != false) goto L_0x0160;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(java.lang.String r23) {
        /*
        // Method dump skipped, instructions count: 661
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.AppSwitchManager.A01(java.lang.String):void");
    }

    @Inject
    public AppSwitchManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
