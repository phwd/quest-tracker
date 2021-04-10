package com.oculus.library.auth;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryAuthListenerAutoProvider extends AbstractProvider<LibraryAuthListener> {
    @Override // javax.inject.Provider
    public LibraryAuthListener get() {
        return new LibraryAuthListener(this);
    }
}
