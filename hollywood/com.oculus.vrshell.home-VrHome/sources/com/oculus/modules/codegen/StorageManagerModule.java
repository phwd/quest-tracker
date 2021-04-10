package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class StorageManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = StorageManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getAppSizeInBytesImpl(String str, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getAvailableBytesImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getTotalBytesImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getAppSizeInBytes", "+sii"));
        list.add(new Pair<>("getAvailableBytes", "+ii"));
        list.add(new Pair<>("getTotalBytes", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getAppSizeInBytes(String packageName, int resolveID, int rejectID) {
        getAppSizeInBytesImpl(packageName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getAvailableBytes(int resolveID, int rejectID) {
        getAvailableBytesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getTotalBytes(int resolveID, int rejectID) {
        getTotalBytesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
