package com.facebook.common.android;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class RuntimeMethodAutoProvider extends AbstractProvider<Runtime> {
    @Override // javax.inject.Provider
    public Runtime get() {
        return AndroidModule.provideRuntime();
    }
}
