package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class LanguageModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = LanguageModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getDeviceLocaleImpl(Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void setDeviceLocaleImpl(String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getDeviceLocale", "+ii"));
        list.add(new Pair<>("setDeviceLocale", "+ssii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getDeviceLocale(int resolveID, int rejectID) {
        getDeviceLocaleImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setDeviceLocale(String language, String country, int resolveID, int rejectID) {
        setDeviceLocaleImpl(language, country, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
