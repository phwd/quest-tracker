package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class DeviceUtilitiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "DeviceUtilitiesModule";

    /* access modifiers changed from: protected */
    public abstract void checkForOTAImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void checkPermissionGrantedImpl(String str, String str2, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getMacAddressImpl(Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void restartHeadsetImpl();

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("checkForOTA", "+ii"));
        arrayList.add(new Pair("checkPermissionGranted", "+ssii"));
        arrayList.add(new Pair("getMacAddress", "+ii"));
        arrayList.add(new Pair("restartHeadset", BuildConfig.PROVIDER_SUFFIX));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void checkForOTA(int i, int i2) {
        checkForOTAImpl(ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void checkPermissionGranted(String str, String str2, int i, int i2) {
        checkPermissionGrantedImpl(str, str2, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getMacAddress(int i, int i2) {
        getMacAddressImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void restartHeadset() {
        restartHeadsetImpl();
    }
}
