package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC02590aM;
import X.AbstractC06520n2;
import X.AbstractC06550n9;
import X.AnonymousClass0a1;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0mY;
import X.AnonymousClass0nB;
import X.AnonymousClass0nM;
import X.AnonymousClass0nO;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C06290mV;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public final class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements AbstractC06520n2, AbstractC06550n9 {
    public static final long serialVersionUID = -3378654289961736240L;
    public JsonDeserializer<Object> _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public HashSet<String> _ignorableProperties;
    public final AnonymousClass0mY _keyDeserializer;
    public final AnonymousClass0aI _mapType;
    public AnonymousClass0nM _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0nB _valueInstantiator;
    public final AnonymousClass0o3 _valueTypeDeserializer;

    public static final boolean A03(AnonymousClass0aI r3, AnonymousClass0mY r4) {
        AnonymousClass0aI A05;
        Class<?> cls;
        return r4 == null || (A05 = r3.A05()) == null || (((cls = A05._class) == String.class || cls == Object.class) && r4.getClass().getAnnotation(JacksonStdImpl.class) != null);
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r10, AbstractC02580aL r11) throws AnonymousClass0aG {
        String[] A0v;
        HashSet<String> hashSet;
        AnonymousClass0mY r5 = this._keyDeserializer;
        if (r5 == null) {
            AnonymousClass0aI A05 = this._mapType.A05();
            r5 = r10._factory.A0K(r10, A05);
            if (r5 == null) {
                throw new AnonymousClass0aG("Can not find a (Map) Key deserializer for type " + A05);
            } else if (r5 instanceof AbstractC06550n9) {
                ((AbstractC06550n9) r5).A7T(r10);
            }
        }
        JsonDeserializer<?> A052 = StdDeserializer.A05(r10, r11, this._valueDeserializer);
        if (A052 == null) {
            A052 = r10.A09(this._mapType.A04(), r11);
        } else if (A052 instanceof AbstractC06520n2) {
            A052 = ((AbstractC06520n2) A052).A1w(r10, r11);
        }
        AnonymousClass0o3 r7 = this._valueTypeDeserializer;
        if (r7 != null) {
            r7 = r7.A04(r11);
        }
        HashSet<String> hashSet2 = this._ignorableProperties;
        AbstractC02590aM A01 = r10._config.A01();
        if (!(A01 == null || r11 == null || (A0v = A01.A0v(r11.A41())) == null)) {
            if (hashSet2 == null) {
                hashSet = new HashSet<>();
            } else {
                hashSet = new HashSet<>(hashSet2);
            }
            hashSet2 = hashSet;
            for (String str : A0v) {
                hashSet2.add(str);
            }
        }
        if (this._keyDeserializer == r5 && this._valueDeserializer == A052 && this._valueTypeDeserializer == r7 && this._ignorableProperties == hashSet2) {
            return this;
        }
        return new MapDeserializer(this, r5, A052, r7, hashSet2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Map<Object, Object> A09(AnonymousClass0aT r10, AbstractC02570aK r11) throws IOException, C05910ld {
        Object A0A;
        Object A0C;
        AnonymousClass0nM r8 = this._propertyBasedCreator;
        if (r8 != null) {
            AnonymousClass0nO A01 = r8.A01(r10, r11, null);
            EnumC05930lf A0G = r10.A0G();
            if (A0G == EnumC05930lf.START_OBJECT) {
                A0G = r10.A0a();
            }
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0o3 r5 = this._valueTypeDeserializer;
            while (A0G == EnumC05930lf.FIELD_NAME) {
                String A0O = r10.A0O();
                EnumC05930lf A0a = r10.A0a();
                HashSet<String> hashSet = this._ignorableProperties;
                if (hashSet == null || !hashSet.contains(A0O)) {
                    AbstractC01680Ku r0 = r8.A00.get(A0O);
                    if (r0 != null) {
                        if (A01.A02(r0.A01(), r0.A05(r10, r11))) {
                            r10.A0a();
                            try {
                                Map<Object, Object> map = (Map) r8.A02(r11, A01);
                                A01(r10, r11, map);
                                return map;
                            } catch (Exception e) {
                                e = e;
                                Class<?> cls = this._mapType._class;
                                while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                                    e = e.getCause();
                                }
                                if ((e instanceof Error) || ((e instanceof IOException) && !(e instanceof AnonymousClass0aG))) {
                                    throw e;
                                }
                                throw AnonymousClass0aG.A01(e, new C06290mV(cls, (String) null));
                            }
                        }
                    } else {
                        Object A00 = this._keyDeserializer.A00(r10.A0O(), r11);
                        if (A0a == EnumC05930lf.VALUE_NULL) {
                            A0C = null;
                        } else if (r5 == null) {
                            A0C = jsonDeserializer.A09(r10, r11);
                        } else {
                            A0C = jsonDeserializer.A0C(r10, r11, r5);
                        }
                        A01.A00 = new AnonymousClass0a1(A01.A00, A0C, A00);
                    }
                } else {
                    r10.A0F();
                }
                A0G = r10.A0a();
            }
            return (Map) r8.A02(r11, A01);
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
        if (jsonDeserializer2 != null) {
            A0A = this._valueInstantiator.A09(r11, jsonDeserializer2.A09(r10, r11));
        } else if (this._hasDefaultCreator) {
            EnumC05930lf A0G2 = r10.A0G();
            if (A0G2 == EnumC05930lf.START_OBJECT || A0G2 == EnumC05930lf.FIELD_NAME || A0G2 == EnumC05930lf.END_OBJECT) {
                Map<Object, Object> map2 = (Map) this._valueInstantiator.A05(r11);
                if (this._standardStringKey) {
                    A02(r10, r11, map2);
                    return map2;
                }
                A01(r10, r11, map2);
                return map2;
            } else if (A0G2 == EnumC05930lf.VALUE_STRING) {
                A0A = this._valueInstantiator.A0A(r11, r10.A0P());
            } else {
                throw r11.A0B(this._mapType._class);
            }
        } else {
            throw r11.A0D(this._mapType._class, "No default constructor found");
        }
        return (Map) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AnonymousClass0aT r3, AbstractC02570aK r4, Object obj) throws IOException, C05910ld {
        Map<Object, Object> map = (Map) obj;
        EnumC05930lf A0G = r3.A0G();
        if (A0G != EnumC05930lf.START_OBJECT && A0G != EnumC05930lf.FIELD_NAME) {
            throw r4.A0B(this._mapType._class);
        } else if (this._standardStringKey) {
            A02(r3, r4, map);
            return map;
        } else {
            A01(r3, r4, map);
            return map;
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    @Override // X.AbstractC06550n9
    public final void A7T(AbstractC02570aK r3) throws AnonymousClass0aG {
        AnonymousClass0nB r1 = this._valueInstantiator;
        if (r1.A0L()) {
            AnonymousClass0aI A01 = r1.A01(r3._config);
            if (A01 != null) {
                this._delegateDeserializer = r3.A09(A01, null);
            } else {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
        }
        AnonymousClass0nB r12 = this._valueInstantiator;
        if (r12.A0I()) {
            this._propertyBasedCreator = AnonymousClass0nM.A00(r3, this._valueInstantiator, r12.A0M(r3._config));
        }
        this._standardStringKey = A03(this._mapType, this._keyDeserializer);
    }

    private final void A01(AnonymousClass0aT r8, AbstractC02570aK r9, Map<Object, Object> map) throws IOException, C05910ld {
        Object A0C;
        EnumC05930lf A0G = r8.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            A0G = r8.A0a();
        }
        AnonymousClass0mY r6 = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0o3 r4 = this._valueTypeDeserializer;
        while (A0G == EnumC05930lf.FIELD_NAME) {
            String A0O = r8.A0O();
            Object A00 = r6.A00(A0O, r9);
            EnumC05930lf A0a = r8.A0a();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0O)) {
                if (A0a == EnumC05930lf.VALUE_NULL) {
                    A0C = null;
                } else if (r4 == null) {
                    A0C = jsonDeserializer.A09(r8, r9);
                } else {
                    A0C = jsonDeserializer.A0C(r8, r9, r4);
                }
                map.put(A00, A0C);
            } else {
                r8.A0F();
            }
            A0G = r8.A0a();
        }
    }

    private final void A02(AnonymousClass0aT r6, AbstractC02570aK r7, Map<Object, Object> map) throws IOException, C05910ld {
        Object A0C;
        EnumC05930lf A0G = r6.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            A0G = r6.A0a();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0o3 r3 = this._valueTypeDeserializer;
        while (A0G == EnumC05930lf.FIELD_NAME) {
            String A0O = r6.A0O();
            EnumC05930lf A0a = r6.A0a();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0O)) {
                if (A0a == EnumC05930lf.VALUE_NULL) {
                    A0C = null;
                } else if (r3 == null) {
                    A0C = jsonDeserializer.A09(r6, r7);
                } else {
                    A0C = jsonDeserializer.A0C(r6, r7, r3);
                }
                map.put(A0O, A0C);
            } else {
                r6.A0F();
            }
            A0G = r6.A0a();
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A09(r2, r3);
    }

    public MapDeserializer(AnonymousClass0aI r2, AnonymousClass0nB r3, AnonymousClass0mY r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0o3 r6) {
        super(Map.class);
        this._mapType = r2;
        this._keyDeserializer = r4;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r6;
        this._valueInstantiator = r3;
        this._hasDefaultCreator = r3.A0K();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = A03(r2, r4);
    }

    public MapDeserializer(MapDeserializer mapDeserializer, AnonymousClass0mY r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0o3 r6, HashSet<String> hashSet) {
        super(mapDeserializer._valueClass);
        AnonymousClass0aI r1 = mapDeserializer._mapType;
        this._mapType = r1;
        this._keyDeserializer = r4;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r6;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = A03(r1, r4);
    }
}
