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

/* renamed from: X.0GU  reason: invalid class name */
public final class AnonymousClass0GU extends AbstractC04040gi implements Serializable {
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect args count in method signature: (LX/0gQ<*>;LX/0lg;LX/0gb;)LX/0mB<*>; */
    private final AbstractC05950mB A00(AbstractC03910gQ r6, AbstractC05680lg r7) {
        AbstractC05950mB stdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) r7.A0L(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) r7.A0L(JsonTypeResolver.class);
        AbstractC05940mA r0 = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo != null) {
                stdTypeResolverBuilder = (AbstractC05950mB) C06330mu.A02(jsonTypeResolver.value(), r6.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
            }
            return null;
        }
        if (jsonTypeInfo != null) {
            EnumC04720jL use = jsonTypeInfo.use();
            EnumC04720jL r1 = EnumC04720jL.NONE;
            if (use == r1) {
                StdTypeResolverBuilder stdTypeResolverBuilder2 = new StdTypeResolverBuilder();
                stdTypeResolverBuilder2._idType = r1;
                stdTypeResolverBuilder2._customIdResolver = null;
                stdTypeResolverBuilder2._typeProperty = r1.getDefaultPropertyName();
                return stdTypeResolverBuilder2;
            }
            stdTypeResolverBuilder = new StdTypeResolverBuilder();
        }
        return null;
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) r7.A0L(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            r0 = (AbstractC05940mA) C06330mu.A02(jsonTypeIdResolver.value(), r6.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS));
        }
        stdTypeResolverBuilder.A4o(jsonTypeInfo.use(), r0);
        EnumC04710jK include = jsonTypeInfo.include();
        if (include == EnumC04710jK.EXTERNAL_PROPERTY && (r7 instanceof C03810gA)) {
            include = EnumC04710jK.PROPERTY;
        }
        stdTypeResolverBuilder.A4m(include);
        stdTypeResolverBuilder.A9g(jsonTypeInfo.property());
        Class<?> defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != AbstractC04730jM.class) {
            stdTypeResolverBuilder.A2C(defaultImpl);
        }
        stdTypeResolverBuilder.A9f(jsonTypeInfo.visible());
        return stdTypeResolverBuilder;
    }

    @Override // X.AbstractC04040gi
    public final C04690j1 A01(AbstractC05680lg r6) {
        JsonFormat jsonFormat = (JsonFormat) r6.A0L(JsonFormat.class);
        if (jsonFormat == null) {
            return null;
        }
        return new C04690j1(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
    }

    @Override // X.AbstractC04040gi
    public final C05170kT A03(AnonymousClass0g9 r4) {
        String value;
        Integer num;
        JsonManagedReference jsonManagedReference = (JsonManagedReference) r4.A0L(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            value = jsonManagedReference.value();
            num = AnonymousClass007.A00;
        } else {
            JsonBackReference jsonBackReference = (JsonBackReference) r4.A0L(JsonBackReference.class);
            if (jsonBackReference == null) {
                return null;
            }
            value = jsonBackReference.value();
            num = AnonymousClass007.A01;
        }
        return new C05170kT(num, value);
    }

    @Override // X.AbstractC04040gi
    public final C05280kg A04(AbstractC05680lg r4) {
        String A0Z;
        if (r4 instanceof AnonymousClass0GX) {
            A0Z = A0V((AnonymousClass0GX) r4);
        } else if (r4 instanceof AnonymousClass07O) {
            A0Z = A0X((AnonymousClass07O) r4);
        } else {
            if (r4 instanceof AnonymousClass0GW) {
                A0Z = A0Z((AnonymousClass0GW) r4);
            }
            return null;
        }
        if (A0Z != null) {
            if (A0Z.length() == 0) {
                return C05280kg.A01;
            }
            return new C05280kg(A0Z);
        }
        return null;
    }

    @Override // X.AbstractC04040gi
    public final C05280kg A05(AbstractC05680lg r4) {
        String A0Y;
        if (r4 instanceof AnonymousClass0GX) {
            A0Y = A0W((AnonymousClass0GX) r4);
        } else {
            if (r4 instanceof AnonymousClass07O) {
                A0Y = A0Y((AnonymousClass07O) r4);
            }
            return null;
        }
        if (A0Y != null) {
            if (A0Y.length() == 0) {
                return C05280kg.A01;
            }
            return new C05280kg(A0Y);
        }
        return null;
    }

    @Override // X.AbstractC04040gi
    public final C05310km A06(C03810gA r3) {
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) r3.A0L(JsonPOJOBuilder.class);
        if (jsonPOJOBuilder == null) {
            return null;
        }
        return new C05310km(jsonPOJOBuilder);
    }

    @Override // X.AbstractC04040gi
    public final C05770lp A07(AbstractC05680lg r6) {
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) r6.A0L(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == AbstractC04150gu.class) {
            return null;
        }
        return new C05770lp(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), false);
    }

    @Override // X.AbstractC04040gi
    public final C05770lp A08(AbstractC05680lg r5, C05770lp r6) {
        boolean alwaysAsId;
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) r5.A0L(JsonIdentityReference.class);
        if (jsonIdentityReference == null || r6.A03 == (alwaysAsId = jsonIdentityReference.alwaysAsId())) {
            return r6;
        }
        return new C05770lp(r6.A02, r6.A01, r6.A00, alwaysAsId);
    }

    @Override // X.AbstractC04040gi
    public final AbstractC05820lu<?> A09(C03810gA r2, AbstractC05820lu<?> r3) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) r2.A0L(JsonAutoDetect.class);
        if (jsonAutoDetect != null) {
            return r3.A9z(jsonAutoDetect);
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r4.length() <= 0) goto L_0x0022;
     */
    @Override // X.AbstractC04040gi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC06410n2 A0D(X.AnonymousClass0g9 r6) {
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
            X.0fk r0 = new X.0fk
            r0.<init>(r4, r3)
            return r0
        L_0x0035:
            r2 = 0
            goto L_0x002b
        L_0x0037:
            X.0fj r0 = new X.0fj
            r0.<init>(r4)
            return r0
        L_0x003d:
            if (r2 == 0) goto L_0x0045
            X.0fi r0 = new X.0fi
            r0.<init>(r3)
            return r0
        L_0x0045:
            X.0n2 r0 = X.AbstractC06410n2.A00
            return r0
        L_0x0048:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0GU.A0D(X.0g9):X.0n2");
    }

    @Override // X.AbstractC04040gi
    public final Boolean A0E(C03810gA r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0L(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // X.AbstractC04040gi
    public final Boolean A0F(C03810gA r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0L(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // X.AbstractC04040gi
    public final Boolean A0G(C03810gA r2) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) r2.A0L(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // X.AbstractC04040gi
    public final Boolean A0H(AnonymousClass0g9 r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    @Override // X.AbstractC04040gi
    public final Class<?> A0I(AbstractC05680lg r3, AbstractC04000gb r4) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == C05340ku.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.AbstractC04040gi
    public final Class<?> A0J(AbstractC05680lg r3, AbstractC04000gb r4) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == C05340ku.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.AbstractC04040gi
    public final Class<?> A0K(AbstractC05680lg r3, AbstractC04000gb r4) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == C05340ku.class) {
            return null;
        }
        return as;
    }

    @Override // X.AbstractC04040gi
    public final Class<?> A0L(C03810gA r4) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r4.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || jsonDeserialize.builder() == C05340ku.class) {
            return null;
        }
        return jsonDeserialize.builder();
    }

    @Override // X.AbstractC04040gi
    public final Object A0M(AbstractC05680lg r3) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.AbstractC04040gi
    public final Object A0N(AbstractC05680lg r3) {
        Class<? extends AbstractC06340mv<?, ?>> converter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (converter = jsonDeserialize.converter()) == AnonymousClass0fm.class) {
            return null;
        }
        return converter;
    }

    @Override // X.AbstractC04040gi
    public final Object A0O(AbstractC05680lg r3) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    @Override // X.AbstractC04040gi
    public final Object A0P(AbstractC05680lg r3) {
        Class<? extends AbstractC05240kb> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == AbstractC03970gW.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.AbstractC04040gi
    public final Object A0Q(C03810gA r2) {
        JsonNaming jsonNaming = (JsonNaming) r2.A0L(JsonNaming.class);
        if (jsonNaming == null) {
            return null;
        }
        return jsonNaming.value();
    }

    @Override // X.AbstractC04040gi
    public final Object A0R(C03810gA r2) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) r2.A0L(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // X.AbstractC04040gi
    public final Object A0S(AnonymousClass0g9 r3) {
        Class<? extends AbstractC06340mv<?, ?>> contentConverter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0L(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentConverter = jsonDeserialize.contentConverter()) == AnonymousClass0fm.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.AbstractC04040gi
    public final Object A0T(AnonymousClass0g9 r3) {
        Class A0J;
        JacksonInject jacksonInject = (JacksonInject) r3.A0L(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (r3 instanceof AnonymousClass07O) {
            AnonymousClass07O r1 = (AnonymousClass07O) r3;
            if (r1.A0W() != 0) {
                A0J = r1.A0X();
                return A0J.getName();
            }
        }
        A0J = r3.A0J();
        return A0J.getName();
    }

    @Override // X.AbstractC04040gi
    public final String A0U(C03810gA r2) {
        JsonTypeName jsonTypeName = (JsonTypeName) r2.A0L(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // X.AbstractC04040gi
    public final String A0V(AnonymousClass0GX r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0I(JsonDeserialize.class) || r2.A0I(JsonView.class) || r2.A0I(JsonBackReference.class) || r2.A0I(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC04040gi
    public final String A0W(AnonymousClass0GX r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0I(JsonSerialize.class) || r2.A0I(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC04040gi
    public final String A0X(AnonymousClass07O r2) {
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

    @Override // X.AbstractC04040gi
    public final String A0Y(AnonymousClass07O r2) {
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

    @Override // X.AbstractC04040gi
    public final String A0Z(AnonymousClass0GW r2) {
        JsonProperty jsonProperty;
        if (r2 == null || (jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // X.AbstractC04040gi
    public final List<C05910m6> A0a(AbstractC05680lg r8) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) r8.A0L(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        int length = value.length;
        ArrayList arrayList = new ArrayList(length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new C05910m6(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // X.AbstractC04040gi
    public final boolean A0b(AbstractC05680lg r2) {
        return r2.A0I(JsonCreator.class);
    }

    @Override // X.AbstractC04040gi
    public final boolean A0c(AnonymousClass0g9 r3) {
        JsonIgnore jsonIgnore = (JsonIgnore) r3.A0L(JsonIgnore.class);
        if (jsonIgnore == null || !jsonIgnore.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC04040gi
    public final boolean A0d(AnonymousClass07O r2) {
        return r2.A0I(JsonAnyGetter.class);
    }

    @Override // X.AbstractC04040gi
    public final boolean A0e(AnonymousClass07O r2) {
        return r2.A0I(JsonAnySetter.class);
    }

    @Override // X.AbstractC04040gi
    public final boolean A0f(AnonymousClass07O r3) {
        JsonValue jsonValue = (JsonValue) r3.A0L(JsonValue.class);
        if (jsonValue == null || !jsonValue.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC04040gi
    public final Class<?>[] A0h(AbstractC05680lg r2) {
        JsonView jsonView = (JsonView) r2.A0L(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // X.AbstractC04040gi
    public final String[] A0i(AbstractC05680lg r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0L(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // X.AbstractC04040gi
    public final String[] A0j(C03810gA r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0L(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // X.AbstractC04040gi
    public final C04690j1 A02(AnonymousClass0g9 r2) {
        return A02(r2);
    }

    @Override // X.AbstractC04040gi
    public final AbstractC05950mB<?> A0A(AbstractC03910gQ<?> r2, C03810gA r3, AbstractC04000gb r4) {
        return A00(r2, r3);
    }

    @Override // X.AbstractC04040gi
    public final AbstractC05950mB<?> A0B(AbstractC03910gQ<?> r3, AnonymousClass0g9 r4, AbstractC04000gb r5) {
        if (r5.A0J()) {
            return A00(r3, r4);
        }
        StringBuilder sb = new StringBuilder("Must call method with a container type (got ");
        sb.append(r5);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // X.AbstractC04040gi
    public final AbstractC05950mB<?> A0C(AbstractC03910gQ<?> r2, AnonymousClass0g9 r3, AbstractC04000gb r4) {
        if (r4.A0J()) {
            return null;
        }
        return A00(r2, r3);
    }

    @Override // X.AbstractC04040gi
    public final boolean A0g(Annotation annotation) {
        if (annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {
            return true;
        }
        return false;
    }
}
