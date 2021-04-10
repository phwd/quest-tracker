package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class LanguageModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "LanguageModule";

    public abstract void getDeviceLocaleImpl(Resolver<String> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void setDeviceLocaleImpl(String str, String str2, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getDeviceLocale", "+ii"));
        arrayList.add(new Pair("setDeviceLocale", "+ssii"));
        return arrayList;
    }

    public final void getDeviceLocale(int i, int i2) {
        getDeviceLocaleImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final String getModuleName() {
        return "LanguageModule";
    }

    public final void setDeviceLocale(String str, String str2, int i, int i2) {
        setDeviceLocaleImpl(str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
