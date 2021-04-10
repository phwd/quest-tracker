package com.oculus.socialplatform.partyservice;

import X.AnonymousClass0VK;

public class PartyServiceAutoProvider extends AnonymousClass0VK<PartyService> {
    public boolean equals(Object obj) {
        return obj instanceof PartyServiceAutoProvider;
    }

    public void inject(PartyService partyService) {
        PartyService._UL_staticInjectMe(this, partyService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        PartyService._UL_staticInjectMe(this, (PartyService) obj);
    }
}
