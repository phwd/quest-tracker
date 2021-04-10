package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass0q;
import X.C1025qv;
import X.EnumC1023qs;
import X.NX;
import X.Q5;
import X.Q8;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer {
    public static final long serialVersionUID = -5893263645879532318L;
    public final Q8 _resolver;

    public class FactoryBasedDeserializer extends StdScalarDeserializer {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class _enumClass;
        public final Method _factory;
        public final Class _inputType;

        public FactoryBasedDeserializer(Class cls, AnonymousClass0q r3, Class cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = r3.A00;
            this._inputType = cls2;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            Object obj;
            Class cls = this._inputType;
            if (cls == null) {
                obj = qiVar.A0p();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(qiVar.A0M());
            } else if (cls == Long.class) {
                obj = Long.valueOf(qiVar.A0P());
            } else {
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                Q5.A04(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    public EnumDeserializer(Q8 q8) {
        super(Enum.class);
        this._resolver = q8;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Enum A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Enum r1;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_STRING || A0U == NX.FIELD_NAME) {
            String A0p = qiVar.A0p();
            r1 = (Enum) this._resolver._enumsById.get(A0p);
            if (r1 == null) {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (A0p.length() == 0 || A0p.trim().length() == 0)) {
                    return null;
                }
                if (!qrVar.A0O(EnumC1023qs.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    qrVar.A0L(this._resolver._enumClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (A0U != NX.VALUE_NUMBER_INT) {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (!qrVar.A0O(EnumC1023qs.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            int A0J = qiVar.A0J();
            Q8 q8 = this._resolver;
            if (A0J >= 0) {
                Enum[] enumArr = q8._enums;
                if (A0J < enumArr.length) {
                    r1 = enumArr[A0J];
                    if (r1 == null && !qrVar.A0O(EnumC1023qs.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        throw new NullPointerException("getText");
                    }
                }
            }
            r1 = null;
            try {
                throw new NullPointerException("getText");
            } catch (Exception unused) {
                throw new NullPointerException("getTokenLocation");
            }
        } else {
            throw C1025qv.A00(null, "Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
        return r1;
    }
}
