package com.fasterxml.jackson.databind.deser;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC06260mR;
import X.AbstractC07200ov;
import X.AnonymousClass0EC;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0nJ;
import X.AnonymousClass0nL;
import X.AnonymousClass0nM;
import X.AnonymousClass0nO;
import X.C01570Jk;
import X.C05910ld;
import X.C06490mz;
import X.C06510n1;
import X.C06560nA;
import X.C06570nF;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public final class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0EC _buildMethod;

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0088, code lost:
        if (r1 == X.EnumC05930lf.START_OBJECT) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008a, code lost:
        r1 = r6.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0090, code lost:
        if (r1 != X.EnumC05930lf.FIELD_NAME) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0092, code lost:
        r1 = r6.A0O();
        r6.A0a();
        r0 = r5._beanProperties.A00(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009f, code lost:
        if (r0 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r8 = r0.A06(r6, r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a6, code lost:
        r0 = r5._ignorableProps;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a8, code lost:
        if (r0 == null) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ae, code lost:
        if (r0.contains(r1) == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b0, code lost:
        r6.A0F();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b4, code lost:
        r0 = r5._anySetter;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b6, code lost:
        if (r0 == null) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b8, code lost:
        r0.A01(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00bc, code lost:
        A0N(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c1, code lost:
        A0g(r0, r8, r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d0, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object A00(X.AnonymousClass0aT r6, X.AbstractC02570aK r7, java.lang.Object r8) throws java.io.IOException, X.C05910ld {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.A00(X.0aT, X.0aK, java.lang.Object):java.lang.Object");
    }

    private final Object A01(AnonymousClass0aT r6, AbstractC02570aK r7, Object obj) throws IOException, C05910ld {
        Class<?> cls;
        if (this._needViewProcesing) {
            cls = r7._view;
        } else {
            cls = null;
        }
        AnonymousClass0nJ r3 = new AnonymousClass0nJ(this._externalTypeIdHandler);
        while (r6.A0G() != EnumC05930lf.END_OBJECT) {
            String A0O = r6.A0O();
            r6.A0a();
            AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0O)) {
                    if (!r3.A02(r6, r7, A0O, obj)) {
                        C06560nA r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r6, r7, obj, A0O);
                        } else {
                            A0N(r6, r7, obj, A0O);
                        }
                    }
                    r6.A0a();
                }
            } else if (cls == null || A00.A0B(cls)) {
                try {
                    obj = A00.A06(r6, r7, obj);
                    r6.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0O, r7);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r6.A0F();
            r6.A0a();
        }
        r3.A01(r6, r7, obj);
        return obj;
    }

    private final Object A03(AbstractC02570aK r3, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, r3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC07200ov r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayBuilderDeserializer(this, this._beanProperties.A04(), this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(AnonymousClass0nL r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BuilderBasedDeserializer(this, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
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
            while (r7.A0G() != EnumC05930lf.END_OBJECT) {
                String A0O = r7.A0O();
                r7.A0a();
                AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
                if (A00 != null) {
                    try {
                        A05 = A00.A06(r7, r8, A05);
                    } catch (Exception e) {
                        A0g(e, A05, A0O, r8);
                    }
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0O)) {
                        C06560nA r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r7, r8, A05, A0O);
                        } else {
                            A0N(r7, r8, A05, A0O);
                        }
                    } else {
                        r7.A0F();
                    }
                }
                r7.A0a();
            }
            return A05;
        } else if (this._unwrappedPropertyHandler != null) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(r8, jsonDeserializer.A09(r7, r8));
            }
            AnonymousClass0nM r5 = this._propertyBasedCreator;
            if (r5 != null) {
                AnonymousClass0nO A01 = r5.A01(r7, r8, this._objectIdReader);
                C01570Jk r2 = new C01570Jk(r7.A0I());
                r2.A0F();
                EnumC05930lf A0G = r7.A0G();
                while (A0G == EnumC05930lf.FIELD_NAME) {
                    String A0O2 = r7.A0O();
                    r7.A0a();
                    AbstractC01680Ku r02 = r5.A00.get(A0O2);
                    if (r02 != null) {
                        if (A01.A02(r02.A01(), r02.A05(r7, r8))) {
                            EnumC05930lf A0a = r7.A0a();
                            try {
                                obj = r5.A02(r8, A01);
                                while (A0a == EnumC05930lf.FIELD_NAME) {
                                    r7.A0a();
                                    r2.A0a(r7);
                                    A0a = r7.A0a();
                                }
                                r2.A0C();
                                if (obj.getClass() != this._beanType._class) {
                                    throw AnonymousClass0aG.A00(r8.A00, "Can not create polymorphic instances with unwrapped values");
                                }
                                this._unwrappedPropertyHandler.A00(r8, obj, r2);
                                return obj;
                            } catch (Exception e2) {
                                A0g(e2, this._beanType._class, A0O2, r8);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!A01.A03(A0O2)) {
                        AbstractC01680Ku A002 = this._beanProperties.A00(A0O2);
                        if (A002 != null) {
                            A01.A01(A002, A002.A05(r7, r8));
                        } else {
                            HashSet<String> hashSet2 = this._ignorableProps;
                            if (hashSet2 == null || !hashSet2.contains(A0O2)) {
                                r2.A0P(A0O2);
                                r2.A0a(r7);
                                C06560nA r1 = this._anySetter;
                                if (r1 != null) {
                                    A01.A00(r1, A0O2, r1.A00(r7, r8));
                                }
                            } else {
                                r7.A0F();
                            }
                        }
                    }
                    A0G = r7.A0a();
                }
                try {
                    obj = r5.A02(r8, A01);
                    this._unwrappedPropertyHandler.A00(r8, obj, r2);
                    return obj;
                } catch (Exception e3) {
                    A0f(e3, r8);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                C01570Jk r52 = new C01570Jk(r7.A0I());
                r52.A0F();
                Object A052 = this._valueInstantiator.A05(r8);
                if (this._injectables != null) {
                    A0d(r8);
                }
                if (this._needViewProcesing) {
                    cls2 = r8._view;
                } else {
                    cls2 = null;
                }
                while (r7.A0G() != EnumC05930lf.END_OBJECT) {
                    String A0O3 = r7.A0O();
                    r7.A0a();
                    AbstractC01680Ku A003 = this._beanProperties.A00(A0O3);
                    if (A003 == null) {
                        HashSet<String> hashSet3 = this._ignorableProps;
                        if (hashSet3 == null || !hashSet3.contains(A0O3)) {
                            r52.A0P(A0O3);
                            r52.A0a(r7);
                            C06560nA r03 = this._anySetter;
                            if (r03 != null) {
                                r03.A01(r7, r8, A052, A0O3);
                            }
                            r7.A0a();
                        }
                    } else if (cls2 == null || A003.A0B(cls2)) {
                        try {
                            A052 = A003.A06(r7, r8, A052);
                            r7.A0a();
                        } catch (Exception e4) {
                            A0g(e4, A052, A0O3, r8);
                        }
                    }
                    r7.A0F();
                    r7.A0a();
                }
                r52.A0C();
                this._unwrappedPropertyHandler.A00(r8, A052, r52);
                return A052;
            }
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
                        return A00(r8, r9, A02);
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

    private final Object A02(AnonymousClass0aT r4, AbstractC02570aK r5, Object obj, Class<?> cls) throws IOException, C05910ld {
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
                    obj = A00.A06(r4, r5, obj);
                    A0G = r4.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0O, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0F();
            A0G = r4.A0a();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        Object obj;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            r4.A0a();
            if (this._vanillaProcessing) {
                Object A05 = this._valueInstantiator.A05(r5);
                while (r4.A0G() != EnumC05930lf.END_OBJECT) {
                    String A0O = r4.A0O();
                    r4.A0a();
                    AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
                    if (A00 != null) {
                        try {
                            A05 = A00.A06(r4, r5, A05);
                        } catch (Exception e) {
                            A0g(e, A05, A0O, r5);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        A0c(r4, r5, A05, A0O);
                    }
                    r4.A0a();
                }
                return A03(r5, A05);
            }
        } else {
            switch (C06510n1.A00[A0G.ordinal()]) {
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
                    return r4.A0M();
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
    public final Object A0A(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        return A03(r3, A00(r2, r3, obj));
    }

    public BuilderBasedDeserializer(C06490mz r3, AbstractC06260mR r4, C06570nF r5, Map<String, AbstractC01680Ku> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r3, r4, r5, map, hashSet, z, z2);
        this._buildMethod = r3.A04;
        if (this._objectIdReader != null) {
            throw new IllegalArgumentException("Can not use Object Id with Builder-based deserialization (type " + r4.A00 + ")");
        }
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, AnonymousClass0nL r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, AbstractC07200ov r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, HashSet<String> hashSet) {
        super(builderBasedDeserializer, hashSet);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }
}
