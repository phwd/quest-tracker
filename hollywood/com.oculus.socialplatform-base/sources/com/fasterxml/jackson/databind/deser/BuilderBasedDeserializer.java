package com.fasterxml.jackson.databind.deser;

import X.AbstractC01170Rz;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04010oz;
import X.AbstractC04870rR;
import X.AnonymousClass0Cr;
import X.AnonymousClass0OD;
import X.AnonymousClass0pa;
import X.C02180iD;
import X.C03620oC;
import X.C04210pY;
import X.C04290pj;
import X.C04330po;
import X.C04370ps;
import X.C04390pu;
import X.C04400pv;
import X.C04420px;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Cr _buildMethod;

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0094, code lost:
        if (r1 == X.EnumC03640oE.START_OBJECT) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0096, code lost:
        r1 = r6.A0j();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        if (r1 != X.EnumC03640oE.FIELD_NAME) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        r1 = r6.A0l();
        r6.A0j();
        r0 = r5._beanProperties.A00(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ab, code lost:
        if (r0 == null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r8 = r0.A06(r6, r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b2, code lost:
        r0 = r5._ignorableProps;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b4, code lost:
        if (r0 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ba, code lost:
        if (r0.contains(r1) == false) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00bc, code lost:
        r6.A0h();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c0, code lost:
        r0 = r5._anySetter;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c2, code lost:
        if (r0 == null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c4, code lost:
        r0.A01(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c8, code lost:
        A0N(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cd, code lost:
        A0g(r0, r8, r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d7, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object A00(X.AbstractC02280iQ r6, X.AbstractC02210iH r7, java.lang.Object r8) throws java.io.IOException, X.C03620oC {
        /*
        // Method dump skipped, instructions count: 225
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.A00(X.0iQ, X.0iH, java.lang.Object):java.lang.Object");
    }

    private final Object A01(AbstractC02280iQ r6, AbstractC02210iH r7, Object obj) throws IOException, C03620oC {
        Class<?> cls;
        if (this._needViewProcesing) {
            cls = r7._view;
        } else {
            cls = null;
        }
        C04370ps r3 = new C04370ps(this._externalTypeIdHandler);
        while (r6.A0i() != EnumC03640oE.END_OBJECT) {
            String A0l = r6.A0l();
            r6.A0j();
            AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0l)) {
                    if (!r3.A02(r6, r7, A0l, obj)) {
                        C04290pj r0 = this._anySetter;
                        if (r0 != null) {
                            try {
                                r0.A01(r6, r7, obj, A0l);
                            } catch (Exception e) {
                                A0g(e, obj, A0l, r7);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            A0N(r6, r7, obj, A0l);
                        }
                    }
                    r6.A0j();
                }
            } else if (cls == null || A00.A0B(cls)) {
                try {
                    obj = A00.A06(r6, r7, obj);
                    r6.A0j();
                } catch (Exception e2) {
                    A0g(e2, obj, A0l, r7);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r6.A0h();
            r6.A0j();
        }
        r3.A01(r6, r7, obj);
        return obj;
    }

    private final Object A03(AbstractC02210iH r3, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, r3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A09(AbstractC04870rR r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayBuilderDeserializer(this, this._beanProperties.A04(), this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C04390pu r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BuilderBasedDeserializer(this, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        Class<?> cls;
        Class<?> cls2;
        Object obj;
        if (!this._nonStandardCreation) {
            Object A05 = this._valueInstantiator.A05(r8);
            if (this._injectables != null) {
                A0d(r8);
            }
            if (this._needViewProcesing && (cls = r8._view) != null) {
                return A02(r7, r8, A05, cls);
            }
            while (r7.A0i() != EnumC03640oE.END_OBJECT) {
                String A0l = r7.A0l();
                r7.A0j();
                AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                if (A00 != null) {
                    try {
                        A05 = A00.A06(r7, r8, A05);
                    } catch (Exception e) {
                        A0g(e, A05, A0l, r8);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0l)) {
                        C04290pj r0 = this._anySetter;
                        if (r0 != null) {
                            try {
                                r0.A01(r7, r8, A05, A0l);
                            } catch (Exception e2) {
                                A0g(e2, A05, A0l, r8);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            A0N(r7, r8, A05, A0l);
                        }
                    } else {
                        r7.A0h();
                    }
                }
                r7.A0j();
            }
            return A05;
        } else if (this._unwrappedPropertyHandler != null) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(r8, jsonDeserializer.A0A(r7, r8));
            }
            C04400pv r5 = this._propertyBasedCreator;
            if (r5 != null) {
                C04420px A01 = r5.A01(r7, r8, this._objectIdReader);
                AnonymousClass0OD r2 = new AnonymousClass0OD(r7.A0N());
                r2.A0I();
                EnumC03640oE A0i = r7.A0i();
                while (true) {
                    if (A0i == EnumC03640oE.FIELD_NAME) {
                        String A0l2 = r7.A0l();
                        r7.A0j();
                        AbstractC01170Rz r02 = r5.A00.get(A0l2);
                        if (r02 != null) {
                            if (A01.A02(r02.A01(), r02.A05(r7, r8))) {
                                EnumC03640oE A0j = r7.A0j();
                                try {
                                    obj = r5.A02(r8, A01);
                                    while (A0j == EnumC03640oE.FIELD_NAME) {
                                        r7.A0j();
                                        r2.A0c(r7);
                                        A0j = r7.A0j();
                                    }
                                    r2.A0F();
                                    if (obj.getClass() != this._beanType._class) {
                                        throw C02180iD.A00(r8.A00, "Can not create polymorphic instances with unwrapped values");
                                    }
                                } catch (Exception e3) {
                                    A0g(e3, this._beanType._class, A0l2, r8);
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        } else if (!A01.A03(A0l2)) {
                            AbstractC01170Rz A002 = this._beanProperties.A00(A0l2);
                            if (A002 != null) {
                                A01.A01(A002, A002.A05(r7, r8));
                            } else {
                                HashSet<String> hashSet2 = this._ignorableProps;
                                if (hashSet2 == null || !hashSet2.contains(A0l2)) {
                                    r2.A0R(A0l2);
                                    r2.A0c(r7);
                                    C04290pj r1 = this._anySetter;
                                    if (r1 != null) {
                                        A01.A00(r1, A0l2, r1.A00(r7, r8));
                                    }
                                } else {
                                    r7.A0h();
                                }
                            }
                        }
                        A0i = r7.A0j();
                    } else {
                        try {
                            obj = r5.A02(r8, A01);
                            break;
                        } catch (Exception e4) {
                            A0f(e4, r8);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                }
                this._unwrappedPropertyHandler.A00(r8, obj, r2);
                return obj;
            }
            AnonymousClass0OD r52 = new AnonymousClass0OD(r7.A0N());
            r52.A0I();
            Object A052 = this._valueInstantiator.A05(r8);
            if (this._injectables != null) {
                A0d(r8);
            }
            if (this._needViewProcesing) {
                cls2 = r8._view;
            } else {
                cls2 = null;
            }
            while (r7.A0i() != EnumC03640oE.END_OBJECT) {
                String A0l3 = r7.A0l();
                r7.A0j();
                AbstractC01170Rz A003 = this._beanProperties.A00(A0l3);
                if (A003 == null) {
                    HashSet<String> hashSet3 = this._ignorableProps;
                    if (hashSet3 == null || !hashSet3.contains(A0l3)) {
                        r52.A0R(A0l3);
                        r52.A0c(r7);
                        C04290pj r03 = this._anySetter;
                        if (r03 != null) {
                            try {
                                r03.A01(r7, r8, A052, A0l3);
                            } catch (Exception e5) {
                                A0g(e5, A052, A0l3, r8);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                        r7.A0j();
                    }
                } else if (cls2 == null || A003.A0B(cls2)) {
                    try {
                        A052 = A003.A06(r7, r8, A052);
                        r7.A0j();
                    } catch (Exception e6) {
                        A0g(e6, A052, A0l3, r8);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                r7.A0h();
                r7.A0j();
            }
            r52.A0F();
            this._unwrappedPropertyHandler.A00(r8, A052, r52);
            return A052;
        } else if (this._externalTypeIdHandler == null) {
            return A0Y(r7, r8);
        } else {
            if (this._propertyBasedCreator == null) {
                return A01(r7, r8, this._valueInstantiator.A05(r8));
            }
            throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
        }
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
                        return A00(r8, r9, A02);
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

    private final Object A02(AbstractC02280iQ r4, AbstractC02210iH r5, Object obj, Class<?> cls) throws IOException, C03620oC {
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
                    obj = A00.A06(r4, r5, obj);
                    A0i = r4.A0j();
                } catch (Exception e) {
                    A0g(e, obj, A0l, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0h();
            A0i = r4.A0j();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        Object obj;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            r4.A0j();
            if (this._vanillaProcessing) {
                Object A05 = this._valueInstantiator.A05(r5);
                while (r4.A0i() != EnumC03640oE.END_OBJECT) {
                    String A0l = r4.A0l();
                    r4.A0j();
                    AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                    if (A00 != null) {
                        try {
                            A05 = A00.A06(r4, r5, A05);
                        } catch (Exception e) {
                            A0g(e, A05, A0l, r5);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        A0c(r4, r5, A05, A0l);
                    }
                    r4.A0j();
                }
                return A03(r5, A05);
            }
        } else {
            switch (AnonymousClass0pa.A00[A0i.ordinal()]) {
                case 1:
                    obj = A0W(r4, r5);
                    break;
                case 2:
                    obj = A0V(r4, r5);
                    break;
                case 3:
                    obj = A0U(r4, r5);
                    break;
                case 4:
                    return r4.A0Z();
                case 5:
                case 6:
                    obj = A0T(r4, r5);
                    break;
                case 7:
                    obj = A0S(r4, r5);
                    break;
                case 8:
                case 9:
                    break;
                default:
                    throw r5.A0B(this._beanType._class);
            }
            return A03(r5, obj);
        }
        obj = A0Z(r4, r5);
        return A03(r5, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        return A03(r3, A00(r2, r3, obj));
    }

    public BuilderBasedDeserializer(C04210pY r3, AbstractC04010oz r4, C04330po r5, Map<String, AbstractC01170Rz> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r3, r4, r5, map, hashSet, z, z2);
        this._buildMethod = r3.A04;
        if (this._objectIdReader != null) {
            StringBuilder sb = new StringBuilder("Can not use Object Id with Builder-based deserialization (type ");
            sb.append(r4.A00);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, C04390pu r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, AbstractC04870rR r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, HashSet<String> hashSet) {
        super(builderBasedDeserializer, hashSet);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }
}
