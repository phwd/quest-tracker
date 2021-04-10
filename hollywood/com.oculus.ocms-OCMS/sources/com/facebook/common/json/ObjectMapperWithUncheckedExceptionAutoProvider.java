package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.common.objectmapper.ObjectMapperModule;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ObjectMapperWithUncheckedExceptionAutoProvider extends AbstractProvider<ObjectMapperWithUncheckedException> {
    @Override // javax.inject.Provider
    public ObjectMapperWithUncheckedException get() {
        return new ObjectMapperWithUncheckedException(ObjectMapperModule._UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_ACCESS_METHOD(this));
    }
}
