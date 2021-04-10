package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SystemPropertiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "SystemPropertiesModule";

    /* access modifiers changed from: protected */
    public abstract double getNumberImpl(String str, double d);

    /* access modifiers changed from: protected */
    public abstract String getStringImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getNumber", "-sd"));
        arrayList.add(new Pair("getString", "-ss"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final Object getNumber(String str, double d) {
        return Double.valueOf(getNumberImpl(str, d));
    }

    /* access modifiers changed from: protected */
    public final Object getString(String str, String str2) {
        return getStringImpl(str, str2);
    }
}
