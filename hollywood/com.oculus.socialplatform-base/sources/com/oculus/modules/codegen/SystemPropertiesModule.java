package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SystemPropertiesModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "SystemPropertiesModule";

    public abstract double getNumberImpl(String str, double d);

    public abstract String getStringImpl(String str, String str2);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getNumber", "-sd"));
        arrayList.add(new Pair("getString", "-ss"));
        return arrayList;
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final Object getNumber(String str, double d) {
        return Double.valueOf(getNumberImpl(str, d));
    }

    public final Object getString(String str, String str2) {
        return getStringImpl(str, str2);
    }
}
