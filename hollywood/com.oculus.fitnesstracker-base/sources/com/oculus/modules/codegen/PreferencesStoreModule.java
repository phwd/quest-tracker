package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PreferencesStoreModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "PreferencesStoreModule";

    /* access modifiers changed from: protected */
    public abstract void getForceApplicationFocusAwarenessImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getHandTrackingOverrideFrequencyImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getMouseSensitivityImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void setForceApplicationFocusAwarenessImpl(double d);

    /* access modifiers changed from: protected */
    public abstract void setHandTrackingOverrideFrequencyImpl(double d);

    /* access modifiers changed from: protected */
    public abstract void setMouseSensitivityImpl(double d);

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getForceApplicationFocusAwareness", "+ii"));
        arrayList.add(new Pair("getHandTrackingOverrideFrequency", "+ii"));
        arrayList.add(new Pair("getMouseSensitivity", "+ii"));
        arrayList.add(new Pair("setForceApplicationFocusAwareness", "d"));
        arrayList.add(new Pair("setHandTrackingOverrideFrequency", "d"));
        arrayList.add(new Pair("setMouseSensitivity", "d"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void getForceApplicationFocusAwareness(int i, int i2) {
        getForceApplicationFocusAwarenessImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getHandTrackingOverrideFrequency(int i, int i2) {
        getHandTrackingOverrideFrequencyImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getMouseSensitivity(int i, int i2) {
        getMouseSensitivityImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void setForceApplicationFocusAwareness(double d) {
        setForceApplicationFocusAwarenessImpl(d);
    }

    /* access modifiers changed from: protected */
    public final void setHandTrackingOverrideFrequency(double d) {
        setHandTrackingOverrideFrequencyImpl(d);
    }

    /* access modifiers changed from: protected */
    public final void setMouseSensitivity(double d) {
        setMouseSensitivityImpl(d);
    }
}
