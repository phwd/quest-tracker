package com.facebook.common.identifiers;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class UniqueIdGeneratorAutoProvider extends AbstractProvider<UniqueIdGenerator> {
    @Override // javax.inject.Provider
    public UniqueIdGenerator get() {
        return new UniqueIdGenerator();
    }
}
