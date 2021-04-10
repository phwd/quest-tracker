package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class ControllerManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ControllerManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getInputHapticsAmplitudeImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getOculusButtonLongPressImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getOculusButtonMovementDetectionImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setInputHapticsAmplitudeImpl(double d, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setOculusButtonLongPressImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setOculusButtonMovementDetectionImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getInputHapticsAmplitude", "+ii"));
        list.add(new Pair<>("getOculusButtonLongPress", "+ii"));
        list.add(new Pair<>("getOculusButtonMovementDetection", "+ii"));
        list.add(new Pair<>("setInputHapticsAmplitude", "+dii"));
        list.add(new Pair<>("setOculusButtonLongPress", "+bii"));
        list.add(new Pair<>("setOculusButtonMovementDetection", "+bii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getInputHapticsAmplitude(int resolveID, int rejectID) {
        getInputHapticsAmplitudeImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getOculusButtonLongPress(int resolveID, int rejectID) {
        getOculusButtonLongPressImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getOculusButtonMovementDetection(int resolveID, int rejectID) {
        getOculusButtonMovementDetectionImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setInputHapticsAmplitude(double value, int resolveID, int rejectID) {
        setInputHapticsAmplitudeImpl(value, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setOculusButtonLongPress(boolean enabled, int resolveID, int rejectID) {
        setOculusButtonLongPressImpl(enabled, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setOculusButtonMovementDetection(boolean enabled, int resolveID, int rejectID) {
        setOculusButtonMovementDetectionImpl(enabled, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
