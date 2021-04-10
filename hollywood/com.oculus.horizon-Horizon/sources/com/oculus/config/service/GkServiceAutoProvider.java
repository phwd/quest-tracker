package com.oculus.config.service;

import X.AnonymousClass0J9;

public class GkServiceAutoProvider extends AnonymousClass0J9<GkService> {
    public boolean equals(Object obj) {
        return obj instanceof GkServiceAutoProvider;
    }

    public void inject(GkService gkService) {
        GkService._UL_staticInjectMe(this, gkService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        GkService._UL_staticInjectMe(this, (GkService) obj);
    }
}
