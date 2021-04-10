package com.facebook.models;

import X.KJ;
import com.facebook.jni.HybridData;
import com.facebook.models.interfaces.ManifestLoaderBase;
import java.util.List;

public class StaticManifestLoader extends ManifestLoaderBase {
    public static final Class TAG = StaticManifestLoader.class;

    public static native HybridData initHybrid(List list);

    static {
        KJ.A05("models-static", 0);
    }

    public StaticManifestLoader(List list) {
        super(initHybrid(list));
    }
}
