package com.fasterxml.jackson.databind.deser;

import X.AbstractC01170Rz;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04010oz;
import X.AbstractC04520qa;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.AnonymousClass0OD;
import X.C02180iD;
import X.C03620oC;
import X.C04200pW;
import X.C04210pY;
import X.C04290pj;
import X.C04330po;
import X.C04360pr;
import X.C04370ps;
import X.C04390pu;
import X.C04400pv;
import X.C04420px;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    private final Object A00(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        Object A05 = this._valueInstantiator.A05(r5);
        while (r4.A0i() != EnumC03640oE.END_OBJECT) {
            String A0l = r4.A0l();
            r4.A0j();
            AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
            if (A00 != null) {
                try {
                    A00.A08(r4, r5, A05);
                } catch (Exception e) {
                    A0g(e, A05, A0l, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0c(r4, r5, A05, A0l);
            }
            r4.A0j();
        }
        return A05;
    }

    private final void A01(AbstractC02280iQ r12, AbstractC02210iH r13, Object obj) throws IOException, C03620oC {
        Class<?> cls;
        Integer num;
        if (this._needViewProcesing) {
            cls = r13._view;
        } else {
            cls = null;
        }
        C04370ps r5 = new C04370ps(this._externalTypeIdHandler);
        while (r12.A0i() != EnumC03640oE.END_OBJECT) {
            String A0l = r12.A0l();
            r12.A0j();
            AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
            if (A00 != null) {
                if (r12.A0i().isScalarValue() && (num = r5.A00.get(A0l)) != null) {
                    int intValue = num.intValue();
                    if (A0l.equals(r5.A01[intValue].A02)) {
                        String A0m = r12.A0m();
                        if (obj != null) {
                            AnonymousClass0OD[] r3 = r5.A02;
                            if (r3[intValue] != null) {
                                C04370ps.A00(r5, r12, r13, obj, intValue, A0m);
                                r3[intValue] = null;
                            }
                        }
                        r5.A03[intValue] = A0m;
                    }
                }
                if (cls == null || A00.A0B(cls)) {
                    try {
                        A00.A08(r12, r13, obj);
                        r12.A0j();
                    } catch (Exception e) {
                        A0g(e, obj, A0l, r13);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0l)) {
                    if (!r5.A02(r12, r13, A0l, obj)) {
                        C04290pj r0 = this._anySetter;
                        if (r0 != null) {
                            try {
                                r0.A01(r12, r13, obj, A0l);
                            } catch (Exception e2) {
                                A0g(e2, obj, A0l, r13);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            A0N(r12, r13, obj, A0l);
                        }
                    }
                    r12.A0j();
                }
            }
            r12.A0h();
            r12.A0j();
        }
        r5.A01(r12, r13, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC02280iQ r6, AbstractC02210iH r7, Object obj) throws IOException, C03620oC {
        Class<?> cls;
        Class<?> cls2;
        if (this._injectables != null) {
            A0d(r7);
        }
        if (this._unwrappedPropertyHandler != null) {
            EnumC03640oE A0i = r6.A0i();
            if (A0i == EnumC03640oE.START_OBJECT) {
                A0i = r6.A0j();
            }
            AnonymousClass0OD r4 = new AnonymousClass0OD(r6.A0N());
            r4.A0I();
            if (this._needViewProcesing) {
                cls2 = r7._view;
            } else {
                cls2 = null;
            }
            while (A0i == EnumC03640oE.FIELD_NAME) {
                String A0l = r6.A0l();
                AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                r6.A0j();
                if (A00 == null) {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0l)) {
                        r4.A0R(A0l);
                        r4.A0c(r6);
                        C04290pj r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r6, r7, obj, A0l);
                        }
                        A0i = r6.A0j();
                    }
                } else if (cls2 == null || A00.A0B(cls2)) {
                    try {
                        A00.A08(r6, r7, obj);
                        A0i = r6.A0j();
                    } catch (Exception e) {
                        A0g(e, obj, A0l, r7);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                r6.A0h();
                A0i = r6.A0j();
            }
            r4.A0F();
            this._unwrappedPropertyHandler.A00(r7, obj, r4);
        } else if (this._externalTypeIdHandler != null) {
            A01(r6, r7, obj);
            return obj;
        } else {
            EnumC03640oE A0i2 = r6.A0i();
            if (A0i2 == EnumC03640oE.START_OBJECT) {
                A0i2 = r6.A0j();
            }
            if (!this._needViewProcesing || (cls = r7._view) == null) {
                while (A0i2 == EnumC03640oE.FIELD_NAME) {
                    String A0l2 = r6.A0l();
                    r6.A0j();
                    AbstractC01170Rz A002 = this._beanProperties.A00(A0l2);
                    if (A002 != null) {
                        try {
                            A002.A08(r6, r7, obj);
                        } catch (Exception e2) {
                            A0g(e2, obj, A0l2, r7);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        HashSet<String> hashSet2 = this._ignorableProps;
                        if (hashSet2 == null || !hashSet2.contains(A0l2)) {
                            C04290pj r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r6, r7, obj, A0l2);
                            } else {
                                A0N(r6, r7, obj, A0l2);
                            }
                        } else {
                            r6.A0h();
                        }
                    }
                    A0i2 = r6.A0j();
                }
            } else {
                A02(r6, r7, obj, cls);
                return obj;
            }
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayDeserializer(this, this._beanProperties.A04());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C04390pu r2) {
        return new BeanDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BeanDeserializer(this, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        C04400pv r6 = this._propertyBasedCreator;
        C04420px A01 = r6.A01(r8, r9, this._objectIdReader);
        EnumC03640oE A0i = r8.A0i();
        AnonymousClass0OD r3 = null;
        while (A0i == EnumC03640oE.FIELD_NAME) {
            String A0l = r8.A0l();
            r8.A0j();
            AbstractC01170Rz r0 = r6.A00.get(A0l);
            if (r0 != null) {
                if (A01.A02(r0.A01(), r0.A05(r8, r9))) {
                    r8.A0j();
                    try {
                        Object A02 = r6.A02(r9, A01);
                        if (A02.getClass() != this._beanType._class) {
                            return A0b(r8, r9, A02, r3);
                        }
                        if (r3 != null) {
                            A0e(r9, A02, r3);
                        }
                        A0C(r8, r9, A02);
                        return A02;
                    } catch (Exception e) {
                        A0g(e, this._beanType._class, A0l, r9);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (!A01.A03(A0l)) {
                AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                if (A00 != null) {
                    A01.A01(A00, A00.A05(r8, r9));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0l)) {
                        C04290pj r1 = this._anySetter;
                        if (r1 != null) {
                            A01.A00(r1, A0l, r1.A00(r8, r9));
                        } else {
                            if (r3 == null) {
                                r3 = new AnonymousClass0OD(r8.A0N());
                            }
                            r3.A0R(A0l);
                            r3.A0c(r8);
                        }
                    } else {
                        r8.A0h();
                    }
                }
            }
            A0i = r8.A0j();
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

    /* JADX WARN: Incorrect return type in method signature: (LX/0iQ;LX/0iH;Ljava/lang/Object;Ljava/lang/Class<*>;)Ljava/lang/Object; */
    private final void A02(AbstractC02280iQ r4, AbstractC02210iH r5, Object obj, Class cls) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        while (A0i == EnumC03640oE.FIELD_NAME) {
            String A0l = r4.A0l();
            r4.A0j();
            AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0l)) {
                    C04290pj r0 = this._anySetter;
                    if (r0 != null) {
                        r0.A01(r4, r5, obj, A0l);
                    } else {
                        A0N(r4, r5, obj, A0l);
                    }
                    A0i = r4.A0j();
                }
            } else if (A00.A0B(cls)) {
                try {
                    A00.A08(r4, r5, obj);
                    A0i = r4.A0j();
                } catch (Exception e) {
                    A0g(e, obj, A0l, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0h();
            A0i = r4.A0j();
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> A09(AbstractC04870rR r3) {
        if (getClass() != BeanDeserializer.class) {
            return this;
        }
        return new BeanDeserializer(this, r3);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            if (this._vanillaProcessing) {
                r4.A0j();
                return A00(r4, r5);
            }
            r4.A0j();
        } else if (A0i == null) {
            throw C02180iD.A00(r5.A00, AnonymousClass006.A07("Unexpected end-of-input when trying to deserialize a ", this._beanType._class.getName()));
        } else {
            switch (C04200pW.A00[A0i.ordinal()]) {
                case 1:
                    return A0W(r4, r5);
                case 2:
                    return A0V(r4, r5);
                case 3:
                    return A0U(r4, r5);
                case 4:
                    return r4.A0Z();
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

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, C04390pu r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, AbstractC04870rR r2) {
        super(beanDeserializerBase, r2);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, hashSet);
    }

    public BeanDeserializer(C04210pY r1, AbstractC04010oz r2, C04330po r3, Map<String, AbstractC01170Rz> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r1, r2, r3, map, hashSet, z, z2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public Object A0Z(AbstractC02280iQ r14, AbstractC02210iH r15) throws IOException, C03620oC {
        Class<?> cls;
        AnonymousClass0OD[] r12;
        Class<?> cls2;
        Object obj;
        if (!this._nonStandardCreation) {
            Object A05 = this._valueInstantiator.A05(r15);
            if (this._injectables != null) {
                A0d(r15);
            }
            if (!this._needViewProcesing || (cls = r15._view) == null) {
                while (r14.A0i() != EnumC03640oE.END_OBJECT) {
                    String A0l = r14.A0l();
                    r14.A0j();
                    AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                    if (A00 != null) {
                        try {
                            A00.A08(r14, r15, A05);
                        } catch (Exception e) {
                            A0g(e, A05, A0l, r15);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0l)) {
                            C04290pj r0 = this._anySetter;
                            if (r0 != null) {
                                try {
                                    r0.A01(r14, r15, A05, A0l);
                                } catch (Exception e2) {
                                    A0g(e2, A05, A0l, r15);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            } else {
                                A0N(r14, r15, A05, A0l);
                            }
                        } else {
                            r14.A0h();
                        }
                    }
                    r14.A0j();
                }
            } else {
                A02(r14, r15, A05, cls);
            }
            return A05;
        } else if (this._unwrappedPropertyHandler != null) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(r15, jsonDeserializer.A0A(r14, r15));
            }
            C04400pv r5 = this._propertyBasedCreator;
            if (r5 != null) {
                C04420px A01 = r5.A01(r14, r15, this._objectIdReader);
                AnonymousClass0OD r2 = new AnonymousClass0OD(r14.A0N());
                r2.A0I();
                EnumC03640oE A0i = r14.A0i();
                while (true) {
                    if (A0i == EnumC03640oE.FIELD_NAME) {
                        String A0l2 = r14.A0l();
                        r14.A0j();
                        AbstractC01170Rz r02 = r5.A00.get(A0l2);
                        if (r02 != null) {
                            if (A01.A02(r02.A01(), r02.A05(r14, r15))) {
                                EnumC03640oE A0j = r14.A0j();
                                try {
                                    obj = r5.A02(r15, A01);
                                    while (A0j == EnumC03640oE.FIELD_NAME) {
                                        r14.A0j();
                                        r2.A0c(r14);
                                        A0j = r14.A0j();
                                    }
                                    r2.A0F();
                                    if (obj.getClass() != this._beanType._class) {
                                        throw C02180iD.A00(r15.A00, "Can not create polymorphic instances with unwrapped values");
                                    }
                                } catch (Exception e3) {
                                    A0g(e3, this._beanType._class, A0l2, r15);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        } else if (!A01.A03(A0l2)) {
                            AbstractC01170Rz A002 = this._beanProperties.A00(A0l2);
                            if (A002 != null) {
                                A01.A01(A002, A002.A05(r14, r15));
                            } else {
                                HashSet<String> hashSet2 = this._ignorableProps;
                                if (hashSet2 == null || !hashSet2.contains(A0l2)) {
                                    r2.A0R(A0l2);
                                    r2.A0c(r14);
                                    C04290pj r1 = this._anySetter;
                                    if (r1 != null) {
                                        A01.A00(r1, A0l2, r1.A00(r14, r15));
                                    }
                                } else {
                                    r14.A0h();
                                }
                            }
                        }
                        A0i = r14.A0j();
                    } else {
                        try {
                            obj = r5.A02(r15, A01);
                            break;
                        } catch (Exception e4) {
                            A0f(e4, r15);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                }
                this._unwrappedPropertyHandler.A00(r15, obj, r2);
                return obj;
            }
            AnonymousClass0OD r52 = new AnonymousClass0OD(r14.A0N());
            r52.A0I();
            Object A052 = this._valueInstantiator.A05(r15);
            if (this._injectables != null) {
                A0d(r15);
            }
            if (this._needViewProcesing) {
                cls2 = r15._view;
            } else {
                cls2 = null;
            }
            while (r14.A0i() != EnumC03640oE.END_OBJECT) {
                String A0l3 = r14.A0l();
                r14.A0j();
                AbstractC01170Rz A003 = this._beanProperties.A00(A0l3);
                if (A003 == null) {
                    HashSet<String> hashSet3 = this._ignorableProps;
                    if (hashSet3 == null || !hashSet3.contains(A0l3)) {
                        r52.A0R(A0l3);
                        r52.A0c(r14);
                        C04290pj r03 = this._anySetter;
                        if (r03 != null) {
                            try {
                                r03.A01(r14, r15, A052, A0l3);
                            } catch (Exception e5) {
                                A0g(e5, A052, A0l3, r15);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                        r14.A0j();
                    }
                } else if (cls2 == null || A003.A0B(cls2)) {
                    try {
                        A003.A08(r14, r15, A052);
                        r14.A0j();
                    } catch (Exception e6) {
                        A0g(e6, A052, A0l3, r15);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                r14.A0h();
                r14.A0j();
            }
            r52.A0F();
            this._unwrappedPropertyHandler.A00(r15, A052, r52);
            return A052;
        } else {
            C04370ps r04 = this._externalTypeIdHandler;
            if (r04 == null) {
                return A0Y(r14, r15);
            }
            C04400pv r8 = this._propertyBasedCreator;
            if (r8 != null) {
                C04370ps r9 = new C04370ps(r04);
                C04420px A012 = r8.A01(r14, r15, this._objectIdReader);
                AnonymousClass0OD r4 = new AnonymousClass0OD(r14.A0N());
                r4.A0I();
                EnumC03640oE A0i2 = r14.A0i();
                while (A0i2 == EnumC03640oE.FIELD_NAME) {
                    String A0l4 = r14.A0l();
                    r14.A0j();
                    AbstractC01170Rz r53 = r8.A00.get(A0l4);
                    if (r53 != null) {
                        if (r9.A02(r14, r15, A0l4, A012)) {
                            continue;
                        } else if (A012.A02(r53.A01(), r53.A05(r14, r15))) {
                            EnumC03640oE A0j2 = r14.A0j();
                            try {
                                Object A02 = r8.A02(r15, A012);
                                while (A0j2 == EnumC03640oE.FIELD_NAME) {
                                    r14.A0j();
                                    r4.A0c(r14);
                                    A0j2 = r14.A0j();
                                }
                                if (A02.getClass() == this._beanType._class) {
                                    r9.A01(r14, r15, A02);
                                    return A02;
                                }
                                throw C02180iD.A00(r15.A00, "Can not create polymorphic instances with unwrapped values");
                            } catch (Exception e7) {
                                A0g(e7, this._beanType._class, A0l4, r15);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!A012.A03(A0l4)) {
                        AbstractC01170Rz A004 = this._beanProperties.A00(A0l4);
                        if (A004 != null) {
                            A012.A01(A004, A004.A05(r14, r15));
                        } else if (!r9.A02(r14, r15, A0l4, null)) {
                            HashSet<String> hashSet4 = this._ignorableProps;
                            if (hashSet4 == null || !hashSet4.contains(A0l4)) {
                                C04290pj r13 = this._anySetter;
                                if (r13 != null) {
                                    A012.A00(r13, A0l4, r13.A00(r14, r15));
                                }
                            } else {
                                r14.A0h();
                            }
                        }
                    }
                    A0i2 = r14.A0j();
                }
                try {
                    C04360pr[] r7 = r9.A01;
                    int length = r7.length;
                    Object[] objArr = new Object[length];
                    for (int i = 0; i < length; i++) {
                        String str = r9.A03[i];
                        if (str == null) {
                            r12 = r9.A02;
                            if (r12[i] != null) {
                                C04360pr r16 = r7[i];
                                boolean z = false;
                                if (r16.A01.A06() != null) {
                                    z = true;
                                }
                                if (z) {
                                    AbstractC04520qa r05 = r7[i].A01;
                                    Class<?> A06 = r05.A06();
                                    str = A06 == null ? null : r05.A05().A5Y(null, A06);
                                } else {
                                    throw C02180iD.A00(r15.A00, AnonymousClass006.A09("Missing external type id property '", r16.A02, "'"));
                                }
                            } else {
                                continue;
                            }
                        } else {
                            r12 = r9.A02;
                            if (r12[i] == null) {
                                C04360pr r42 = r7[i];
                                throw C02180iD.A00(r15.A00, AnonymousClass006.A0B("Missing property '", r42.A00._propName, "' for external type id '", r42.A02));
                            }
                        }
                        AnonymousClass0OD r17 = new AnonymousClass0OD(r14.A0N());
                        r17.A0H();
                        r17.A0U(str);
                        AbstractC02280iQ A0b = r12[i].A0b(r14);
                        A0b.A0j();
                        r17.A0c(A0b);
                        r17.A0E();
                        AbstractC02280iQ A0b2 = r17.A0b(r14);
                        A0b2.A0j();
                        objArr[i] = r7[i].A00.A05(A0b2, r15);
                    }
                    for (int i2 = 0; i2 < length; i2++) {
                        AbstractC01170Rz r22 = r7[i2].A00;
                        if (r8.A00.get(r22._propName) != null) {
                            A012.A02(r22.A01(), objArr[i2]);
                        }
                    }
                    Object A022 = r8.A02(r15, A012);
                    for (int i3 = 0; i3 < length; i3++) {
                        AbstractC01170Rz r23 = r7[i3].A00;
                        if (r8.A00.get(r23._propName) == null) {
                            r23.A0A(A022, objArr[i3]);
                        }
                    }
                    return A022;
                } catch (Exception e8) {
                    A0f(e8, r15);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                Object A053 = this._valueInstantiator.A05(r15);
                A01(r14, r15, A053);
                return A053;
            }
        }
    }
}
