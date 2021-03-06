package com.facebook.common.json;

import com.facebook.common.json.FbJsonModule;
import com.facebook.common.objectmapper.ObjectMapperModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ObjectMapperWithUncheckedException {
    private static volatile ObjectMapperWithUncheckedException _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_INSTANCE;
    private final ObjectMapper mObjectMapper;

    @AutoGeneratedAccessMethod
    public static final ObjectMapperWithUncheckedException _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ObjectMapperWithUncheckedException) UL.factorymap.get(FbJsonModule.UL_id._UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ObjectMapperWithUncheckedException _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_INSTANCE == null) {
            synchronized (ObjectMapperWithUncheckedException.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_INSTANCE = new ObjectMapperWithUncheckedException(ObjectMapperModule._UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_common_json_ObjectMapperWithUncheckedException_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(FbJsonModule.UL_id._UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_common_json_ObjectMapperWithUncheckedException_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(FbJsonModule.UL_id._UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ObjectMapperWithUncheckedException(ObjectMapper objectMapper) {
        this.mObjectMapper = objectMapper;
    }

    public JsonNode readTree(String str) {
        try {
            return this.mObjectMapper.readTree(str);
        } catch (IOException e) {
            throw new JsonParseRuntimeException(e);
        }
    }

    public String writeValueAsString(Object obj) {
        try {
            return this.mObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonParseRuntimeException(e);
        }
    }

    public <T> T readValue(String str, Class<T> cls) {
        try {
            return (T) this.mObjectMapper.readValue(str, cls);
        } catch (IOException e) {
            throw new JsonParseRuntimeException(e);
        }
    }
}
