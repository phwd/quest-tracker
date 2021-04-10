package com.oculus.library.refresher;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibraryCacheRefresherAutoProvider extends AbstractProvider<LibraryCacheRefresher> {
    @Override // javax.inject.Provider
    public LibraryCacheRefresher get() {
        return new LibraryCacheRefresher(this);
    }
}
