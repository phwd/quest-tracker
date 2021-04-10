package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class HmdPairingCodeModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "HmdPairingCodeModule";

    /* access modifiers changed from: protected */
    public abstract String getHmdPairingCodeImpl();

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
        arrayList.add(new Pair("getHmdPairingCode", "-"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final Object getHmdPairingCode() {
        return getHmdPairingCodeImpl();
    }
}
