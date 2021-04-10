package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class JsonLoggerMethodAutoProvider extends AbstractProvider<JsonLogger> {
    @Override // javax.inject.Provider
    public JsonLogger get() {
        return FbJsonModule.provideJsonLogger(FbJsonModule._UL__ULSEP_com_facebook_common_json_JsonLoggerStub_ULSEP_ACCESS_METHOD(this));
    }
}
