package X;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0K9  reason: invalid class name */
public final class AnonymousClass0K9 extends AbstractC02590aM implements Serializable {
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect args count in method signature: (LX/0a7<*>;LX/0nb;LX/0aI;)LX/0o5<*>; */
    private final AnonymousClass0o5 A00(AnonymousClass0a7 r6, AbstractC06640nb r7) {
        AnonymousClass0o5 stdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) r7.A0M(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) r7.A0M(JsonTypeResolver.class);
        AnonymousClass0o4 r0 = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo != null) {
                stdTypeResolverBuilder = (AnonymousClass0o5) C07130om.A02(jsonTypeResolver.value(), r6.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
            }
            return null;
        }
        if (jsonTypeInfo != null) {
            EnumC05780lJ use = jsonTypeInfo.use();
            EnumC05780lJ r1 = EnumC05780lJ.NONE;
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
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) r7.A0M(JsonTypeIdResolver.class);
        if (jsonTypeIdResolver != null) {
            r0 = (AnonymousClass0o4) C07130om.A02(jsonTypeIdResolver.value(), r6.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS));
        }
        stdTypeResolverBuilder.A5B(jsonTypeInfo.use(), r0);
        EnumC05770lI include = jsonTypeInfo.include();
        if (include == EnumC05770lI.EXTERNAL_PROPERTY && (r7 instanceof C02460Zs)) {
            include = EnumC05770lI.PROPERTY;
        }
        stdTypeResolverBuilder.A59(include);
        stdTypeResolverBuilder.A8e(jsonTypeInfo.property());
        Class<?> defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != AbstractC05790lK.class) {
            stdTypeResolverBuilder.A25(defaultImpl);
        }
        stdTypeResolverBuilder.A8d(jsonTypeInfo.visible());
        return stdTypeResolverBuilder;
    }

    @Override // X.AbstractC02590aM
    public final C05750kz A01(AbstractC06640nb r6) {
        JsonFormat jsonFormat = (JsonFormat) r6.A0M(JsonFormat.class);
        if (jsonFormat == null) {
            return null;
        }
        return new C05750kz(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
    }

    @Override // X.AbstractC02590aM
    public final EnumC05760l7 A03(AbstractC06640nb r2, EnumC05760l7 r3) {
        JsonInclude jsonInclude = (JsonInclude) r2.A0M(JsonInclude.class);
        if (jsonInclude != null) {
            return jsonInclude.value();
        }
        JsonSerialize jsonSerialize = (JsonSerialize) r2.A0M(JsonSerialize.class);
        if (jsonSerialize != null) {
            switch (jsonSerialize.include().ordinal()) {
                case 0:
                    return EnumC05760l7.ALWAYS;
                case 1:
                    return EnumC05760l7.NON_NULL;
                case 2:
                    return EnumC05760l7.NON_DEFAULT;
                case 3:
                    return EnumC05760l7.NON_EMPTY;
            }
        }
        return r3;
    }

    @Override // X.AbstractC02590aM
    public final C06250mQ A04(AbstractC02450Zr r4) {
        String value;
        Integer num;
        JsonManagedReference jsonManagedReference = (JsonManagedReference) r4.A0M(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            value = jsonManagedReference.value();
            num = AnonymousClass007.A00;
        } else {
            JsonBackReference jsonBackReference = (JsonBackReference) r4.A0M(JsonBackReference.class);
            if (jsonBackReference == null) {
                return null;
            }
            value = jsonBackReference.value();
            num = AnonymousClass007.A01;
        }
        return new C06250mQ(num, value);
    }

    @Override // X.AbstractC02590aM
    public final C06350mc A05(AbstractC06640nb r4) {
        String A0m;
        if (r4 instanceof AnonymousClass0KC) {
            A0m = A0i((AnonymousClass0KC) r4);
        } else if (r4 instanceof AnonymousClass0EC) {
            A0m = A0k((AnonymousClass0EC) r4);
        } else {
            if (r4 instanceof AnonymousClass0KB) {
                A0m = A0m((AnonymousClass0KB) r4);
            }
            return null;
        }
        if (A0m != null) {
            if (A0m.length() == 0) {
                return C06350mc.A01;
            }
            return new C06350mc(A0m);
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final C06350mc A06(AbstractC06640nb r4) {
        String A0l;
        if (r4 instanceof AnonymousClass0KC) {
            A0l = A0j((AnonymousClass0KC) r4);
        } else {
            if (r4 instanceof AnonymousClass0EC) {
                A0l = A0l((AnonymousClass0EC) r4);
            }
            return null;
        }
        if (A0l != null) {
            if (A0l.length() == 0) {
                return C06350mc.A01;
            }
            return new C06350mc(A0l);
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final C06350mc A07(C02460Zs r3) {
        JsonRootName jsonRootName = (JsonRootName) r3.A0M(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        return new C06350mc(jsonRootName.value());
    }

    @Override // X.AbstractC02590aM
    public final C06380mi A08(C02460Zs r3) {
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) r3.A0M(JsonPOJOBuilder.class);
        if (jsonPOJOBuilder == null) {
            return null;
        }
        return new C06380mi(jsonPOJOBuilder);
    }

    @Override // X.AbstractC02590aM
    public final EnumC06400ml A09(AbstractC06640nb r2) {
        JsonSerialize jsonSerialize = (JsonSerialize) r2.A0M(JsonSerialize.class);
        if (jsonSerialize == null) {
            return null;
        }
        return jsonSerialize.typing();
    }

    @Override // X.AbstractC02590aM
    public final C06720nk A0A(AbstractC06640nb r6) {
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) r6.A0M(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == AbstractC02670aY.class) {
            return null;
        }
        return new C06720nk(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), false);
    }

    @Override // X.AbstractC02590aM
    public final C06720nk A0B(AbstractC06640nb r5, C06720nk r6) {
        boolean alwaysAsId;
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) r5.A0M(JsonIdentityReference.class);
        if (jsonIdentityReference == null || r6.A03 == (alwaysAsId = jsonIdentityReference.alwaysAsId())) {
            return r6;
        }
        return new C06720nk(r6.A02, r6.A01, r6.A00, alwaysAsId);
    }

    @Override // X.AbstractC02590aM
    public final AbstractC06760no<?> A0C(C02460Zs r2, AbstractC06760no<?> r3) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) r2.A0M(JsonAutoDetect.class);
        if (jsonAutoDetect != null) {
            return r3.A8o(jsonAutoDetect);
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r4.length() <= 0) goto L_0x0022;
     */
    @Override // X.AbstractC02590aM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC07200ov A0G(X.AbstractC02450Zr r6) {
        /*
            r5 = this;
            java.lang.Class<com.fasterxml.jackson.annotation.JsonUnwrapped> r0 = com.fasterxml.jackson.annotation.JsonUnwrapped.class
            java.lang.annotation.Annotation r1 = r6.A0M(r0)
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
            X.0ZM r0 = new X.0ZM
            r0.<init>(r4, r3)
            return r0
        L_0x0035:
            r2 = 0
            goto L_0x002b
        L_0x0037:
            X.0ZL r0 = new X.0ZL
            r0.<init>(r4)
            return r0
        L_0x003d:
            if (r2 == 0) goto L_0x0045
            X.0ZK r0 = new X.0ZK
            r0.<init>(r3)
            return r0
        L_0x0045:
            X.0ov r0 = X.AbstractC07200ov.A00
            return r0
        L_0x0048:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0K9.A0G(X.0Zr):X.0ov");
    }

    @Override // X.AbstractC02590aM
    public final Boolean A0H(C02460Zs r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0M(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // X.AbstractC02590aM
    public final Boolean A0I(C02460Zs r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0M(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // X.AbstractC02590aM
    public final Boolean A0J(C02460Zs r2) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) r2.A0M(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // X.AbstractC02590aM
    public final Boolean A0K(AbstractC02450Zr r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final Boolean A0L(AbstractC02450Zr r2) {
        return Boolean.valueOf(r2.A0J(JsonTypeId.class));
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0M(AbstractC06640nb r3) {
        Class<?> as;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (as = jsonSerialize.as()) == C06410mq.class) {
            return null;
        }
        return as;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0N(AbstractC06640nb r3, AnonymousClass0aI r4) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == C06410mq.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0O(AbstractC06640nb r3, AnonymousClass0aI r4) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == C06410mq.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0P(AbstractC06640nb r3, AnonymousClass0aI r4) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == C06410mq.class) {
            return null;
        }
        return as;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0Q(AbstractC06640nb r3, AnonymousClass0aI r4) {
        Class<?> contentAs;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentAs = jsonSerialize.contentAs()) == C06410mq.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0R(AbstractC06640nb r3, AnonymousClass0aI r4) {
        Class<?> keyAs;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (keyAs = jsonSerialize.keyAs()) == C06410mq.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.AbstractC02590aM
    public final Class<?> A0S(C02460Zs r4) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r4.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || jsonDeserialize.builder() == C06410mq.class) {
            return null;
        }
        return jsonDeserialize.builder();
    }

    @Override // X.AbstractC02590aM
    public final Object A0T(AbstractC06640nb r3) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.AbstractC02590aM
    public final Object A0U(AbstractC06640nb r3) {
        Class<? extends JsonSerializer<?>> contentUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.AbstractC02590aM
    public final Object A0V(AbstractC06640nb r3) {
        Class<? extends AbstractC07140on<?, ?>> converter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (converter = jsonDeserialize.converter()) == AnonymousClass0ZO.class) {
            return null;
        }
        return converter;
    }

    @Override // X.AbstractC02590aM
    public final Object A0W(AbstractC06640nb r3) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    @Override // X.AbstractC02590aM
    public final Object A0X(AbstractC06640nb r3) {
        Class<? extends AnonymousClass0mY> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == AbstractC02550aD.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.AbstractC02590aM
    public final Object A0Y(AbstractC06640nb r3) {
        Class<? extends JsonSerializer<?>> keyUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (keyUsing = jsonSerialize.keyUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.AbstractC02590aM
    public final Object A0Z(AbstractC06640nb r3) {
        Class<? extends AbstractC07140on<?, ?>> converter;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (converter = jsonSerialize.converter()) == AnonymousClass0ZO.class) {
            return null;
        }
        return converter;
    }

    @Override // X.AbstractC02590aM
    public final Object A0a(AbstractC06640nb r3) {
        Class<? extends JsonSerializer<?>> using;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize != null && (using = jsonSerialize.using()) != JsonSerializer.None.class) {
            return using;
        }
        JsonRawValue jsonRawValue = (JsonRawValue) r3.A0M(JsonRawValue.class);
        if (jsonRawValue == null || !jsonRawValue.value()) {
            return null;
        }
        return new RawSerializer(r3.A0K());
    }

    @Override // X.AbstractC02590aM
    public final Object A0b(C02460Zs r3) {
        JsonFilter jsonFilter = (JsonFilter) r3.A0M(JsonFilter.class);
        if (jsonFilter == null) {
            return null;
        }
        String value = jsonFilter.value();
        if (value.length() > 0) {
            return value;
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final Object A0c(C02460Zs r2) {
        JsonNaming jsonNaming = (JsonNaming) r2.A0M(JsonNaming.class);
        if (jsonNaming == null) {
            return null;
        }
        return jsonNaming.value();
    }

    @Override // X.AbstractC02590aM
    public final Object A0d(C02460Zs r2) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) r2.A0M(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // X.AbstractC02590aM
    public final Object A0e(AbstractC02450Zr r3) {
        Class<? extends AbstractC07140on<?, ?>> contentConverter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentConverter = jsonDeserialize.contentConverter()) == AnonymousClass0ZO.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.AbstractC02590aM
    public final Object A0f(AbstractC02450Zr r3) {
        Class A0K;
        JacksonInject jacksonInject = (JacksonInject) r3.A0M(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (r3 instanceof AnonymousClass0EC) {
            AnonymousClass0EC r1 = (AnonymousClass0EC) r3;
            if (r1.A0Z() != 0) {
                A0K = r1.A0a();
                return A0K.getName();
            }
        }
        A0K = r3.A0K();
        return A0K.getName();
    }

    @Override // X.AbstractC02590aM
    public final Object A0g(AbstractC02450Zr r3) {
        Class<? extends AbstractC07140on<?, ?>> contentConverter;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentConverter = jsonSerialize.contentConverter()) == AnonymousClass0ZO.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.AbstractC02590aM
    public final String A0h(C02460Zs r2) {
        JsonTypeName jsonTypeName = (JsonTypeName) r2.A0M(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // X.AbstractC02590aM
    public final String A0i(AnonymousClass0KC r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonDeserialize.class) || r2.A0J(JsonView.class) || r2.A0J(JsonBackReference.class) || r2.A0J(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final String A0j(AnonymousClass0KC r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonSerialize.class) || r2.A0J(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final String A0k(AnonymousClass0EC r2) {
        JsonSetter jsonSetter = (JsonSetter) r2.A0M(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonDeserialize.class) || r2.A0J(JsonView.class) || r2.A0J(JsonBackReference.class) || r2.A0J(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final String A0l(AnonymousClass0EC r2) {
        JsonGetter jsonGetter = (JsonGetter) r2.A0M(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonSerialize.class) || r2.A0J(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02590aM
    public final String A0m(AnonymousClass0KB r2) {
        JsonProperty jsonProperty;
        if (r2 == null || (jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // X.AbstractC02590aM
    public final List<AnonymousClass0o0> A0n(AbstractC06640nb r8) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) r8.A0M(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        int length = value.length;
        ArrayList arrayList = new ArrayList(length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new AnonymousClass0o0(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // X.AbstractC02590aM
    public final boolean A0o(AbstractC06640nb r2) {
        return r2.A0J(JsonCreator.class);
    }

    @Override // X.AbstractC02590aM
    public final boolean A0p(AbstractC02450Zr r3) {
        JsonIgnore jsonIgnore = (JsonIgnore) r3.A0M(JsonIgnore.class);
        if (jsonIgnore == null || !jsonIgnore.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02590aM
    public final boolean A0q(AnonymousClass0EC r2) {
        return r2.A0J(JsonAnyGetter.class);
    }

    @Override // X.AbstractC02590aM
    public final boolean A0r(AnonymousClass0EC r2) {
        return r2.A0J(JsonAnySetter.class);
    }

    @Override // X.AbstractC02590aM
    public final boolean A0s(AnonymousClass0EC r3) {
        JsonValue jsonValue = (JsonValue) r3.A0M(JsonValue.class);
        if (jsonValue == null || !jsonValue.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02590aM
    public final Class<?>[] A0u(AbstractC06640nb r2) {
        JsonView jsonView = (JsonView) r2.A0M(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // X.AbstractC02590aM
    public final String[] A0v(AbstractC06640nb r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0M(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // X.AbstractC02590aM
    public final String[] A0w(C02460Zs r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0M(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // X.AbstractC02590aM
    public final C05750kz A02(AbstractC02450Zr r2) {
        return A02(r2);
    }

    @Override // X.AbstractC02590aM
    public final AnonymousClass0o5<?> A0D(AnonymousClass0a7<?> r2, C02460Zs r3, AnonymousClass0aI r4) {
        return A00(r2, r3);
    }

    @Override // X.AbstractC02590aM
    public final AnonymousClass0o5<?> A0E(AnonymousClass0a7<?> r3, AbstractC02450Zr r4, AnonymousClass0aI r5) {
        if (r5.A0N()) {
            return A00(r3, r4);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + r5 + ")");
    }

    @Override // X.AbstractC02590aM
    public final AnonymousClass0o5<?> A0F(AnonymousClass0a7<?> r2, AbstractC02450Zr r3, AnonymousClass0aI r4) {
        if (r4.A0N()) {
            return null;
        }
        return A00(r2, r3);
    }

    @Override // X.AbstractC02590aM
    public final boolean A0t(Annotation annotation) {
        if (annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {
            return true;
        }
        return false;
    }
}
