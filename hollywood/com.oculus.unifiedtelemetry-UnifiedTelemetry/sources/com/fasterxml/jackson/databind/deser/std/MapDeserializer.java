package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0073Cr;
import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
import X.AbstractC0264Yo;
import X.AbstractC0420hV;
import X.C0201Vh;
import X.C0203Vk;
import X.C0223Wj;
import X.EnumC0470q2;
import X.V4;
import X.WT;
import X.Wp;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public final class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements Zy, AbstractC0264Yo {
    public static final long serialVersionUID = -3378654289961736240L;
    public JsonDeserializer<Object> _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public HashSet<String> _ignorableProperties;
    public final AbstractC0420hV _keyDeserializer;
    public final AbstractC0224Wl _mapType;
    public C0203Vk _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AbstractC0262Ym _valueInstantiator;
    public final V4 _valueTypeDeserializer;

    public static final boolean A03(AbstractC0224Wl wl, AbstractC0420hV hVVar) {
        AbstractC0224Wl A04;
        Class<?> cls;
        return hVVar == null || (A04 = wl.A04()) == null || (((cls = A04._class) == String.class || cls == Object.class) && hVVar.getClass().getAnnotation(JacksonStdImpl.class) != null);
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        String[] A0j;
        HashSet<String> hashSet;
        AbstractC0420hV hVVar = this._keyDeserializer;
        if (hVVar == null) {
            AbstractC0224Wl A04 = this._mapType.A04();
            hVVar = wn._factory.A0K(wn, A04);
            if (hVVar == null) {
                StringBuilder sb = new StringBuilder("Can not find a (Map) Key deserializer for type ");
                sb.append(A04);
                throw new C0223Wj(sb.toString());
            } else if (hVVar instanceof AbstractC0264Yo) {
                ((AbstractC0264Yo) hVVar).A4n(wn);
            }
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(wn, wo, this._valueDeserializer);
        if (A05 == null) {
            A05 = wn.A06(this._mapType.A03(), wo);
        } else if (A05 instanceof Zy) {
            A05 = ((Zy) A05).A1g(wn, wo);
        }
        V4 v4 = this._valueTypeDeserializer;
        if (v4 != null) {
            v4 = v4.A04(wo);
        }
        HashSet<String> hashSet2 = this._ignorableProperties;
        Wp A01 = wn._config.A01();
        if (!(A01 == null || wo == null || (A0j = A01.A0j(wo.A2d())) == null)) {
            if (hashSet2 == null) {
                hashSet = new HashSet<>();
            } else {
                hashSet = new HashSet<>(hashSet2);
            }
            hashSet2 = hashSet;
            for (String str : A0j) {
                hashSet2.add(str);
            }
        }
        if (this._keyDeserializer == hVVar && this._valueDeserializer == A05 && this._valueTypeDeserializer == v4 && this._ignorableProperties == hashSet2) {
            return this;
        }
        return new MapDeserializer(this, hVVar, A05, v4, hashSet2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Map<Object, Object> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A0A;
        Object A0C;
        C0203Vk vk = this._propertyBasedCreator;
        if (vk != null) {
            C0201Vh A01 = vk.A01(ww, wn, null);
            EnumC0470q2 A0Z = ww.A0Z();
            if (A0Z == EnumC0470q2.START_OBJECT) {
                A0Z = ww.A0a();
            }
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            V4 v4 = this._valueTypeDeserializer;
            while (A0Z == EnumC0470q2.FIELD_NAME) {
                String A0c = ww.A0c();
                EnumC0470q2 A0a = ww.A0a();
                HashSet<String> hashSet = this._ignorableProperties;
                if (hashSet == null || !hashSet.contains(A0c)) {
                    AbstractC0073Cr cr = vk.A00.get(A0c);
                    if (cr != null) {
                        if (A01.A02(cr.A01(), cr.A05(ww, wn))) {
                            ww.A0a();
                            try {
                                Map<Object, Object> map = (Map) vk.A02(wn, A01);
                                A01(ww, wn, map);
                                return map;
                            } catch (Exception e) {
                                e = e;
                                Class<?> cls = this._mapType._class;
                                while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                                    e = e.getCause();
                                }
                                if ((e instanceof Error) || ((e instanceof IOException) && !(e instanceof C0223Wj))) {
                                    throw e;
                                }
                                throw C0223Wj.A01(e, cls, null);
                            }
                        }
                    } else {
                        Object A00 = this._keyDeserializer.A00(ww.A0c(), wn);
                        if (A0a == EnumC0470q2.VALUE_NULL) {
                            A0C = null;
                        } else if (v4 == null) {
                            A0C = jsonDeserializer.A09(ww, wn);
                        } else {
                            A0C = jsonDeserializer.A0C(ww, wn, v4);
                        }
                        A01.A00 = new WT(A01.A00, A0C, A00);
                    }
                } else {
                    ww.A0Y();
                }
                A0Z = ww.A0a();
            }
            return (Map) vk.A02(wn, A01);
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
        if (jsonDeserializer2 != null) {
            A0A = this._valueInstantiator.A09(wn, jsonDeserializer2.A09(ww, wn));
        } else if (this._hasDefaultCreator) {
            EnumC0470q2 A0Z2 = ww.A0Z();
            if (A0Z2 == EnumC0470q2.START_OBJECT || A0Z2 == EnumC0470q2.FIELD_NAME || A0Z2 == EnumC0470q2.END_OBJECT) {
                Map<Object, Object> map2 = (Map) this._valueInstantiator.A05(wn);
                if (this._standardStringKey) {
                    A02(ww, wn, map2);
                    return map2;
                }
                A01(ww, wn, map2);
                return map2;
            } else if (A0Z2 == EnumC0470q2.VALUE_STRING) {
                A0A = this._valueInstantiator.A0A(wn, ww.A0d());
            } else {
                throw wn.A08(this._mapType._class);
            }
        } else {
            throw wn.A0A(this._mapType._class, "No default constructor found");
        }
        return (Map) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Map<Object, Object> map = (Map) obj;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z != EnumC0470q2.START_OBJECT && A0Z != EnumC0470q2.FIELD_NAME) {
            throw wn.A08(this._mapType._class);
        } else if (this._standardStringKey) {
            A02(ww, wn, map);
            return map;
        } else {
            A01(ww, wn, map);
            return map;
        }
    }

    @Override // X.AbstractC0264Yo
    public final void A4n(AbstractC0226Wn wn) throws C0223Wj {
        AbstractC0262Ym ym = this._valueInstantiator;
        if (ym.A0L()) {
            AbstractC0224Wl A01 = ym.A01(wn._config);
            if (A01 != null) {
                this._delegateDeserializer = wn.A06(A01, null);
            } else {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._mapType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        AbstractC0262Ym ym2 = this._valueInstantiator;
        if (ym2.A0I()) {
            this._propertyBasedCreator = C0203Vk.A00(wn, this._valueInstantiator, ym2.A0M(wn._config));
        }
        this._standardStringKey = A03(this._mapType, this._keyDeserializer);
    }

    private final void A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Map<Object, Object> map) throws IOException, q0 {
        Object A0C;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            A0Z = ww.A0a();
        }
        AbstractC0420hV hVVar = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        V4 v4 = this._valueTypeDeserializer;
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            Object A00 = hVVar.A00(A0c, wn);
            EnumC0470q2 A0a = ww.A0a();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0c)) {
                if (A0a == EnumC0470q2.VALUE_NULL) {
                    A0C = null;
                } else if (v4 == null) {
                    A0C = jsonDeserializer.A09(ww, wn);
                } else {
                    A0C = jsonDeserializer.A0C(ww, wn, v4);
                }
                map.put(A00, A0C);
            } else {
                ww.A0Y();
            }
            A0Z = ww.A0a();
        }
    }

    private final void A02(AbstractC0232Ww ww, AbstractC0226Wn wn, Map<Object, Object> map) throws IOException, q0 {
        Object A0C;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            A0Z = ww.A0a();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        V4 v4 = this._valueTypeDeserializer;
        while (A0Z == EnumC0470q2.FIELD_NAME) {
            String A0c = ww.A0c();
            EnumC0470q2 A0a = ww.A0a();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0c)) {
                if (A0a == EnumC0470q2.VALUE_NULL) {
                    A0C = null;
                } else if (v4 == null) {
                    A0C = jsonDeserializer.A09(ww, wn);
                } else {
                    A0C = jsonDeserializer.A0C(ww, wn, v4);
                }
                map.put(A0c, A0C);
            } else {
                ww.A0Y();
            }
            A0Z = ww.A0a();
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A09(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    public MapDeserializer(AbstractC0224Wl wl, AbstractC0262Ym ym, AbstractC0420hV hVVar, JsonDeserializer<Object> jsonDeserializer, V4 v4) {
        super(Map.class);
        this._mapType = wl;
        this._keyDeserializer = hVVar;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = v4;
        this._valueInstantiator = ym;
        this._hasDefaultCreator = ym.A0K();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = A03(wl, hVVar);
    }

    public MapDeserializer(MapDeserializer mapDeserializer, AbstractC0420hV hVVar, JsonDeserializer<Object> jsonDeserializer, V4 v4, HashSet<String> hashSet) {
        super(mapDeserializer._valueClass);
        AbstractC0224Wl wl = mapDeserializer._mapType;
        this._mapType = wl;
        this._keyDeserializer = hVVar;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = v4;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = A03(wl, hVVar);
    }
}
