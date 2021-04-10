package com.oculus.deviceconfigservice;

import android.content.pm.Signature;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.util.constants.OculusConstants;

/* JADX WARN: Init of enum OCULUS_BROWSER can be incorrect */
/* JADX WARN: Init of enum OCULUS_EXPLORE can be incorrect */
/* JADX WARN: Init of enum OCULUS_GUARDIAN can be incorrect */
/* JADX WARN: Init of enum OCULUS_SUSTEM_UTILITIES can be incorrect */
/* JADX WARN: Init of enum OCULUS_SYSTEM_SHELL can be incorrect */
/* JADX WARN: Init of enum OCULUS_SOCIAL_PLATFORM can be incorrect */
/* JADX WARN: Init of enum OCULUS_SYSTEM_UX can be incorrect */
/* JADX WARN: Init of enum OCULUS_VRDRIVER can be incorrect */
/* JADX WARN: Init of enum OCULUS_SHELL_HOME can be incorrect */
/* JADX WARN: Init of enum OCULUS_REACTVR_DEVICECONFIG_TEST can be incorrect */
/* JADX WARN: Init of enum OCULUS_SHELL_ENV can be incorrect */
/* JADX WARN: Init of enum OCULUS_HORIZON can be incorrect */
@Nullsafe(Nullsafe.Mode.LOCAL)
public enum DeviceConfigVerifiedApps {
    OCULUS_BROWSER("com.oculus.browser", OculusConstants.OCULUS_BROWSER_QUEST_APP_ID, r7, r8),
    OCULUS_EXPLORE("com.oculus.explore", "2646830762095036", r7, r8),
    OCULUS_GUARDIAN("com.oculus.guardian", "173071410476987", r7, r8),
    OCULUS_SUSTEM_UTILITIES(OculusConstants.PACKAGE_NAME_SYSTEM_UTILITIES, "1689311011174858", r7, r8),
    OCULUS_SYSTEM_SHELL("com.oculus.vrshell", "1031607236937163", r7, r8),
    OCULUS_SOCIAL_PLATFORM("com.oculus.socialplatform", "1953748974690454", r7, r8),
    OCULUS_SYSTEM_UX("com.oculus.systemux", "2141310506170802", r7, r8),
    OCULUS_VRDRIVER("com.oculus.vrdriver", "520345558148882", r7, r8),
    OCULUS_SHELL_HOME("com.oculus.vrshell.home", "1481000308606657", r7, r8),
    OCULUS_REACTVR_DEVICECONFIG_TEST("com.oculus.reactvrdeviceconfigtest", "3065422263553732", r7, r8),
    OCULUS_SHELL_ENV("com.oculus.shellenv", "3057535150987422", r7, r8),
    OCULUS_HORIZON("com.oculus.horizon", OculusConstants.HORIZON_APP_ID, r7, r8);
    
    public final String mAppID;
    public final DeviceConfigTrustedSignatures mDebugSignature;
    public final String mPackageName;
    public final DeviceConfigTrustedSignatures mReleaseSignature;

    /* access modifiers changed from: public */
    static {
        DeviceConfigTrustedSignatures deviceConfigTrustedSignatures = DeviceConfigTrustedSignatures.OCULUS_FIRST_PARTY_PROD_SIG;
        DeviceConfigTrustedSignatures deviceConfigTrustedSignatures2 = DeviceConfigTrustedSignatures.FBANDROID_FIRST_PARTY_DEBUG_SIG;
    }

    public Signature getDebugSignature() {
        return this.mDebugSignature.getSignature();
    }

    public Signature getReleaseSignature() {
        return this.mReleaseSignature.getSignature();
    }

    /* access modifiers changed from: public */
    DeviceConfigVerifiedApps(String str, String str2, DeviceConfigTrustedSignatures deviceConfigTrustedSignatures, DeviceConfigTrustedSignatures deviceConfigTrustedSignatures2) {
        this.mPackageName = str;
        this.mAppID = str2;
        this.mReleaseSignature = deviceConfigTrustedSignatures;
        this.mDebugSignature = deviceConfigTrustedSignatures2;
    }

    public String getAppId() {
        return this.mAppID;
    }

    public String getPackageName() {
        return this.mPackageName;
    }
}
