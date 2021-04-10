package com.oculus.ocms.library.provider;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryDownloadListenerAutoProvider extends AbstractProvider<LibraryDownloadListener> {
    @Override // javax.inject.Provider
    public LibraryDownloadListener get() {
        return new LibraryDownloadListener(this);
    }
}
