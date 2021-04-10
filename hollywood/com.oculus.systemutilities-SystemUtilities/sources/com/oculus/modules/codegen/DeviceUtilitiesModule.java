package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class DeviceUtilitiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = DeviceUtilitiesModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void checkForOTAImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void checkPermissionGrantedImpl(String str, String str2, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getMacAddressImpl(Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void restartHeadsetImpl();

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("checkForOTA", "+ii"));
        list.add(new Pair<>("checkPermissionGranted", "+ssii"));
        list.add(new Pair<>("getMacAddress", "+ii"));
        list.add(new Pair<>("restartHeadset", ""));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void checkForOTA(int resolveID, int rejectID) {
        checkForOTAImpl(ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void checkPermissionGranted(String packageName, String permissionKey, int resolveID, int rejectID) {
        checkPermissionGrantedImpl(packageName, permissionKey, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getMacAddress(int resolveID, int rejectID) {
        getMacAddressImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void restartHeadset() {
        restartHeadsetImpl();
    }

    public void shutdownModule() {
    }
}
