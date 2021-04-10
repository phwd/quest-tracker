package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class LivestreamingModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = LivestreamingModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void startLivestreamingImpl(String str, String str2, String str3, String str4, boolean z, boolean z2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("startLivestreaming", "+ssssbbii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void startLivestreaming(String location, String audience, String groupID, String pageID, boolean shouldTagGame, boolean isMicEnabled, int resolveID, int rejectID) {
        startLivestreamingImpl(location, audience, groupID, pageID, shouldTagGame, isMicEnabled, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
