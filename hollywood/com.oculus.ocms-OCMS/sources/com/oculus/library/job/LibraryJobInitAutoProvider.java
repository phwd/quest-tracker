package com.oculus.library.job;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryJobInitAutoProvider extends AbstractProvider<LibraryJobInit> {
    @Override // javax.inject.Provider
    public LibraryJobInit get() {
        return new LibraryJobInit(this);
    }
}
