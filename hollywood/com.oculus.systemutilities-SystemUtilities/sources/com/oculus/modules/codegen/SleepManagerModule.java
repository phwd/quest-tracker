package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SleepManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SleepManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getAutoSleepTimeImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getAutoWakeEnabledImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setAutoSleepTimeImpl(double d, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setAutoWakeEnabledImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getAutoSleepTime", "+ii"));
        list.add(new Pair<>("getAutoWakeEnabled", "+ii"));
        list.add(new Pair<>("setAutoSleepTime", "+dii"));
        list.add(new Pair<>("setAutoWakeEnabled", "+bii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getAutoSleepTime(int resolveID, int rejectID) {
        getAutoSleepTimeImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getAutoWakeEnabled(int resolveID, int rejectID) {
        getAutoWakeEnabledImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setAutoSleepTime(double sleepTime, int resolveID, int rejectID) {
        setAutoSleepTimeImpl(sleepTime, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setAutoWakeEnabled(boolean autoWakeEnabled, int resolveID, int rejectID) {
        setAutoWakeEnabledImpl(autoWakeEnabled, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
