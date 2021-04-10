package com.facebook.mobileconfig;

import android.content.res.AssetManager;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.tigon.iface.TigonServiceHolder;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigDependenciesInWearables {
    @DoNotStrip
    private final HybridData mHybridData;

    private native MobileConfigManagerHolderImpl createManagerInternal(String str, String str2, String str3, String str4, String str5, @Nullable AssetManager assetManager, boolean z, @Nullable MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder, Map<String, String> map);

    private static native HybridData initHybrid(@Nullable AndroidAsyncExecutorFactory androidAsyncExecutorFactory, @Nullable TigonServiceHolder tigonServiceHolder, boolean z, @Nullable MobileConfigCxxLogger mobileConfigCxxLogger);

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }
}
