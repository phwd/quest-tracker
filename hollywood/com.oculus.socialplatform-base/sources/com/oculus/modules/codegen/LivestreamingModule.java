package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class LivestreamingModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "LivestreamingModule";

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public abstract void startLivestreamingImpl(String str, String str2, String str3, String str4, boolean z, boolean z2, Resolver<Void> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("startLivestreaming", "+ssssbbii"));
        return arrayList;
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void startLivestreaming(String str, String str2, String str3, String str4, boolean z, boolean z2, int i, int i2) {
        startLivestreamingImpl(str, str2, str3, str4, z, z2, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
