package com.facebook.mobileconfig;

import X.KV;
import android.content.res.AssetManager;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.jni.HybridData;
import java.util.Map;

public class MobileConfigUsingPureJavaDependencies {
    public final HybridData mHybridData;

    private native MobileConfigManagerHolderImpl createManagerInternal(String str, String str2, String str3, String str4, String str5, AssetManager assetManager, boolean z, MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder, Map map);

    public static native HybridData initHybrid(AndroidAsyncExecutorFactory androidAsyncExecutorFactory, MobileConfigFetcher mobileConfigFetcher, boolean z, MobileConfigCxxLogger mobileConfigCxxLogger);

    public static native void setNetworkService(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, MobileConfigFetcher mobileConfigFetcher, boolean z);

    static {
        KV.A01("mobileconfig-jni");
    }
}
