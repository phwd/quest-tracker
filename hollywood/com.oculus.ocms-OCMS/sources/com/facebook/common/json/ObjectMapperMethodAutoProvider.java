package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@Generated({"By: InjectorProcessor"})
public class ObjectMapperMethodAutoProvider extends AbstractProvider<ObjectMapper> {
    @Override // javax.inject.Provider
    public ObjectMapper get() {
        return FbJsonModule.provideDefaultObjectMapper(FbJsonModule._UL__ULSEP_com_facebook_common_json_FbObjectMapper_ULSEP_ACCESS_METHOD(this));
    }
}
