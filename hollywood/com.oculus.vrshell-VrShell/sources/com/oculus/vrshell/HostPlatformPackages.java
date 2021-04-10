package com.oculus.vrshell;

import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.MicrophoneUsage;
import com.oculus.vrshell.panelservice.VerifierConstants;
import com.oculus.vrshell.util.HorizonUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class HostPlatformPackages {
    private static final Set<String> APPS_REQUIRING_AVATARS = new HashSet(Arrays.asList("com.dreamdev.deathhorizon", "com.estudiofuture.fusionWars07Arcade", "com.hulu.huluvr", "com.oculus.rooms", VerifierConstants.OCULUS_VENUES_PACKAGE_NAME, "com.marineverse.more", "com.parkline.wander", "com.pileated.tippettVR", "com.PixelToys.DropDead", "com.tinycontent.gearpoker", "games.b4t.epicrollercoasters.oculus", "net.vtime.gearvr"));
    private static final String PACKAGE_INFO_KEY_APP_NEEDS_AVATAR = "requiresAvatars";
    private static final String PACKAGE_INFO_KEY_CAN_UPDATE = "canUpdate";
    private static final String PACKAGE_INFO_KEY_HAS_CLOUD_STORAGE_ENABLED = "hasCloudStorageEnabled";
    private static final String PACKAGE_INFO_KEY_HAS_UPDATE = "hasUpdate";
    private static final String PACKAGE_INFO_KEY_IS_DEGRADED = "isDegraded";
    private static final String PACKAGE_INFO_KEY_IS_DISABLED = "isDisabled";
    private static final String PACKAGE_INFO_KEY_IS_DUC_NON_COMPLIANT = "isDucNonCompliant";
    private static final String PACKAGE_INFO_KEY_IS_INSTALLED = "isInstalled";
    private static final String PACKAGE_INFO_KEY_REQUIRES_6DOF = "requires6dof";
    private static final String PACKAGE_INFO_KEY_REQUIRES_EXCLUSIVE_MICROPHONE = "requiresExclusiveMicrophone";
    private static final String PACKAGE_INFO_KEY_SUPPORTED_INPUT_METHODS = "supportedInputMethods";
    private static final String PACKAGE_INFO_KEY_USER_HAS_AVATAR = "avatarConfigured";
    private static final String TAG = LoggingUtil.tag(HostPlatformPackages.class);
    private final WeakReference<ShellApplication> shellContext;

    public HostPlatformPackages(ShellApplication shellApplication) {
        this.shellContext = new WeakReference<>(shellApplication);
    }

    public String getPackageInfoJson(String str) {
        App application;
        ShellApplication shellApplication = this.shellContext.get();
        if (shellApplication == null || str == null || str.isEmpty() || (application = HorizonUtil.getApplication(shellApplication, str)) == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = true;
            jSONObject.put(PACKAGE_INFO_KEY_HAS_CLOUD_STORAGE_ENABLED, application.cloudStorageStatus != CloudStorageStatus.DISABLED);
            jSONObject.put(PACKAGE_INFO_KEY_HAS_UPDATE, application.hasUpdate());
            jSONObject.put(PACKAGE_INFO_KEY_IS_DEGRADED, application.versionCode < application.minRecommendedVersionCode);
            jSONObject.put(PACKAGE_INFO_KEY_IS_DISABLED, application.versionCode < application.minRequiredVersionCode);
            jSONObject.put(PACKAGE_INFO_KEY_IS_DUC_NON_COMPLIANT, application.ducNonCompliant);
            jSONObject.put(PACKAGE_INFO_KEY_IS_INSTALLED, shellApplication.isPackageInstalled(str));
            jSONObject.put(PACKAGE_INFO_KEY_REQUIRES_6DOF, application.headTracking == HeadTracking.REQUIRE_6DOF);
            jSONObject.put(PACKAGE_INFO_KEY_REQUIRES_EXCLUSIVE_MICROPHONE, application.microphoneUsage == MicrophoneUsage.EXCLUSIVE);
            jSONObject.put(PACKAGE_INFO_KEY_SUPPORTED_INPUT_METHODS, HorizonUtil.getSupportedInputMethodsBitField(shellApplication, str));
            boolean contains = APPS_REQUIRING_AVATARS.contains(str);
            jSONObject.put(PACKAGE_INFO_KEY_APP_NEEDS_AVATAR, contains);
            jSONObject.put(PACKAGE_INFO_KEY_USER_HAS_AVATAR, contains ? shellApplication.getCurrentAndResetAvatarStatus() : true);
            if (application.hasUpdate() && !Build.TYPE.equals(ServiceContract.EXTRA_USER)) {
                try {
                    if ((shellApplication.getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                        z = false;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            jSONObject.put(PACKAGE_INFO_KEY_CAN_UPDATE, z);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Failed to encode package info.", e);
            return "";
        }
    }
}
