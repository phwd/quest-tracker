package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PartyCallsManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = PartyCallsManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void createPartyImpl(String str, boolean z, String str2, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void joinPartyImpl(String str, boolean z, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("createParty", "+sbsii"));
        list.add(new Pair<>("joinParty", "+sbii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void createParty(String partyType, boolean useSocialPlatformContentProvider, String socialActivityID, int resolveID, int rejectID) {
        createPartyImpl(partyType, useSocialPlatformContentProvider, socialActivityID, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void joinParty(String partyId, boolean useSocialPlatformContentProvider, int resolveID, int rejectID) {
        joinPartyImpl(partyId, useSocialPlatformContentProvider, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
