package com.facebook.graphservice.nativeconfigloader;

import X.C05400jG;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingSoLoaderLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GraphServiceNativeConfigLoader {
    public static native void loadNativeConfigs();

    static {
        C05400jG.A00("graphservice-jni-nativeconfigloader");
    }
}
