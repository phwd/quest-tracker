package com.facebook.common.json;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.fasterxml.jackson.core.JsonFactory;

@Generated({"By: InjectorProcessor"})
public class JsonFactory_com_facebook_common_json_SmileJsonFactoryMethodAutoProvider extends AbstractProvider<JsonFactory> {
    @Override // javax.inject.Provider
    public JsonFactory get() {
        return FbJsonModule.provideSmileJsonFactory(FbJsonModule._UL__ULSEP_com_fasterxml_jackson_dataformat_smile_SmileFactory_ULSEP_ACCESS_METHOD(this));
    }
}
