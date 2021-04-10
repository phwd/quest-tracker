package com.oculus.ocms.library.provider;

import com.facebook.inject.AbstractComponentProvider;
import com.facebook.inject.InjectorLike;

public class LibraryProviderAutoProvider extends AbstractComponentProvider<LibraryProvider> {
    public void inject(LibraryProvider libraryProvider) {
        LibraryProvider._UL_staticInjectMe((InjectorLike) this, libraryProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof LibraryProviderAutoProvider;
    }
}
