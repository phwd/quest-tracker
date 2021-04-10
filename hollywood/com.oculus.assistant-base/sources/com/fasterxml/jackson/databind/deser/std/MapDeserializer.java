package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1020qp;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AbstractC1034r7;
import X.C0274Ov;
import X.C0276Ox;
import X.C1025qv;
import X.C1036r9;
import X.C1042rH;
import X.NX;
import X.O5;
import X.O9;
import X.OD;
import X.Oi;
import X.Ok;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase implements AbstractC0264Od, Oi {
    public static final long serialVersionUID = -3378654289961736240L;
    public JsonDeserializer _delegateDeserializer;
    public final boolean _hasDefaultCreator;
    public HashSet _ignorableProperties;
    public final OD _keyDeserializer;
    public final AbstractC1024qt _mapType;
    public C0274Ov _propertyBasedCreator;
    public boolean _standardStringKey;
    public final JsonDeserializer _valueDeserializer;
    public final Ok _valueInstantiator;
    public final PR _valueTypeDeserializer;

    public static final boolean A04(AbstractC1024qt qtVar, OD od) {
        AbstractC1024qt A05;
        Class cls;
        return od == null || (A05 = qtVar.A05()) == null || (((cls = A05._class) == String.class || cls == Object.class) && od.getClass().getAnnotation(JacksonStdImpl.class) != null);
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        String[] A0Q;
        HashSet hashSet;
        OD od = this._keyDeserializer;
        if (od == null) {
            od = qrVar.A0D(this._mapType.A05());
        }
        JsonDeserializer A05 = StdDeserializer.A05(qrVar, o5, this._valueDeserializer);
        if (A05 == null) {
            A05 = qrVar.A08(this._mapType.A04(), o5);
        } else if (A05 instanceof AbstractC0264Od) {
            A05 = ((AbstractC0264Od) A05).A1X(qrVar, o5);
        }
        PR pr = this._valueTypeDeserializer;
        if (pr != null) {
            pr = pr.A03(o5);
        }
        HashSet hashSet2 = this._ignorableProperties;
        AbstractC1020qp A01 = qrVar._config.A01();
        if (!(A01 == null || o5 == null || (A0Q = A01.A0Q(o5.A2e())) == null)) {
            if (hashSet2 == null) {
                hashSet = new HashSet();
            } else {
                hashSet = new HashSet(hashSet2);
            }
            hashSet2 = hashSet;
            for (String str : A0Q) {
                hashSet2.add(str);
            }
        }
        if (this._keyDeserializer == od && this._valueDeserializer == A05 && this._valueTypeDeserializer == pr && this._ignorableProperties == hashSet2) {
            return this;
        }
        return new MapDeserializer(this, od, A05, pr, hashSet2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Map A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A03;
        Object A09;
        C0274Ov ov = this._propertyBasedCreator;
        if (ov != null) {
            C0276Ox A01 = ov.A01(qiVar, qrVar, null);
            NX A0U = qiVar.A0U();
            if (A0U == NX.START_OBJECT) {
                A0U = qiVar.A0o();
            }
            JsonDeserializer jsonDeserializer = this._valueDeserializer;
            PR pr = this._valueTypeDeserializer;
            while (A0U == NX.FIELD_NAME) {
                String A0b = qiVar.A0b();
                NX A0o = qiVar.A0o();
                HashSet hashSet = this._ignorableProperties;
                if (hashSet == null || !hashSet.contains(A0b)) {
                    AbstractC1034r7 r7Var = (AbstractC1034r7) ov.A00.get(A0b);
                    if (r7Var != null) {
                        if (A01.A02(r7Var.A01(), r7Var.A04(qiVar, qrVar))) {
                            qiVar.A0o();
                            try {
                                Map map = (Map) ov.A02(qrVar, A01);
                                A01(this, qiVar, qrVar, map);
                                return map;
                            } catch (Exception e) {
                                A03(e, this._mapType._class);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else {
                        Object A00 = this._keyDeserializer.A00(qiVar.A0b(), qrVar);
                        if (A0o == NX.VALUE_NULL) {
                            A09 = null;
                        } else if (pr == null) {
                            A09 = jsonDeserializer.A0C(qiVar, qrVar);
                        } else {
                            A09 = jsonDeserializer.A09(qiVar, qrVar, pr);
                        }
                        A01.A00 = new C1036r9(A01.A00, A09, A00);
                    }
                } else {
                    qiVar.A0T();
                }
                A0U = qiVar.A0o();
            }
            try {
                return (Map) ov.A02(qrVar, A01);
            } catch (Exception e2) {
                A03(e2, this._mapType._class);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            JsonDeserializer jsonDeserializer2 = this._delegateDeserializer;
            if (jsonDeserializer2 != null) {
                A03 = this._valueInstantiator.A02(qrVar, jsonDeserializer2.A0C(qiVar, qrVar));
            } else if (this._hasDefaultCreator) {
                NX A0U2 = qiVar.A0U();
                if (A0U2 == NX.START_OBJECT || A0U2 == NX.FIELD_NAME || A0U2 == NX.END_OBJECT) {
                    Map map2 = (Map) this._valueInstantiator.A01(qrVar);
                    if (this._standardStringKey) {
                        A02(this, qiVar, qrVar, map2);
                        return map2;
                    }
                    A01(this, qiVar, qrVar, map2);
                    return map2;
                } else if (A0U2 == NX.VALUE_STRING) {
                    A03 = this._valueInstantiator.A03(qrVar, qiVar.A0p());
                } else {
                    qrVar.A0J();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                throw qrVar.A0B(this._mapType._class, "No default constructor found");
            }
            return (Map) A03;
        }
    }

    public static final void A03(Throwable th, Object obj) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw th;
        } else if (!(th instanceof IOException) || (th instanceof C1025qv)) {
            throw C1025qv.A01(th, new O9(obj, (String) null));
        } else {
            throw th;
        }
    }

    @Override // X.Oi
    public final void A4s(AbstractC1022qr qrVar) {
        AbstractC1024qt qtVar;
        Ok ok = this._valueInstantiator;
        if (ok.A08()) {
            if (!(ok instanceof C1042rH) || (qtVar = ((C1042rH) ok)._delegateType) == null) {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._mapType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
            this._delegateDeserializer = qrVar.A08(qtVar, null);
        }
        Ok ok2 = this._valueInstantiator;
        if (ok2.A06()) {
            this._propertyBasedCreator = C0274Ov.A00(qrVar, this._valueInstantiator, ok2.A09(qrVar._config));
        }
        this._standardStringKey = A04(this._mapType, this._keyDeserializer);
    }

    public static final void A01(MapDeserializer mapDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Map map) {
        Object A09;
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            A0U = qiVar.A0o();
        }
        OD od = mapDeserializer._keyDeserializer;
        JsonDeserializer jsonDeserializer = mapDeserializer._valueDeserializer;
        PR pr = mapDeserializer._valueTypeDeserializer;
        while (A0U == NX.FIELD_NAME) {
            String A0b = qiVar.A0b();
            Object A00 = od.A00(A0b, qrVar);
            NX A0o = qiVar.A0o();
            HashSet hashSet = mapDeserializer._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0b)) {
                if (A0o == NX.VALUE_NULL) {
                    A09 = null;
                } else if (pr == null) {
                    A09 = jsonDeserializer.A0C(qiVar, qrVar);
                } else {
                    A09 = jsonDeserializer.A09(qiVar, qrVar, pr);
                }
                map.put(A00, A09);
            } else {
                qiVar.A0T();
            }
            A0U = qiVar.A0o();
        }
    }

    public static final void A02(MapDeserializer mapDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Map map) {
        Object A09;
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            A0U = qiVar.A0o();
        }
        JsonDeserializer jsonDeserializer = mapDeserializer._valueDeserializer;
        PR pr = mapDeserializer._valueTypeDeserializer;
        while (A0U == NX.FIELD_NAME) {
            String A0b = qiVar.A0b();
            NX A0o = qiVar.A0o();
            HashSet hashSet = mapDeserializer._ignorableProperties;
            if (hashSet == null || !hashSet.contains(A0b)) {
                if (A0o == NX.VALUE_NULL) {
                    A09 = null;
                } else if (pr == null) {
                    A09 = jsonDeserializer.A0C(qiVar, qrVar);
                } else {
                    A09 = jsonDeserializer.A09(qiVar, qrVar, pr);
                }
                map.put(A0b, A09);
            } else {
                qiVar.A0T();
            }
            A0U = qiVar.A0o();
        }
    }

    public MapDeserializer(AbstractC1024qt qtVar, Ok ok, OD od, JsonDeserializer jsonDeserializer, PR pr) {
        super(Map.class);
        this._mapType = qtVar;
        this._keyDeserializer = od;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = pr;
        this._valueInstantiator = ok;
        this._hasDefaultCreator = ok.A07();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = A04(qtVar, od);
    }

    public MapDeserializer(MapDeserializer mapDeserializer, OD od, JsonDeserializer jsonDeserializer, PR pr, HashSet hashSet) {
        super(mapDeserializer._valueClass);
        AbstractC1024qt qtVar = mapDeserializer._mapType;
        this._mapType = qtVar;
        this._keyDeserializer = od;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = pr;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = A04(qtVar, od);
    }
}
