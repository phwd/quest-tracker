package com.oculus.http.core;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import javax.inject.Provider;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID"})
public class ApiResponseConverter implements Converter {
    private static final String EVENT_BACKEND_EXCEPTION = "oculus_graph_backend_exception";
    private static final String EXTRA_EXCEPTION_CLASS = "exception_class";
    private static final String EXTRA_EXCEPTION_MESSAGE = "exception_message";
    private static final String EXTRA_EXCEPTION_STACK_TRACE = "exception_stack_trace";
    private static final String EXTRA_MESSAGE = "message";
    private static final String EXTRA_PATH = "path";
    private static final String EXTRA_SEVERITY = "severity";
    private static final String TAG = "ApiResponseConverter";
    private InjectionContext _UL_mInjectionContext;
    private final GsonConverter mGsonConverter;
    private final GsonConverter mGsonSingleMapConverter;

    public static class SingleEntryResponseWithErrorSupport<T> {
        public T data;
        public Error[] errors;
    }

    @AutoGeneratedAccessMethod
    public static final ApiResponseConverter _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApiResponseConverter) UL.factorymap.get(ApiModule.UL_id._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ApiResponseConverter _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new ApiResponseConverter(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_http_core_ApiResponseConverter_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(ApiModule.UL_id._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_http_core_ApiResponseConverter_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(ApiModule.UL_id._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ApiResponseConverter(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
        final Gson gson = new Gson();
        this.mGsonSingleMapConverter = new GsonConverter(new GsonBuilder().registerTypeHierarchyAdapter(Object.class, new JsonDeserializer<SingleEntryResponseWithErrorSupport>() {
            /* class com.oculus.http.core.ApiResponseConverter.AnonymousClass1 */

            @Override // com.google.gson.JsonDeserializer
            public SingleEntryResponseWithErrorSupport deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                SingleEntryResponseWithErrorSupport singleEntryResponseWithErrorSupport = new SingleEntryResponseWithErrorSupport();
                for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    if (entry.getKey().equals("errors")) {
                        singleEntryResponseWithErrorSupport.errors = (Error[]) gson.fromJson(entry.getValue(), Error[].class);
                    } else if (singleEntryResponseWithErrorSupport.data == null) {
                        singleEntryResponseWithErrorSupport.data = (T) gson.fromJson(entry.getValue(), ((ParameterizedType) type).getActualTypeArguments()[0]);
                    } else {
                        throw new JsonParseException("Encountered too many root objects");
                    }
                }
                return singleEntryResponseWithErrorSupport;
            }
        }).create());
        this.mGsonConverter = new GsonConverter(gson);
    }

    @Override // retrofit.converter.Converter
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        Object jsonObjectFromBody = getJsonObjectFromBody(typedInput, type);
        return isSingleEntryMapResponseType(type) ? getObjectFromJsonMapResponse(jsonObjectFromBody) : jsonObjectFromBody;
    }

    private Object getJsonObjectFromBody(TypedInput typedInput, Type type) throws ConversionException {
        Object obj;
        if (isSingleEntryMapResponseType(type)) {
            try {
                obj = this.mGsonSingleMapConverter.fromBody(typedInput, new SingleEntryResponseWithErrorSupportType(type));
            } catch (ConversionException e) {
                throw new ConversionException("Failed to parse concrete class out of map. Maybe the response wasn't an actual map? " + e.getMessage());
            }
        } else {
            obj = this.mGsonConverter.fromBody(typedInput, type);
        }
        if (obj != null) {
            return obj;
        }
        throw new ConversionException(getSimpleNameFromType(type) + " is null");
    }

    private String getSimpleNameFromType(Type type) {
        String obj = type.toString();
        return obj.substring(obj.lastIndexOf(46) + 1);
    }

    private boolean isSingleEntryMapResponseType(Type type) {
        return (type instanceof Class) && ((Class) type).isAnnotationPresent(SingleEntryMapResponse.class);
    }

    private Object getObjectFromJsonMapResponse(Object obj) throws ConversionException {
        try {
            SingleEntryResponseWithErrorSupport singleEntryResponseWithErrorSupport = (SingleEntryResponseWithErrorSupport) obj;
            if (!(!((Boolean) FbInjector.lazyInstance(1, ApiModule.UL_id._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID, this._UL_mInjectionContext)).booleanValue() || singleEntryResponseWithErrorSupport.errors == null || singleEntryResponseWithErrorSupport.errors.length == 0)) {
                Error[] errorArr = singleEntryResponseWithErrorSupport.errors;
                for (Error error : errorArr) {
                    try {
                        BLog.e(TAG, "Backend encountered error while processing request: %s", error.message);
                        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(EVENT_BACKEND_EXCEPTION).addExtra("message", error.message).addExtra(EXTRA_SEVERITY, error.severity).addExtra("path", TextUtils.join("\n", error.path)).addExtra(EXTRA_EXCEPTION_CLASS, error.exception.cls).addExtra(EXTRA_EXCEPTION_MESSAGE, error.exception.message).addExtra(EXTRA_EXCEPTION_STACK_TRACE, TextUtils.join("\n", error.exception.stack_trace)).logAndRelease();
                    } catch (Exception e) {
                        BLog.e(TAG, "unable to unpack exception", e);
                    }
                }
            }
            return singleEntryResponseWithErrorSupport.data;
        } catch (ClassCastException e2) {
            throw new ConversionException("Json response not a map " + e2.getMessage());
        }
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        return this.mGsonConverter.toBody(obj);
    }

    /* access modifiers changed from: private */
    public static class SingleEntryResponseWithErrorSupportType implements ParameterizedType {
        private final Type mActualTypeArgument;

        public Type getOwnerType() {
            return null;
        }

        public SingleEntryResponseWithErrorSupportType(Type type) {
            this.mActualTypeArgument = type;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{this.mActualTypeArgument};
        }

        public Type getRawType() {
            return SingleEntryResponseWithErrorSupport.class;
        }
    }

    public class Error {
        public BackendException exception;
        public String message;
        public String[] path;
        public String severity;

        public Error() {
        }
    }

    public class BackendException {
        @SerializedName("class")
        public String cls;
        public String message;
        public String[] stack_trace;

        public BackendException() {
        }
    }
}
