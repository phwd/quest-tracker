package X;

import java.util.Map;

/* renamed from: X.141  reason: invalid class name */
public final class AnonymousClass141<K, V> implements Map.Entry<K, V> {
    public int A00;
    public AnonymousClass141<K, V> A01;
    public AnonymousClass141<K, V> A02;
    public AnonymousClass141<K, V> A03;
    public V A04;
    public AnonymousClass141<K, V> A05;
    public AnonymousClass141<K, V> A06;
    public final K A07;

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.util.Map.Entry
            r2 = 0
            if (r0 == 0) goto L_0x001c
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            K r1 = r3.A07
            if (r1 != 0) goto L_0x0024
            java.lang.Object r0 = r4.getKey()
            if (r0 != 0) goto L_0x001c
        L_0x0011:
            V r1 = r3.A04
            java.lang.Object r0 = r4.getValue()
            if (r1 != 0) goto L_0x001d
            if (r0 != 0) goto L_0x001c
        L_0x001b:
            r2 = 1
        L_0x001c:
            return r2
        L_0x001d:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x001c
            goto L_0x001b
        L_0x0024:
            java.lang.Object r0 = r4.getKey()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x001c
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass141.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode;
        K k = this.A07;
        int i = 0;
        if (k == null) {
            hashCode = 0;
        } else {
            hashCode = k.hashCode();
        }
        V v = this.A04;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        V v2 = this.A04;
        this.A04 = v;
        return v2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.A07);
        sb.append("=");
        sb.append((Object) this.A04);
        return sb.toString();
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.A07;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.A04;
    }

    public AnonymousClass141() {
        this.A07 = null;
        this.A03 = this;
        this.A01 = this;
    }

    public AnonymousClass141(AnonymousClass141<K, V> r2, K k, AnonymousClass141<K, V> r4, AnonymousClass141<K, V> r5) {
        this.A02 = r2;
        this.A07 = k;
        this.A00 = 1;
        this.A01 = r4;
        this.A03 = r5;
        r5.A01 = this;
        r4.A03 = this;
    }
}
