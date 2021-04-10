package com.oculus.library.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryStorageAutoProvider extends AbstractProvider<LibraryStorage> {
    @Override // javax.inject.Provider
    public LibraryStorage get() {
        return new LibraryStorage(this);
    }
}
