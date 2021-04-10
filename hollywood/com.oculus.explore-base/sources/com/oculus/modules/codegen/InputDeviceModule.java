package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class InputDeviceModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = InputDeviceModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getGameControllerIdListImpl(Resolver<List<String>> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getGameControllerIdList", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getGameControllerIdList(int resolveID, int rejectID) {
        getGameControllerIdListImpl(ResolverFactory.createStringListResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
