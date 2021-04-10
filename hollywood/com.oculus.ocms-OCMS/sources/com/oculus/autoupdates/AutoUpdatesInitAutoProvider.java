package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AutoUpdatesInitAutoProvider extends AbstractProvider<AutoUpdatesInit> {
    @Override // javax.inject.Provider
    public AutoUpdatesInit get() {
        return new AutoUpdatesInit(this);
    }
}
