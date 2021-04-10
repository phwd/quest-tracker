package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC0292Po;
import X.AbstractC1012qg;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.C0290Pm;
import X.C1062rc;
import X.EnumC1030r1;
import X.O5;
import X.PU;
import X.fF;
import com.facebook.assistant.oacr.OacrConstants;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer implements AbstractC0278Pa {
    public static final AbstractC1024qt A09 = new fF(Object.class);
    public JsonSerializer A00;
    public JsonSerializer A01;
    public AbstractC0292Po A02;
    public final O5 A03;
    public final AbstractC1024qt A04;
    public final PU A05;
    public final HashSet A06;
    public final AbstractC1024qt A07;
    public final boolean A08;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r0 != null) goto L_0x003b;
     */
    @Override // X.AbstractC0278Pa
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer A1Y(X.AbstractC1031r2 r10, X.O5 r11) {
        /*
        // Method dump skipped, instructions count: 177
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.A1Y(X.r2, X.O5):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (java.lang.reflect.Modifier.isFinal(r5._class.getModifiers()) == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r5._class != java.lang.Object.class) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.databind.ser.std.MapSerializer A01(java.lang.String[] r7, X.AbstractC1024qt r8, boolean r9, X.PU r10, com.fasterxml.jackson.databind.JsonSerializer r11, com.fasterxml.jackson.databind.JsonSerializer r12) {
        /*
            if (r7 == 0) goto L_0x0015
            int r2 = r7.length
            if (r2 == 0) goto L_0x0015
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>(r2)
            r1 = 0
        L_0x000b:
            if (r1 >= r2) goto L_0x0016
            r0 = r7[r1]
            r3.add(r0)
            int r1 = r1 + 1
            goto L_0x000b
        L_0x0015:
            r3 = 0
        L_0x0016:
            X.qt r4 = r8.A05()
            X.qt r5 = r8.A04()
            if (r9 != 0) goto L_0x003a
            if (r5 == 0) goto L_0x002f
            java.lang.Class r0 = r5._class
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isFinal(r0)
            r9 = 1
            if (r0 != 0) goto L_0x0030
        L_0x002f:
            r9 = 0
        L_0x0030:
            r6 = r9
        L_0x0031:
            r8 = r11
            r9 = r12
            r7 = r10
            com.fasterxml.jackson.databind.ser.std.MapSerializer r2 = new com.fasterxml.jackson.databind.ser.std.MapSerializer
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r2
        L_0x003a:
            java.lang.Class r1 = r5._class
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            r6 = 0
            if (r1 == r0) goto L_0x0031
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.A01(java.lang.String[], X.qt, boolean, X.PU, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.ser.std.MapSerializer");
    }

    public static final void A06(MapSerializer mapSerializer, Map map, AbstractC1012qg qgVar, AbstractC1031r2 r2Var, JsonSerializer jsonSerializer) {
        JsonSerializer jsonSerializer2 = mapSerializer.A00;
        HashSet hashSet = mapSerializer.A06;
        PU pu = mapSerializer.A05;
        boolean z = !r2Var._config.A06(EnumC1030r1.WRITE_NULL_MAP_VALUES);
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                r2Var._nullKeySerializer.A0B(null, qgVar, r2Var);
            } else if (!z || value != null) {
                if (hashSet == null || !hashSet.contains(key)) {
                    jsonSerializer2.A0B(key, qgVar, r2Var);
                }
            }
            if (value == null) {
                r2Var.A0D(qgVar);
            } else if (pu == null) {
                try {
                    jsonSerializer.A0B(value, qgVar, r2Var);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder(OacrConstants.AUTO_SPEECH_DOMAIN);
                    sb.append(key);
                    StdSerializer.A04(r2Var, e, map, sb.toString());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                jsonSerializer.A09(value, qgVar, r2Var, pu);
            }
        }
    }

    public final void A0D(Map map, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        JsonSerializer A0A;
        AbstractC0292Po A012;
        PU pu = this.A05;
        if (pu != null) {
            JsonSerializer jsonSerializer = this.A00;
            HashSet hashSet = this.A06;
            boolean z = !r2Var._config.A06(EnumC1030r1.WRITE_NULL_MAP_VALUES);
            Class<?> cls = null;
            JsonSerializer jsonSerializer2 = null;
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                if (key == null) {
                    r2Var._nullKeySerializer.A0B(null, qgVar, r2Var);
                } else if (!z || value != null) {
                    if (hashSet == null || !hashSet.contains(key)) {
                        jsonSerializer.A0B(key, qgVar, r2Var);
                    }
                }
                if (value == null) {
                    r2Var.A0D(qgVar);
                } else {
                    Class<?> cls2 = value.getClass();
                    if (cls2 != cls) {
                        AbstractC1024qt qtVar = this.A04;
                        if (qtVar.A0F()) {
                            jsonSerializer2 = r2Var.A08(r2Var.A03(qtVar, cls2), this.A03);
                        } else {
                            jsonSerializer2 = r2Var.A0A(cls2, this.A03);
                        }
                        cls = cls2;
                    }
                    try {
                        jsonSerializer2.A09(value, qgVar, r2Var, pu);
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder(OacrConstants.AUTO_SPEECH_DOMAIN);
                        sb.append(key);
                        StdSerializer.A04(r2Var, e, map, sb.toString());
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
            return;
        }
        JsonSerializer jsonSerializer3 = this.A00;
        HashSet hashSet2 = this.A06;
        boolean z2 = !r2Var._config.A06(EnumC1030r1.WRITE_NULL_MAP_VALUES);
        AbstractC0292Po po = this.A02;
        for (Map.Entry entry2 : map.entrySet()) {
            Object value2 = entry2.getValue();
            Object key2 = entry2.getKey();
            if (key2 == null) {
                r2Var._nullKeySerializer.A0B(null, qgVar, r2Var);
            } else if (!z2 || value2 != null) {
                if (hashSet2 == null || !hashSet2.contains(key2)) {
                    jsonSerializer3.A0B(key2, qgVar, r2Var);
                }
            }
            if (value2 == null) {
                r2Var.A0D(qgVar);
            } else {
                Class<?> cls3 = value2.getClass();
                JsonSerializer A002 = po.A00(cls3);
                if (A002 == null) {
                    AbstractC1024qt qtVar2 = this.A04;
                    if (qtVar2.A0F()) {
                        AbstractC1024qt A032 = r2Var.A03(qtVar2, cls3);
                        A0A = r2Var.A08(A032, this.A03);
                        A012 = po.A01(A032._class, A0A);
                    } else {
                        A0A = r2Var.A0A(cls3, this.A03);
                        A012 = po.A01(cls3, A0A);
                    }
                    C0290Pm pm = new C0290Pm(A0A, A012);
                    AbstractC0292Po po2 = pm.A01;
                    if (po != po2) {
                        this.A02 = po2;
                    }
                    A002 = pm.A00;
                    po = this.A02;
                }
                try {
                    A002.A0B(value2, qgVar, r2Var);
                } catch (Exception e2) {
                    StringBuilder sb2 = new StringBuilder(OacrConstants.AUTO_SPEECH_DOMAIN);
                    sb2.append(key2);
                    StdSerializer.A04(r2Var, e2, map, sb2.toString());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
    }

    public MapSerializer(MapSerializer mapSerializer, O5 o5, JsonSerializer jsonSerializer, JsonSerializer jsonSerializer2, HashSet hashSet) {
        super(Map.class, false);
        this.A06 = hashSet;
        this.A07 = mapSerializer.A07;
        this.A04 = mapSerializer.A04;
        this.A08 = mapSerializer.A08;
        this.A05 = mapSerializer.A05;
        this.A00 = jsonSerializer;
        this.A01 = jsonSerializer2;
        this.A02 = mapSerializer.A02;
        this.A03 = o5;
    }

    public MapSerializer(MapSerializer mapSerializer, PU pu) {
        super(Map.class, false);
        this.A06 = mapSerializer.A06;
        this.A07 = mapSerializer.A07;
        this.A04 = mapSerializer.A04;
        this.A08 = mapSerializer.A08;
        this.A05 = pu;
        this.A00 = mapSerializer.A00;
        this.A01 = mapSerializer.A01;
        this.A02 = mapSerializer.A02;
        this.A03 = mapSerializer.A03;
    }

    public MapSerializer(HashSet hashSet, AbstractC1024qt qtVar, AbstractC1024qt qtVar2, boolean z, PU pu, JsonSerializer jsonSerializer, JsonSerializer jsonSerializer2) {
        super(Map.class, false);
        this.A06 = hashSet;
        this.A07 = qtVar;
        this.A04 = qtVar2;
        this.A08 = z;
        this.A05 = pu;
        this.A00 = jsonSerializer;
        this.A01 = jsonSerializer2;
        this.A02 = C1062rc.A00;
        this.A03 = null;
    }
}
