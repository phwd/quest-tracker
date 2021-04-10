package com.oculus.content;

import X.AnonymousClass0VK;

public class OculusPublicContentProviderAutoProvider extends AnonymousClass0VK<OculusPublicContentProvider> {
    public boolean equals(Object obj) {
        return obj instanceof OculusPublicContentProviderAutoProvider;
    }

    public void inject(OculusPublicContentProvider oculusPublicContentProvider) {
        OculusPublicContentProvider._UL_staticInjectMe(this, oculusPublicContentProvider);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        OculusPublicContentProvider._UL_staticInjectMe(this, (OculusPublicContentProvider) obj);
    }
}
