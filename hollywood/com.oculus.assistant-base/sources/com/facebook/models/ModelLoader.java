package com.facebook.models;

import X.AbstractC0241Mg;
import X.C0895oI;
import X.KJ;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.jni.HybridData;
import com.facebook.models.interfaces.ManifestLoaderBase;
import com.facebook.models.interfaces.ModelLoaderBase;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.HashSet;
import java.util.Set;

public class ModelLoader extends ModelLoaderBase {
    public static final Class TAG = ModelLoader.class;

    public static native HybridData initHybrid(XAnalyticsHolder xAnalyticsHolder, TigonServiceHolder tigonServiceHolder, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, ManifestLoaderBase manifestLoaderBase, String str);

    private native void load(String str, long j, Set set, ModelLoaderCallbacks modelLoaderCallbacks);

    static {
        KJ.A05("models-core", 0);
    }

    public ModelLoader(AbstractC0241Mg mg, TigonServiceHolder tigonServiceHolder, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, ManifestLoaderBase manifestLoaderBase, String str) {
        super(initHybrid(mg.A38(), tigonServiceHolder, androidAsyncExecutorFactory, manifestLoaderBase, str));
    }

    @Override // com.facebook.models.interfaces.ModelLoaderBase
    public ListenableFuture load(String str, long j) {
        HashSet hashSet = new HashSet();
        SettableFuture settableFuture = new SettableFuture();
        load(str, j, hashSet, new C0895oI(this, settableFuture));
        return settableFuture;
    }
}
