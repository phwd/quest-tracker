package com.oculus.dumpsysledger;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DumpsysInitAutoProvider extends AbstractProvider<DumpsysInit> {
    @Override // javax.inject.Provider
    public DumpsysInit get() {
        return new DumpsysInit(this);
    }
}
