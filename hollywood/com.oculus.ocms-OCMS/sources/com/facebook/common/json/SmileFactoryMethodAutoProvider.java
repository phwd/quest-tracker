package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.common.objectmapper.ObjectMapperModule;
import com.facebook.inject.AbstractProvider;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

@Generated({"By: InjectorProcessor"})
public class SmileFactoryMethodAutoProvider extends AbstractProvider<SmileFactory> {
    @Override // javax.inject.Provider
    public SmileFactory get() {
        return FbJsonModule.provideSmileFactory(ObjectMapperModule._UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_ACCESS_METHOD(this));
    }
}
