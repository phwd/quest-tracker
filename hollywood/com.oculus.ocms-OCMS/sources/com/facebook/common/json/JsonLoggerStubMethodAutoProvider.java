package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class JsonLoggerStubMethodAutoProvider extends AbstractProvider<JsonLoggerStub> {
    @Override // javax.inject.Provider
    public JsonLoggerStub get() {
        return FbJsonModule.provideJsonLoggerStub();
    }
}
