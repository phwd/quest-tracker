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
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Ov  reason: invalid class name */
public final class AnonymousClass0Ov extends AbstractC02230iJ implements Serializable {
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect args count in method signature: (LX/0i2<*>;LX/0qA;LX/0iF;)LX/0qc<*>; */
    private final AbstractC04540qc A00(AbstractC02110i2 r6, AnonymousClass0qA r7) {
        AbstractC04540qc stdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) r7.A0M(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) r7.A0M(JsonTypeResolver.class);
        AbstractC04530qb r0 = null;
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo != null) {
                stdTypeResolverBuilder = (AbstractC04540qc) C04810rI.A02(jsonTypeResolver.value(), r6.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
            }
            return null;
        }
        if (jsonTypeInfo != null) {
            EnumC03580nr use = jsonTypeInfo.use();
            EnumC03580nr r1 = EnumC03580nr.NONE;
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
            r0 = (AbstractC04530qb) C04810rI.A02(jsonTypeIdResolver.value(), r6.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
        }
        stdTypeResolverBuilder.A5b(jsonTypeInfo.use(), r0);
        EnumC03570nq include = jsonTypeInfo.include();
        if (include == EnumC03570nq.EXTERNAL_PROPERTY && (r7 instanceof C02000hn)) {
            include = EnumC03570nq.PROPERTY;
        }
        stdTypeResolverBuilder.A5Z(include);
        stdTypeResolverBuilder.AAp(jsonTypeInfo.property());
        Class<?> defaultImpl = jsonTypeInfo.defaultImpl();
        if (defaultImpl != AnonymousClass0ns.class) {
            stdTypeResolverBuilder.A2Y(defaultImpl);
        }
        stdTypeResolverBuilder.AAo(jsonTypeInfo.visible());
        return stdTypeResolverBuilder;
    }

    @Override // X.AbstractC02230iJ
    public final C03550nX A01(AnonymousClass0qA r6) {
        JsonFormat jsonFormat = (JsonFormat) r6.A0M(JsonFormat.class);
        if (jsonFormat == null) {
            return null;
        }
        return new C03550nX(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
    }

    @Override // X.AbstractC02230iJ
    public final EnumC03560nf A03(AnonymousClass0qA r2, EnumC03560nf r3) {
        JsonInclude jsonInclude = (JsonInclude) r2.A0M(JsonInclude.class);
        if (jsonInclude != null) {
            return jsonInclude.value();
        }
        JsonSerialize jsonSerialize = (JsonSerialize) r2.A0M(JsonSerialize.class);
        if (jsonSerialize != null) {
            switch (jsonSerialize.include().ordinal()) {
                case 0:
                    return EnumC03560nf.ALWAYS;
                case 1:
                    return EnumC03560nf.NON_NULL;
                case 2:
                    return EnumC03560nf.NON_DEFAULT;
                case 3:
                    return EnumC03560nf.NON_EMPTY;
            }
        }
        return r3;
    }

    @Override // X.AbstractC02230iJ
    public final C04000oy A04(AbstractC01990hm r4) {
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
        return new C04000oy(num, value);
    }

    @Override // X.AbstractC02230iJ
    public final C04070pB A05(AnonymousClass0qA r4) {
        String A0m;
        if (r4 instanceof AnonymousClass0Oy) {
            A0m = A0i((AnonymousClass0Oy) r4);
        } else if (r4 instanceof AnonymousClass0Cr) {
            A0m = A0k((AnonymousClass0Cr) r4);
        } else {
            if (r4 instanceof AnonymousClass0Ox) {
                A0m = A0m((AnonymousClass0Ox) r4);
            }
            return null;
        }
        if (A0m != null) {
            if (A0m.length() == 0) {
                return C04070pB.A01;
            }
            return new C04070pB(A0m);
        }
        return null;
    }

    @Override // X.AbstractC02230iJ
    public final C04070pB A06(AnonymousClass0qA r4) {
        String A0l;
        if (r4 instanceof AnonymousClass0Oy) {
            A0l = A0j((AnonymousClass0Oy) r4);
        } else {
            if (r4 instanceof AnonymousClass0Cr) {
                A0l = A0l((AnonymousClass0Cr) r4);
            }
            return null;
        }
        if (A0l != null) {
            if (A0l.length() == 0) {
                return C04070pB.A01;
            }
            return new C04070pB(A0l);
        }
        return null;
    }

    @Override // X.AbstractC02230iJ
    public final C04070pB A07(C02000hn r3) {
        JsonRootName jsonRootName = (JsonRootName) r3.A0M(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        return new C04070pB(jsonRootName.value());
    }

    @Override // X.AbstractC02230iJ
    public final C04100pH A08(C02000hn r3) {
        JsonPOJOBuilder jsonPOJOBuilder = (JsonPOJOBuilder) r3.A0M(JsonPOJOBuilder.class);
        if (jsonPOJOBuilder == null) {
            return null;
        }
        return new C04100pH(jsonPOJOBuilder);
    }

    @Override // X.AbstractC02230iJ
    public final EnumC04120pK A09(AnonymousClass0qA r2) {
        JsonSerialize jsonSerialize = (JsonSerialize) r2.A0M(JsonSerialize.class);
        if (jsonSerialize == null) {
            return null;
        }
        return jsonSerialize.typing();
    }

    @Override // X.AbstractC02230iJ
    public final C04480qJ A0A(AnonymousClass0qA r6) {
        JsonIdentityInfo jsonIdentityInfo = (JsonIdentityInfo) r6.A0M(JsonIdentityInfo.class);
        if (jsonIdentityInfo == null || jsonIdentityInfo.generator() == AbstractC02320iV.class) {
            return null;
        }
        return new C04480qJ(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), false);
    }

    @Override // X.AbstractC02230iJ
    public final C04480qJ A0B(AnonymousClass0qA r5, C04480qJ r6) {
        boolean alwaysAsId;
        JsonIdentityReference jsonIdentityReference = (JsonIdentityReference) r5.A0M(JsonIdentityReference.class);
        if (jsonIdentityReference == null || r6.A03 == (alwaysAsId = jsonIdentityReference.alwaysAsId())) {
            return r6;
        }
        return new C04480qJ(r6.A02, r6.A01, r6.A00, alwaysAsId);
    }

    @Override // X.AbstractC02230iJ
    public final AnonymousClass0qO<?> A0C(C02000hn r2, AnonymousClass0qO<?> r3) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) r2.A0M(JsonAutoDetect.class);
        if (jsonAutoDetect != null) {
            return r3.AB2(jsonAutoDetect);
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r4.length() <= 0) goto L_0x0022;
     */
    @Override // X.AbstractC02230iJ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC04870rR A0G(X.AbstractC01990hm r6) {
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
            X.0gw r0 = new X.0gw
            r0.<init>(r4, r3)
            return r0
        L_0x0035:
            r2 = 0
            goto L_0x002b
        L_0x0037:
            X.0gv r0 = new X.0gv
            r0.<init>(r4)
            return r0
        L_0x003d:
            if (r2 == 0) goto L_0x0045
            X.0gu r0 = new X.0gu
            r0.<init>(r3)
            return r0
        L_0x0045:
            X.0rR r0 = X.AbstractC04870rR.A00
            return r0
        L_0x0048:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Ov.A0G(X.0hm):X.0rR");
    }

    @Override // X.AbstractC02230iJ
    public final Boolean A0H(C02000hn r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0M(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // X.AbstractC02230iJ
    public final Boolean A0I(C02000hn r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0M(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // X.AbstractC02230iJ
    public final Boolean A0J(C02000hn r2) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) r2.A0M(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // X.AbstractC02230iJ
    public final Boolean A0K(AbstractC01990hm r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    @Override // X.AbstractC02230iJ
    public final Boolean A0L(AbstractC01990hm r2) {
        return Boolean.valueOf(r2.A0J(JsonTypeId.class));
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0M(AnonymousClass0qA r3) {
        Class<?> as;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (as = jsonSerialize.as()) == C04130pP.class) {
            return null;
        }
        return as;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0N(AnonymousClass0qA r3, AbstractC02190iF r4) {
        Class<?> contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == C04130pP.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0O(AnonymousClass0qA r3, AbstractC02190iF r4) {
        Class<?> keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == C04130pP.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0P(AnonymousClass0qA r3, AbstractC02190iF r4) {
        Class<?> as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == C04130pP.class) {
            return null;
        }
        return as;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0Q(AnonymousClass0qA r3, AbstractC02190iF r4) {
        Class<?> contentAs;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentAs = jsonSerialize.contentAs()) == C04130pP.class) {
            return null;
        }
        return contentAs;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0R(AnonymousClass0qA r3, AbstractC02190iF r4) {
        Class<?> keyAs;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (keyAs = jsonSerialize.keyAs()) == C04130pP.class) {
            return null;
        }
        return keyAs;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?> A0S(C02000hn r4) {
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r4.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || jsonDeserialize.builder() == C04130pP.class) {
            return null;
        }
        return jsonDeserialize.builder();
    }

    @Override // X.AbstractC02230iJ
    public final Object A0T(AnonymousClass0qA r3) {
        Class<? extends JsonDeserializer<?>> contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0U(AnonymousClass0qA r3) {
        Class<? extends JsonSerializer<?>> contentUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0V(AnonymousClass0qA r3) {
        Class<? extends AnonymousClass0rJ<?, ?>> converter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (converter = jsonDeserialize.converter()) == AbstractC01760gy.class) {
            return null;
        }
        return converter;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0W(AnonymousClass0qA r3) {
        Class<? extends JsonDeserializer<?>> using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0X(AnonymousClass0qA r3) {
        Class<? extends AnonymousClass0p6> keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == AnonymousClass0iA.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0Y(AnonymousClass0qA r3) {
        Class<? extends JsonSerializer<?>> keyUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (keyUsing = jsonSerialize.keyUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0Z(AnonymousClass0qA r3) {
        Class<? extends AnonymousClass0rJ<?, ?>> converter;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (converter = jsonSerialize.converter()) == AbstractC01760gy.class) {
            return null;
        }
        return converter;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0a(AnonymousClass0qA r3) {
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

    @Override // X.AbstractC02230iJ
    public final Object A0b(C02000hn r3) {
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

    @Override // X.AbstractC02230iJ
    public final Object A0c(C02000hn r2) {
        JsonNaming jsonNaming = (JsonNaming) r2.A0M(JsonNaming.class);
        if (jsonNaming == null) {
            return null;
        }
        return jsonNaming.value();
    }

    @Override // X.AbstractC02230iJ
    public final Object A0d(C02000hn r2) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) r2.A0M(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // X.AbstractC02230iJ
    public final Object A0e(AbstractC01990hm r3) {
        Class<? extends AnonymousClass0rJ<?, ?>> contentConverter;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r3.A0M(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentConverter = jsonDeserialize.contentConverter()) == AbstractC01760gy.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.AbstractC02230iJ
    public final Object A0f(AbstractC01990hm r3) {
        Class A0K;
        JacksonInject jacksonInject = (JacksonInject) r3.A0M(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (r3 instanceof AnonymousClass0Cr) {
            AnonymousClass0Cr r1 = (AnonymousClass0Cr) r3;
            if (r1.A0Z() != 0) {
                A0K = r1.A0a();
                return A0K.getName();
            }
        }
        A0K = r3.A0K();
        return A0K.getName();
    }

    @Override // X.AbstractC02230iJ
    public final Object A0g(AbstractC01990hm r3) {
        Class<? extends AnonymousClass0rJ<?, ?>> contentConverter;
        JsonSerialize jsonSerialize = (JsonSerialize) r3.A0M(JsonSerialize.class);
        if (jsonSerialize == null || (contentConverter = jsonSerialize.contentConverter()) == AbstractC01760gy.class) {
            return null;
        }
        return contentConverter;
    }

    @Override // X.AbstractC02230iJ
    public final String A0h(C02000hn r2) {
        JsonTypeName jsonTypeName = (JsonTypeName) r2.A0M(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // X.AbstractC02230iJ
    public final String A0i(AnonymousClass0Oy r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonDeserialize.class) || r2.A0J(JsonView.class) || r2.A0J(JsonBackReference.class) || r2.A0J(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02230iJ
    public final String A0j(AnonymousClass0Oy r2) {
        JsonProperty jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0J(JsonSerialize.class) || r2.A0J(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // X.AbstractC02230iJ
    public final String A0k(AnonymousClass0Cr r2) {
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

    @Override // X.AbstractC02230iJ
    public final String A0l(AnonymousClass0Cr r2) {
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

    @Override // X.AbstractC02230iJ
    public final String A0m(AnonymousClass0Ox r2) {
        JsonProperty jsonProperty;
        if (r2 == null || (jsonProperty = (JsonProperty) r2.A0M(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // X.AbstractC02230iJ
    public final List<AnonymousClass0qX> A0n(AnonymousClass0qA r8) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) r8.A0M(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        int length = value.length;
        ArrayList arrayList = new ArrayList(length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new AnonymousClass0qX(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0o(AnonymousClass0qA r2) {
        return r2.A0J(JsonCreator.class);
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0p(AbstractC01990hm r3) {
        JsonIgnore jsonIgnore = (JsonIgnore) r3.A0M(JsonIgnore.class);
        if (jsonIgnore == null || !jsonIgnore.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0q(AnonymousClass0Cr r2) {
        return r2.A0J(JsonAnyGetter.class);
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0r(AnonymousClass0Cr r2) {
        return r2.A0J(JsonAnySetter.class);
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0s(AnonymousClass0Cr r3) {
        JsonValue jsonValue = (JsonValue) r3.A0M(JsonValue.class);
        if (jsonValue == null || !jsonValue.value()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02230iJ
    public final Class<?>[] A0u(AnonymousClass0qA r2) {
        JsonView jsonView = (JsonView) r2.A0M(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // X.AbstractC02230iJ
    public final String[] A0v(AnonymousClass0qA r2) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) r2.A0M(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // X.AbstractC02230iJ
    public final String[] A0w(C02000hn r2) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) r2.A0M(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // X.AbstractC02230iJ
    public final C03550nX A02(AbstractC01990hm r2) {
        return A02(r2);
    }

    @Override // X.AbstractC02230iJ
    public final AbstractC04540qc<?> A0D(AbstractC02110i2<?> r2, C02000hn r3, AbstractC02190iF r4) {
        return A00(r2, r3);
    }

    @Override // X.AbstractC02230iJ
    public final AbstractC04540qc<?> A0E(AbstractC02110i2<?> r3, AbstractC01990hm r4, AbstractC02190iF r5) {
        if (r5.A0N()) {
            return A00(r3, r4);
        }
        StringBuilder sb = new StringBuilder("Must call method with a container type (got ");
        sb.append(r5);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // X.AbstractC02230iJ
    public final AbstractC04540qc<?> A0F(AbstractC02110i2<?> r2, AbstractC01990hm r3, AbstractC02190iF r4) {
        if (r4.A0N()) {
            return null;
        }
        return A00(r2, r3);
    }

    @Override // X.AbstractC02230iJ
    public final boolean A0t(Annotation annotation) {
        if (annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02230iJ, X.AbstractC03700oK
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }
}
