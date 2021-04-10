package com.oculus.library.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryTableAutoProvider extends AbstractProvider<LibraryTable> {
    @Override // javax.inject.Provider
    public LibraryTable get() {
        return new LibraryTable();
    }
}
