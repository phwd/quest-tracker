package com.oculus.http.core;

import X.AbstractC03180ld;
import X.AnonymousClass006;
import X.AnonymousClass0Hr;
import X.AnonymousClass0MD;
import X.AnonymousClass0RE;
import X.AnonymousClass0VC;
import X.AnonymousClass0VF;
import X.AnonymousClass0eT;
import X.AnonymousClass0lg;
import X.AnonymousClass13N;
import X.AnonymousClass13O;
import X.AnonymousClass13P;
import X.AnonymousClass13Q;
import X.AnonymousClass13R;
import X.AnonymousClass13S;
import X.AnonymousClass13Z;
import X.AnonymousClass13f;
import X.AnonymousClass144;
import X.AnonymousClass1TK;
import X.C01410dw;
import android.text.TextUtils;
import bolts.WebViewAppLinkResolver;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.bind.TreeTypeAdapter$SingleTypeFactory;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.logging.utils.EventManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID"})
public class ApiResponseConverter implements Converter {
    public static final String EVENT_BACKEND_EXCEPTION = "oculus_graph_backend_exception";
    public static final String EXTRA_EXCEPTION_CLASS = "exception_class";
    public static final String EXTRA_EXCEPTION_MESSAGE = "exception_message";
    public static final String EXTRA_EXCEPTION_STACK_TRACE = "exception_stack_trace";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_PATH = "path";
    public static final String EXTRA_SEVERITY = "severity";
    public static final String TAG = "ApiResponseConverter";
    public AnonymousClass0RE _UL_mInjectionContext;
    public final GsonConverter mGsonConverter;
    public final GsonConverter mGsonSingleMapConverter;

    public class BackendException {
        @SerializedName(WebViewAppLinkResolver.KEY_CLASS)
        public final String cls;
        public final String message;
        public final String[] stack_trace;

        public BackendException() {
        }
    }

    public class Error {
        public final BackendException exception;
        public final String message;
        public final String[] path;
        public final String severity;

        public Error() {
        }
    }

    public static class SingleEntryResponseWithErrorSupport<T> {
        public T data;
        public Error[] errors;
    }

    public static class SingleEntryResponseWithErrorSupportType implements ParameterizedType {
        public final Type mActualTypeArgument;

        public Type getOwnerType() {
            return null;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{this.mActualTypeArgument};
        }

        public Type getRawType() {
            return SingleEntryResponseWithErrorSupport.class;
        }

        public SingleEntryResponseWithErrorSupportType(Type type) {
            this.mActualTypeArgument = type;
        }
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_http_core_ApiResponseConverter_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(99, r1);
    }

    @AutoGeneratedAccessMethod
    public static final ApiResponseConverter _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (ApiResponseConverter) AnonymousClass1TK.A00(99, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final ApiResponseConverter _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        return new ApiResponseConverter(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_http_core_ApiResponseConverter_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(99, r1);
    }

    private Object getObjectFromJsonMapResponse(Object obj) throws ConversionException {
        Error[] errorArr;
        try {
            SingleEntryResponseWithErrorSupport singleEntryResponseWithErrorSupport = (SingleEntryResponseWithErrorSupport) obj;
            if (!(!((Boolean) AnonymousClass0VF.A03(1, 31, this._UL_mInjectionContext)).booleanValue() || (errorArr = singleEntryResponseWithErrorSupport.errors) == null || (r5 = errorArr.length) == 0)) {
                for (Error error : errorArr) {
                    try {
                        AnonymousClass0MD.A09(TAG, "Backend encountered error while processing request: %s", error.message);
                        ((EventManager) AnonymousClass0VF.A03(0, 92, this._UL_mInjectionContext)).createEvent(EVENT_BACKEND_EXCEPTION).addExtra("message", error.message).addExtra(EXTRA_SEVERITY, error.severity).addExtra("path", TextUtils.join("\n", error.path)).addExtra(EXTRA_EXCEPTION_CLASS, error.exception.cls).addExtra(EXTRA_EXCEPTION_MESSAGE, error.exception.message).addExtra(EXTRA_EXCEPTION_STACK_TRACE, TextUtils.join("\n", error.exception.stack_trace)).logAndRelease();
                    } catch (Exception e) {
                        AnonymousClass0MD.A07(TAG, "unable to unpack exception", e);
                    }
                }
            }
            return singleEntryResponseWithErrorSupport.data;
        } catch (ClassCastException e2) {
            throw new ConversionException(AnonymousClass006.A07("Json response not a map ", e2.getMessage()));
        }
    }

    private boolean isSingleEntryMapResponseType(Type type) {
        if (!(type instanceof Class) || !((Class) type).isAnnotationPresent(SingleEntryMapResponse.class)) {
            return false;
        }
        return true;
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        return this.mGsonConverter.toBody(obj);
    }

    @Inject
    public ApiResponseConverter(AnonymousClass0lg r12) {
        this._UL_mInjectionContext = new AnonymousClass0RE(2, r12);
        final AnonymousClass13N r2 = new AnonymousClass13N();
        AnonymousClass1 r4 = new AnonymousClass13Q<SingleEntryResponseWithErrorSupport>() {
            /* class com.oculus.http.core.ApiResponseConverter.AnonymousClass1 */

            @Override // X.AnonymousClass13Q
            public SingleEntryResponseWithErrorSupport deserialize(AnonymousClass13R r7, Type type, AnonymousClass13P r9) throws AnonymousClass13S {
                Object A00;
                SingleEntryResponseWithErrorSupport singleEntryResponseWithErrorSupport = new SingleEntryResponseWithErrorSupport();
                if (r7 instanceof AnonymousClass0eT) {
                    for (Map.Entry<String, AnonymousClass13R> entry : ((AnonymousClass0eT) r7).A00.entrySet()) {
                        if (entry.getKey().equals("errors")) {
                            AnonymousClass13N r3 = r2;
                            AnonymousClass13R value = entry.getValue();
                            Class<Error[]> cls = Error[].class;
                            if (value == null) {
                                A00 = null;
                            } else {
                                A00 = AnonymousClass13N.A00(r3, new C01410dw(value), cls);
                            }
                            Class<?> cls2 = AnonymousClass144.A00.get(cls);
                            if (cls2 != null) {
                                cls = cls2;
                            }
                            singleEntryResponseWithErrorSupport.errors = cls.cast(A00);
                        } else if (singleEntryResponseWithErrorSupport.data == null) {
                            AnonymousClass13N r32 = r2;
                            AnonymousClass13R value2 = entry.getValue();
                            singleEntryResponseWithErrorSupport.data = value2 == null ? null : (T) AnonymousClass13N.A00(r32, new C01410dw(value2), ((ParameterizedType) type).getActualTypeArguments()[0]);
                        } else {
                            throw new AnonymousClass13S("Encountered too many root objects");
                        }
                    }
                    return singleEntryResponseWithErrorSupport;
                }
                StringBuilder sb = new StringBuilder("Not a JSON Object: ");
                sb.append(r7);
                throw new IllegalStateException(sb.toString());
            }
        };
        AnonymousClass13O r3 = new AnonymousClass13O();
        boolean z = r4 instanceof AnonymousClass13Q;
        AnonymousClass13f.A00(!z ? false : true);
        if (z) {
            r3.A04.add(new TreeTypeAdapter$SingleTypeFactory(r4));
        }
        List<AnonymousClass13Z> list = r3.A03;
        int size = list.size();
        List<AnonymousClass13Z> list2 = r3.A04;
        ArrayList arrayList = new ArrayList(size + list2.size() + 3);
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        this.mGsonSingleMapConverter = new GsonConverter(new AnonymousClass13N(r3.A02, r3.A00, r3.A05, r3.A01, list, list2, arrayList));
        this.mGsonConverter = new GsonConverter(r2);
    }

    private Object getJsonObjectFromBody(TypedInput typedInput, Type type) throws ConversionException {
        Object obj;
        if (isSingleEntryMapResponseType(type)) {
            try {
                obj = this.mGsonSingleMapConverter.fromBody(typedInput, new SingleEntryResponseWithErrorSupportType(type));
            } catch (ConversionException e) {
                throw new ConversionException(AnonymousClass006.A07("Failed to parse concrete class out of map. Maybe the response wasn't an actual map? ", e.getMessage()));
            }
        } else {
            obj = this.mGsonConverter.fromBody(typedInput, type);
        }
        if (obj != null) {
            return obj;
        }
        throw new ConversionException(AnonymousClass006.A07(getSimpleNameFromType(type), " is null"));
    }

    private String getSimpleNameFromType(Type type) {
        String obj = type.toString();
        return obj.substring(obj.lastIndexOf(46) + 1);
    }

    @Override // retrofit.converter.Converter
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        Object jsonObjectFromBody = getJsonObjectFromBody(typedInput, type);
        if (isSingleEntryMapResponseType(type)) {
            return getObjectFromJsonMapResponse(jsonObjectFromBody);
        }
        return jsonObjectFromBody;
    }
}