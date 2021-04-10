package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.qp  reason: case insensitive filesystem */
public abstract class AbstractC1020qp implements Nc, Serializable {
    private final C0244Mv A02(AbstractC1044rJ rJVar) {
        if (!(this instanceof Rw)) {
            return null;
        }
        return A02(rJVar);
    }

    private final String A0I(St st) {
        if (!(this instanceof Rw)) {
            return null;
        }
        JsonProperty jsonProperty = (JsonProperty) st.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (st.A0O(JsonDeserialize.class) || st.A0O(JsonView.class) || st.A0O(JsonBackReference.class) || st.A0O(JsonManagedReference.class)) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        return null;
    }

    private final String A0J(St st) {
        if (!(this instanceof Rw)) {
            return null;
        }
        JsonProperty jsonProperty = (JsonProperty) st.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (st.A0O(JsonSerialize.class) || st.A0O(JsonView.class)) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        return null;
    }

    private final String A0K(AnonymousClass0q r2) {
        if (!(this instanceof Rw)) {
            return null;
        }
        JsonSetter jsonSetter = (JsonSetter) r2.A0L(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0O(JsonDeserialize.class) || r2.A0O(JsonView.class) || r2.A0O(JsonBackReference.class) || r2.A0O(JsonManagedReference.class)) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        return null;
    }

    private final String A0L(AnonymousClass0q r2) {
        if (!(this instanceof Rw)) {
            return null;
        }
        JsonGetter jsonGetter = (JsonGetter) r2.A0L(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        JsonProperty jsonProperty = (JsonProperty) r2.A0L(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (r2.A0O(JsonSerialize.class) || r2.A0O(JsonView.class)) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        return null;
    }

    public final C0244Mv A01(P9 p9) {
        boolean z = this instanceof Rw;
        if (z) {
            JsonFormat jsonFormat = (JsonFormat) p9.A0L(JsonFormat.class);
            if (jsonFormat != null) {
                return new C0244Mv(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone());
            }
            return null;
        } else if (!(p9 instanceof AbstractC1044rJ)) {
            return null;
        } else {
            AbstractC1044rJ rJVar = (AbstractC1044rJ) p9;
            if (z) {
                return A02(rJVar);
            }
            return null;
        }
    }

    public final N3 A03(P9 p9, N3 n3) {
        if (!(this instanceof Rw)) {
            return n3;
        }
        JsonInclude jsonInclude = (JsonInclude) p9.A0L(JsonInclude.class);
        if (jsonInclude != null) {
            return jsonInclude.value();
        }
        JsonSerialize jsonSerialize = (JsonSerialize) p9.A0L(JsonSerialize.class);
        if (jsonSerialize == null) {
            return n3;
        }
        switch (jsonSerialize.include().ordinal()) {
            case 0:
                return N3.ALWAYS;
            case 1:
                return N3.NON_NULL;
            case 2:
                return N3.NON_DEFAULT;
            case 3:
                return N3.NON_EMPTY;
            default:
                return n3;
        }
    }

    public final O3 A04(AbstractC1044rJ rJVar) {
        String value;
        Integer num;
        if (!(this instanceof Rw)) {
            return null;
        }
        JsonManagedReference jsonManagedReference = (JsonManagedReference) rJVar.A0L(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            value = jsonManagedReference.value();
            num = AnonymousClass09.A00;
        } else {
            JsonBackReference jsonBackReference = (JsonBackReference) rJVar.A0L(JsonBackReference.class);
            if (jsonBackReference == null) {
                return null;
            }
            value = jsonBackReference.value();
            num = AnonymousClass09.A01;
        }
        return new O3(num, value);
    }

    public final OE A05(P9 p9) {
        JsonProperty jsonProperty;
        String value;
        JsonProperty jsonProperty2;
        String value2;
        boolean z = this instanceof Rw;
        if (!z) {
            if (p9 instanceof St) {
                value2 = A0I((St) p9);
            } else if (p9 instanceof AnonymousClass0q) {
                value2 = A0K((AnonymousClass0q) p9);
            } else {
                if (p9 instanceof Ss) {
                    Ss ss = (Ss) p9;
                    if (!(!z || ss == null || (jsonProperty2 = (JsonProperty) ss.A0L(JsonProperty.class)) == null)) {
                        value2 = jsonProperty2.value();
                    }
                }
                return null;
            }
            if (value2 != null) {
                if (value2.length() == 0) {
                    return OE.A01;
                }
                return new OE(value2);
            }
            return null;
        }
        if (p9 instanceof St) {
            value = A0I((St) p9);
        } else if (p9 instanceof AnonymousClass0q) {
            value = A0K((AnonymousClass0q) p9);
        } else if (!(p9 instanceof Ss)) {
            return null;
        } else {
            Ss ss2 = (Ss) p9;
            if (!z || ss2 == null || (jsonProperty = (JsonProperty) ss2.A0L(JsonProperty.class)) == null) {
                return null;
            }
            value = jsonProperty.value();
        }
        if (value == null) {
            return null;
        }
        if (value.length() == 0) {
            return OE.A01;
        }
        return new OE(value);
    }

    public final OE A06(P9 p9) {
        String A0L;
        String A0L2;
        if (!(this instanceof Rw)) {
            if (p9 instanceof St) {
                A0L2 = A0J((St) p9);
            } else {
                if (p9 instanceof AnonymousClass0q) {
                    A0L2 = A0L((AnonymousClass0q) p9);
                }
                return null;
            }
            if (A0L2 != null) {
                if (A0L2.length() == 0) {
                    return OE.A01;
                }
                return new OE(A0L2);
            }
            return null;
        }
        if (p9 instanceof St) {
            A0L = A0J((St) p9);
        } else if (!(p9 instanceof AnonymousClass0q)) {
            return null;
        } else {
            A0L = A0L((AnonymousClass0q) p9);
        }
        if (A0L == null) {
            return null;
        }
        if (A0L.length() == 0) {
            return OE.A01;
        }
        return new OE(A0L);
    }

    public final PJ A07(P9 p9) {
        JsonIdentityInfo jsonIdentityInfo;
        if (!(this instanceof Rw) || (jsonIdentityInfo = (JsonIdentityInfo) p9.A0L(JsonIdentityInfo.class)) == null || jsonIdentityInfo.generator() == AbstractC1009qd.class) {
            return null;
        }
        return new PJ(jsonIdentityInfo.property(), jsonIdentityInfo.scope(), jsonIdentityInfo.generator(), false);
    }

    public final PJ A08(P9 p9, PJ pj) {
        JsonIdentityReference jsonIdentityReference;
        boolean alwaysAsId;
        if (!(this instanceof Rw) || (jsonIdentityReference = (JsonIdentityReference) p9.A0L(JsonIdentityReference.class)) == null || pj.A03 == (alwaysAsId = jsonIdentityReference.alwaysAsId())) {
            return pj;
        }
        return new PJ(pj.A02, pj.A01, pj.A00, alwaysAsId);
    }

    public final PT A09(AbstractC1032r3 r3Var, AbstractC1044rJ rJVar, AbstractC1024qt qtVar) {
        if (!(this instanceof Rw)) {
            return null;
        }
        Rw rw = (Rw) this;
        if (qtVar.A0H()) {
            return Rw.A00(rw, r3Var, rJVar);
        }
        StringBuilder sb = new StringBuilder("Must call method with a container type (got ");
        sb.append(qtVar);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r4.length() <= 0) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.QC A0A(X.AbstractC1044rJ r6) {
        /*
            r5 = this;
            boolean r0 = r5 instanceof X.Rw
            if (r0 == 0) goto L_0x004c
            java.lang.Class<com.fasterxml.jackson.annotation.JsonUnwrapped> r0 = com.fasterxml.jackson.annotation.JsonUnwrapped.class
            java.lang.annotation.Annotation r1 = r6.A0L(r0)
            com.fasterxml.jackson.annotation.JsonUnwrapped r1 = (com.fasterxml.jackson.annotation.JsonUnwrapped) r1
            if (r1 == 0) goto L_0x004c
            boolean r0 = r1.enabled()
            if (r0 == 0) goto L_0x004c
            java.lang.String r4 = r1.prefix()
            java.lang.String r3 = r1.suffix()
            r2 = 1
            if (r4 == 0) goto L_0x0026
            int r0 = r4.length()
            r1 = 1
            if (r0 > 0) goto L_0x0027
        L_0x0026:
            r1 = 0
        L_0x0027:
            if (r3 == 0) goto L_0x0039
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x0039
        L_0x002f:
            if (r1 == 0) goto L_0x0041
            if (r2 == 0) goto L_0x003b
            X.rq r0 = new X.rq
            r0.<init>(r4, r3)
            return r0
        L_0x0039:
            r2 = 0
            goto L_0x002f
        L_0x003b:
            X.rr r0 = new X.rr
            r0.<init>(r4)
            return r0
        L_0x0041:
            if (r2 == 0) goto L_0x0049
            X.rs r0 = new X.rs
            r0.<init>(r3)
            return r0
        L_0x0049:
            X.QC r0 = X.QC.A00
            return r0
        L_0x004c:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1020qp.A0A(X.rJ):X.QC");
    }

    public final Object A0B(P9 p9) {
        JsonDeserialize jsonDeserialize;
        Class contentUsing;
        if (!(this instanceof Rw) || (jsonDeserialize = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    public final Object A0C(P9 p9) {
        JsonSerialize jsonSerialize;
        Class contentUsing;
        if (!(this instanceof Rw) || (jsonSerialize = (JsonSerialize) p9.A0L(JsonSerialize.class)) == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    public final Object A0D(P9 p9) {
        JsonDeserialize jsonDeserialize;
        Class converter;
        if (!(this instanceof Rw) || (jsonDeserialize = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (converter = jsonDeserialize.converter()) == AbstractC1072ro.class) {
            return null;
        }
        return converter;
    }

    public final Object A0E(P9 p9) {
        JsonDeserialize jsonDeserialize;
        Class keyUsing;
        if (!(this instanceof Rw) || (jsonDeserialize = (JsonDeserialize) p9.A0L(JsonDeserialize.class)) == null || (keyUsing = jsonDeserialize.keyUsing()) == AbstractC1026qx.class) {
            return null;
        }
        return keyUsing;
    }

    public final Object A0F(P9 p9) {
        JsonSerialize jsonSerialize;
        Class converter;
        if (!(this instanceof Rw) || (jsonSerialize = (JsonSerialize) p9.A0L(JsonSerialize.class)) == null || (converter = jsonSerialize.converter()) == AbstractC1072ro.class) {
            return null;
        }
        return converter;
    }

    public final Object A0G(AbstractC1044rJ rJVar) {
        JacksonInject jacksonInject;
        Class A0J;
        if (!(this instanceof Rw) || (jacksonInject = (JacksonInject) rJVar.A0L(JacksonInject.class)) == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() != 0) {
            return value;
        }
        if (rJVar instanceof AnonymousClass0q) {
            AnonymousClass0q r1 = (AnonymousClass0q) rJVar;
            if (r1.A0X() != 0) {
                A0J = r1.A0Y();
                return A0J.getName();
            }
        }
        A0J = rJVar.A0J();
        return A0J.getName();
    }

    public final String A0H(C1043rI rIVar) {
        JsonTypeName jsonTypeName;
        if (!(this instanceof Rw) || (jsonTypeName = (JsonTypeName) rIVar.A0L(JsonTypeName.class)) == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    public final List A0M(P9 p9) {
        JsonSubTypes jsonSubTypes;
        if (!(this instanceof Rw) || (jsonSubTypes = (JsonSubTypes) p9.A0L(JsonSubTypes.class)) == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        int length = value.length;
        ArrayList arrayList = new ArrayList(length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new PO(type.value(), type.name()));
        }
        return arrayList;
    }

    public final boolean A0N(P9 p9) {
        if (!(this instanceof Rw)) {
            return false;
        }
        return p9.A0O(JsonCreator.class);
    }

    public final boolean A0O(AbstractC1044rJ rJVar) {
        JsonIgnore jsonIgnore;
        if (!(this instanceof Rw) || (jsonIgnore = (JsonIgnore) rJVar.A0L(JsonIgnore.class)) == null || !jsonIgnore.value()) {
            return false;
        }
        return true;
    }

    public final boolean A0P(Annotation annotation) {
        if ((this instanceof Rw) && annotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {
            return true;
        }
        return false;
    }

    public final String[] A0Q(P9 p9) {
        JsonIgnoreProperties jsonIgnoreProperties;
        if (!(this instanceof Rw) || (jsonIgnoreProperties = (JsonIgnoreProperties) p9.A0L(JsonIgnoreProperties.class)) == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }
}
