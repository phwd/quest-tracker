package com.oculus.util.vr;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;
import java.lang.reflect.InvocationTargetException;

public class VRUtils {
    public static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    public static final String METADATA_VR_MODE_DUAL = "dual";
    public static final String METADATA_VR_MODE_KEY = "com.samsung.android.vr.application.mode";
    public static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    public static final String TAG = "com.oculus.util.vr.VRUtils";
    public final Context mContext;
    public final boolean mStandaloneDevice;

    public boolean isInteractive() {
        return ((PowerManager) this.mContext.getSystemService("power")).isInteractive();
    }

    public boolean isVRModeActive() {
        if (this.mStandaloneDevice) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.app.IVRManager");
            Object systemService = this.mContext.getSystemService((String) cls.getDeclaredField("VR_MANAGER").get(cls));
            if (systemService != null) {
                return ((Boolean) cls.getDeclaredMethod("isVRMode", new Class[0]).invoke(systemService, new Object[0])).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public boolean isVrApp(String str) {
        if (this.mStandaloneDevice) {
            return true;
        }
        try {
            Bundle bundle = this.mContext.getPackageManager().getPackageInfo(str, 128).applicationInfo.metaData;
            String str2 = null;
            if (bundle != null) {
                str2 = bundle.getString("com.samsung.android.vr.application.mode", null);
            }
            if ("vr_only".equals(str2) || "dual".equals(str2)) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public VRUtils(Context context) {
        this.mContext = context;
        this.mStandaloneDevice = context.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
    }

    public boolean isStandaloneDevice() {
        return this.mStandaloneDevice;
    }
}
