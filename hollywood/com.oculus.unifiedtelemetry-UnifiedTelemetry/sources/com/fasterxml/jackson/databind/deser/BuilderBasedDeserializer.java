package com.fasterxml.jackson.databind.deser;

import X.AbstractC0073Cr;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass7P;
import X.Br;
import X.C0201Vh;
import X.C0203Vk;
import X.C0204Vm;
import X.C0223Wj;
import X.C0245Xs;
import X.C0263Yn;
import X.C0284a5;
import X.C0295aT;
import X.EnumC0470q2;
import X.KI;
import X.X5;
import X.jm;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public final class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass7P _buildMethod;

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0088, code lost:
        if (r1 == X.EnumC0470q2.START_OBJECT) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008a, code lost:
        r1 = r6.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0090, code lost:
        if (r1 != X.EnumC0470q2.FIELD_NAME) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0092, code lost:
        r1 = r6.A0c();
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
        r6.A0Y();
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
    private final java.lang.Object A00(X.AbstractC0232Ww r6, X.AbstractC0226Wn r7, java.lang.Object r8) throws java.io.IOException, X.q0 {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.A00(X.Ww, X.Wn, java.lang.Object):java.lang.Object");
    }

    private final Object A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Class<?> cls;
        if (this._needViewProcesing) {
            cls = wn._view;
        } else {
            cls = null;
        }
        X5 x5 = new X5(this._externalTypeIdHandler);
        while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0c)) {
                    if (!x5.A02(ww, wn, A0c, obj)) {
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            yn.A01(ww, wn, obj, A0c);
                        } else {
                            A0N(ww, wn, obj, A0c);
                        }
                    }
                    ww.A0a();
                }
            } else if (cls == null || A00.A0B(cls)) {
                try {
                    obj = A00.A06(ww, wn, obj);
                    ww.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0c, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            ww.A0Y();
            ww.A0a();
        }
        x5.A01(ww, wn, obj);
        return obj;
    }

    private final Object A03(AbstractC0226Wn wn, Object obj) throws IOException {
        try {
            return this._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            A0f(e, wn);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(KI ki) {
        return new BuilderBasedDeserializer(this, ki);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0P() {
        return new BeanAsArrayBuilderDeserializer(this, this._beanProperties.A04(), this._buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0Q(C0204Vm vm) {
        return new BuilderBasedDeserializer(this, vm);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final BeanDeserializerBase A0R(HashSet hashSet) {
        return new BuilderBasedDeserializer(this, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0Z(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Class<?> cls;
        Class<?> cls2;
        Object obj;
        if (!this._nonStandardCreation) {
            Object A05 = this._valueInstantiator.A05(wn);
            if (this._injectables != null) {
                A0d(wn);
            }
            if (this._needViewProcesing && (cls = wn._view) != null) {
                return A02(ww, wn, A05, cls);
            }
            while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
                String A0c = ww.A0c();
                ww.A0a();
                AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                if (A00 != null) {
                    try {
                        A05 = A00.A06(ww, wn, A05);
                    } catch (Exception e) {
                        A0g(e, A05, A0c, wn);
                    }
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0c)) {
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            yn.A01(ww, wn, A05, A0c);
                        } else {
                            A0N(ww, wn, A05, A0c);
                        }
                    } else {
                        ww.A0Y();
                    }
                }
                ww.A0a();
            }
            return A05;
        } else if (this._unwrappedPropertyHandler != null) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                return this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
            }
            C0203Vk vk = this._propertyBasedCreator;
            if (vk != null) {
                C0201Vh A01 = vk.A01(ww, wn, this._objectIdReader);
                Br br = new Br(ww.A0H());
                br.A07();
                EnumC0470q2 A0Z = ww.A0Z();
                while (A0Z == EnumC0470q2.FIELD_NAME) {
                    String A0c2 = ww.A0c();
                    ww.A0a();
                    AbstractC0073Cr cr = vk.A00.get(A0c2);
                    if (cr != null) {
                        if (A01.A02(cr.A01(), cr.A05(ww, wn))) {
                            EnumC0470q2 A0a = ww.A0a();
                            try {
                                obj = vk.A02(wn, A01);
                                while (A0a == EnumC0470q2.FIELD_NAME) {
                                    ww.A0a();
                                    br.A08(ww);
                                    A0a = ww.A0a();
                                }
                                br.A05();
                                if (obj.getClass() != this._beanType._class) {
                                    throw C0223Wj.A00(wn.A00, "Can not create polymorphic instances with unwrapped values");
                                }
                                this._unwrappedPropertyHandler.A00(wn, obj, br);
                                return obj;
                            } catch (Exception e2) {
                                A0g(e2, this._beanType._class, A0c2, wn);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else if (!A01.A03(A0c2)) {
                        AbstractC0073Cr A002 = this._beanProperties.A00(A0c2);
                        if (A002 != null) {
                            A01.A01(A002, A002.A05(ww, wn));
                        } else {
                            HashSet<String> hashSet2 = this._ignorableProps;
                            if (hashSet2 == null || !hashSet2.contains(A0c2)) {
                                br.A09(A0c2);
                                br.A08(ww);
                                C0263Yn yn2 = this._anySetter;
                                if (yn2 != null) {
                                    A01.A00(yn2, A0c2, yn2.A00(ww, wn));
                                }
                            } else {
                                ww.A0Y();
                            }
                        }
                    }
                    A0Z = ww.A0a();
                }
                try {
                    obj = vk.A02(wn, A01);
                    this._unwrappedPropertyHandler.A00(wn, obj, br);
                    return obj;
                } catch (Exception e3) {
                    A0f(e3, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                Br br2 = new Br(ww.A0H());
                br2.A07();
                Object A052 = this._valueInstantiator.A05(wn);
                if (this._injectables != null) {
                    A0d(wn);
                }
                if (this._needViewProcesing) {
                    cls2 = wn._view;
                } else {
                    cls2 = null;
                }
                while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
                    String A0c3 = ww.A0c();
                    ww.A0a();
                    AbstractC0073Cr A003 = this._beanProperties.A00(A0c3);
                    if (A003 == null) {
                        HashSet<String> hashSet3 = this._ignorableProps;
                        if (hashSet3 == null || !hashSet3.contains(A0c3)) {
                            br2.A09(A0c3);
                            br2.A08(ww);
                            C0263Yn yn3 = this._anySetter;
                            if (yn3 != null) {
                                yn3.A01(ww, wn, A052, A0c3);
                            }
                            ww.A0a();
                        }
                    } else if (cls2 == null || A003.A0B(cls2)) {
                        try {
                            A052 = A003.A06(ww, wn, A052);
                            ww.A0a();
                        } catch (Exception e4) {
                            A0g(e4, A052, A0c3, wn);
                        }
                    }
                    ww.A0Y();
                    ww.A0a();
                }
                br2.A05();
                this._unwrappedPropertyHandler.A00(wn, A052, br2);
                return A052;
            }
        } else if (this._externalTypeIdHandler == null) {
            return A0Y(ww, wn);
        } else {
            if (this._propertyBasedCreator == null) {
                return A01(ww, wn, this._valueInstantiator.A05(wn));
            }
            throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public final Object A0a(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        C0203Vk vk = this._propertyBasedCreator;
        C0201Vh A01 = vk.A01(ww, wn, this._objectIdReader);
        EnumC0470q2 A0Z = ww.A0Z();
        Br br = null;
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr cr = vk.A00.get(A0c);
            if (cr != null) {
                if (A01.A02(cr.A01(), cr.A05(ww, wn))) {
                    ww.A0a();
                    try {
                        Object A02 = vk.A02(wn, A01);
                        if (A02.getClass() != this._beanType._class) {
                            return A0b(ww, wn, A02, br);
                        }
                        if (br != null) {
                            A0e(wn, A02, br);
                        }
                        return A00(ww, wn, A02);
                    } catch (Exception e) {
                        A0g(e, this._beanType._class, A0c, wn);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (!A01.A03(A0c)) {
                AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                if (A00 != null) {
                    A01.A01(A00, A00.A05(ww, wn));
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet == null || !hashSet.contains(A0c)) {
                        C0263Yn yn = this._anySetter;
                        if (yn != null) {
                            A01.A00(yn, A0c, yn.A00(ww, wn));
                        } else {
                            if (br == null) {
                                br = new Br(ww.A0H());
                            }
                            br.A09(A0c);
                            br.A08(ww);
                        }
                    } else {
                        ww.A0Y();
                    }
                }
            }
            A0Z = ww.A0a();
        }
        try {
            Object A022 = vk.A02(wn, A01);
            if (br == null) {
                return A022;
            }
            if (A022.getClass() != this._beanType._class) {
                return A0b(null, wn, A022, br);
            }
            A0e(wn, A022, br);
            return A022;
        } catch (Exception e2) {
            A0f(e2, wn);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final Object A02(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, Class<?> cls) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            ww.A0a();
            AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
            if (A00 == null) {
                HashSet<String> hashSet = this._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0c)) {
                    C0263Yn yn = this._anySetter;
                    if (yn != null) {
                        yn.A01(ww, wn, obj, A0c);
                    } else {
                        A0N(ww, wn, obj, A0c);
                    }
                    A0Z = ww.A0a();
                }
            } else if (A00.A0B(cls)) {
                try {
                    obj = A00.A06(ww, wn, obj);
                    A0Z = ww.A0a();
                } catch (Exception e) {
                    A0g(e, obj, A0c, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            ww.A0Y();
            A0Z = ww.A0a();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object obj;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            ww.A0a();
            if (this._vanillaProcessing) {
                Object A05 = this._valueInstantiator.A05(wn);
                while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
                    String A0c = ww.A0c();
                    ww.A0a();
                    AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                    if (A00 != null) {
                        try {
                            A05 = A00.A06(ww, wn, A05);
                        } catch (Exception e) {
                            A0g(e, A05, A0c, wn);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        A0c(ww, wn, A05, A0c);
                    }
                    ww.A0a();
                }
                return A03(wn, A05);
            }
        } else {
            switch (C0284a5.A00[A0Z.ordinal()]) {
                case 1:
                    obj = A0W(ww, wn);
                    break;
                case 2:
                    obj = A0V(ww, wn);
                    break;
                case 3:
                    obj = A0U(ww, wn);
                    break;
                case 4:
                    return ww.A0R();
                case 5:
                case 6:
                    obj = A0T(ww, wn);
                    break;
                case 7:
                    obj = A0S(ww, wn);
                    break;
                case 8:
                case 9:
                    break;
                default:
                    throw wn.A08(this._beanType._class);
            }
            return A03(wn, obj);
        }
        obj = A0Z(ww, wn);
        return A03(wn, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        return A03(wn, A00(ww, wn, obj));
    }

    public BuilderBasedDeserializer(C0295aT aTVar, jm jmVar, C0245Xs xs, Map<String, AbstractC0073Cr> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(aTVar, jmVar, xs, map, hashSet, z, z2);
        this._buildMethod = aTVar.A04;
        if (this._objectIdReader != null) {
            StringBuilder sb = new StringBuilder("Can not use Object Id with Builder-based deserialization (type ");
            sb.append(jmVar.A00);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, C0204Vm vm) {
        super(builderBasedDeserializer, vm);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, KI ki) {
        super(builderBasedDeserializer, ki);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, HashSet<String> hashSet) {
        super(builderBasedDeserializer, hashSet);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }
}
