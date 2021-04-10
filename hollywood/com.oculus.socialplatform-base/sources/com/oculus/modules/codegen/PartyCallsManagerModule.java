package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class PartyCallsManagerModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "PartyCallsManagerModule";

    public abstract void createPartyImpl(String str, boolean z, String str2, Resolver<String> resolver);

    public abstract void joinPartyImpl(String str, boolean z, Resolver<Void> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(CreateVrInviteViewModel.CREATE_PARTY_KEY, "+sbsii"));
        arrayList.add(new Pair("joinParty", "+sbii"));
        return arrayList;
    }

    public final void createParty(String str, boolean z, String str2, int i, int i2) {
        createPartyImpl(str, z, str2, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void joinParty(String str, boolean z, int i, int i2) {
        joinPartyImpl(str, z, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
