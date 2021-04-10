package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PreferencesStoreModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = PreferencesStoreModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getForceApplicationFocusAwarenessImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getHandTrackingOverrideFrequencyImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getMouseSensitivityImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void setForceApplicationFocusAwarenessImpl(double d);

    /* access modifiers changed from: protected */
    public abstract void setHandTrackingOverrideFrequencyImpl(double d);

    /* access modifiers changed from: protected */
    public abstract void setMouseSensitivityImpl(double d);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getForceApplicationFocusAwareness", "+ii"));
        list.add(new Pair<>("getHandTrackingOverrideFrequency", "+ii"));
        list.add(new Pair<>("getMouseSensitivity", "+ii"));
        list.add(new Pair<>("setForceApplicationFocusAwareness", "d"));
        list.add(new Pair<>("setHandTrackingOverrideFrequency", "d"));
        list.add(new Pair<>("setMouseSensitivity", "d"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getForceApplicationFocusAwareness(int resolveID, int rejectID) {
        getForceApplicationFocusAwarenessImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getHandTrackingOverrideFrequency(int resolveID, int rejectID) {
        getHandTrackingOverrideFrequencyImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getMouseSensitivity(int resolveID, int rejectID) {
        getMouseSensitivityImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setForceApplicationFocusAwareness(double value) {
        setForceApplicationFocusAwarenessImpl(value);
    }

    /* access modifiers changed from: protected */
    public final void setHandTrackingOverrideFrequency(double value) {
        setHandTrackingOverrideFrequencyImpl(value);
    }

    /* access modifiers changed from: protected */
    public final void setMouseSensitivity(double value) {
        setMouseSensitivityImpl(value);
    }

    public void shutdownModule() {
    }
}
