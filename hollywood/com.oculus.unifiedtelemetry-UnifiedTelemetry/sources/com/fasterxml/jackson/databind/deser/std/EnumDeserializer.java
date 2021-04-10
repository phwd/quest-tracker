package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.AnonymousClass7P;
import X.C0223Wj;
import X.CG;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.M9;
import X.Mv;
import X.q0;
import java.io.IOException;
import java.lang.reflect.Method;

public final class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    public static final long serialVersionUID = -5893263645879532318L;
    public final M9<?> _resolver;

    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class<?> _enumClass;
        public final Method _factory;
        public final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnonymousClass7P r3, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = r3.A00;
            this._inputType = cls2;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            Object obj;
            Class<?> cls = this._inputType;
            if (cls == null) {
                obj = ww.A0d();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(ww.A0C());
            } else if (cls == Long.class) {
                obj = Long.valueOf(ww.A0D());
            } else {
                throw wn.A08(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                Mv.A03(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public EnumDeserializer(M9<?> m9) {
        super(Enum.class);
        this._resolver = m9;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0027 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.lang.Enum<?> */
    /* JADX WARN: Type inference failed for: r13v0, types: [X.Wn] */
    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Enum<?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Enum r1;
        String str;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_STRING || A0Z == EnumC0470q2.FIELD_NAME) {
            String A0d = ww.A0d();
            r1 = (T) this._resolver._enumsById.get(A0d);
            if (r1 == null) {
                if (wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (A0d.length() == 0 || A0d.trim().length() == 0)) {
                    return null;
                }
                if (!wn.A0L(EnumC0225Wm.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    throw wn.A0D(A0d, this._resolver._enumClass, "value not one of declared Enum instance names");
                }
            }
        } else if (A0Z != EnumC0470q2.VALUE_NUMBER_INT) {
            throw wn.A08(this._resolver._enumClass);
        } else if (!wn.A0L(EnumC0225Wm.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            int A0L = ww.A0L();
            M9<?> m9 = this._resolver;
            if (A0L >= 0) {
                Enum[] enumArr = (T[]) m9._enums;
                if (A0L < enumArr.length) {
                    r1 = (T) enumArr[A0L];
                    if (r1 == null && !wn.A0L(EnumC0225Wm.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        Class<T> cls = m9._enumClass;
                        String A02 = AnonymousClass06.A02("index value outside legal index range [0..", m9._enums.length - 1, "]");
                        AbstractC0232Ww ww2 = wn.A00;
                        String name = cls.getName();
                        str = AbstractC0226Wn.A02(ww2.A0d());
                        throw new CG(AnonymousClass06.A08("Can not construct instance of ", name, " from number value (", str, "): ", A02), ww2.A0O(), null, cls);
                    }
                }
            }
            r1 = null;
            Class<T> cls2 = m9._enumClass;
            String A022 = AnonymousClass06.A02("index value outside legal index range [0..", m9._enums.length - 1, "]");
            AbstractC0232Ww ww22 = wn.A00;
            String name2 = cls2.getName();
            try {
                str = AbstractC0226Wn.A02(ww22.A0d());
            } catch (Exception unused) {
                str = "[N/A]";
            }
            throw new CG(AnonymousClass06.A08("Can not construct instance of ", name2, " from number value (", str, "): ", A022), ww22.A0O(), null, cls2);
        } else {
            throw C0223Wj.A00(wn.A00, "Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
        return r1;
    }
}
