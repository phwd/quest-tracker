package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service_media.MC;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.os.SettingsManager;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class InstantReplayHelper {
    public static final String OCULUS_LINK_CLIENT = "com.oculus.xrstreamingclient";
    public static final String OCULUS_SYSTEM_DRIVER = "com.oculus.systemdriver";
    public static final String OCULUS_VRSHELL = "com.oculus.vrshell";
    public static final String SYSTEM_DIALOG_PACKAGE_NAME = "android";
    public static final String SYSTEM_PERMISSIONS_DIALOG_NAME = "com.android.packageinstaller";
    public static final String SYSTEM_UI_PACKAGE_NAME = "com.android.systemui";
    public AnonymousClass0QC _UL_mInjectionContext;

    public final boolean A00(@Nullable SettingsManager settingsManager, @Nullable String str, OVRMediaServiceManager oVRMediaServiceManager) {
        OVRMediaServiceManager.VideoCaptureStatus videoCaptureStatus;
        if (str != null && ((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_mobile_instant_replay_horizon.enabled) && settingsManager != null && settingsManager.getBoolean("instant_replay_enabled", false) && !str.equals("android") && !str.equals("com.android.systemui") && !str.equals(SYSTEM_PERMISSIONS_DIALOG_NAME) && !str.equals("com.oculus.vrshell") && !str.equals("com.oculus.systemdriver") && !str.equals(OCULUS_LINK_CLIENT) && !oVRMediaServiceManager.mFileCapture.A05() && !oVRMediaServiceManager.A0d()) {
            synchronized (oVRMediaServiceManager) {
                videoCaptureStatus = oVRMediaServiceManager.mDefaultCaptureStatus;
            }
            if (videoCaptureStatus == OVRMediaServiceManager.VideoCaptureStatus.OFF) {
                return true;
            }
        }
        return false;
    }

    @Inject
    public InstantReplayHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
