package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class FirstTimeNUXSettingsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = FirstTimeNUXSettingsModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void markAllowGuardianImpl();

    /* access modifiers changed from: protected */
    public abstract void markFirstTimeNUXCompleteImpl();

    /* access modifiers changed from: protected */
    public abstract void markHealthSafetyCompleteImpl();

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("markAllowGuardian", ""));
        list.add(new Pair<>("markFirstTimeNUXComplete", ""));
        list.add(new Pair<>("markHealthSafetyComplete", ""));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void markAllowGuardian() {
        markAllowGuardianImpl();
    }

    /* access modifiers changed from: protected */
    public final void markFirstTimeNUXComplete() {
        markFirstTimeNUXCompleteImpl();
    }

    /* access modifiers changed from: protected */
    public final void markHealthSafetyComplete() {
        markHealthSafetyCompleteImpl();
    }

    public void shutdownModule() {
    }
}
