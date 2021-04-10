package com.fasterxml.jackson.databind.deser;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC06520n2;
import X.AbstractC06550n9;
import X.AbstractC07080oh;
import X.AbstractC07200ov;
import X.AnonymousClass0C8;
import X.AnonymousClass0Er;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0nB;
import X.AnonymousClass0nJ;
import X.AnonymousClass0nL;
import X.AnonymousClass0nM;
import X.AnonymousClass0nQ;
import X.AnonymousClass0o3;
import X.C01570Jk;
import X.C05910ld;
import X.C06560nA;
import X.C06570nF;
import X.C07010oa;
import X.EnumC05740ky;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements AbstractC06520n2, AbstractC06550n9, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public transient HashMap<C07010oa, JsonDeserializer<Object>> A00;
    public final transient AbstractC07080oh A01;
    public C06560nA _anySetter;
    public final Map<String, AbstractC01680Ku> _backRefs;
    public final C06570nF _beanProperties;
    public final AnonymousClass0aI _beanType;
    public JsonDeserializer<Object> _delegateDeserializer;
    public AnonymousClass0nJ _externalTypeIdHandler;
    public final HashSet<String> _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final AnonymousClass0Er[] _injectables;
    public final boolean _needViewProcesing;
    public boolean _nonStandardCreation;
    public final AnonymousClass0nL _objectIdReader;
    public AnonymousClass0nM _propertyBasedCreator;
    public final EnumC05740ky _serializationShape;
    public AnonymousClass0nQ _unwrappedPropertyHandler;
    public final AnonymousClass0nB _valueInstantiator;
    public boolean _vanillaProcessing;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public abstract JsonDeserializer<Object> A0B(AbstractC07200ov v);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public abstract BeanDeserializerBase A0P();

    public abstract BeanDeserializerBase A0Q(AnonymousClass0nL v);

    public abstract BeanDeserializerBase A0R(HashSet<String> hashSet);

    public abstract Object A0Z(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public abstract Object A0a(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public final Object A0b(AnonymousClass0aT r6, AbstractC02570aK r7, Object obj, C01570Jk r9) throws IOException, C05910ld {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            HashMap<C07010oa, JsonDeserializer<Object>> hashMap = this.A00;
            if (hashMap == null) {
                jsonDeserializer = null;
            } else {
                jsonDeserializer = hashMap.get(new C07010oa(obj.getClass()));
            }
        }
        if (jsonDeserializer == null) {
            Class<?> cls = obj.getClass();
            jsonDeserializer = r7.A08(r7._config.A03(cls));
            if (jsonDeserializer != null) {
                synchronized (this) {
                    HashMap<C07010oa, JsonDeserializer<Object>> hashMap2 = this.A00;
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap<>();
                        this.A00 = hashMap2;
                    }
                    hashMap2.put(new C07010oa(cls), jsonDeserializer);
                }
            }
        }
        if (jsonDeserializer != null) {
            if (r9 != null) {
                r9.A0C();
                AnonymousClass0C8 r0 = new AnonymousClass0C8(r9.A01, r9.A00);
                r0.A0a();
                obj = jsonDeserializer.A0A(r0, r7, obj);
            }
            if (r6 != null) {
                return jsonDeserializer.A0A(r6, r7, obj);
            }
            return obj;
        }
        if (r9 != null) {
            A0e(r7, obj, r9);
        }
        if (r6 != null) {
            return A0A(r6, r7, obj);
        }
        return obj;
    }

    private final Object A04(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        Object A09 = this._objectIdReader.deserializer.A09(r4, r5);
        Object obj = r5.A0I(A09, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Could not resolve Object Id [" + A09 + "] (for " + this._beanType + ") -- unresolved forward-reference?");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        EnumC05930lf A0G;
        if (this._objectIdReader == null || (A0G = r2.A0G()) == null || !A0G.isScalarValue()) {
            return r4.A09(r2, r3);
        }
        return A04(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Collection<Object> A0D() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractC01680Ku> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next()._propName);
        }
        return arrayList;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A0N(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj, String str) throws IOException, C05910ld {
        HashSet<String> hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            r2.A0F();
        } else {
            super.A0N(r2, r3, obj, str);
        }
    }

    public final Object A0S(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            try {
                Object A09 = this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
                if (this._injectables != null) {
                    A0d(r4);
                }
                return A09;
            } catch (Exception e) {
                A0f(e, r4);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw r4.A0B(this._beanType._class);
        }
    }

    public final Object A0T(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AnonymousClass0nB r1 = this._valueInstantiator;
            if (!r1.A0E()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                if (this._injectables != null) {
                    A0d(r5);
                }
                return A09;
            }
        }
        boolean z = false;
        if (r4.A0G() == EnumC05930lf.VALUE_TRUE) {
            z = true;
        }
        return this._valueInstantiator.A0B(r5, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r1.A0G() == false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r1.A0G() == false) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A0V(X.AnonymousClass0aT r4, X.AbstractC02570aK r5) throws java.io.IOException, X.C05910ld {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0V(X.0aT, X.0aK):java.lang.Object");
    }

    public final Object A0W(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        if (this._objectIdReader != null) {
            return A04(r4, r5);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AnonymousClass0nB r1 = this._valueInstantiator;
            if (!r1.A0J()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                if (this._injectables == null) {
                    return A09;
                }
                A0d(r5);
                return A09;
            }
        }
        return this._valueInstantiator.A0A(r5, r4.A0P());
    }

    public final Object A0X(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        String str = this._objectIdReader.propertyName;
        if (str.equals(r8.A0O())) {
            return A0Z(r8, r9);
        }
        C01570Jk r5 = new C01570Jk(r8.A0I());
        C01570Jk r3 = null;
        while (r8.A0G() != EnumC05930lf.END_OBJECT) {
            String A0O = r8.A0O();
            if (r3 != null) {
                r3.A0P(A0O);
                r8.A0a();
                r3.A0a(r8);
            } else if (str.equals(A0O)) {
                r3 = new C01570Jk(r8.A0I());
                r3.A0P(A0O);
                r8.A0a();
                r3.A0a(r8);
                AnonymousClass0C8 r1 = new AnonymousClass0C8(r5.A01, r5.A00);
                while (r1.A0a() != null) {
                    C01570Jk.A02(r3, r1);
                }
                r5 = null;
            } else {
                r5.A0P(A0O);
                r8.A0a();
                r5.A0a(r8);
            }
            r8.A0a();
        }
        if (r3 == null) {
            r3 = r5;
        }
        r3.A0C();
        AnonymousClass0C8 r0 = new AnonymousClass0C8(r3.A01, r3.A00);
        r0.A0a();
        return A0Z(r0, r9);
    }

    public final Object A0Y(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        }
        if (this._propertyBasedCreator != null) {
            return A0a(r3, r4);
        }
        if (this._beanType.A0I()) {
            sb = new StringBuilder("Can not instantiate abstract type ");
            sb.append(this._beanType);
            str = " (need to add/enable type information?)";
        } else {
            sb = new StringBuilder("No suitable constructor found for type ");
            sb.append(this._beanType);
            str = ": can not instantiate from JSON object (need to add/enable type information?)";
        }
        sb.append(str);
        throw AnonymousClass0aG.A00(r3, sb.toString());
    }

    public final void A0c(AnonymousClass0aT r3, AbstractC02570aK r4, Object obj, String str) throws IOException, C05910ld {
        HashSet<String> hashSet = this._ignorableProps;
        if (hashSet == null || !hashSet.contains(str)) {
            C06560nA r0 = this._anySetter;
            if (r0 != null) {
                try {
                    r0.A01(r3, r4, obj, str);
                } catch (Exception e) {
                    A0g(e, obj, str, r4);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0N(r3, r4, obj, str);
            }
        } else {
            r3.A0F();
        }
    }

    public final void A0d(AbstractC02570aK r4) throws IOException, C05910ld {
        AnonymousClass0Er[] r2 = this._injectables;
        if (0 < r2.length) {
            r4.A0N(r2[0].A00);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0O(X.EnumC02560aJ.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0f(java.lang.Throwable r3, X.AbstractC02570aK r4) throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x0029
            if (r4 == 0) goto L_0x001e
            X.0aJ r0 = X.EnumC02560aJ.WRAP_EXCEPTIONS
            boolean r0 = r4.A0O(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 != 0) goto L_0x0029
            if (r1 != 0) goto L_0x002a
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x002a
        L_0x0029:
            throw r3
        L_0x002a:
            X.0aI r0 = r2._beanType
            java.lang.Class<?> r0 = r0._class
            X.0aG r3 = r4.A0F(r0, r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0f(java.lang.Throwable, X.0aK):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.A0O(X.EnumC02560aJ.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0g(java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5, X.AbstractC02570aK r6) throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x0032
            if (r6 == 0) goto L_0x001e
            X.0aJ r0 = X.EnumC02560aJ.WRAP_EXCEPTIONS
            boolean r0 = r6.A0O(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x0033
            if (r1 == 0) goto L_0x0032
            boolean r0 = r3 instanceof X.C05910ld
            if (r0 == 0) goto L_0x0032
        L_0x0029:
            X.0mV r0 = new X.0mV
            r0.<init>(r4, r5)
            X.0aG r3 = X.AnonymousClass0aG.A01(r3, r0)
        L_0x0032:
            throw r3
        L_0x0033:
            if (r1 != 0) goto L_0x0029
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 != 0) goto L_0x0032
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0g(java.lang.Throwable, java.lang.Object, java.lang.String, X.0aK):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        if (r6 == null) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071 A[LOOP:0: B:28:0x006f->B:29:0x0071, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractC06520n2
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A1w(X.AbstractC02570aK r13, X.AbstractC02580aL r14) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 209
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A1w(X.0aK, X.0aL):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:171:0x013a */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0215, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("Can not handle managed/back reference '");
        r1.append(r11);
        r1.append("': no back reference property found from type ");
        r1.append(r10.A4h());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0302, code lost:
        r1.append(")");
     */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0215 A[EDGE_INSN: B:160:0x0215->B:120:0x0215 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dd  */
    @Override // X.AbstractC06550n9
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A7T(X.AbstractC02570aK r17) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 783
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A7T(X.0aK):void");
    }

    public final Object A0U(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        switch (r4.A0J().intValue()) {
            case 3:
            case 4:
                JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    AnonymousClass0nB r1 = this._valueInstantiator;
                    if (!r1.A0F()) {
                        Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                        if (this._injectables == null) {
                            return A09;
                        }
                        A0d(r5);
                        return A09;
                    }
                }
                return this._valueInstantiator.A06(r5, r4.A03());
            default:
                JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
                if (jsonDeserializer2 != null) {
                    return this._valueInstantiator.A09(r5, jsonDeserializer2.A09(r4, r5));
                }
                throw r5.A0D(this._beanType._class, "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public final void A0e(AbstractC02570aK r4, Object obj, C01570Jk r6) throws IOException, C05910ld {
        r6.A0C();
        AnonymousClass0C8 r2 = new AnonymousClass0C8(r6.A01, r6.A00);
        while (r2.A0a() != EnumC05930lf.END_OBJECT) {
            String A0O = r2.A0O();
            r2.A0a();
            A0N(r2, r4, obj, A0O);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, X.AnonymousClass0nL r4) {
        /*
            r2 = this;
            X.0aI r1 = r3._beanType
            r2.<init>(r1)
            X.0oh r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0nB r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0nM r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0Ku> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0nA r0 = r3._anySetter
            r2._anySetter = r0
            X.0Er[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0nQ r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0ky r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            r2._objectIdReader = r4
            if (r4 != 0) goto L_0x0048
            X.0nF r0 = r3._beanProperties
        L_0x0045:
            r2._beanProperties = r0
            return
        L_0x0048:
            X.0Eu r1 = new X.0Eu
            r1.<init>(r4)
            X.0nF r0 = r3._beanProperties
            X.0nF r0 = r0.A01(r1)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0nL):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        if (r8._ignoreAllUnknown != false) goto L_0x0027;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, X.AbstractC07200ov r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0ov):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, java.util.HashSet<java.lang.String> r4) {
        /*
            r2 = this;
            X.0aI r1 = r3._beanType
            r2.<init>(r1)
            X.0oh r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0nB r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0nM r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0Ku> r0 = r3._backRefs
            r2._backRefs = r0
            r2._ignorableProps = r4
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0nA r0 = r3._anySetter
            r2._anySetter = r0
            X.0Er[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0nQ r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0ky r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            X.0nL r0 = r3._objectIdReader
            r2._objectIdReader = r0
            X.0nF r0 = r3._beanProperties
            r2._beanProperties = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, java.util.HashSet):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, boolean r4) {
        /*
            r2 = this;
            X.0aI r1 = r3._beanType
            r2.<init>(r1)
            X.0oh r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0nB r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0nM r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            X.0nF r0 = r3._beanProperties
            r2._beanProperties = r0
            java.util.Map<java.lang.String, X.0Ku> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            r2._ignoreAllUnknown = r4
            X.0nA r0 = r3._anySetter
            r2._anySetter = r0
            X.0Er[] r0 = r3._injectables
            r2._injectables = r0
            X.0nL r0 = r3._objectIdReader
            r2._objectIdReader = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0nQ r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0ky r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005b, code lost:
        if (r2.A0K() == false) goto L_0x005d;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(X.C06490mz r5, X.AbstractC06260mR r6, X.C06570nF r7, java.util.Map<java.lang.String, X.AbstractC01680Ku> r8, java.util.HashSet<java.lang.String> r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(X.0mz, X.0mR, X.0nF, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }
}
