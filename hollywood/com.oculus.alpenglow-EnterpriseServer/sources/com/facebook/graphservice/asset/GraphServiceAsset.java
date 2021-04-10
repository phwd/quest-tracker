package com.facebook.graphservice.asset;

import X.AnonymousClass0PF;
import X.C05400jG;
import android.content.Context;
import com.facebook.graphservice.nativeconfigloader.GraphServiceNativeConfigLoader;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GraphServiceAsset {
    public final HybridData mHybridData;

    public static native HybridData initHybridData(String str, boolean z);

    static {
        C05400jG.A00("graphservice-jni-asset");
    }

    @DoNotStrip
    public GraphServiceAsset(String str, Context context) {
        this.mHybridData = initHybridData(str, AnonymousClass0PF.A00(context));
        GraphServiceNativeConfigLoader.loadNativeConfigs();
    }
}
