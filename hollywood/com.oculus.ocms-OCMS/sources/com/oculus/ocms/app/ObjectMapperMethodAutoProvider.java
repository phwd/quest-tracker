package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@Generated({"By: InjectorProcessor"})
public class ObjectMapperMethodAutoProvider extends AbstractProvider<ObjectMapper> {
    @Override // javax.inject.Provider
    public ObjectMapper get() {
        return OCMSAppModule.provideObjectMapper();
    }
}
