package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SystemPropertiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SystemPropertiesModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract double getNumberImpl(String str, double d);

    /* access modifiers changed from: protected */
    public abstract String getStringImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getNumber", "-sd"));
        list.add(new Pair<>("getString", "-ss"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final Object getNumber(String key, double defaultValue) {
        return Double.valueOf(getNumberImpl(key, defaultValue));
    }

    /* access modifiers changed from: protected */
    public final Object getString(String key, String defaultValue) {
        return getStringImpl(key, defaultValue);
    }

    public void shutdownModule() {
    }
}
