package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class WebTaskLaunchModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = WebTaskLaunchModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void broadcastWebTaskLaunchImpl(String str);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("broadcastWebTaskLaunch", "s"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void broadcastWebTaskLaunch(String uri) {
        broadcastWebTaskLaunchImpl(uri);
    }

    public void shutdownModule() {
    }
}
