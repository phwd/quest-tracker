package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass006;
import X.AnonymousClass0Cr;
import X.AnonymousClass0P3;
import X.C02180iD;
import X.C03620oC;
import X.C04810rI;
import X.C04820rL;
import X.EnumC02200iG;
import X.EnumC03640oE;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    public static final long serialVersionUID = -5893263645879532318L;
    public final C04820rL<?> _resolver;

    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class<?> _enumClass;
        public final Method _factory;
        public final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnonymousClass0Cr r3, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = r3.A00;
            this._inputType = cls2;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Object A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
            Object obj;
            Class<?> cls = this._inputType;
            if (cls == null) {
                obj = r4.A0m();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(r4.A0G());
            } else if (cls == Long.class) {
                obj = Long.valueOf(r4.A0H());
            } else {
                throw r5.A0B(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                C04810rI.A04(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public EnumDeserializer(C04820rL<?> r2) {
        super(Enum.class);
        this._resolver = r2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0027 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.lang.Enum<?> */
    /* JADX WARN: Type inference failed for: r13v0, types: [X.0iH] */
    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Enum<?> A0A(AbstractC02280iQ r12, AbstractC02210iH r13) throws IOException, C03620oC {
        Enum r1;
        String str;
        EnumC03640oE A0i = r12.A0i();
        if (A0i == EnumC03640oE.VALUE_STRING || A0i == EnumC03640oE.FIELD_NAME) {
            String A0m = r12.A0m();
            r1 = (T) this._resolver._enumsById.get(A0m);
            if (r1 == null) {
                if (r13.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (A0m.length() == 0 || A0m.trim().length() == 0)) {
                    return null;
                }
                if (!r13.A0P(EnumC02200iG.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    throw r13.A0G(A0m, this._resolver._enumClass, "value not one of declared Enum instance names");
                }
            }
        } else if (A0i != EnumC03640oE.VALUE_NUMBER_INT) {
            throw r13.A0B(this._resolver._enumClass);
        } else if (!r13.A0P(EnumC02200iG.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            int A0T = r12.A0T();
            C04820rL<?> r3 = this._resolver;
            if (A0T >= 0) {
                Enum[] enumArr = (T[]) r3._enums;
                if (A0T < enumArr.length) {
                    r1 = (T) enumArr[A0T];
                    if (r1 == null && !r13.A0P(EnumC02200iG.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        Class<T> cls = r3._enumClass;
                        String A04 = AnonymousClass006.A04("index value outside legal index range [0..", r3._enums.length - 1, "]");
                        AbstractC02280iQ r14 = r13.A00;
                        String name = cls.getName();
                        str = AbstractC02210iH.A02(r14.A0m());
                        throw new AnonymousClass0P3(AnonymousClass006.A0D("Can not construct instance of ", name, " from number value (", str, "): ", A04), r14.A0W(), null, cls);
                    }
                }
            }
            r1 = null;
            Class<T> cls2 = r3._enumClass;
            String A042 = AnonymousClass006.A04("index value outside legal index range [0..", r3._enums.length - 1, "]");
            AbstractC02280iQ r142 = r13.A00;
            String name2 = cls2.getName();
            try {
                str = AbstractC02210iH.A02(r142.A0m());
            } catch (Exception unused) {
                str = "[N/A]";
            }
            throw new AnonymousClass0P3(AnonymousClass006.A0D("Can not construct instance of ", name2, " from number value (", str, "): ", A042), r142.A0W(), null, cls2);
        } else {
            throw C02180iD.A00(r13.A00, "Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
        return r1;
    }
}
