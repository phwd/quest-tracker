package com.fasterxml.jackson.databind.deser;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05180kU;
import X.AbstractC06410n2;
import X.AnonymousClass006;
import X.AnonymousClass0Fv;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.AnonymousClass0l3;
import X.AnonymousClass0lK;
import X.C03990gZ;
import X.C05410l1;
import X.C05480lF;
import X.C05510lO;
import X.C05530lQ;
import X.C05540lR;
import X.C05560lT;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    private final Object A00(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        Object A05 = this._valueInstantiator.A05(r5);
        while (r4.A0a() != EnumC04820ji.END_OBJECT) {
            String A0d = r4.A0d();
            r4.A0b();
            AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
            if (A00 != null) {
                try {
                    A00.A08(r4, r5, A05);
                } catch (Exception e) {
                    A0g(e, A05, A0d, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0c(r4, r5, A05, A0d);
            }
            r4.A0b();
        }
        return A05;
    }

    private final void A01(AbstractC04100gp r12, AbstractC04020gg r13, Object obj) throws IOException, AnonymousClass0jg {
        Class<?> cls;
        Integer num;
        if (this._needViewProcesing) {
            cls = r13._view;
        } else {
            cls = null;
        }
        C05510lO r5 = new C05510lO(this._externalTypeIdHandler);
        while (r12.A0a() != EnumC04820ji.END_OBJECT) {
            String A0d = r12.A0d();
            r12.A0b();
            AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
            if (A00 != null) {
                if (r12.A0a().isScalarValue() && (num = r5.A00.get(A0d)) != null) {
                    int intValue = num.intValue();
                    if (A0d.equals(r5.A01[intValue].A02)) {
                        String A0e = r12.A0e();
                        if (obj != null) {
                            AnonymousClass0Fv[] r3 = r5.A02;
                            if (r3[intValue] != null) {
                                C05510lO.A00(r5, r12, r13, obj, intValue, A0e);
                                r3[intValue] = null;
                            }
                        }
                        r5.A03[intValue] = A0e;
                    }
                }
                if (cls == null || A00.A0B(cls)) {
                    try {
                        A00.A08(r12, r13, obj);
                        r12.A0b();
                    } catch (Exception e) {
                        A0g(e, obj, A0d, r13);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0d)) {
                    if (!r5.A02(r12, r13, A0d, obj)) {
                        C05480lF r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r12, r13, obj, A0d);
                        } else {
                            A0N(r12, r13, obj, A0d);
                        }
                    }
                    r12.A0b();
                }
            }
            r12.A0Z();
            r12.A0b();
        }
        r5.A01(r12, r13, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC04100gp r6, AbstractC04020gg r7, Object obj) throws IOException, AnonymousClass0jg {
        Class<?> cls;
        Class<?> cls2;
        if (this._injectables != null) {
            A0d(r7);
        }
        if (this._unwrappedPropertyHandler != null) {
            EnumC04820ji A0a = r6.A0a();
            if (A0a == EnumC04820ji.START_OBJECT) {
                A0a = r6.A0b();
            }
            AnonymousClass0Fv r4 = new AnonymousClass0Fv(r6.A0I());
            r4.A07();
            if (this._needViewProcesing) {
                cls2 = r7._view;
            } else {
                cls2 = null;
            }
            while (A0a == EnumC04820ji.FIELD_NAME) {
                String A0d = r6.A0d();
                AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
                r6.A0b();
                if (A00 == null) {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0d)) {
                        r4.A09(A0d);
                        r4.A08(r6);
                        C05480lF r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r6, r7, obj, A0d);
                        }
                        A0a = r6.A0b();
                    }
                } else if (cls2 == null || A00.A0B(cls2)) {
                    try {
                        A00.A08(r6, r7, obj);
                        A0a = r6.A0b();
                    } catch (Exception e) {
                        A0g(e, obj, A0d, r7);
                    }
                }
                r6.A0Z();
                A0a = r6.A0b();
            }
            r4.A05();
            this._unwrappedPropertyHandler.A00(r7, obj, r4);
        } else if (this._externalTypeIdHandler != null) {
            A01(r6, r7, obj);
            return obj;
        } else {
            EnumC04820ji A0a2 = r6.A0a();
            if (A0a2 == EnumC04820ji.START_OBJECT) {
                A0a2 = r6.A0b();
            }
            if (!this._needViewProcesing || (cls = r7._view) == null) {
                while (A0a2 == EnumC04820ji.FIELD_NAME) {
                    String A0d2 = r6.A0d();
                    r6.A0b();
                    AnonymousClass0HD A002 = this._beanProperties.A00(A0d2);
                    if (A002 != null) {
                        try {
                            A002.A08(r6, r7, obj);
                        } catch (Exception e2) {
                            A0g(e2, obj, A0d2, r7);
                        }
                    } else {
                        HashSet<String> hashSet2 = this._ignorableProps;
                        if (hashSet2 == null || !hashSet2.contains(A0d2)) {
                            C05480lF r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r6, r7, obj, A0d2);
                            } else {
                                A0N(r6, r7, obj, A0d2);
                            }
                        } else {
                            r6.A0Z();
                        }
                    }
                    A0a2 = r6.A0b();
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
    public final BeanDeserializerBase A0Q(C05530lQ r2) {
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
        if (r1 != X.EnumC04820ji.FIELD_NAME) goto L_0x02aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01c5, code lost:
        r14.A0b();
        r5.A08(r14);
        r1 = r14.A0b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d1, code lost:
        A0g(r1, r13._beanType._class, r4, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02b2, code lost:
        if (r3.getClass() != r13._beanType._class) goto L_0x02b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02b4, code lost:
        r2.A01(r14, r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02b7, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b6, code lost:
        if (r4.getClass() == r13._beanType._class) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017e, code lost:
        r1 = r14.A0b();
     */
    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object A0Z(X.AbstractC04100gp r14, X.AbstractC04020gg r15) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 826
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializer.A0Z(X.0gp, X.0gg):java.lang.Object");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC04100gp r8, AbstractC04020gg r9) throws IOException, AnonymousClass0jg {
        C05540lR r6 = this._propertyBasedCreator;
        C05560lT A01 = r6.A01(r8, r9, this._objectIdReader);
        EnumC04820ji A0a = r8.A0a();
        AnonymousClass0Fv r3 = null;
        while (A0a == EnumC04820ji.FIELD_NAME) {
            String A0d = r8.A0d();
            r8.A0b();
            AnonymousClass0HD r0 = r6.A00.get(A0d);
            if (r0 != null) {
                if (A01.A02(r0.A01(), r0.A05(r8, r9))) {
                    r8.A0b();
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
                        A0g(e, this._beanType._class, A0d, r9);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (!A01.A03(A0d)) {
                AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
                if (A00 != null) {
                    A01.A01(A00, A00.A05(r8, r9));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0d)) {
                        C05480lF r1 = this._anySetter;
                        if (r1 != null) {
                            A01.A00(r1, A0d, r1.A00(r8, r9));
                        } else {
                            if (r3 == null) {
                                r3 = new AnonymousClass0Fv(r8.A0I());
                            }
                            r3.A09(A0d);
                            r3.A08(r8);
                        }
                    } else {
                        r8.A0Z();
                    }
                }
            }
            A0a = r8.A0b();
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

    /* JADX WARN: Incorrect return type in method signature: (LX/0gp;LX/0gg;Ljava/lang/Object;Ljava/lang/Class<*>;)Ljava/lang/Object; */
    private final void A02(AbstractC04100gp r4, AbstractC04020gg r5, Object obj, Class cls) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        while (A0a == EnumC04820ji.FIELD_NAME) {
            String A0d = r4.A0d();
            r4.A0b();
            AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0d)) {
                    C05480lF r0 = this._anySetter;
                    if (r0 != null) {
                        r0.A01(r4, r5, obj, A0d);
                    } else {
                        A0N(r4, r5, obj, A0d);
                    }
                    A0a = r4.A0b();
                }
            } else if (A00.A0B(cls)) {
                try {
                    A00.A08(r4, r5, obj);
                    A0a = r4.A0b();
                } catch (Exception e) {
                    A0g(e, obj, A0d, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0Z();
            A0a = r4.A0b();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            if (this._vanillaProcessing) {
                r4.A0b();
                return A00(r4, r5);
            }
            r4.A0b();
        } else if (A0a == null) {
            throw C03990gZ.A00(null, AnonymousClass006.A05("Unexpected end-of-input when trying to deserialize a ", this._beanType._class.getName()));
        } else {
            switch (C05410l1.A00[A0a.ordinal()]) {
                case 1:
                    return A0W(r4, r5);
                case 2:
                    return A0V(r4, r5);
                case 3:
                    return A0U(r4, r5);
                case 4:
                    return r4.A0S();
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
                    throw null;
            }
        }
        if (this._objectIdReader != null) {
            return A0X(r4, r5);
        }
        return A0Z(r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> A0B(AbstractC06410n2 r3) {
        if (getClass() != BeanDeserializer.class) {
            return this;
        }
        return new BeanDeserializer(this, r3);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, C05530lQ r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC06410n2 r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public BeanDeserializer(AnonymousClass0l3 r1, AbstractC05180kU r2, AnonymousClass0lK r3, Map<String, AnonymousClass0HD> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r1, r2, r3, map, hashSet, z, z2);
    }
}
