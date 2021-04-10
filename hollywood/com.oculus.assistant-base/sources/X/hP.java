package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class hP implements AnonymousClass6D {
    public final C00446t A00;
    public final yZ A01;

    public hP(yZ yZVar, C00446t r3) {
        C0514bB.A02(yZVar, "analyticsManager");
        C0514bB.A02(r3, "eventBuilder");
        this.A01 = yZVar;
        this.A00 = r3;
    }

    @Override // X.AnonymousClass6D
    public final void A1A(String str, AnonymousClass6C r5) {
        C0514bB.A02(str, "key");
        if (r5 == null) {
            this.A00.A03(str, "null");
            return;
        }
        Object value = r5.getValue();
        C0514bB.A01(value, "value.getValue()");
        if (value instanceof String) {
            this.A00.A03(str, (String) value);
        } else if (value instanceof Number) {
            this.A00.A02(str, (Number) value);
        } else {
            StringBuilder sb = new StringBuilder("Enum type expects String or Number, but got: ");
            sb.append(value);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // X.AnonymousClass6D
    public final void A1B(String str, Long l) {
        C0514bB.A02(str, "key");
        this.A00.A02(str, l);
    }

    @Override // X.AnonymousClass6D
    public final void A1C(String str, Map map) {
        C0514bB.A02(str, "key");
        if (map == null) {
            this.A00.A03(str, "null");
            return;
        }
        C0847jr A05 = this.A00.A04().A05(str);
        C0514bB.A01(A05, "eventBuilder.getExtras().acquireMapThenAdd(key)");
        A01(A05, map);
    }

    @Override // X.AnonymousClass6D
    public final void A1D(String str, String str2) {
        C0514bB.A02(str, "key");
        this.A00.A03(str, str2);
    }

    @Override // X.AnonymousClass6D
    public final void A1E(String str, List list) {
        C0514bB.A02(str, "key");
        if (list == null) {
            this.A00.A03(str, "null");
            return;
        }
        C0846jq A04 = this.A00.A04().A04(str);
        C0514bB.A01(A04, "eventBuilder.getExtras().acquireArrayThenAdd(key)");
        A00(A04, list);
    }

    @Override // X.AnonymousClass6D
    public final void A3j() {
        C00446t r2 = this.A00;
        r2.A02 = AnonymousClass75.USL_ENABLED.getTag() | r2.A02;
        String obj = Locale.getDefault().toString();
        C0514bB.A01(obj, "Locale.getDefault().toString()");
        A1D("assistant_locale", obj);
        r2.A02("assistant_client_type", 0);
        r2.A02("assistant_log_source", 2);
        this.A01.A02(r2);
    }

    @Override // X.AnonymousClass6D
    public final boolean isSampled() {
        return this.A00.A05();
    }

    public static final void A00(C0846jq jqVar, Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                if (next instanceof C0947pP) {
                    next = C0947pP.A00((C0947pP) next);
                    C0514bB.A01(next, "obj.toSerializableType()");
                }
                if ((next instanceof Number) || (next instanceof Boolean) || (next instanceof String)) {
                    C0846jq.A01(jqVar, next);
                } else if (next instanceof Map) {
                    C0847jr A002 = jqVar.A01.A00();
                    C0846jq.A00(jqVar, A002);
                    C0514bB.A01(A002, "paramArray.acquireMapThenAdd()");
                    A01(A002, (Map) next);
                } else if (next instanceof Collection) {
                    DR dr = jqVar.A01;
                    C0846jq jqVar2 = (C0846jq) dr.A01.A18();
                    if (jqVar2 == null) {
                        jqVar2 = new C0846jq(dr.A00);
                    }
                    jqVar2.A03(dr);
                    C0846jq.A00(jqVar, jqVar2);
                    A00(jqVar2, (Collection) next);
                } else if (next instanceof AnonymousClass6C) {
                    Object value = ((AnonymousClass6C) next).getValue();
                    C0514bB.A01(value, "objKey.getValue()");
                    if ((value instanceof String) || (value instanceof Number)) {
                        C0846jq.A01(jqVar, value);
                    } else {
                        StringBuilder sb = new StringBuilder("Enum type expects String and Number, but got: ");
                        sb.append(next);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Unsupported type: ");
                    sb2.append(next);
                    throw new RuntimeException(sb2.toString());
                }
            }
        }
    }

    public static final void A01(C0847jr jrVar, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                String valueOf = String.valueOf(key);
                if (key instanceof AnonymousClass6C) {
                    valueOf = ((AnonymousClass6C) key).getValue().toString();
                }
                if (value instanceof C0947pP) {
                    value = C0947pP.A00((C0947pP) value);
                    C0514bB.A01(value, "obj.toSerializableType()");
                }
                if ((value instanceof Number) || (value instanceof Boolean) || (value instanceof String)) {
                    C0847jr.A01(jrVar, valueOf, value);
                } else if (value instanceof Map) {
                    C0847jr A05 = jrVar.A05(valueOf);
                    C0514bB.A01(A05, "paramMap.acquireMapThenAdd(key)");
                    A01(A05, (Map) value);
                } else if (value instanceof Collection) {
                    C0846jq A04 = jrVar.A04(valueOf);
                    C0514bB.A01(A04, "paramMap.acquireArrayThenAdd(key)");
                    A00(A04, (Collection) value);
                } else if (value instanceof AnonymousClass6C) {
                    Object value2 = ((AnonymousClass6C) value).getValue();
                    if (value2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    } else if ((value2 instanceof String) || (value2 instanceof Number)) {
                        C0847jr.A01(jrVar, valueOf, value2);
                    } else {
                        StringBuilder sb = new StringBuilder("Enum type expects String and Number, but got: ");
                        sb.append(value);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Unsupported type: ");
                    sb2.append(key);
                    throw new RuntimeException(sb2.toString());
                }
            }
        }
    }
}
