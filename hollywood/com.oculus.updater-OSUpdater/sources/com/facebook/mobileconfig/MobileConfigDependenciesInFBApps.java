package com.facebook.mobileconfig;

import android.content.res.AssetManager;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import java.io.File;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigDependenciesInFBApps {
    @DoNotStrip
    private final HybridData mHybridData;

    private native MobileConfigManagerHolderImpl createManagerInternal(String str, String str2, String str3, String str4, String str5, @Nullable AssetManager assetManager, boolean z, @Nullable MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder, Map<String, String> map);

    private static native HybridData initHybrid(@Nullable AndroidAsyncExecutorFactory androidAsyncExecutorFactory, @Nullable TigonServiceHolder tigonServiceHolder, boolean z, @Nullable XAnalyticsHolder xAnalyticsHolder);

    public static native void setNetworkService(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, TigonServiceHolder tigonServiceHolder, boolean z);

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }

    public MobileConfigDependenciesInFBApps(@Nullable AndroidAsyncExecutorFactory androidAsyncExecutorFactory, @Nullable TigonServiceHolder tigonServiceHolder, boolean z, @Nullable XAnalyticsHolder xAnalyticsHolder) {
        this.mHybridData = initHybrid(androidAsyncExecutorFactory, tigonServiceHolder, z, xAnalyticsHolder);
    }

    public MobileConfigManagerHolderImpl createManager(File file, String str, String str2, String str3, String str4, @Nullable AssetManager assetManager, boolean z, @Nullable MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder, Map<String, String> map) {
        MobileConfigManagerHolderImpl createManagerInternal = createManagerInternal(file.getPath(), str, str2, str3, str4, assetManager, z, mobileConfigManagerParamsHolder, map);
        if (createManagerInternal != null && createManagerInternal.isValid()) {
            createManagerInternal.setDataDirPath(file.getAbsolutePath());
            createManagerInternal.setHasSessionId(str3 != null && !str3.isEmpty());
        }
        return createManagerInternal;
    }

    public static void setAuthenticatedNetworkService(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, TigonServiceHolder tigonServiceHolder) {
        setNetworkService(mobileConfigManagerHolderImpl, tigonServiceHolder, true);
    }
}
