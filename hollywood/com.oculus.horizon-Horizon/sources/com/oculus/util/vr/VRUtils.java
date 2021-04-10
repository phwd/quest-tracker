package com.oculus.util.vr;

import android.content.Context;

public class VRUtils {
    public static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    public static final String METADATA_VR_MODE_DUAL = "dual";
    public static final String METADATA_VR_MODE_KEY = "com.samsung.android.vr.application.mode";
    public static final String METADATA_VR_MODE_VR_ONLY = "vr_only";
    public static final String TAG = "com.oculus.util.vr.VRUtils";
    public final Context mContext;
    public final boolean mStandaloneDevice;

    public VRUtils(Context context) {
        this.mContext = context;
        this.mStandaloneDevice = context.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
    }
}
