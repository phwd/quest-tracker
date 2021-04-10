package com.fasterxml.jackson.databind.deser;

import X.AbstractC0073Cr;
import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
import X.AbstractC0264Yo;
import X.AnonymousClass72;
import X.AnonymousClass87;
import X.Br;
import X.C0199Vf;
import X.C0203Vk;
import X.C0204Vm;
import X.C0223Wj;
import X.C0245Xs;
import X.C0263Yn;
import X.EnumC0470q2;
import X.KI;
import X.N6;
import X.ON;
import X.V4;
import X.X5;
import X.Zy;
import X.pM;
import X.q0;
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

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements Zy, AbstractC0264Yo, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public transient HashMap<ON, JsonDeserializer<Object>> A00;
    public final transient N6 A01;
    public C0263Yn _anySetter;
    public final Map<String, AbstractC0073Cr> _backRefs;
    public final C0245Xs _beanProperties;
    public final AbstractC0224Wl _beanType;
    public JsonDeserializer<Object> _delegateDeserializer;
    public X5 _externalTypeIdHandler;
    public final HashSet<String> _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final AnonymousClass87[] _injectables;
    public final boolean _needViewProcesing;
    public boolean _nonStandardCreation;
    public final C0204Vm _objectIdReader;
    public C0203Vk _propertyBasedCreator;
    public final pM _serializationShape;
    public C0199Vf _unwrappedPropertyHandler;
    public final AbstractC0262Ym _valueInstantiator;
    public boolean _vanillaProcessing;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public abstract JsonDeserializer<Object> A0B(KI ki);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public abstract BeanDeserializerBase A0P();

    public abstract BeanDeserializerBase A0Q(C0204Vm vm);

    public abstract BeanDeserializerBase A0R(HashSet<String> hashSet);

    public abstract Object A0Z(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public abstract Object A0a(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public final Object A0b(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, Br br) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            HashMap<ON, JsonDeserializer<Object>> hashMap = this.A00;
            if (hashMap == null) {
                jsonDeserializer = null;
            } else {
                jsonDeserializer = hashMap.get(new ON(obj.getClass()));
            }
        }
        if (jsonDeserializer == null) {
            Class<?> cls = obj.getClass();
            jsonDeserializer = wn.A05(wn._config.A03(cls));
            if (jsonDeserializer != null) {
                synchronized (this) {
                    HashMap<ON, JsonDeserializer<Object>> hashMap2 = this.A00;
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap<>();
                        this.A00 = hashMap2;
                    }
                    hashMap2.put(new ON(cls), jsonDeserializer);
                }
            }
        }
        if (jsonDeserializer != null) {
            if (br != null) {
                br.A05();
                AnonymousClass72 r0 = new AnonymousClass72(br.A01, br.A00);
                r0.A0a();
                obj = jsonDeserializer.A0A(r0, wn, obj);
            }
            if (ww != null) {
                return jsonDeserializer.A0A(ww, wn, obj);
            }
            return obj;
        }
        if (br != null) {
            A0e(wn, obj, br);
        }
        if (ww != null) {
            return A0A(ww, wn, obj);
        }
        return obj;
    }

    private final Object A04(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A09 = this._objectIdReader.deserializer.A09(ww, wn);
        Object obj = wn.A0F(A09, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
        sb.append(A09);
        sb.append("] (for ");
        sb.append(this._beanType);
        sb.append(") -- unresolved forward-reference?");
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        EnumC0470q2 A0Z;
        if (this._objectIdReader == null || (A0Z = ww.A0Z()) == null || !A0Z.isScalarValue()) {
            return v4.A09(ww, wn);
        }
        return A04(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Collection<Object> A0D() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractC0073Cr> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next()._propName);
        }
        return arrayList;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A0N(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, String str) throws IOException, q0 {
        HashSet<String> hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            ww.A0Y();
        } else {
            super.A0N(ww, wn, obj, str);
        }
    }

    public final Object A0S(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            try {
                Object A09 = this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
                if (this._injectables != null) {
                    A0d(wn);
                }
                return A09;
            } catch (Exception e) {
                A0f(e, wn);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw wn.A08(this._beanType._class);
        }
    }

    public final Object A0T(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AbstractC0262Ym ym = this._valueInstantiator;
            if (!ym.A0E()) {
                Object A09 = ym.A09(wn, jsonDeserializer.A09(ww, wn));
                if (this._injectables != null) {
                    A0d(wn);
                }
                return A09;
            }
        }
        boolean z = false;
        if (ww.A0Z() == EnumC0470q2.VALUE_TRUE) {
            z = true;
        }
        return this._valueInstantiator.A0B(wn, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r1.A0G() == false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r1.A0G() == false) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A0V(X.AbstractC0232Ww r4, X.AbstractC0226Wn r5) throws java.io.IOException, X.q0 {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0V(X.Ww, X.Wn):java.lang.Object");
    }

    public final Object A0W(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (this._objectIdReader != null) {
            return A04(ww, wn);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AbstractC0262Ym ym = this._valueInstantiator;
            if (!ym.A0J()) {
                Object A09 = ym.A09(wn, jsonDeserializer.A09(ww, wn));
                if (this._injectables == null) {
                    return A09;
                }
                A0d(wn);
                return A09;
            }
        }
        return this._valueInstantiator.A0A(wn, ww.A0d());
    }

    public final Object A0X(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String str = this._objectIdReader.propertyName;
        if (str.equals(ww.A0c())) {
            return A0Z(ww, wn);
        }
        Br br = new Br(ww.A0H());
        Br br2 = null;
        while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
            String A0c = ww.A0c();
            if (br2 != null) {
                br2.A09(A0c);
                ww.A0a();
                br2.A08(ww);
            } else if (str.equals(A0c)) {
                br2 = new Br(ww.A0H());
                br2.A09(A0c);
                ww.A0a();
                br2.A08(ww);
                AnonymousClass72 r1 = new AnonymousClass72(br.A01, br.A00);
                while (r1.A0a() != null) {
                    Br.A02(br2, r1);
                }
                br = null;
            } else {
                br.A09(A0c);
                ww.A0a();
                br.A08(ww);
            }
            ww.A0a();
        }
        if (br2 == null) {
            br2 = br;
        }
        br2.A05();
        AnonymousClass72 r0 = new AnonymousClass72(br2.A01, br2.A00);
        r0.A0a();
        return A0Z(r0, wn);
    }

    public final Object A0Y(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
        }
        if (this._propertyBasedCreator != null) {
            return A0a(ww, wn);
        }
        if (this._beanType.A0E()) {
            sb = new StringBuilder("Can not instantiate abstract type ");
            sb.append(this._beanType);
            str = " (need to add/enable type information?)";
        } else {
            sb = new StringBuilder("No suitable constructor found for type ");
            sb.append(this._beanType);
            str = ": can not instantiate from JSON object (need to add/enable type information?)";
        }
        sb.append(str);
        throw C0223Wj.A00(ww, sb.toString());
    }

    public final void A0c(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, String str) throws IOException, q0 {
        HashSet<String> hashSet = this._ignorableProps;
        if (hashSet == null || !hashSet.contains(str)) {
            C0263Yn yn = this._anySetter;
            if (yn != null) {
                try {
                    yn.A01(ww, wn, obj, str);
                } catch (Exception e) {
                    A0g(e, obj, str, wn);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                A0N(ww, wn, obj, str);
            }
        } else {
            ww.A0Y();
        }
    }

    public final void A0d(AbstractC0226Wn wn) throws IOException, q0 {
        AnonymousClass87[] r2 = this._injectables;
        if (0 < r2.length) {
            wn.A0K(r2[0].A00);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0L(X.EnumC0225Wm.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0f(java.lang.Throwable r3, X.AbstractC0226Wn r4) throws java.io.IOException {
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
            X.Wm r0 = X.EnumC0225Wm.WRAP_EXCEPTIONS
            boolean r0 = r4.A0L(r0)
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
            X.Wl r0 = r2._beanType
            java.lang.Class<?> r0 = r0._class
            X.Wj r3 = r4.A0C(r0, r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0f(java.lang.Throwable, X.Wn):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.A0L(X.EnumC0225Wm.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0g(java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5, X.AbstractC0226Wn r6) throws java.io.IOException {
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
            if (r0 != 0) goto L_0x0035
            if (r6 == 0) goto L_0x001e
            X.Wm r0 = X.EnumC0225Wm.WRAP_EXCEPTIONS
            boolean r0 = r6.A0L(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002e
            if (r1 == 0) goto L_0x0035
            boolean r0 = r3 instanceof X.q0
            if (r0 == 0) goto L_0x0035
        L_0x0029:
            X.Wj r0 = X.C0223Wj.A01(r3, r4, r5)
            throw r0
        L_0x002e:
            if (r1 != 0) goto L_0x0029
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 != 0) goto L_0x0035
            goto L_0x0029
        L_0x0035:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0g(java.lang.Throwable, java.lang.Object, java.lang.String, X.Wn):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ab, code lost:
        if (r6 == null) goto L_0x00ae;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006f A[LOOP:0: B:28:0x006d->B:29:0x006f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // X.Zy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A1g(X.AbstractC0226Wn r13, X.AbstractC0227Wo r14) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A1g(X.Wn, X.Wo):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:171:0x0136 */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x020f, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("Can not handle managed/back reference '");
        r1.append(r11);
        r1.append("': no back reference property found from type ");
        r1.append(r10._type);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02fa, code lost:
        r1.append(")");
     */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x020f A[EDGE_INSN: B:161:0x020f->B:120:0x020f ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00db  */
    @Override // X.AbstractC0264Yo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A4n(X.AbstractC0226Wn r17) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 775
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A4n(X.Wn):void");
    }

    public final Object A0U(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        switch (ww.A0P().intValue()) {
            case 3:
            case 4:
                JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    AbstractC0262Ym ym = this._valueInstantiator;
                    if (!ym.A0F()) {
                        Object A09 = ym.A09(wn, jsonDeserializer.A09(ww, wn));
                        if (this._injectables == null) {
                            return A09;
                        }
                        A0d(wn);
                        return A09;
                    }
                }
                return this._valueInstantiator.A06(wn, ww.A0J());
            default:
                JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
                if (jsonDeserializer2 != null) {
                    return this._valueInstantiator.A09(wn, jsonDeserializer2.A09(ww, wn));
                }
                throw wn.A0A(this._beanType._class, "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public final void A0e(AbstractC0226Wn wn, Object obj, Br br) throws IOException, q0 {
        br.A05();
        AnonymousClass72 r2 = new AnonymousClass72(br.A01, br.A00);
        while (r2.A0a() != EnumC0470q2.END_OBJECT) {
            String A0c = r2.A0c();
            r2.A0a();
            A0N(r2, wn, obj, A0c);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, X.C0204Vm r4) {
        /*
            r2 = this;
            X.Wl r1 = r3._beanType
            r2.<init>(r1)
            X.N6 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ym r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Vk r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.Cr> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.Yn r0 = r3._anySetter
            r2._anySetter = r0
            X.87[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Vf r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.pM r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            r2._objectIdReader = r4
            if (r4 != 0) goto L_0x0048
            X.Xs r0 = r3._beanProperties
        L_0x0045:
            r2._beanProperties = r0
            return
        L_0x0048:
            X.8A r1 = new X.8A
            r1.<init>(r4)
            X.Xs r0 = r3._beanProperties
            X.Xs r0 = r0.A01(r1)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.Vm):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        if (r8._ignoreAllUnknown != false) goto L_0x0027;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, X.KI r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.KI):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, java.util.HashSet<java.lang.String> r4) {
        /*
            r2 = this;
            X.Wl r1 = r3._beanType
            r2.<init>(r1)
            X.N6 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ym r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Vk r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.Cr> r0 = r3._backRefs
            r2._backRefs = r0
            r2._ignorableProps = r4
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.Yn r0 = r3._anySetter
            r2._anySetter = r0
            X.87[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Vf r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.pM r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            X.Vm r0 = r3._objectIdReader
            r2._objectIdReader = r0
            X.Xs r0 = r3._beanProperties
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
            X.Wl r1 = r3._beanType
            r2.<init>(r1)
            X.N6 r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.Ym r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.Vk r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            X.Xs r0 = r3._beanProperties
            r2._beanProperties = r0
            java.util.Map<java.lang.String, X.Cr> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            r2._ignoreAllUnknown = r4
            X.Yn r0 = r3._anySetter
            r2._anySetter = r0
            X.87[] r0 = r3._injectables
            r2._injectables = r0
            X.Vm r0 = r3._objectIdReader
            r2._objectIdReader = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.Vf r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.pM r0 = r3._serializationShape
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
    public BeanDeserializerBase(X.C0295aT r5, X.jm r6, X.C0245Xs r7, java.util.Map<java.lang.String, X.AbstractC0073Cr> r8, java.util.HashSet<java.lang.String> r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(X.aT, X.jm, X.Xs, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }
}
