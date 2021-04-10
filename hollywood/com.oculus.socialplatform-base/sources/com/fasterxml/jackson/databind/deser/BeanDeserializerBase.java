package com.fasterxml.jackson.databind.deser;

import X.AbstractC01170Rz;
import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04280pi;
import X.AbstractC04300pk;
import X.AbstractC04520qa;
import X.AbstractC04760rD;
import X.AbstractC04870rR;
import X.AnonymousClass0C6;
import X.AnonymousClass0OD;
import X.AnonymousClass0nW;
import X.C00530Gr;
import X.C02180iD;
import X.C03620oC;
import X.C04290pj;
import X.C04330po;
import X.C04370ps;
import X.C04390pu;
import X.C04400pv;
import X.C04440pz;
import X.C04720r6;
import X.EnumC03640oE;
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

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements AbstractC04230pb, AbstractC04280pi, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public transient HashMap<C04720r6, JsonDeserializer<Object>> A00;
    public final transient AbstractC04760rD A01;
    public C04290pj _anySetter;
    public final Map<String, AbstractC01170Rz> _backRefs;
    public final C04330po _beanProperties;
    public final AbstractC02190iF _beanType;
    public JsonDeserializer<Object> _delegateDeserializer;
    public C04370ps _externalTypeIdHandler;
    public final HashSet<String> _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final C00530Gr[] _injectables;
    public final boolean _needViewProcesing;
    public boolean _nonStandardCreation;
    public final C04390pu _objectIdReader;
    public C04400pv _propertyBasedCreator;
    public final AnonymousClass0nW _serializationShape;
    public C04440pz _unwrappedPropertyHandler;
    public final AbstractC04300pk _valueInstantiator;
    public boolean _vanillaProcessing;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public abstract JsonDeserializer<Object> A09(AbstractC04870rR v);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public abstract BeanDeserializerBase A0P();

    public abstract BeanDeserializerBase A0Q(C04390pu v);

    public abstract BeanDeserializerBase A0R(HashSet<String> hashSet);

    public abstract Object A0Z(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public abstract Object A0a(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public final Object A0b(AbstractC02280iQ r6, AbstractC02210iH r7, Object obj, AnonymousClass0OD r9) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            HashMap<C04720r6, JsonDeserializer<Object>> hashMap = this.A00;
            if (hashMap == null) {
                jsonDeserializer = null;
            } else {
                jsonDeserializer = hashMap.get(new C04720r6(obj.getClass()));
            }
        }
        if (jsonDeserializer == null) {
            Class<?> cls = obj.getClass();
            jsonDeserializer = r7.A08(r7._config.A03(cls));
            if (jsonDeserializer != null) {
                synchronized (this) {
                    HashMap<C04720r6, JsonDeserializer<Object>> hashMap2 = this.A00;
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap<>();
                        this.A00 = hashMap2;
                    }
                    hashMap2.put(new C04720r6(cls), jsonDeserializer);
                }
            }
        }
        if (jsonDeserializer != null) {
            if (r9 != null) {
                r9.A0F();
                AnonymousClass0C6 r0 = new AnonymousClass0C6(r9.A01, r9.A00);
                r0.A0j();
                obj = jsonDeserializer.A0C(r0, r7, obj);
            }
            if (r6 != null) {
                return jsonDeserializer.A0C(r6, r7, obj);
            }
            return obj;
        }
        if (r9 != null) {
            A0e(r7, obj, r9);
        }
        if (r6 != null) {
            return A0C(r6, r7, obj);
        }
        return obj;
    }

    private final Object A04(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        Object A0A = this._objectIdReader.deserializer.A0A(r4, r5);
        Object obj = r5.A0J(A0A, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
        sb.append(A0A);
        sb.append("] (for ");
        sb.append(this._beanType);
        sb.append(") -- unresolved forward-reference?");
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        EnumC03640oE A0i;
        if (this._objectIdReader == null || (A0i = r2.A0i()) == null || !A0i.isScalarValue()) {
            return r4.A09(r2, r3);
        }
        return A04(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Collection<Object> A0D() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractC01170Rz> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next()._propName);
        }
        return arrayList;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A0N(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj, String str) throws IOException, C03620oC {
        HashSet<String> hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            r2.A0h();
        } else {
            super.A0N(r2, r3, obj, str);
        }
    }

    public final Object A0S(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            try {
                Object A09 = this._valueInstantiator.A09(r4, jsonDeserializer.A0A(r3, r4));
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

    public final Object A0T(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AbstractC04300pk r1 = this._valueInstantiator;
            if (!r1.A0E()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A0A(r4, r5));
                if (this._injectables != null) {
                    A0d(r5);
                }
                return A09;
            }
        }
        boolean z = false;
        if (r4.A0i() == EnumC03640oE.VALUE_TRUE) {
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
    public final java.lang.Object A0V(X.AbstractC02280iQ r4, X.AbstractC02210iH r5) throws java.io.IOException, X.C03620oC {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0V(X.0iQ, X.0iH):java.lang.Object");
    }

    public final Object A0W(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        if (this._objectIdReader != null) {
            return A04(r4, r5);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AbstractC04300pk r1 = this._valueInstantiator;
            if (!r1.A0J()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A0A(r4, r5));
                if (this._injectables == null) {
                    return A09;
                }
                A0d(r5);
                return A09;
            }
        }
        return this._valueInstantiator.A0A(r5, r4.A0m());
    }

    public final Object A0X(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        String str = this._objectIdReader.propertyName;
        if (str.equals(r8.A0l())) {
            return A0Z(r8, r9);
        }
        AnonymousClass0OD r5 = new AnonymousClass0OD(r8.A0N());
        AnonymousClass0OD r3 = null;
        while (r8.A0i() != EnumC03640oE.END_OBJECT) {
            String A0l = r8.A0l();
            if (r3 != null) {
                r3.A0R(A0l);
                r8.A0j();
                r3.A0c(r8);
            } else if (str.equals(A0l)) {
                r3 = new AnonymousClass0OD(r8.A0N());
                r3.A0R(A0l);
                r8.A0j();
                r3.A0c(r8);
                AnonymousClass0C6 r1 = new AnonymousClass0C6(r5.A01, r5.A00);
                while (r1.A0j() != null) {
                    AnonymousClass0OD.A02(r3, r1);
                }
                r5 = null;
            } else {
                r5.A0R(A0l);
                r8.A0j();
                r5.A0c(r8);
            }
            r8.A0j();
        }
        if (r3 == null) {
            r3 = r5;
        }
        r3.A0F();
        AnonymousClass0C6 r0 = new AnonymousClass0C6(r3.A01, r3.A00);
        r0.A0j();
        return A0Z(r0, r9);
    }

    public final Object A0Y(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r4, jsonDeserializer.A0A(r3, r4));
        }
        if (this._propertyBasedCreator != null) {
            return A0a(r3, r4);
        }
        if (this._beanType.A0I()) {
            StringBuilder sb = new StringBuilder("Can not instantiate abstract type ");
            sb.append(this._beanType);
            sb.append(" (need to add/enable type information?)");
            throw C02180iD.A00(r3, sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("No suitable constructor found for type ");
        sb2.append(this._beanType);
        sb2.append(": can not instantiate from JSON object (need to add/enable type information?)");
        throw C02180iD.A00(r3, sb2.toString());
    }

    public final void A0c(AbstractC02280iQ r3, AbstractC02210iH r4, Object obj, String str) throws IOException, C03620oC {
        HashSet<String> hashSet = this._ignorableProps;
        if (hashSet == null || !hashSet.contains(str)) {
            C04290pj r0 = this._anySetter;
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
            r3.A0h();
        }
    }

    public final void A0d(AbstractC02210iH r4) throws IOException, C03620oC {
        C00530Gr[] r2 = this._injectables;
        if (0 < r2.length) {
            r4.A0O(r2[0].A00);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0P(X.EnumC02200iG.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0f(java.lang.Throwable r3, X.AbstractC02210iH r4) throws java.io.IOException {
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
            if (r0 != 0) goto L_0x0034
            if (r4 == 0) goto L_0x001e
            X.0iG r0 = X.EnumC02200iG.WRAP_EXCEPTIONS
            boolean r0 = r4.A0P(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 != 0) goto L_0x0033
            if (r1 != 0) goto L_0x002a
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x002a
            throw r3
        L_0x002a:
            X.0iF r0 = r2._beanType
            java.lang.Class<?> r0 = r0._class
            X.0iD r0 = r4.A0F(r0, r3)
            throw r0
        L_0x0033:
            throw r3
        L_0x0034:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0f(java.lang.Throwable, X.0iH):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.A0P(X.EnumC02200iG.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0g(java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5, X.AbstractC02210iH r6) throws java.io.IOException {
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
            if (r0 != 0) goto L_0x003b
            if (r6 == 0) goto L_0x001e
            X.0iG r0 = X.EnumC02200iG.WRAP_EXCEPTIONS
            boolean r0 = r6.A0P(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x0033
            if (r1 == 0) goto L_0x003a
            boolean r0 = r3 instanceof X.C03620oC
            if (r0 == 0) goto L_0x003a
        L_0x0029:
            X.0p3 r0 = new X.0p3
            r0.<init>(r4, r5)
            X.0iD r0 = X.C02180iD.A01(r3, r0)
            throw r0
        L_0x0033:
            if (r1 != 0) goto L_0x0029
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0029
            throw r3
        L_0x003a:
            throw r3
        L_0x003b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0g(java.lang.Throwable, java.lang.Object, java.lang.String, X.0iH):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        if (r6 == null) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071 A[LOOP:0: B:28:0x006f->B:29:0x0071, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractC04230pb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A2O(X.AbstractC02210iH r13, X.AbstractC02220iI r14) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 209
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A2O(X.0iH, X.0iI):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:172:0x0142 */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x024f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e5  */
    @Override // X.AbstractC04280pi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A9N(X.AbstractC02210iH r23) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 801
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A9N(X.0iH):void");
    }

    public final Object A0U(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        switch (r4.A0X().intValue()) {
            case 3:
            case 4:
                JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    AbstractC04300pk r1 = this._valueInstantiator;
                    if (!r1.A0F()) {
                        Object A09 = r1.A09(r5, jsonDeserializer.A0A(r4, r5));
                        if (this._injectables == null) {
                            return A09;
                        }
                        A0d(r5);
                        return A09;
                    }
                }
                return this._valueInstantiator.A06(r5, r4.A0R());
            default:
                JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
                if (jsonDeserializer2 != null) {
                    return this._valueInstantiator.A09(r5, jsonDeserializer2.A0A(r4, r5));
                }
                throw r5.A0D(this._beanType._class, "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public final void A0e(AbstractC02210iH r4, Object obj, AnonymousClass0OD r6) throws IOException, C03620oC {
        r6.A0F();
        AnonymousClass0C6 r2 = new AnonymousClass0C6(r6.A01, r6.A00);
        while (r2.A0j() != EnumC03640oE.END_OBJECT) {
            String A0l = r2.A0l();
            r2.A0j();
            A0N(r2, r4, obj, A0l);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, X.C04390pu r4) {
        /*
            r2 = this;
            X.0iF r1 = r3._beanType
            r2.<init>(r1)
            X.0rD r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0pk r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0pv r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0Rz> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0pj r0 = r3._anySetter
            r2._anySetter = r0
            X.0Gr[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0pz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0nW r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            r2._objectIdReader = r4
            if (r4 != 0) goto L_0x0048
            X.0po r0 = r3._beanProperties
        L_0x0045:
            r2._beanProperties = r0
            return
        L_0x0048:
            X.0Gu r1 = new X.0Gu
            r1.<init>(r4)
            X.0po r0 = r3._beanProperties
            X.0po r0 = r0.A01(r1)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0pu):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        if (r8._ignoreAllUnknown != false) goto L_0x0027;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, X.AbstractC04870rR r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0rR):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, java.util.HashSet<java.lang.String> r4) {
        /*
            r2 = this;
            X.0iF r1 = r3._beanType
            r2.<init>(r1)
            X.0rD r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0pk r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0pv r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0Rz> r0 = r3._backRefs
            r2._backRefs = r0
            r2._ignorableProps = r4
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0pj r0 = r3._anySetter
            r2._anySetter = r0
            X.0Gr[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0pz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0nW r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            X.0pu r0 = r3._objectIdReader
            r2._objectIdReader = r0
            X.0po r0 = r3._beanProperties
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
            X.0iF r1 = r3._beanType
            r2.<init>(r1)
            X.0rD r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0pk r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0pv r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            X.0po r0 = r3._beanProperties
            r2._beanProperties = r0
            java.util.Map<java.lang.String, X.0Rz> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            r2._ignoreAllUnknown = r4
            X.0pj r0 = r3._anySetter
            r2._anySetter = r0
            X.0Gr[] r0 = r3._injectables
            r2._injectables = r0
            X.0pu r0 = r3._objectIdReader
            r2._objectIdReader = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0pz r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0nW r0 = r3._serializationShape
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
    public BeanDeserializerBase(X.C04210pY r5, X.AbstractC04010oz r6, X.C04330po r7, java.util.Map<java.lang.String, X.AbstractC01170Rz> r8, java.util.HashSet<java.lang.String> r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(X.0pY, X.0oz, X.0po, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }
}
