package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass006;
import X.AnonymousClass0EC;
import X.AnonymousClass0KG;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C07130om;
import X.C07160op;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import java.io.IOException;
import java.lang.reflect.Method;

public final class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    public static final long serialVersionUID = -5893263645879532318L;
    public final C07160op<?> _resolver;

    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class<?> _enumClass;
        public final Method _factory;
        public final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnonymousClass0EC r3, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = r3.A00;
            this._inputType = cls2;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final Object A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
            Object obj;
            Class<?> cls = this._inputType;
            if (cls == null) {
                obj = r4.A0P();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(r4.A05());
            } else if (cls == Long.class) {
                obj = Long.valueOf(r4.A0A());
            } else {
                throw r5.A0B(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                C07130om.A04(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public EnumDeserializer(C07160op<?> r2) {
        super(Enum.class);
        this._resolver = r2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0027 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.lang.Enum<?> */
    /* JADX WARN: Type inference failed for: r13v0, types: [X.0aK] */
    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Enum<?> A09(AnonymousClass0aT r12, AbstractC02570aK r13) throws IOException, C05910ld {
        Enum r1;
        String str;
        EnumC05930lf A0G = r12.A0G();
        if (A0G == EnumC05930lf.VALUE_STRING || A0G == EnumC05930lf.FIELD_NAME) {
            String A0P = r12.A0P();
            r1 = (T) this._resolver._enumsById.get(A0P);
            if (r1 == null) {
                if (r13.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (A0P.length() == 0 || A0P.trim().length() == 0)) {
                    return null;
                }
                if (!r13.A0O(EnumC02560aJ.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    throw r13.A0G(A0P, this._resolver._enumClass, "value not one of declared Enum instance names");
                }
            }
        } else if (A0G != EnumC05930lf.VALUE_NUMBER_INT) {
            throw r13.A0B(this._resolver._enumClass);
        } else if (!r13.A0O(EnumC02560aJ.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            int A06 = r12.A06();
            C07160op<?> r3 = this._resolver;
            if (A06 >= 0) {
                Enum[] enumArr = (T[]) r3._enums;
                if (A06 < enumArr.length) {
                    r1 = (T) enumArr[A06];
                    if (r1 == null && !r13.A0O(EnumC02560aJ.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        Class<T> cls = r3._enumClass;
                        String A02 = AnonymousClass006.A02("index value outside legal index range [0..", r3._enums.length - 1, "]");
                        AnonymousClass0aT r14 = r13.A00;
                        String name = cls.getName();
                        str = AbstractC02570aK.A02(r14.A0P());
                        throw new AnonymousClass0KG(AnonymousClass006.A0A("Can not construct instance of ", name, " from number value (", str, "): ", A02), r14.A0E(), null, cls);
                    }
                }
            }
            r1 = null;
            Class<T> cls2 = r3._enumClass;
            String A022 = AnonymousClass006.A02("index value outside legal index range [0..", r3._enums.length - 1, "]");
            AnonymousClass0aT r142 = r13.A00;
            String name2 = cls2.getName();
            try {
                str = AbstractC02570aK.A02(r142.A0P());
            } catch (Exception unused) {
                str = "[N/A]";
            }
            throw new AnonymousClass0KG(AnonymousClass006.A0A("Can not construct instance of ", name2, " from number value (", str, "): ", A022), r142.A0E(), null, cls2);
        } else {
            throw AnonymousClass0aG.A00(r13.A00, "Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
        return r1;
    }
}
