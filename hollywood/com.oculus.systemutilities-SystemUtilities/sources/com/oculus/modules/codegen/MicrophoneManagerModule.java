package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class MicrophoneManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = MicrophoneManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getMicrophoneStatusImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setMicrophoneStatusImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getMicrophoneStatus", "+ii"));
        list.add(new Pair<>("setMicrophoneStatus", "+bii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getMicrophoneStatus(int resolveID, int rejectID) {
        getMicrophoneStatusImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setMicrophoneStatus(boolean muted, int resolveID, int rejectID) {
        setMicrophoneStatusImpl(muted, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
