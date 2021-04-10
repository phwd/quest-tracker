package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class FbObjectMapperMethodAutoProvider extends AbstractProvider<FbObjectMapper> {
    @Override // javax.inject.Provider
    public FbObjectMapper get() {
        return FbJsonModule.provideObjectMapper();
    }
}
