package com.fasterxml.jackson.databind.deser;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC06260mR;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0nJ;
import X.AnonymousClass0nL;
import X.AnonymousClass0nM;
import X.AnonymousClass0nO;
import X.C01570Jk;
import X.C05910ld;
import X.C06480mx;
import X.C06490mz;
import X.C06560nA;
import X.C06570nF;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    private final Object A00(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        Object A05 = this._valueInstantiator.A05(r5);
        while (r4.A0G() != EnumC05930lf.END_OBJECT) {
            String A0O = r4.A0O();
            r4.A0a();
            AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
            if (A00 != null) {
                try {
                    A00.A08(r4, r5, A05);
                } catch (Exception e) {
                    A0g(e, A05, A0O, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0c(r4, r5, A05, A0O);
            }
            r4.A0a();
        }
        return A05;
    }

    private final void A01(AnonymousClass0aT r12, AbstractC02570aK r13, Object obj) throws IOException, C05910ld {
        Class<?> cls;
        Integer num;
        if (this._needViewProcesing) {
            cls = r13._view;
        } else {
            cls = null;
        }
        AnonymousClass0nJ r5 = new AnonymousClass0nJ(this._externalTypeIdHandler);
        while (r12.A0G() != EnumC05930lf.END_OBJECT) {
            String A0O = r12.A0O();
            r12.A0a();
            AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
            if (A00 != null) {
                if (r12.A0G().isScalarValue() && (num = r5.A00.get(A0O)) != null) {
                    int intValue = num.intValue();
                    if (A0O.equals(r5.A01[intValue].A02)) {
                        String A0P = r12.A0P();
                        if (obj != null) {
                            C01570Jk[] r3 = r5.A02;
                            if (r3[intValue] != null) {
                                AnonymousClass0nJ.A00(r5, r12, r13, obj, intValue, A0P);
                                r3[intValue] = null;
                            }
                        }
                        r5.A03[intValue] = A0P;
                    }
                }
                if (cls == null || A00.A0B(cls)) {
                    try {
                        A00.A08(r12, r13, obj);
                        r12.A0a();
                    } catch (Exception e) {
                        A0g(e, obj, A0O, r13);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0O)) {
                    if (!r5.A02(r12, r13, A0O, obj)) {
                        C06560nA r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r12, r13, obj, A0O);
                        } else {
                            A0N(r12, r13, obj, A0O);
                        }
                    }
                    r12.A0a();
                }
            }
            r12.A0F();
            r12.A0a();
        }
        r5.A01(r12, r13, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AnonymousClass0aT r6, AbstractC02570aK r7, Object obj) throws IOException, C05910ld {
        Class<?> cls;
        Class<?> cls2;
        if (this._injectables != null) {
            A0d(r7);
        }
        if (this._unwrappedPropertyHandler != null) {
            EnumC05930lf A0G = r6.A0G();
            if (A0G == EnumC05930lf.START_OBJECT) {
                A0G = r6.A0a();
            }
            C01570Jk r4 = new C01570Jk(r6.A0I());
            r4.A0F();
            if (this._needViewProcesing) {
                cls2 = r7._view;
            } else {
                cls2 = null;
            }
            while (A0G == EnumC05930lf.FIELD_NAME) {
                String A0O = r6.A0O();
                AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
                r6.A0a();
                if (A00 == null) {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0O)) {
                        r4.A0P(A0O);
                        r4.A0a(r6);
                        C06560nA r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r6, r7, obj, A0O);
                        }
                        A0G = r6.A0a();
                    }
                } else if (cls2 == null || A00.A0B(cls2)) {
                    try {
                        A00.A08(r6, r7, obj);
                        A0G = r6.A0a();
                    } catch (Exception e) {
                        A0g(e, obj, A0O, r7);
                    }
                }
                r6.A0F();
                A0G = r6.A0a();
            }
            r4.A0C();
            this._unwrappedPropertyHandler.A00(r7, obj, r4);
        } else if (this._externalTypeIdHandler != null) {
            A01(r6, r7, obj);
            return obj;
        } else {
            EnumC05930lf A0G2 = r6.A0G();
            if (A0G2 == EnumC05930lf.START_OBJECT) {
                A0G2 = r6.A0a();
            }
            if (!this._needViewProcesing || (cls = r7._view) == null) {
                while (A0G2 == EnumC05930lf.FIELD_NAME) {
                    String A0O2 = r6.A0O();
                    r6.A0a();
                    AbstractC01680Ku A002 = this._beanProperties.A00(A0O2);
                    if (A002 != null) {
                        try {
                            A002.A08(r6, r7, obj);
                        } catch (Exception e2) {
                            A0g(e2, obj, A0O2, r7);
                        }
                    } else {
                        HashSet<String> hashSet2 = this._ignorableProps;
                        if (hashSet2 == null || !hashSet2.contains(A0O2)) {
                            C06560nA r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r6, r7, obj, A0O2);
                            } else {
                                A0N(r6, r7, obj, A0O2);
                            }
                        } else {
                            r6.A0F();
                        }
                    }
                    A0G2 = r6.A0a();
                }
            } else {
                A02(r6, r7, obj, cls);
                return obj;
            }
        }
        return obj;
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayDeserializer(this, this._beanProperties.A04());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(AnonymousClass0nL r2) {
        return new BeanDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanDeserializer(this, hashSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r3 = r8.A02(r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01c3, code lost:
        if (r1 != X.EnumC05930lf.FIELD_NAME) goto L_0x02ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01c5, code lost:
        r14.A0a();
        r4.A0a(r14);
        r1 = r14.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d1, code lost:
        A0g(r1, r13._beanType._class, r2, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02b6, code lost:
        if (r3.getClass() != r13._beanType._class) goto L_0x02bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02b8, code lost:
        r9.A01(r14, r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02bb, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        if (r4.getClass() == r13._beanType._class) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017e, code lost:
        r1 = r14.A0a();
     */
    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object A0Z(X.AnonymousClass0aT r14, X.AbstractC02570aK r15) throws java.io.IOException, X.C05910ld {
        /*
        // Method dump skipped, instructions count: 831
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializer.A0Z(X.0aT, X.0aK):java.lang.Object");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        AnonymousClass0nM r6 = this._propertyBasedCreator;
        AnonymousClass0nO A01 = r6.A01(r8, r9, this._objectIdReader);
        EnumC05930lf A0G = r8.A0G();
        C01570Jk r3 = null;
        while (A0G == EnumC05930lf.FIELD_NAME) {
            String A0O = r8.A0O();
            r8.A0a();
            AbstractC01680Ku r0 = r6.A00.get(A0O);
            if (r0 != null) {
                if (A01.A02(r0.A01(), r0.A05(r8, r9))) {
                    r8.A0a();
                    try {
                        Object A02 = r6.A02(r9, A01);
                        if (A02.getClass() != this._beanType._class) {
                            return A0b(r8, r9, A02, r3);
                        }
                        if (r3 != null) {
                            A0e(r9, A02, r3);
                        }
                        A0A(r8, r9, A02);
                        return A02;
                    } catch (Exception e) {
                        A0g(e, this._beanType._class, A0O, r9);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (!A01.A03(A0O)) {
                AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
                if (A00 != null) {
                    A01.A01(A00, A00.A05(r8, r9));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0O)) {
                        C06560nA r1 = this._anySetter;
                        if (r1 != null) {
                            A01.A00(r1, A0O, r1.A00(r8, r9));
                        } else {
                            if (r3 == null) {
                                r3 = new C01570Jk(r8.A0I());
                            }
                            r3.A0P(A0O);
                            r3.A0a(r8);
                        }
                    } else {
                        r8.A0F();
                    }
                }
            }
            A0G = r8.A0a();
        }
        try {
            Object A022 = r6.A02(r9, A01);
            if (r3 == null) {
                return A022;
            }
            if (A022.getClass() != this._beanType._class) {
                return A0b(null, r9, A022, r3);
            }
            A0e(r9, A022, r3);
            return A022;
        } catch (Exception e2) {
            A0f(e2, r9);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0aT;LX/0aK;Ljava/lang/Object;Ljava/lang/Class<*>;)Ljava/lang/Object; */
    private final void A02(AnonymousClass0aT r4, AbstractC02570aK r5, Object obj, Class cls) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        while (A0G == EnumC05930lf.FIELD_NAME) {
            String A0O = r4.A0O();
            r4.A0a();
            AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0O)) {
                    C06560nA r0 = this._anySetter;
                    if (r0 != null) {
                        r0.A01(r4, r5, obj, A0O);
                    } else {
                        A0N(r4, r5, obj, A0O);
                    }
                    A0G = r4.A0a();
                }
            } else if (A00.A0B(cls)) {
                try {
                    A00.A08(r4, r5, obj);
                    A0G = r4.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0O, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0F();
            A0G = r4.A0a();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            if (this._vanillaProcessing) {
                r4.A0a();
                return A00(r4, r5);
            }
            r4.A0a();
        } else if (A0G == null) {
            throw AnonymousClass0aG.A00(r5.A00, AnonymousClass006.A05("Unexpected end-of-input when trying to deserialize a ", this._beanType._class.getName()));
        } else {
            switch (C06480mx.A00[A0G.ordinal()]) {
                case 1:
                    return A0W(r4, r5);
                case 2:
                    return A0V(r4, r5);
                case 3:
                    return A0U(r4, r5);
                case 4:
                    return r4.A0M();
                case 5:
                case 6:
                    return A0T(r4, r5);
                case 7:
                    return A0S(r4, r5);
                case 8:
                case 9:
                    if (this._vanillaProcessing) {
                        return A00(r4, r5);
                    }
                    break;
                default:
                    throw r5.A0B(this._beanType._class);
            }
        }
        if (this._objectIdReader != null) {
            return A0X(r4, r5);
        }
        return A0Z(r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> A0B(AbstractC07200ov r3) {
        if (getClass() != BeanDeserializer.class) {
            return this;
        }
        return new BeanDeserializer(this, r3);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, AnonymousClass0nL r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC07200ov r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public BeanDeserializer(C06490mz r1, AbstractC06260mR r2, C06570nF r3, Map<String, AbstractC01680Ku> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r1, r2, r3, map, hashSet, z, z2);
    }
}
