package com.oculus.content;

import com.facebook.inject.AbstractComponentProvider;

public class OculusFbPermissionsContentProviderAutoProvider extends AbstractComponentProvider<OculusFbPermissionsContentProvider> {
    public void inject(OculusFbPermissionsContentProvider oculusFbPermissionsContentProvider) {
        OculusFbPermissionsContentProvider._UL_staticInjectMe(this, oculusFbPermissionsContentProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusFbPermissionsContentProviderAutoProvider;
    }
}
