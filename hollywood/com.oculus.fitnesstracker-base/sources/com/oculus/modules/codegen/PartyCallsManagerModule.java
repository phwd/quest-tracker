package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PartyCallsManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "PartyCallsManagerModule";

    /* access modifiers changed from: protected */
    public abstract void createPartyImpl(String str, boolean z, String str2, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void joinPartyImpl(String str, boolean z, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("createParty", "+sbsii"));
        arrayList.add(new Pair("joinParty", "+sbii"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void createParty(String str, boolean z, String str2, int i, int i2) {
        createPartyImpl(str, z, str2, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void joinParty(String str, boolean z, int i, int i2) {
        joinPartyImpl(str, z, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
