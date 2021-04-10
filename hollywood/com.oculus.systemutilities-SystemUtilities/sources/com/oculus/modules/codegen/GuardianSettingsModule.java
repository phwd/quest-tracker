package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class GuardianSettingsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = GuardianSettingsModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getElectricalGridValueImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void setElectricalGridValueImpl(double d);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getElectricalGridValue", "+ii"));
        list.add(new Pair<>("setElectricalGridValue", "d"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getElectricalGridValue(int resolveID, int rejectID) {
        getElectricalGridValueImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setElectricalGridValue(double value) {
        setElectricalGridValueImpl(value);
    }

    public void shutdownModule() {
    }
}
