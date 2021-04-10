package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.fasterxml.jackson.core.JsonFactory;

@Generated({"By: InjectorProcessor"})
public class JsonFactoryMethodAutoProvider extends AbstractProvider<JsonFactory> {
    @Override // javax.inject.Provider
    public JsonFactory get() {
        return FbJsonModule.provideJsonFactory();
    }
}
