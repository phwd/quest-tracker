package com.oculus.util.vr;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import com.facebook.debug.log.BLog;
import java.lang.reflect.InvocationTargetException;

public class VRUtils {
    private static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    private static final String METADATA_VR_MODE_DUAL = "dual";
    private static final String METADATA_VR_MODE_KEY = "com.samsung.android.vr.application.mode";
    private static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    public static final String TAG = "com.oculus.util.vr.VRUtils";
    private final Context mContext;
    private final boolean mStandaloneDevice;

    public VRUtils(Context context) {
        this.mContext = context;
        this.mStandaloneDevice = context.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
    }

    public boolean isStandaloneDevice() {
        return this.mStandaloneDevice;
    }

    public boolean isInteractive() {
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        if (Build.VERSION.SDK_INT < 20) {
            return powerManager.isScreenOn();
        }
        return powerManager.isInteractive();
    }

    public boolean isVrApp(String str) {
        if (isStandaloneDevice()) {
            return true;
        }
        try {
            Bundle bundle = this.mContext.getPackageManager().getPackageInfo(str, 128).applicationInfo.metaData;
            String str2 = null;
            if (bundle != null) {
                str2 = bundle.getString(METADATA_VR_MODE_KEY, null);
            }
            if (METADATA_VR_MODE_VR_ONLY.equals(str2) || METADATA_VR_MODE_DUAL.equals(str2)) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean isVRModeActive() {
        if (isStandaloneDevice()) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.app.IVRManager");
            Object systemService = this.mContext.getSystemService((String) cls.getDeclaredField("VR_MANAGER").get(cls));
            if (systemService != null) {
                return ((Boolean) cls.getDeclaredMethod("isVRMode", new Class[0]).invoke(systemService, new Object[0])).booleanValue();
            }
            BLog.d(TAG, "VR_MANAGER service not found.");
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }
}
