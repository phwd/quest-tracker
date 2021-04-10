package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PreferencesStoreModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "PreferencesStoreModule";

    public abstract void getForceApplicationFocusAwarenessImpl(Resolver<Double> resolver);

    public abstract void getHandTrackingOverrideFrequencyImpl(Resolver<Double> resolver);

    public abstract void getMouseSensitivityImpl(Resolver<Double> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void setForceApplicationFocusAwarenessImpl(double d);

    public abstract void setHandTrackingOverrideFrequencyImpl(double d);

    public abstract void setMouseSensitivityImpl(double d);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getForceApplicationFocusAwareness", "+ii"));
        arrayList.add(new Pair("getHandTrackingOverrideFrequency", "+ii"));
        arrayList.add(new Pair("getMouseSensitivity", "+ii"));
        arrayList.add(new Pair("setForceApplicationFocusAwareness", "d"));
        arrayList.add(new Pair("setHandTrackingOverrideFrequency", "d"));
        arrayList.add(new Pair("setMouseSensitivity", "d"));
        return arrayList;
    }

    public final void getForceApplicationFocusAwareness(int i, int i2) {
        getForceApplicationFocusAwarenessImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getHandTrackingOverrideFrequency(int i, int i2) {
        getHandTrackingOverrideFrequencyImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void getMouseSensitivity(int i, int i2) {
        getMouseSensitivityImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void setForceApplicationFocusAwareness(double d) {
        setForceApplicationFocusAwarenessImpl(d);
    }

    public final void setHandTrackingOverrideFrequency(double d) {
        setHandTrackingOverrideFrequencyImpl(d);
    }

    public final void setMouseSensitivity(double d) {
        setMouseSensitivityImpl(d);
    }
}
