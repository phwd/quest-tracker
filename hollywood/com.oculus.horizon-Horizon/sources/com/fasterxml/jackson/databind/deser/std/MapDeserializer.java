package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04040gi;
import X.AbstractC04100gp;
import X.AbstractC05240kb;
import X.AbstractC05430l6;
import X.AbstractC05470lE;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.AnonymousClass0m9;
import X.C03870gK;
import X.C03990gZ;
import X.C05540lR;
import X.C05560lT;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public final class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements AbstractC05430l6, AbstractC05470lE {
    public static final long serialVersionUID = -3378654289961736240L;
    public JsonDeserializer<Object> _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public HashSet<String> _ignorableProperties;
    public final AbstractC05240kb _keyDeserializer;
    public final AbstractC04000gb _mapType;
    public C05540lR _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0lG _valueInstantiator;
    public final AnonymousClass0m9 _valueTypeDeserializer;

    public static final boolean A03(AbstractC04000gb r3, AbstractC05240kb r4) {
        AbstractC04000gb A04;
        Class<?> cls;
        return r4 == null || (A04 = r3.A04()) == null || (((cls = A04._class) == String.class || cls == Object.class) && r4.getClass().getAnnotation(JacksonStdImpl.class) != null);
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r10, AbstractC04030gh r11) throws C03990gZ {
        String[] A0i;
        HashSet<String> hashSet;
        AbstractC05240kb r5 = this._keyDeserializer;
        if (r5 == null) {
            AbstractC04000gb A04 = this._mapType.A04();
            r5 = r10._factory.A0K(r10, A04);
            if (r5 == null) {
                StringBuilder sb = new StringBuilder("Can not find a (Map) Key deserializer for type ");
                sb.append(A04);
                throw new C03990gZ(sb.toString());
            } else if (r5 instanceof AbstractC05470lE) {
                ((AbstractC05470lE) r5).A8N(r10);
            }
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(r10, r11, this._valueDeserializer);
        if (A05 == null) {
            A05 = r10.A05(this._mapType.A03(), r11);
        } else if (A05 instanceof AbstractC05430l6) {
            A05 = ((AbstractC05430l6) A05).A21(r10, r11);
        }
        AnonymousClass0m9 r7 = this._valueTypeDeserializer;
        if (r7 != null) {
            r7 = r7.A04(r11);
        }
        HashSet<String> hashSet2 = this._ignorableProperties;
        AbstractC04040gi A01 = r10._config.A01();
        if (!(A01 == null || r11 == null || (A0i = A01.A0i(r11.A3p())) == null)) {
            if (hashSet2 == null) {
                hashSet = new HashSet<>();
            } else {
                hashSet = new HashSet<>(hashSet2);
            }
            hashSet2 = hashSet;
            for (String str : A0i) {
                hashSet2.add(str);
            }
        }
        if (this._keyDeserializer == r5 && this._valueDeserializer == A05 && this._valueTypeDeserializer == r7 && this._ignorableProperties == hashSet2) {
            return this;
        }
        return new MapDeserializer(this, r5, A05, r7, hashSet2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Map<Object, Object> A09(AbstractC04100gp r10, AbstractC04020gg r11) throws IOException, AnonymousClass0jg {
        Object A0A;
        Object A0C;
        C05540lR r8 = this._propertyBasedCreator;
        if (r8 != null) {
            C05560lT A01 = r8.A01(r10, r11, null);
            EnumC04820ji A0a = r10.A0a();
            if (A0a == EnumC04820ji.START_OBJECT) {
                A0a = r10.A0b();
            }
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0m9 r5 = this._valueTypeDeserializer;
            while (A0a == EnumC04820ji.FIELD_NAME) {
                String A0d = r10.A0d();
                EnumC04820ji A0b = r10.A0b();
                HashSet<String> hashSet = this._ignorableProperties;
                if (hashSet == null || !hashSet.contains(A0d)) {
                    AnonymousClass0HD r0 = r8.A00.get(A0d);
                    if (r0 != null) {
                        if (A01.A02(r0.A01(), r0.A05(r10, r11))) {
                            r10.A0b();
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
                                if ((e instanceof Error) || ((e instanceof IOException) && !(e instanceof C03990gZ))) {
                                    throw e;
                                }
                                throw C03990gZ.A01(e, cls, null);
                            }
                        }
                    } else {
                        Object A00 = this._keyDeserializer.A00(r10.A0d(), r11);
                        if (A0b == EnumC04820ji.VALUE_NULL) {
                            A0C = null;
                        } else if (r5 == null) {
                            A0C = jsonDeserializer.A09(r10, r11);
                        } else {
                            A0C = jsonDeserializer.A0C(r10, r11, r5);
                        }
                        A01.A00 = new C03870gK(A01.A00, A0C, A00);
                    }
                } else {
                    r10.A0Z();
                }
                A0a = r10.A0b();
            }
            return (Map) r8.A02(r11, A01);
        }
        JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
        if (jsonDeserializer2 != null) {
            A0A = this._valueInstantiator.A09(r11, jsonDeserializer2.A09(r10, r11));
        } else if (this._hasDefaultCreator) {
            EnumC04820ji A0a2 = r10.A0a();
            if (A0a2 == EnumC04820ji.START_OBJECT || A0a2 == EnumC04820ji.FIELD_NAME || A0a2 == EnumC04820ji.END_OBJECT) {
                Map<Object, Object> map2 = (Map) this._valueInstantiator.A05(r11);
                if (this._standardStringKey) {
                    A02(r10, r11, map2);
                    return map2;
                }
                A01(r10, r11, map2);
                return map2;
            } else if (A0a2 == EnumC04820ji.VALUE_STRING) {
                A0A = this._valueInstantiator.A0A(r11, r10.A0e());
            } else {
                throw null;
            }
        } else {
            throw r11.A08(this._mapType._class, "No default constructor found");
        }
        return (Map) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC04100gp r3, AbstractC04020gg r4, Object obj) throws IOException, AnonymousClass0jg {
        Map<Object, Object> map = (Map) obj;
        EnumC04820ji A0a = r3.A0a();
        if (A0a != EnumC04820ji.START_OBJECT && A0a != EnumC04820ji.FIELD_NAME) {
            throw null;
        } else if (this._standardStringKey) {
            A02(r3, r4, map);
            return map;
        } else {
            A01(r3, r4, map);
            return map;
        }
    }

    @Override // X.AbstractC05470lE
    public final void A8N(AbstractC04020gg r3) throws C03990gZ {
        AnonymousClass0lG r1 = this._valueInstantiator;
        if (r1.A0L()) {
            AbstractC04000gb A01 = r1.A01(r3._config);
            if (A01 != null) {
                this._delegateDeserializer = r3.A05(A01, null);
            } else {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._mapType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        AnonymousClass0lG r12 = this._valueInstantiator;
        if (r12.A0I()) {
            this._propertyBasedCreator = C05540lR.A00(r3, this._valueInstantiator, r12.A0M(r3._config));
        }
        this._standardStringKey = A03(this._mapType, this._keyDeserializer);
    }

    private final void A01(AbstractC04100gp r8, AbstractC04020gg r9, Map<Object, Object> map) throws IOException, AnonymousClass0jg {
        Object A0C;
        EnumC04820ji A0a = r8.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            A0a = r8.A0b();
        }
        AbstractC05240kb r6 = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0m9 r4 = this._valueTypeDeserializer;
        while (A0a == EnumC04820ji.FIELD_NAME) {
            String A0d = r8.A0d();
            Object A00 = r6.A00(A0d, r9);
            EnumC04820ji A0b = r8.A0b();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0d)) {
                if (A0b == EnumC04820ji.VALUE_NULL) {
                    A0C = null;
                } else if (r4 == null) {
                    A0C = jsonDeserializer.A09(r8, r9);
                } else {
                    A0C = jsonDeserializer.A0C(r8, r9, r4);
                }
                map.put(A00, A0C);
            } else {
                r8.A0Z();
            }
            A0a = r8.A0b();
        }
    }

    private final void A02(AbstractC04100gp r6, AbstractC04020gg r7, Map<Object, Object> map) throws IOException, AnonymousClass0jg {
        Object A0C;
        EnumC04820ji A0a = r6.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            A0a = r6.A0b();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0m9 r3 = this._valueTypeDeserializer;
        while (A0a == EnumC04820ji.FIELD_NAME) {
            String A0d = r6.A0d();
            EnumC04820ji A0b = r6.A0b();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0d)) {
                if (A0b == EnumC04820ji.VALUE_NULL) {
                    A0C = null;
                } else if (r3 == null) {
                    A0C = jsonDeserializer.A09(r6, r7);
                } else {
                    A0C = jsonDeserializer.A0C(r6, r7, r3);
                }
                map.put(A0d, A0C);
            } else {
                r6.A0Z();
            }
            A0a = r6.A0b();
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A09(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    public MapDeserializer(AbstractC04000gb r2, AnonymousClass0lG r3, AbstractC05240kb r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0m9 r6) {
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

    public MapDeserializer(MapDeserializer mapDeserializer, AbstractC05240kb r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0m9 r6, HashSet<String> hashSet) {
        super(mapDeserializer._valueClass);
        AbstractC04000gb r1 = mapDeserializer._mapType;
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
