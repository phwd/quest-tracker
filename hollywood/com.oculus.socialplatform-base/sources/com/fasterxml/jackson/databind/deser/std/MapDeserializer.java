package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC01170Rz;
import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02230iJ;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04280pi;
import X.AbstractC04300pk;
import X.AbstractC04520qa;
import X.AnonymousClass0p6;
import X.C02070hw;
import X.C02180iD;
import X.C03620oC;
import X.C04030p3;
import X.C04400pv;
import X.C04420px;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements AbstractC04230pb, AbstractC04280pi {
    public static final long serialVersionUID = -3378654289961736240L;
    public JsonDeserializer<Object> _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public HashSet<String> _ignorableProperties;
    public final AnonymousClass0p6 _keyDeserializer;
    public final AbstractC02190iF _mapType;
    public C04400pv _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AbstractC04300pk _valueInstantiator;
    public final AbstractC04520qa _valueTypeDeserializer;

    public static final boolean A04(AbstractC02190iF r3, AnonymousClass0p6 r4) {
        AbstractC02190iF A05;
        Class<?> cls;
        return r4 == null || (A05 = r3.A05()) == null || (((cls = A05._class) == String.class || cls == Object.class) && r4.getClass().getAnnotation(JacksonStdImpl.class) != null);
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r10, AbstractC02220iI r11) throws C02180iD {
        String[] A0v;
        HashSet<String> hashSet;
        AnonymousClass0p6 r5 = this._keyDeserializer;
        if (r5 == null) {
            r5 = r10.A0H(this._mapType.A05());
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(r10, r11, this._valueDeserializer);
        if (A05 == null) {
            A05 = r10.A09(this._mapType.A04(), r11);
        } else if (A05 instanceof AbstractC04230pb) {
            A05 = ((AbstractC04230pb) A05).A2O(r10, r11);
        }
        AbstractC04520qa r7 = this._valueTypeDeserializer;
        if (r7 != null) {
            r7 = r7.A04(r11);
        }
        HashSet<String> hashSet2 = this._ignorableProperties;
        AbstractC02230iJ A01 = r10._config.A01();
        if (!(A01 == null || r11 == null || (A0v = A01.A0v(r11.A4N())) == null)) {
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
        if (this._keyDeserializer == r5 && this._valueDeserializer == A05 && this._valueTypeDeserializer == r7 && this._ignorableProperties == hashSet2) {
            return this;
        }
        return new MapDeserializer(this, r5, A05, r7, hashSet2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Map<Object, Object> A0A(AbstractC02280iQ r10, AbstractC02210iH r11) throws IOException, C03620oC {
        Object A0A;
        Object A0B;
        C04400pv r8 = this._propertyBasedCreator;
        if (r8 != null) {
            C04420px A01 = r8.A01(r10, r11, null);
            EnumC03640oE A0i = r10.A0i();
            if (A0i == EnumC03640oE.START_OBJECT) {
                A0i = r10.A0j();
            }
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AbstractC04520qa r4 = this._valueTypeDeserializer;
            while (A0i == EnumC03640oE.FIELD_NAME) {
                String A0l = r10.A0l();
                EnumC03640oE A0j = r10.A0j();
                HashSet<String> hashSet = this._ignorableProperties;
                if (hashSet == null || !hashSet.contains(A0l)) {
                    AbstractC01170Rz r0 = r8.A00.get(A0l);
                    if (r0 != null) {
                        if (A01.A02(r0.A01(), r0.A05(r10, r11))) {
                            r10.A0j();
                            try {
                                Map<Object, Object> map = (Map) r8.A02(r11, A01);
                                A01(r10, r11, map);
                                return map;
                            } catch (Exception e) {
                                A03(e, this._mapType._class);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else {
                        Object A00 = this._keyDeserializer.A00(r10.A0l(), r11);
                        if (A0j == EnumC03640oE.VALUE_NULL) {
                            A0B = null;
                        } else if (r4 == null) {
                            A0B = jsonDeserializer.A0A(r10, r11);
                        } else {
                            A0B = jsonDeserializer.A0B(r10, r11, r4);
                        }
                        A01.A00 = new C02070hw(A01.A00, A0B, A00);
                    }
                } else {
                    r10.A0h();
                }
                A0i = r10.A0j();
            }
            try {
                return (Map) r8.A02(r11, A01);
            } catch (Exception e2) {
                A03(e2, this._mapType._class);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
            if (jsonDeserializer2 != null) {
                A0A = this._valueInstantiator.A09(r11, jsonDeserializer2.A0A(r10, r11));
            } else if (this._hasDefaultCreator) {
                EnumC03640oE A0i2 = r10.A0i();
                if (A0i2 == EnumC03640oE.START_OBJECT || A0i2 == EnumC03640oE.FIELD_NAME || A0i2 == EnumC03640oE.END_OBJECT) {
                    Map<Object, Object> map2 = (Map) this._valueInstantiator.A05(r11);
                    if (this._standardStringKey) {
                        A02(r10, r11, map2);
                        return map2;
                    }
                    A01(r10, r11, map2);
                    return map2;
                } else if (A0i2 == EnumC03640oE.VALUE_STRING) {
                    A0A = this._valueInstantiator.A0A(r11, r10.A0m());
                } else {
                    throw r11.A0B(this._mapType._class);
                }
            } else {
                throw r11.A0D(this._mapType._class, "No default constructor found");
            }
            return (Map) A0A;
        }
    }

    public static final void A03(Throwable th, Object obj) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw th;
        } else if (!(th instanceof IOException) || (th instanceof C02180iD)) {
            throw C02180iD.A01(th, new C04030p3(obj, (String) null));
        } else {
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC02280iQ r3, AbstractC02210iH r4, Object obj) throws IOException, C03620oC {
        Map<Object, Object> map = (Map) obj;
        EnumC03640oE A0i = r3.A0i();
        if (A0i != EnumC03640oE.START_OBJECT && A0i != EnumC03640oE.FIELD_NAME) {
            throw r4.A0B(this._mapType._class);
        } else if (this._standardStringKey) {
            A02(r3, r4, map);
            return map;
        } else {
            A01(r3, r4, map);
            return map;
        }
    }

    @Override // X.AbstractC04280pi
    public final void A9N(AbstractC02210iH r3) throws C02180iD {
        AbstractC04300pk r1 = this._valueInstantiator;
        if (r1.A0L()) {
            AbstractC02190iF A01 = r1.A01(r3._config);
            if (A01 != null) {
                this._delegateDeserializer = r3.A09(A01, null);
            } else {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._mapType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        AbstractC04300pk r12 = this._valueInstantiator;
        if (r12.A0I()) {
            this._propertyBasedCreator = C04400pv.A00(r3, this._valueInstantiator, r12.A0M(r3._config));
        }
        this._standardStringKey = A04(this._mapType, this._keyDeserializer);
    }

    private final void A01(AbstractC02280iQ r8, AbstractC02210iH r9, Map<Object, Object> map) throws IOException, C03620oC {
        Object A0B;
        EnumC03640oE A0i = r8.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            A0i = r8.A0j();
        }
        AnonymousClass0p6 r6 = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AbstractC04520qa r4 = this._valueTypeDeserializer;
        while (A0i == EnumC03640oE.FIELD_NAME) {
            String A0l = r8.A0l();
            Object A00 = r6.A00(A0l, r9);
            EnumC03640oE A0j = r8.A0j();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0l)) {
                if (A0j == EnumC03640oE.VALUE_NULL) {
                    A0B = null;
                } else if (r4 == null) {
                    A0B = jsonDeserializer.A0A(r8, r9);
                } else {
                    A0B = jsonDeserializer.A0B(r8, r9, r4);
                }
                map.put(A00, A0B);
            } else {
                r8.A0h();
            }
            A0i = r8.A0j();
        }
    }

    private final void A02(AbstractC02280iQ r6, AbstractC02210iH r7, Map<Object, Object> map) throws IOException, C03620oC {
        Object A0B;
        EnumC03640oE A0i = r6.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            A0i = r6.A0j();
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AbstractC04520qa r3 = this._valueTypeDeserializer;
        while (A0i == EnumC03640oE.FIELD_NAME) {
            String A0l = r6.A0l();
            EnumC03640oE A0j = r6.A0j();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0l)) {
                if (A0j == EnumC03640oE.VALUE_NULL) {
                    A0B = null;
                } else if (r3 == null) {
                    A0B = jsonDeserializer.A0A(r6, r7);
                } else {
                    A0B = jsonDeserializer.A0B(r6, r7, r3);
                }
                map.put(A0l, A0B);
            } else {
                r6.A0h();
            }
            A0i = r6.A0j();
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A09(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    public MapDeserializer(AbstractC02190iF r2, AbstractC04300pk r3, AnonymousClass0p6 r4, JsonDeserializer<Object> jsonDeserializer, AbstractC04520qa r6) {
        super(Map.class);
        this._mapType = r2;
        this._keyDeserializer = r4;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r6;
        this._valueInstantiator = r3;
        this._hasDefaultCreator = r3.A0K();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = A04(r2, r4);
    }

    public MapDeserializer(MapDeserializer mapDeserializer, AnonymousClass0p6 r4, JsonDeserializer<Object> jsonDeserializer, AbstractC04520qa r6, HashSet<String> hashSet) {
        super(mapDeserializer._valueClass);
        AbstractC02190iF r1 = mapDeserializer._mapType;
        this._mapType = r1;
        this._keyDeserializer = r4;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r6;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = A04(r1, r4);
    }
}
