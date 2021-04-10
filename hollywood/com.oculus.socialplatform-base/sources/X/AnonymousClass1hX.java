package X;

import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* renamed from: X.1hX  reason: invalid class name */
public final class AnonymousClass1hX {
    public int A00;
    public final int A01;
    public final AnonymousClass1hZ<C09261ha, Object> A02;
    public final C09271hb A03;
    public final Map<Class<?>, NavigableMap<Integer, Integer>> A04;
    public final Map<Class<?>, AbstractC09321hi<?>> A05;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (r2 >= 2) goto L_0x002a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized <T> T A04(int r5, java.lang.Class<T> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map<java.lang.Class<?>, java.util.NavigableMap<java.lang.Integer, java.lang.Integer>> r0 = r4.A04     // Catch:{ all -> 0x0051 }
            java.lang.Object r1 = r0.get(r6)     // Catch:{ all -> 0x0051 }
            java.util.NavigableMap r1 = (java.util.NavigableMap) r1     // Catch:{ all -> 0x0051 }
            if (r1 != 0) goto L_0x0013
            java.util.TreeMap r1 = new java.util.TreeMap     // Catch:{ all -> 0x0051 }
            r1.<init>()     // Catch:{ all -> 0x0051 }
            r0.put(r6, r1)     // Catch:{ all -> 0x0051 }
        L_0x0013:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0051 }
            java.lang.Object r3 = r1.ceilingKey(r0)     // Catch:{ all -> 0x0051 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0037
            int r0 = r4.A00     // Catch:{ all -> 0x0051 }
            if (r0 == 0) goto L_0x002a
            int r2 = r4.A01     // Catch:{ all -> 0x0051 }
            int r2 = r2 / r0
            r1 = 2
            r0 = 0
            if (r2 < r1) goto L_0x002b
        L_0x002a:
            r0 = 1
        L_0x002b:
            if (r0 != 0) goto L_0x0035
            int r1 = r3.intValue()     // Catch:{ all -> 0x0051 }
            int r0 = r5 << 3
            if (r1 > r0) goto L_0x0037
        L_0x0035:
            r0 = 1
            goto L_0x0038
        L_0x0037:
            r0 = 0
        L_0x0038:
            if (r0 == 0) goto L_0x0045
            X.1hb r1 = r4.A03     // Catch:{ all -> 0x0051 }
            int r0 = r3.intValue()     // Catch:{ all -> 0x0051 }
            X.1ha r0 = r1.A00(r0, r6)     // Catch:{ all -> 0x0051 }
            goto L_0x004b
        L_0x0045:
            X.1hb r0 = r4.A03     // Catch:{ all -> 0x0051 }
            X.1ha r0 = r0.A00(r5, r6)     // Catch:{ all -> 0x0051 }
        L_0x004b:
            java.lang.Object r0 = A01(r4, r0, r6)     // Catch:{ all -> 0x0051 }
            monitor-exit(r4)
            return r0
        L_0x0051:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1hX.A04(int, java.lang.Class):java.lang.Object");
    }

    public final synchronized <T> void A05(T t) {
        Class<?> cls = t.getClass();
        AbstractC09321hi<T> A002 = A00(cls);
        int A3P = A002.A3P(t);
        int A3r = A002.A3r() * A3P;
        int i = this.A01;
        if (A3r <= (i >> 1)) {
            C09261ha A003 = this.A03.A00(A3P, cls);
            this.A02.A02(A003, t);
            Map<Class<?>, NavigableMap<Integer, Integer>> map = this.A04;
            NavigableMap<Integer, Integer> navigableMap = map.get(cls);
            if (navigableMap == null) {
                navigableMap = new TreeMap<>();
                map.put(cls, navigableMap);
            }
            Integer num = (Integer) navigableMap.get(Integer.valueOf(A003.A00));
            Integer valueOf = Integer.valueOf(A003.A00);
            int i2 = 1;
            if (num != null) {
                i2 = 1 + num.intValue();
            }
            navigableMap.put(valueOf, Integer.valueOf(i2));
            this.A00 += A3r;
            A03(this, i);
        }
    }

    private <T> AbstractC09321hi<T> A00(Class<T> cls) {
        Map<Class<?>, AbstractC09321hi<?>> map = this.A05;
        AbstractC09321hi<?> r0 = map.get(cls);
        if (r0 == null) {
            if (cls.equals(int[].class)) {
                r0 = new C09341hk();
            } else if (cls.equals(byte[].class)) {
                r0 = new C09331hj();
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A07("No array pool found for: ", cls.getSimpleName()));
            }
            map.put(cls, r0);
        }
        return r0;
    }

    private void A02(int i, Class<?> cls) {
        Map<Class<?>, NavigableMap<Integer, Integer>> map = this.A04;
        NavigableMap<Integer, Integer> navigableMap = map.get(cls);
        if (navigableMap == null) {
            navigableMap = new TreeMap<>();
            map.put(cls, navigableMap);
        }
        Integer valueOf = Integer.valueOf(i);
        Number number = (Number) navigableMap.get(valueOf);
        if (number != null) {
            int intValue = number.intValue();
            if (intValue == 1) {
                navigableMap.remove(valueOf);
            } else {
                navigableMap.put(valueOf, Integer.valueOf(intValue - 1));
            }
        } else {
            StringBuilder sb = new StringBuilder("Tried to decrement empty size, size: ");
            sb.append(i);
            sb.append(", this: ");
            sb.append(this);
            throw new NullPointerException(sb.toString());
        }
    }

    public static void A03(AnonymousClass1hX r4, int i) {
        while (r4.A00 > i) {
            Object A002 = r4.A02.A00();
            AnonymousClass1S2.A00(A002);
            Class<?> cls = A002.getClass();
            AbstractC09321hi A003 = r4.A00(cls);
            int i2 = r4.A00;
            int A3P = A003.A3P(A002);
            r4.A00 = i2 - (A003.A3r() * A3P);
            r4.A02(A3P, cls);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(LX/1ha;Ljava/lang/Class<TT;>;)TT; */
    public static Object A01(AnonymousClass1hX r5, C09261ha r6, Class cls) {
        AbstractC09321hi A002 = r5.A00(cls);
        Object A012 = r5.A02.A01(r6);
        if (A012 == null) {
            return A002.A6Q(r6.A00);
        }
        int i = r5.A00;
        int A3P = A002.A3P(A012);
        r5.A00 = i - (A002.A3r() * A3P);
        r5.A02(A3P, cls);
        return A012;
    }

    @VisibleForTesting
    public AnonymousClass1hX() {
        this.A02 = new AnonymousClass1hZ<>();
        this.A03 = new C09271hb();
        this.A04 = new HashMap();
        this.A05 = new HashMap();
        this.A01 = 4194304;
    }

    public AnonymousClass1hX(int i) {
        this.A02 = new AnonymousClass1hZ<>();
        this.A03 = new C09271hb();
        this.A04 = new HashMap();
        this.A05 = new HashMap();
        this.A01 = i;
    }
}
