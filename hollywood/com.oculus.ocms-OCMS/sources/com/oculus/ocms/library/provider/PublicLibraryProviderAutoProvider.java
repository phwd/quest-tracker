package com.oculus.ocms.library.provider;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class PublicLibraryProviderAutoProvider extends AbstractComponentProvider<PublicLibraryProvider> {
    public void inject(PublicLibraryProvider publicLibraryProvider) {
        PublicLibraryProvider._UL_staticInjectMe((InjectorLike) this, publicLibraryProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof PublicLibraryProviderAutoProvider;
    }
}
