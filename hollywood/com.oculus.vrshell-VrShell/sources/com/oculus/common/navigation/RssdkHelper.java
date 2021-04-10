package com.oculus.common.navigation;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public class RssdkHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = LoggingUtil.tag(RssdkHelper.class);
    private long mRssdkPtr = 0;

    private static native String nativeGetCurrentPackageInfo(long j);

    private static native long nativeRssdkInit(Context context);

    public RssdkHelper(Context context) {
        Log.d(TAG, "Init");
        this.mRssdkPtr = nativeRssdkInit(context);
    }

    public CurrentPackageInfo getCurrentPackageInfo() {
        Log.d(TAG, "getCurrentPackageInfo");
        try {
            String[] split = nativeGetCurrentPackageInfo(this.mRssdkPtr).split(" ");
            return new CurrentPackageInfo(split[0], Boolean.parseBoolean(split[1]));
        } catch (Throwable th) {
            String str = TAG;
            Log.e(str, "getCurrentPackageInfo " + th);
            return new CurrentPackageInfo("[Error]", false);
        }
    }

    public class CurrentPackageInfo {
        public final boolean mIsFocusAware;
        public final String mPackageName;

        CurrentPackageInfo(String str, boolean z) {
            this.mPackageName = str;
            this.mIsFocusAware = z;
        }
    }
}
