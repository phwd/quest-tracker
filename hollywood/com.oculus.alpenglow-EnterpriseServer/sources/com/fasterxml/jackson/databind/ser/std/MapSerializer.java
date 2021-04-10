package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AbstractC06960oT;
import X.AnonymousClass0C9;
import X.AnonymousClass0ZY;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import X.C06940oR;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public final class MapSerializer extends ContainerSerializer<Map<?, ?>> implements AbstractC06840oE {
    public static final AnonymousClass0aI A09 = new AnonymousClass0C9(Object.class);
    public JsonSerializer<Object> A00;
    public AbstractC06960oT A01;
    public JsonSerializer<Object> A02;
    public final AbstractC02580aL A03;
    public final AnonymousClass0aI A04;
    public final AnonymousClass0o6 A05;
    public final HashSet<String> A06;
    public final AnonymousClass0aI A07;
    public final boolean A08;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r0 != null) goto L_0x0029;
     */
    @Override // X.AbstractC06840oE
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<?> A1x(X.AnonymousClass0a8 r10, X.AbstractC02580aL r11) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.A1x(X.0a8, X.0aL):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (java.lang.reflect.Modifier.isFinal(r5._class.getModifiers()) == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r5._class != java.lang.Object.class) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.databind.ser.std.MapSerializer A00(java.lang.String[] r7, X.AnonymousClass0aI r8, boolean r9, X.AnonymousClass0o6 r10, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r11, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r12) {
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
            X.0aI r4 = r8.A05()
            X.0aI r5 = r8.A04()
            if (r9 != 0) goto L_0x003a
            if (r5 == 0) goto L_0x002f
            java.lang.Class<?> r0 = r5._class
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
            java.lang.Class<?> r1 = r5._class
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            r6 = 0
            if (r1 == r0) goto L_0x0031
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.A00(java.lang.String[], X.0aI, boolean, X.0o6, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.ser.std.MapSerializer");
    }

    private final void A01(Map<?, ?> map, AbstractC02640aV r11, AnonymousClass0a8 r12, JsonSerializer<Object> jsonSerializer) throws IOException, C02650aW {
        JsonSerializer<Object> jsonSerializer2 = this.A00;
        HashSet<String> hashSet = this.A06;
        AnonymousClass0o6 r5 = this.A05;
        boolean z = !r12._config.A06(AnonymousClass0a9.WRITE_NULL_MAP_VALUES);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                r12._nullKeySerializer.A0D(null, r11, r12);
            } else if (!z || value != null) {
                if (hashSet == null || !hashSet.contains(key)) {
                    jsonSerializer2.A0D(key, r11, r12);
                }
            }
            if (value == null) {
                r12.A0D(r11);
            } else if (r5 == null) {
                try {
                    jsonSerializer.A0D(value, r11, r12);
                } catch (Exception e) {
                    StdSerializer.A05(r12, e, map, "" + key);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                jsonSerializer.A0A(value, r11, r12, r5);
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5, AnonymousClass0o6 r6) throws IOException, C05910ld {
        Map<?, ?> map = (Map) obj;
        r6.A02(map, r4);
        if (!map.isEmpty()) {
            if (r5._config.A06(AnonymousClass0a9.ORDER_MAP_ENTRIES_BY_KEYS) && !(map instanceof SortedMap)) {
                map = new TreeMap((Map<? extends Object, ? extends Object>) map);
            }
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer != null) {
                A01(map, r4, r5, jsonSerializer);
            } else {
                A0G(map, r4, r5);
            }
        }
        r6.A05(map, r4);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Map<?, ?> map) {
        Map<?, ?> map2 = map;
        if (map2 == null || map2.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        Map<?, ?> map = (Map) obj;
        r4.A0F();
        if (!map.isEmpty()) {
            if (r5._config.A06(AnonymousClass0a9.ORDER_MAP_ENTRIES_BY_KEYS) && !(map instanceof SortedMap)) {
                map = new TreeMap((Map<? extends Object, ? extends Object>) map);
            }
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer != null) {
                A01(map, r4, r5, jsonSerializer);
            } else {
                A0G(map, r4, r5);
            }
        }
        r4.A0C();
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer A0E(AnonymousClass0o6 r2) {
        return new MapSerializer(this, r2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Map<?, ?> map) {
        if (map.size() != 1) {
            return false;
        }
        return true;
    }

    public final void A0G(Map<?, ?> map, AbstractC02640aV r14, AnonymousClass0a8 r15) throws IOException, C02650aW {
        JsonSerializer<Object> A0B;
        AbstractC06960oT A012;
        AnonymousClass0o6 r7 = this.A05;
        if (r7 != null) {
            JsonSerializer<Object> jsonSerializer = this.A00;
            HashSet<String> hashSet = this.A06;
            boolean z = !r15._config.A06(AnonymousClass0a9.WRITE_NULL_MAP_VALUES);
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer2 = null;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object value = entry.getValue();
                Object key = entry.getKey();
                if (key == null) {
                    r15._nullKeySerializer.A0D(null, r14, r15);
                } else if (!z || value != null) {
                    if (hashSet == null || !hashSet.contains(key)) {
                        jsonSerializer.A0D(key, r14, r15);
                    }
                }
                if (value == null) {
                    r15.A0D(r14);
                } else {
                    Class<?> cls2 = value.getClass();
                    if (cls2 != cls) {
                        AnonymousClass0aI r1 = this.A04;
                        if (r1.A0H()) {
                            jsonSerializer2 = r15.A08(r15.A04(r1, cls2), this.A03);
                        } else {
                            jsonSerializer2 = r15.A0B(cls2, this.A03);
                        }
                        cls = cls2;
                    }
                    try {
                        jsonSerializer2.A0A(value, r14, r15, r7);
                    } catch (Exception e) {
                        StdSerializer.A05(r15, e, map, "" + key);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
            return;
        }
        JsonSerializer<Object> jsonSerializer3 = this.A00;
        HashSet<String> hashSet2 = this.A06;
        boolean z2 = !r15._config.A06(AnonymousClass0a9.WRITE_NULL_MAP_VALUES);
        AbstractC06960oT r4 = this.A01;
        for (Map.Entry<?, ?> entry2 : map.entrySet()) {
            Object value2 = entry2.getValue();
            Object key2 = entry2.getKey();
            if (key2 == null) {
                r15._nullKeySerializer.A0D(null, r14, r15);
            } else if ((!z2 || value2 != null) && (hashSet2 == null || !hashSet2.contains(key2))) {
                jsonSerializer3.A0D(key2, r14, r15);
            }
            if (value2 == null) {
                r15.A0D(r14);
            } else {
                Class<?> cls3 = value2.getClass();
                JsonSerializer<Object> A002 = r4.A00(cls3);
                if (A002 == null) {
                    AnonymousClass0aI r12 = this.A04;
                    if (r12.A0H()) {
                        AnonymousClass0aI A042 = r15.A04(r12, cls3);
                        A0B = r15.A08(A042, this.A03);
                        A012 = r4.A01(A042._class, A0B);
                    } else {
                        A0B = r15.A0B(cls3, this.A03);
                        A012 = r4.A01(cls3, A0B);
                    }
                    C06940oR r13 = new C06940oR(A0B, A012);
                    AbstractC06960oT r0 = r13.A01;
                    if (r4 != r0) {
                        this.A01 = r0;
                    }
                    A002 = r13.A00;
                    r4 = this.A01;
                }
                A002.A0D(value2, r14, r15);
            }
        }
    }

    public MapSerializer(MapSerializer mapSerializer, AbstractC02580aL r4, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        super(Map.class, false);
        this.A06 = hashSet;
        this.A07 = mapSerializer.A07;
        this.A04 = mapSerializer.A04;
        this.A08 = mapSerializer.A08;
        this.A05 = mapSerializer.A05;
        this.A00 = jsonSerializer;
        this.A02 = jsonSerializer2;
        this.A01 = mapSerializer.A01;
        this.A03 = r4;
    }

    public MapSerializer(MapSerializer mapSerializer, AnonymousClass0o6 r4) {
        super(Map.class, false);
        this.A06 = mapSerializer.A06;
        this.A07 = mapSerializer.A07;
        this.A04 = mapSerializer.A04;
        this.A08 = mapSerializer.A08;
        this.A05 = r4;
        this.A00 = mapSerializer.A00;
        this.A02 = mapSerializer.A02;
        this.A01 = mapSerializer.A01;
        this.A03 = mapSerializer.A03;
    }

    public MapSerializer(HashSet<String> hashSet, AnonymousClass0aI r4, AnonymousClass0aI r5, boolean z, AnonymousClass0o6 r7, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this.A06 = hashSet;
        this.A07 = r4;
        this.A04 = r5;
        this.A08 = z;
        this.A05 = r7;
        this.A00 = jsonSerializer;
        this.A02 = jsonSerializer2;
        this.A01 = AnonymousClass0ZY.A00;
        this.A03 = null;
    }
}
