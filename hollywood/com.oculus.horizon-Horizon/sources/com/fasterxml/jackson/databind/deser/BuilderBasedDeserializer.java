package com.fasterxml.jackson.databind.deser;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05180kU;
import X.AbstractC06410n2;
import X.AnonymousClass07O;
import X.AnonymousClass0Fv;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.AnonymousClass0l3;
import X.AnonymousClass0l5;
import X.AnonymousClass0lK;
import X.C03990gZ;
import X.C05480lF;
import X.C05510lO;
import X.C05530lQ;
import X.C05540lR;
import X.C05560lT;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public final class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass07O _buildMethod;

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0088, code lost:
        if (r1 == X.EnumC04820ji.START_OBJECT) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008a, code lost:
        r1 = r6.A0b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0090, code lost:
        if (r1 != X.EnumC04820ji.FIELD_NAME) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0092, code lost:
        r1 = r6.A0d();
        r6.A0b();
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
        r6.A0Z();
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
    private final java.lang.Object A00(X.AbstractC04100gp r6, X.AbstractC04020gg r7, java.lang.Object r8) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.A00(X.0gp, X.0gg, java.lang.Object):java.lang.Object");
    }

    private final Object A01(AbstractC04100gp r6, AbstractC04020gg r7, Object obj) throws IOException, AnonymousClass0jg {
        Class<?> cls;
        if (this._needViewProcesing) {
            cls = r7._view;
        } else {
            cls = null;
        }
        C05510lO r3 = new C05510lO(this._externalTypeIdHandler);
        while (r6.A0a() != EnumC04820ji.END_OBJECT) {
            String A0d = r6.A0d();
            r6.A0b();
            AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0d)) {
                    if (!r3.A02(r6, r7, A0d, obj)) {
                        C05480lF r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r6, r7, obj, A0d);
                        } else {
                            A0N(r6, r7, obj, A0d);
                        }
                    }
                    r6.A0b();
                }
            } else if (cls == null || A00.A0B(cls)) {
                try {
                    obj = A00.A06(r6, r7, obj);
                    r6.A0b();
                } catch (Exception e) {
                    A0g(e, obj, A0d, r7);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r6.A0Z();
            r6.A0b();
        }
        r3.A01(r6, r7, obj);
        return obj;
    }

    private final Object A03(AbstractC04020gg r3, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, r3);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC06410n2 r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayBuilderDeserializer(this, this._beanProperties.A04(), this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C05530lQ r2) {
        return new BuilderBasedDeserializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BuilderBasedDeserializer(this, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
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
            while (r7.A0a() != EnumC04820ji.END_OBJECT) {
                String A0d = r7.A0d();
                r7.A0b();
                AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
                if (A00 != null) {
                    try {
                        A05 = A00.A06(r7, r8, A05);
                    } catch (Exception e) {
                        A0g(e, A05, A0d, r8);
                    }
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0d)) {
                        C05480lF r0 = this._anySetter;
                        if (r0 != null) {
                            r0.A01(r7, r8, A05, A0d);
                        } else {
                            A0N(r7, r8, A05, A0d);
                        }
                    } else {
                        r7.A0Z();
                    }
                }
                r7.A0b();
            }
            return A05;
        } else if (this._unwrappedPropertyHandler != null) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(r8, jsonDeserializer.A09(r7, r8));
            }
            C05540lR r5 = this._propertyBasedCreator;
            if (r5 != null) {
                C05560lT A01 = r5.A01(r7, r8, this._objectIdReader);
                AnonymousClass0Fv r2 = new AnonymousClass0Fv(r7.A0I());
                r2.A07();
                EnumC04820ji A0a = r7.A0a();
                while (A0a == EnumC04820ji.FIELD_NAME) {
                    String A0d2 = r7.A0d();
                    r7.A0b();
                    AnonymousClass0HD r02 = r5.A00.get(A0d2);
                    if (r02 != null) {
                        if (A01.A02(r02.A01(), r02.A05(r7, r8))) {
                            EnumC04820ji A0b = r7.A0b();
                            try {
                                obj = r5.A02(r8, A01);
                                while (A0b == EnumC04820ji.FIELD_NAME) {
                                    r7.A0b();
                                    r2.A08(r7);
                                    A0b = r7.A0b();
                                }
                                r2.A05();
                                if (obj.getClass() != this._beanType._class) {
                                    throw C03990gZ.A00(null, "Can not create polymorphic instances with unwrapped values");
                                }
                                this._unwrappedPropertyHandler.A00(r8, obj, r2);
                                return obj;
                            } catch (Exception e2) {
                                A0g(e2, this._beanType._class, A0d2, r8);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!A01.A03(A0d2)) {
                        AnonymousClass0HD A002 = this._beanProperties.A00(A0d2);
                        if (A002 != null) {
                            A01.A01(A002, A002.A05(r7, r8));
                        } else {
                            HashSet<String> hashSet2 = this._ignorableProps;
                            if (hashSet2 == null || !hashSet2.contains(A0d2)) {
                                r2.A09(A0d2);
                                r2.A08(r7);
                                C05480lF r1 = this._anySetter;
                                if (r1 != null) {
                                    A01.A00(r1, A0d2, r1.A00(r7, r8));
                                }
                            } else {
                                r7.A0Z();
                            }
                        }
                    }
                    A0a = r7.A0b();
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
                AnonymousClass0Fv r52 = new AnonymousClass0Fv(r7.A0I());
                r52.A07();
                Object A052 = this._valueInstantiator.A05(r8);
                if (this._injectables != null) {
                    A0d(r8);
                }
                if (this._needViewProcesing) {
                    cls2 = r8._view;
                } else {
                    cls2 = null;
                }
                while (r7.A0a() != EnumC04820ji.END_OBJECT) {
                    String A0d3 = r7.A0d();
                    r7.A0b();
                    AnonymousClass0HD A003 = this._beanProperties.A00(A0d3);
                    if (A003 == null) {
                        HashSet<String> hashSet3 = this._ignorableProps;
                        if (hashSet3 == null || !hashSet3.contains(A0d3)) {
                            r52.A09(A0d3);
                            r52.A08(r7);
                            C05480lF r03 = this._anySetter;
                            if (r03 != null) {
                                r03.A01(r7, r8, A052, A0d3);
                            }
                            r7.A0b();
                        }
                    } else if (cls2 == null || A003.A0B(cls2)) {
                        try {
                            A052 = A003.A06(r7, r8, A052);
                            r7.A0b();
                        } catch (Exception e4) {
                            A0g(e4, A052, A0d3, r8);
                        }
                    }
                    r7.A0Z();
                    r7.A0b();
                }
                r52.A05();
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
                        return A00(r8, r9, A02);
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

    private final Object A02(AbstractC04100gp r4, AbstractC04020gg r5, Object obj, Class<?> cls) throws IOException, AnonymousClass0jg {
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
                    obj = A00.A06(r4, r5, obj);
                    A0a = r4.A0b();
                } catch (Exception e) {
                    A0g(e, obj, A0d, r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            r4.A0Z();
            A0a = r4.A0b();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        Object obj;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            r4.A0b();
            if (this._vanillaProcessing) {
                Object A05 = this._valueInstantiator.A05(r5);
                while (r4.A0a() != EnumC04820ji.END_OBJECT) {
                    String A0d = r4.A0d();
                    r4.A0b();
                    AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
                    if (A00 != null) {
                        try {
                            A05 = A00.A06(r4, r5, A05);
                        } catch (Exception e) {
                            A0g(e, A05, A0d, r5);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        A0c(r4, r5, A05, A0d);
                    }
                    r4.A0b();
                }
                return A03(r5, A05);
            }
        } else {
            switch (AnonymousClass0l5.A00[A0a.ordinal()]) {
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
                    return r4.A0S();
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
                    throw null;
            }
            return A03(r5, obj);
        }
        obj = A0Z(r4, r5);
        return A03(r5, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        return A03(r3, A00(r2, r3, obj));
    }

    public BuilderBasedDeserializer(AnonymousClass0l3 r3, AbstractC05180kU r4, AnonymousClass0lK r5, Map<String, AnonymousClass0HD> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(r3, r4, r5, map, hashSet, z, z2);
        this._buildMethod = r3.A04;
        if (this._objectIdReader != null) {
            StringBuilder sb = new StringBuilder("Can not use Object Id with Builder-based deserialization (type ");
            sb.append(r4.A00);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, C05530lQ r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, AbstractC06410n2 r3) {
        super(builderBasedDeserializer, r3);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, HashSet<String> hashSet) {
        super(builderBasedDeserializer, hashSet);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }
}
