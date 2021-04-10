package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;

public abstract class HmdPairingCodeModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "HmdPairingCodeModule";

    public abstract String getHmdPairingCodeImpl();

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getHmdPairingCode", HelpFormatter.DEFAULT_OPT_PREFIX));
        return arrayList;
    }

    public final Object getHmdPairingCode() {
        return getHmdPairingCodeImpl();
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }
}
