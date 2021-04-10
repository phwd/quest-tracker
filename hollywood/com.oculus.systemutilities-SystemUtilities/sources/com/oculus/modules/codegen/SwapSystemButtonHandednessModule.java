package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SwapSystemButtonHandednessModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SwapSystemButtonHandednessModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getSwapSystemButtonHandednessImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setSwapSystemButtonHandednessImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getSwapSystemButtonHandedness", "+ii"));
        list.add(new Pair<>("setSwapSystemButtonHandedness", "+bii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getSwapSystemButtonHandedness(int resolveID, int rejectID) {
        getSwapSystemButtonHandednessImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setSwapSystemButtonHandedness(boolean enabled, int resolveID, int rejectID) {
        setSwapSystemButtonHandednessImpl(enabled, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
