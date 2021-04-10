package com.oculus.content;

import com.facebook.inject.AbstractComponentProvider;

public class OculusSecureContentProviderAutoProvider extends AbstractComponentProvider<OculusSecureContentProvider> {
    public void inject(OculusSecureContentProvider oculusSecureContentProvider) {
        OculusSecureContentProvider._UL_staticInjectMe(this, oculusSecureContentProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusSecureContentProviderAutoProvider;
    }
}
