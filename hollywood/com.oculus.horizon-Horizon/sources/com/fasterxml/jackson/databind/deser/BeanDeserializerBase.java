package com.fasterxml.jackson.databind.deser;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AbstractC05470lE;
import X.AbstractC06280mp;
import X.AbstractC06410n2;
import X.AnonymousClass070;
import X.AnonymousClass08I;
import X.AnonymousClass0Fv;
import X.AnonymousClass0HD;
import X.AnonymousClass0j0;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.AnonymousClass0lK;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.C05480lF;
import X.C05510lO;
import X.C05530lQ;
import X.C05540lR;
import X.C05580lV;
import X.C06210mi;
import X.EnumC04820ji;
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

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements AbstractC05430l6, AbstractC05470lE, Serializable {
    public static final long serialVersionUID = -2038793552422727904L;
    public transient HashMap<C06210mi, JsonDeserializer<Object>> A00;
    public final transient AbstractC06280mp A01;
    public C05480lF _anySetter;
    public final Map<String, AnonymousClass0HD> _backRefs;
    public final AnonymousClass0lK _beanProperties;
    public final AbstractC04000gb _beanType;
    public JsonDeserializer<Object> _delegateDeserializer;
    public C05510lO _externalTypeIdHandler;
    public final HashSet<String> _ignorableProps;
    public final boolean _ignoreAllUnknown;
    public final AnonymousClass08I[] _injectables;
    public final boolean _needViewProcesing;
    public boolean _nonStandardCreation;
    public final C05530lQ _objectIdReader;
    public C05540lR _propertyBasedCreator;
    public final AnonymousClass0j0 _serializationShape;
    public C05580lV _unwrappedPropertyHandler;
    public final AnonymousClass0lG _valueInstantiator;
    public boolean _vanillaProcessing;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public abstract JsonDeserializer<Object> A0B(AbstractC06410n2 v);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public abstract BeanDeserializerBase A0P();

    public abstract BeanDeserializerBase A0Q(C05530lQ v);

    public abstract BeanDeserializerBase A0R(HashSet<String> hashSet);

    public abstract Object A0Z(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public abstract Object A0a(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public final Object A0b(AbstractC04100gp r6, AbstractC04020gg r7, Object obj, AnonymousClass0Fv r9) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            HashMap<C06210mi, JsonDeserializer<Object>> hashMap = this.A00;
            if (hashMap == null) {
                jsonDeserializer = null;
            } else {
                jsonDeserializer = hashMap.get(new C06210mi(obj.getClass()));
            }
        }
        if (jsonDeserializer == null) {
            Class<?> cls = obj.getClass();
            jsonDeserializer = r7.A04(r7._config.A03(cls));
            if (jsonDeserializer != null) {
                synchronized (this) {
                    HashMap<C06210mi, JsonDeserializer<Object>> hashMap2 = this.A00;
                    if (hashMap2 == null) {
                        hashMap2 = new HashMap<>();
                        this.A00 = hashMap2;
                    }
                    hashMap2.put(new C06210mi(cls), jsonDeserializer);
                }
            }
        }
        if (jsonDeserializer != null) {
            if (r9 != null) {
                r9.A05();
                AnonymousClass070 r0 = new AnonymousClass070(r9.A01, r9.A00);
                r0.A0b();
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

    private final Object A04(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        Object A09 = this._objectIdReader.deserializer.A09(r4, r5);
        Object obj = r5.A0B(A09, this._objectIdReader.generator).A00;
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
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a;
        if (this._objectIdReader == null || (A0a = r2.A0a()) == null || !A0a.isScalarValue()) {
            return r4.A09(r2, r3);
        }
        return A04(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Collection<Object> A0D() {
        ArrayList arrayList = new ArrayList();
        Iterator<AnonymousClass0HD> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next()._propName);
        }
        return arrayList;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public final void A0N(AbstractC04100gp r2, AbstractC04020gg r3, Object obj, String str) throws IOException, AnonymousClass0jg {
        HashSet<String> hashSet;
        if (this._ignoreAllUnknown || ((hashSet = this._ignorableProps) != null && hashSet.contains(str))) {
            r2.A0Z();
        } else {
            super.A0N(r2, r3, obj, str);
        }
    }

    public final Object A0S(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
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
            throw null;
        }
    }

    public final Object A0T(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AnonymousClass0lG r1 = this._valueInstantiator;
            if (!r1.A0E()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                if (this._injectables != null) {
                    A0d(r5);
                }
                return A09;
            }
        }
        boolean z = false;
        if (r4.A0a() == EnumC04820ji.VALUE_TRUE) {
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
    public final java.lang.Object A0V(X.AbstractC04100gp r4, X.AbstractC04020gg r5) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0V(X.0gp, X.0gg):java.lang.Object");
    }

    public final Object A0W(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        if (this._objectIdReader != null) {
            return A04(r4, r5);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            AnonymousClass0lG r1 = this._valueInstantiator;
            if (!r1.A0J()) {
                Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                if (this._injectables == null) {
                    return A09;
                }
                A0d(r5);
                return A09;
            }
        }
        return this._valueInstantiator.A0A(r5, r4.A0e());
    }

    public final Object A0X(AbstractC04100gp r8, AbstractC04020gg r9) throws IOException, AnonymousClass0jg {
        String str = this._objectIdReader.propertyName;
        if (str.equals(r8.A0d())) {
            return A0Z(r8, r9);
        }
        AnonymousClass0Fv r5 = new AnonymousClass0Fv(r8.A0I());
        AnonymousClass0Fv r3 = null;
        while (r8.A0a() != EnumC04820ji.END_OBJECT) {
            String A0d = r8.A0d();
            if (r3 != null) {
                r3.A09(A0d);
                r8.A0b();
                r3.A08(r8);
            } else if (str.equals(A0d)) {
                r3 = new AnonymousClass0Fv(r8.A0I());
                r3.A09(A0d);
                r8.A0b();
                r3.A08(r8);
                AnonymousClass070 r1 = new AnonymousClass070(r5.A01, r5.A00);
                while (r1.A0b() != null) {
                    AnonymousClass0Fv.A02(r3, r1);
                }
                r5 = null;
            } else {
                r5.A09(A0d);
                r8.A0b();
                r5.A08(r8);
            }
            r8.A0b();
        }
        if (r3 == null) {
            r3 = r5;
        }
        r3.A05();
        AnonymousClass070 r0 = new AnonymousClass070(r3.A01, r3.A00);
        r0.A0b();
        return A0Z(r0, r9);
    }

    public final Object A0Y(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        StringBuilder sb;
        String str;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        }
        if (this._propertyBasedCreator != null) {
            return A0a(r3, r4);
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
        throw C03990gZ.A00(r3, sb.toString());
    }

    public final void A0c(AbstractC04100gp r3, AbstractC04020gg r4, Object obj, String str) throws IOException, AnonymousClass0jg {
        HashSet<String> hashSet = this._ignorableProps;
        if (hashSet == null || !hashSet.contains(str)) {
            C05480lF r0 = this._anySetter;
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
            r3.A0Z();
        }
    }

    public final void A0d(AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        AnonymousClass08I[] r2 = this._injectables;
        if (0 < r2.length) {
            r4.A0H(r2[0].A00);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0I(X.EnumC04010gf.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0f(java.lang.Throwable r3, X.AbstractC04020gg r4) throws java.io.IOException {
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
            X.0gf r0 = X.EnumC04010gf.WRAP_EXCEPTIONS
            boolean r0 = r4.A0I(r0)
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
            X.0gb r0 = r2._beanType
            java.lang.Class<?> r0 = r0._class
            X.0gZ r3 = r4.A09(r0, r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0f(java.lang.Throwable, X.0gg):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6.A0I(X.EnumC04010gf.WRAP_EXCEPTIONS) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0g(java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5, X.AbstractC04020gg r6) throws java.io.IOException {
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
            X.0gf r0 = X.EnumC04010gf.WRAP_EXCEPTIONS
            boolean r0 = r6.A0I(r0)
            r1 = 0
            if (r0 == 0) goto L_0x001f
        L_0x001e:
            r1 = 1
        L_0x001f:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002e
            if (r1 == 0) goto L_0x0035
            boolean r0 = r3 instanceof X.AnonymousClass0jg
            if (r0 == 0) goto L_0x0035
        L_0x0029:
            X.0gZ r0 = X.C03990gZ.A01(r3, r4, r5)
            throw r0
        L_0x002e:
            if (r1 != 0) goto L_0x0029
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 != 0) goto L_0x0035
            goto L_0x0029
        L_0x0035:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A0g(java.lang.Throwable, java.lang.Object, java.lang.String, X.0gg):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ab, code lost:
        if (r6 == null) goto L_0x00ae;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006f A[LOOP:0: B:28:0x006d->B:29:0x006f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractC05430l6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A21(X.AbstractC04020gg r13, X.AbstractC04030gh r14) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A21(X.0gg, X.0gh):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:170:0x013e */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x02b0, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("Can not handle managed/back reference '");
        r1.append(r13);
        r1.append("': no back reference property found from type ");
        r1.append(r8._type);
        r1 = r1.toString();
     */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02b0 A[EDGE_INSN: B:160:0x02b0->B:143:0x02b0 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e3  */
    @Override // X.AbstractC05470lE
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8N(X.AbstractC04020gg r23) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 777
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.A8N(X.0gg):void");
    }

    public final Object A0U(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        switch (r4.A0Q().intValue()) {
            case 3:
            case 4:
                JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
                if (jsonDeserializer != null) {
                    AnonymousClass0lG r1 = this._valueInstantiator;
                    if (!r1.A0F()) {
                        Object A09 = r1.A09(r5, jsonDeserializer.A09(r4, r5));
                        if (this._injectables == null) {
                            return A09;
                        }
                        A0d(r5);
                        return A09;
                    }
                }
                return this._valueInstantiator.A06(r5, r4.A0K());
            default:
                JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
                if (jsonDeserializer2 != null) {
                    return this._valueInstantiator.A09(r5, jsonDeserializer2.A09(r4, r5));
                }
                throw r5.A08(this._beanType._class, "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public final void A0e(AbstractC04020gg r4, Object obj, AnonymousClass0Fv r6) throws IOException, AnonymousClass0jg {
        r6.A05();
        AnonymousClass070 r2 = new AnonymousClass070(r6.A01, r6.A00);
        while (r2.A0b() != EnumC04820ji.END_OBJECT) {
            String A0d = r2.A0d();
            r2.A0b();
            A0N(r2, r4, obj, A0d);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, X.C05530lQ r4) {
        /*
            r2 = this;
            X.0gb r1 = r3._beanType
            r2.<init>(r1)
            X.0mp r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0lG r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0lR r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0HD> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0lF r0 = r3._anySetter
            r2._anySetter = r0
            X.08I[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0lV r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0j0 r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            r2._objectIdReader = r4
            if (r4 != 0) goto L_0x0048
            X.0lK r0 = r3._beanProperties
        L_0x0045:
            r2._beanProperties = r0
            return
        L_0x0048:
            X.08L r1 = new X.08L
            r1.<init>(r4)
            X.0lK r0 = r3._beanProperties
            X.0lK r0 = r0.A01(r1)
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0lQ):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0025, code lost:
        if (r8._ignoreAllUnknown != false) goto L_0x0027;
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, X.AbstractC06410n2 r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.fasterxml.jackson.databind.deser.BeanDeserializerBase, X.0n2):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.fasterxml.jackson.databind.deser.BeanDeserializerBase r3, java.util.HashSet<java.lang.String> r4) {
        /*
            r2 = this;
            X.0gb r1 = r3._beanType
            r2.<init>(r1)
            X.0mp r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0lG r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0lR r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            java.util.Map<java.lang.String, X.0HD> r0 = r3._backRefs
            r2._backRefs = r0
            r2._ignorableProps = r4
            boolean r0 = r3._ignoreAllUnknown
            r2._ignoreAllUnknown = r0
            X.0lF r0 = r3._anySetter
            r2._anySetter = r0
            X.08I[] r0 = r3._injectables
            r2._injectables = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0lV r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0j0 r0 = r3._serializationShape
            r2._serializationShape = r0
            boolean r0 = r3._vanillaProcessing
            r2._vanillaProcessing = r0
            X.0lQ r0 = r3._objectIdReader
            r2._objectIdReader = r0
            X.0lK r0 = r3._beanProperties
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
            X.0gb r1 = r3._beanType
            r2.<init>(r1)
            X.0mp r0 = r3.A01
            r2.A01 = r0
            r2._beanType = r1
            X.0lG r0 = r3._valueInstantiator
            r2._valueInstantiator = r0
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r3._delegateDeserializer
            r2._delegateDeserializer = r0
            X.0lR r0 = r3._propertyBasedCreator
            r2._propertyBasedCreator = r0
            X.0lK r0 = r3._beanProperties
            r2._beanProperties = r0
            java.util.Map<java.lang.String, X.0HD> r0 = r3._backRefs
            r2._backRefs = r0
            java.util.HashSet<java.lang.String> r0 = r3._ignorableProps
            r2._ignorableProps = r0
            r2._ignoreAllUnknown = r4
            X.0lF r0 = r3._anySetter
            r2._anySetter = r0
            X.08I[] r0 = r3._injectables
            r2._injectables = r0
            X.0lQ r0 = r3._objectIdReader
            r2._objectIdReader = r0
            boolean r0 = r3._nonStandardCreation
            r2._nonStandardCreation = r0
            X.0lV r0 = r3._unwrappedPropertyHandler
            r2._unwrappedPropertyHandler = r0
            boolean r0 = r3._needViewProcesing
            r2._needViewProcesing = r0
            X.0j0 r0 = r3._serializationShape
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
    public BeanDeserializerBase(X.AnonymousClass0l3 r5, X.AbstractC05180kU r6, X.AnonymousClass0lK r7, java.util.Map<java.lang.String, X.AnonymousClass0HD> r8, java.util.HashSet<java.lang.String> r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(X.0l3, X.0kU, X.0lK, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }
}
