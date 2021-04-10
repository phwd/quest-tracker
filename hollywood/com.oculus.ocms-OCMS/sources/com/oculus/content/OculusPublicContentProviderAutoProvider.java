package com.oculus.content;

import com.facebook.inject.AbstractComponentProvider;

public class OculusPublicContentProviderAutoProvider extends AbstractComponentProvider<OculusPublicContentProvider> {
    public void inject(OculusPublicContentProvider oculusPublicContentProvider) {
        OculusPublicContentProvider._UL_staticInjectMe(this, oculusPublicContentProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusPublicContentProviderAutoProvider;
    }
}
