package com.oculus.http.core;

import X.AbstractC0237hg;
import X.AbstractC0241hm;
import X.AbstractC0242hn;
import X.AbstractC0243ho;
import X.C0236ha;
import X.C0240hl;
import X.C0245hq;
import X.C0246hr;
import X.LF;
import X.LT;
import X.M2;
import X.Om;
import X.SZ;
import X.hK;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
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
    public Om _UL_mInjectionContext;
    public final GsonConverter mGsonConverter;
    public final GsonConverter mGsonSingleMapConverter;

    public class BackendException {
        @SerializedName("class")
        public final String cls;
        public final String message;
        public final String[] stack_trace;
        public final /* synthetic */ ApiResponseConverter this$0;
    }

    public class Error {
        public final BackendException exception;
        public final String message;
        public final String[] path;
        public final String severity;
        public final /* synthetic */ ApiResponseConverter this$0;
    }

    public static class SingleEntryResponseWithErrorSupport<T> {
        public T data;
        public Error[] errors;
    }

    public static class SingleEntryResponseWithErrorSupportType implements ParameterizedType {
        public final Type mActualTypeArgument;

        public final Type getOwnerType() {
            return null;
        }

        public final Type[] getActualTypeArguments() {
            return new Type[]{this.mActualTypeArgument};
        }

        public SingleEntryResponseWithErrorSupportType(Type type) {
            this.mActualTypeArgument = type;
        }

        public final Type getRawType() {
            return SingleEntryResponseWithErrorSupport.class;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        if (((java.lang.Class) r14).isAnnotationPresent(com.oculus.http.core.annotations.SingleEntryMapResponse.class) == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (((java.lang.Class) r14).isAnnotationPresent(com.oculus.http.core.annotations.SingleEntryMapResponse.class) == false) goto L_0x0010;
     */
    @Override // retrofit.converter.Converter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object fromBody(retrofit.mime.TypedInput r13, java.lang.reflect.Type r14) throws retrofit.converter.ConversionException {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.http.core.ApiResponseConverter.fromBody(retrofit.mime.TypedInput, java.lang.reflect.Type):java.lang.Object");
    }

    @Override // retrofit.converter.Converter
    public final TypedOutput toBody(Object obj) {
        return this.mGsonConverter.toBody(obj);
    }

    @Inject
    public ApiResponseConverter(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
        final C0246hr hrVar = new C0246hr();
        AnonymousClass1 r4 = new AbstractC0242hn<SingleEntryResponseWithErrorSupport>() {
            /* class com.oculus.http.core.ApiResponseConverter.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // X.AbstractC0242hn
            public final SingleEntryResponseWithErrorSupport A1N(AbstractC0241hm hmVar, Type type, AbstractC0243ho hoVar) throws C0240hl {
                Object A00;
                SingleEntryResponseWithErrorSupport singleEntryResponseWithErrorSupport = new SingleEntryResponseWithErrorSupport();
                if (hmVar instanceof M2) {
                    for (Map.Entry<String, AbstractC0241hm> entry : ((M2) hmVar).A00.entrySet()) {
                        if (entry.getKey().equals("errors")) {
                            C0246hr hrVar = hrVar;
                            AbstractC0241hm value = entry.getValue();
                            Class<Error[]> cls = Error[].class;
                            if (value == null) {
                                A00 = null;
                            } else {
                                A00 = C0246hr.A00(hrVar, new LT(value), cls);
                            }
                            Class<?> cls2 = hK.A00.get(cls);
                            if (cls2 != null) {
                                cls = cls2;
                            }
                            singleEntryResponseWithErrorSupport.errors = cls.cast(A00);
                        } else if (singleEntryResponseWithErrorSupport.data == null) {
                            C0246hr hrVar2 = hrVar;
                            AbstractC0241hm value2 = entry.getValue();
                            singleEntryResponseWithErrorSupport.data = value2 == null ? null : (T) C0246hr.A00(hrVar2, new LT(value2), ((ParameterizedType) type).getActualTypeArguments()[0]);
                        } else {
                            throw new C0240hl("Encountered too many root objects");
                        }
                    }
                    return singleEntryResponseWithErrorSupport;
                }
                StringBuilder sb = new StringBuilder("Not a JSON Object: ");
                sb.append(hmVar);
                throw new IllegalStateException(sb.toString());
            }
        };
        C0245hq hqVar = new C0245hq();
        boolean z = r4 instanceof AbstractC0242hn;
        C0236ha.A00(!z ? false : true);
        if (z) {
            hqVar.A04.add(new LF(r4));
        }
        List<AbstractC0237hg> list = hqVar.A03;
        int size = list.size();
        List<AbstractC0237hg> list2 = hqVar.A04;
        ArrayList arrayList = new ArrayList(size + list2.size() + 3);
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        this.mGsonSingleMapConverter = new GsonConverter(new C0246hr(hqVar.A02, hqVar.A00, hqVar.A05, hqVar.A01, list, list2, arrayList));
        this.mGsonConverter = new GsonConverter(hrVar);
    }
}
