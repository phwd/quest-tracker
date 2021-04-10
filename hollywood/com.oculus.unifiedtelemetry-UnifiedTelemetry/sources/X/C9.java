package X;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public final class C9 extends Wp implements Serializable {
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect args count in method signature: (LX/WZ<*>;LX/VV;LX/Wl;)LX/V2<*>; */
    private final V2 A00(WZ wz, VV vv) {
        V2 stdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) vv.A0L(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) vv.A0L(JsonTypeResolver.class);
        V3 v3 = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo != null) {
                stdTypeResolverBuilder = (V2) Mv.A02(jsonTypeResolver.value(), wz.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
            }
            return null;
        }
        if (jsonTypeInfo != null) {
            EnumC0464ph use = jsonTypeInfo.use();
            EnumC0464ph phVar = EnumC0464ph.NONE;
            if (use == phVar) {
                StdTypeResolverBuilder stdTypeResolverBuilder2 = new StdTypeResolverBuilder();
                stdTypeResolverBuilder2._idType = phVar;
                stdTypeResolverBuilder2._customIdResolver = null;
                stdTypeResolverBuilder2._typeProperty = phVar.getDefaultPropertyName();
                return stdTypeResolverBuilder2;
            }
            stdTypeResolverBuilder = new StdTypeResolverBuilder();
        }
        return null;
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) vv.A0L(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            v3 = (V3) Mv.A02(jsonTypeIdResolver.value(), wz.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS));
        }
        stdTypeResolverBuilder.A35(jsonTypeInfo.use(), v3);
        EnumC0463pg include = jsonTypeInfo.include();
        if (include == EnumC0463pg.EXTERNAL_PROPERTY && (vv instanceof WK)) {
            include = EnumC0463pg.PROPERTY;
        }
        stdTypeResolverBuilder.A33(include);
        stdTypeResolverBuilder.A5Y(jsonTypeInfo.property());
        Class<?> defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != pi.class) {
            stdTypeResolverBuilder.A1n(defaultImpl);
        }
        stdTypeResolverBuilder.A5X(jsonTypeInfo.visible());
        return stdTypeResolverBuilder;
    }

    @Override // X.Wp
    public final pN A01(VV vv) {
        JsonFormat jsonFormat = (JsonFormat) vv.A0L(JsonFormat.class);
        if (jsonFormat == null) {
            return null;
        }
        return new pN(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
    }

    @Override // X.Wp
    public final jn A03(WJ wj) {
        String value;
        Integer num;
        JsonManagedReference jsonManagedReference = (JsonManagedReference) wj.A0L(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            value = jsonManagedReference.value();
            num = AnonymousClass07.A00;
        } else {
            JsonBackReference jsonBackReference = (JsonBackReference) wj.A0L(JsonBackReference.class);
            if (jsonBackReference == null) {
                return null;
            }
            value = jsonBackReference.value();
            num = AnonymousClass07.A01;
        }
        return new jn(num, value);
    }

    @Override // X.Wp
    public final C0417hQ A04(VV vv) {
        String A0a;
        if (vv instanceof CD) {
            A0a = A0W((CD) vv);
        } else if (vv instanceof AnonymousClass7P) {
            A0a = A0Y((AnonymousClass7P) vv);
        } else {
            if (vv instanceof CC) {
                A0a = A0a((CC) vv);
            }
            return null;
        }
        if (A0a != null) {
            if (A0a.length() == 0) {
                return C0417hQ.A01;
            }
            return new C0417hQ(A0a);
        }
        return null;
    }

    @Override // X.Wp
    public final C0417hQ A05(VV vv) {
        String A0Z;
        if (vv instanceof CD) {
            A0Z = A0X((CD) vv);
        } else {
            if (vv instanceof AnonymousClass7P) {
                A0Z = A0Z((AnonymousClass7P) vv);
            }
            return null;
        }
        if (A0Z != null) {
            if (A0Z.length() == 0) {
                return C0417hQ.A01;
            }
            return new C0417hQ(A0Z);
        }
        return null;
    }

    @Override // X.Wp
    public final C0417hQ A06(WK wk) {
        JsonRootName jsonRootName = (JsonRootName) wk.A0L(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        return new C0417hQ(jsonRootName.value());
    }

    @Override // X.Wp
    public final C0410hE A07(WK wk) {
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) wk.A0L(JsonPOJOBuilder.class);
        if (jsonPOJOBuilder == null) {
            return null;
        }
        return new C0410hE(jsonPOJOBuilder);
    }

    @Override // X.Wp
    public final VN A08(VV vv) {
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) vv.A0L(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == X1.class) {
            return null;
        }
        return new VN(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), false);
    }

    @Override // X.Wp
    public final VN A09(VV vv, VN vn) {
        boolean alwaysAsId;
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) vv.A0L(JsonIdentityReference.class);
        if (jsonIdentityReference == null || vn.A03 == (alwaysAsId = jsonIdentityReference.alwaysAsId())) {
            return vn;
        }
        return new VN(vn.A02, vn.A01, vn.A00, alwaysAsId);
    }

    @Override // X.Wp
    public final VI<?> A0A(WK wk, VI<?> vi) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) wk.A0L(JsonAutoDetect.class);
        if (jsonAutoDetect != null) {
            return vi.A5f(jsonAutoDetect);
        }
        return vi;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r4.length() <= 0) goto L_0x0022;
     */
    @Override // X.Wp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.KI A0E(X.WJ r6) {
        /*
            r5 = this;
            java.lang.Class<com.fasterxml.jackson.annotation.JsonUnwrapped> r0 = com.fasterxml.jackson.annotation.JsonUnwrapped.class
            java.lang.annotation.Annotation r1 = r6.A0L(r0)
            com.fasterxml.jackson.annotation.JsonUnwrapped r1 = (com.fasterxml.jackson.annotation.JsonUnwrapped) r1
            if (r1 == 0) goto L_0x0048
            boolean r0 = r1.enabled()
            if (r0 == 0) goto L_0x0048
            java.lang.String r4 = r1.prefix()
            java.lang.String r3 = r1.suffix()
            r2 = 1
            if (r4 == 0) goto L_0x0022
            int r0 = r4.length()
            r1 = 1
            if (r0 > 0) goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            if (r3 == 0) goto L_0x0035
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x0035
        L_0x002b:
            if (r1 == 0) goto L_0x003d
            if (r2 == 0) goto L_0x0037
            X.Vv r0 = new X.Vv
            r0.<init>(r4, r3)
            return r0
        L_0x0035:
            r2 = 0
            goto L_0x002b
        L_0x0037:
            X.Vu r0 = new X.Vu
            r0.<init>(r4)
            return r0
        L_0x003d:
            if (r2 == 0) goto L_0x0045
            X.Vt r0 = new X.Vt
            r0.<init>(r3)
            return r0
        L_0x0045:
            X.KI r0 = X.KI.A00
            return r0
        L_0x0048:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C9.A0E(X.WJ):X.KI");
    }

    @Override // X.Wp
    public final Boolean A0F(WK wk) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) wk.A0L(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // X.Wp
    public final Boolean A0G(WK wk) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) wk.A0L(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // X.Wp
    public final Boolean A0H(WK wk) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) wk.A0L(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // X.Wp
    public final Boolean A0I(WJ wj) {
        JsonProperty jsonProperty = (JsonProperty) wj.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    @Override // X.Wp
    public final Class<?> A0J(VV vv, AbstractC0224Wl wl) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == dY.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.Wp
    public final Class<?> A0K(VV vv, AbstractC0224Wl wl) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == dY.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.Wp
    public final Class<?> A0L(VV vv, AbstractC0224Wl wl) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == dY.class) {
            return null;
        }
        return as;
    }

    @Override // X.Wp
    public final Class<?> A0M(WK wk) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) wk.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || jsonDeserialize.builder() == dY.class) {
            return null;
        }
        return jsonDeserialize.builder();
    }

    @Override // X.Wp
    public final Object A0N(VV vv) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.Wp
    public final Object A0O(VV vv) {
        Class<? extends MH<?, ?>> converter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (converter = jsonDeserialize.converter()) == AbstractC0213Vx.class) {
            return null;
        }
        return converter;
    }

    @Override // X.Wp
    public final Object A0P(VV vv) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    @Override // X.Wp
    public final Object A0Q(VV vv) {
        Class<? extends AbstractC0420hV> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) vv.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == AbstractC0221Wg.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.Wp
    public final Object A0R(WK wk) {
        JsonNaming jsonNaming = (JsonNaming) wk.A0L(JsonNaming.class);
        if (jsonNaming == null) {
            return null;
        }
        return jsonNaming.value();
    }

    @Override // X.Wp
    public final Object A0S(WK wk) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) wk.A0L(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // X.Wp
    public final Object A0T(WJ wj) {
        Class<? extends MH<?, ?>> contentConverter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) wj.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentConverter = jsonDeserialize.contentConverter()) == AbstractC0213Vx.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.Wp
    public final Object A0U(WJ wj) {
        Class A0J;
        JacksonInject jacksonInject = (JacksonInject) wj.A0L(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (wj instanceof AnonymousClass7P) {
            AnonymousClass7P r1 = (AnonymousClass7P) wj;
            if (r1.A0W() != 0) {
                A0J = r1.A0X();
                return A0J.getName();
            }
        }
        A0J = wj.A0J();
        return A0J.getName();
    }

    @Override // X.Wp
    public final String A0V(WK wk) {
        JsonTypeName jsonTypeName = (JsonTypeName) wk.A0L(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // X.Wp
    public final String A0W(CD cd) {
        JsonProperty jsonProperty = (JsonProperty) cd.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (cd.A0I(JsonDeserialize.class) || cd.A0I(JsonView.class) || cd.A0I(JsonBackReference.class) || cd.A0I(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.Wp
    public final String A0X(CD cd) {
        JsonProperty jsonProperty = (JsonProperty) cd.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (cd.A0I(JsonSerialize.class) || cd.A0I(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.Wp
    public final String A0Y(AnonymousClass7P r2) {
        JsonSetter jsonSetter = (JsonSetter) r2.A0L(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0I(JsonDeserialize.class) || r2.A0I(JsonView.class) || r2.A0I(JsonBackReference.class) || r2.A0I(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.Wp
    public final String A0Z(AnonymousClass7P r2) {
        JsonGetter jsonGetter = (JsonGetter) r2.A0L(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0I(JsonSerialize.class) || r2.A0I(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.Wp
    public final String A0a(CC cc) {
        JsonProperty jsonProperty;
        if (cc == null || (jsonProperty = (JsonProperty) cc.A0L(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // X.Wp
    public final List<V7> A0b(VV vv) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) vv.A0L(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        int length = value.length;
        ArrayList arrayList = new ArrayList(length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new V7(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // X.Wp
    public final boolean A0c(VV vv) {
        return vv.A0I(JsonCreator.class);
    }

    @Override // X.Wp
    public final boolean A0d(WJ wj) {
        JsonIgnore jsonIgnore = (JsonIgnore) wj.A0L(JsonIgnore.class);
        if (jsonIgnore == null || !jsonIgnore.value()) {
            return false;
        }
        return true;
    }

    @Override // X.Wp
    public final boolean A0e(AnonymousClass7P r2) {
        return r2.A0I(JsonAnyGetter.class);
    }

    @Override // X.Wp
    public final boolean A0f(AnonymousClass7P r2) {
        return r2.A0I(JsonAnySetter.class);
    }

    @Override // X.Wp
    public final boolean A0g(AnonymousClass7P r3) {
        JsonValue jsonValue = (JsonValue) r3.A0L(JsonValue.class);
        if (jsonValue == null || !jsonValue.value()) {
            return false;
        }
        return true;
    }

    @Override // X.Wp
    public final Class<?>[] A0i(VV vv) {
        JsonView jsonView = (JsonView) vv.A0L(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // X.Wp
    public final String[] A0j(VV vv) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) vv.A0L(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // X.Wp
    public final String[] A0k(WK wk) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) wk.A0L(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // X.Wp
    public final pN A02(WJ wj) {
        return A02(wj);
    }

    @Override // X.Wp
    public final V2<?> A0B(WZ<?> wz, WK wk, AbstractC0224Wl wl) {
        return A00(wz, wk);
    }

    @Override // X.Wp
    public final V2<?> A0C(WZ<?> wz, WJ wj, AbstractC0224Wl wl) {
        if (wl.A0J()) {
            return A00(wz, wj);
        }
        StringBuilder sb = new StringBuilder("Must call method with a container type (got ");
        sb.append(wl);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // X.Wp
    public final V2<?> A0D(WZ<?> wz, WJ wj, AbstractC0224Wl wl) {
        if (wl.A0J()) {
            return null;
        }
        return A00(wz, wj);
    }

    @Override // X.Wp
    public final boolean A0h(Annotation annotation) {
        if (annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {
            return true;
        }
        return false;
    }
}
