package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public abstract class ConfigOverrideModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ConfigOverrideModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void fetchGKOverridesImpl(Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchQEOverridesImpl(Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("fetchGKOverrides", "+ii"));
        list.add(new Pair<>("fetchQEOverrides", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void fetchGKOverrides(int resolveID, int rejectID) {
        fetchGKOverridesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchQEOverrides(int resolveID, int rejectID) {
        fetchQEOverridesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
