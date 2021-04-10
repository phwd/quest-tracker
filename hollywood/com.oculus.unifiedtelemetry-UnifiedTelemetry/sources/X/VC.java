package X;

import java.util.Map;

public final class VC<K, V> implements Map.Entry<K, V> {
    public int A00;
    public VC<K, V> A01;
    public VC<K, V> A02;
    public VC<K, V> A03;
    public V A04;
    public VC<K, V> A05;
    public VC<K, V> A06;
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
        throw new UnsupportedOperationException("Method not decompiled: X.VC.equals(java.lang.Object):boolean");
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

    public VC() {
        this.A07 = null;
        this.A03 = this;
        this.A01 = this;
    }

    public VC(VC<K, V> vc, K k, VC<K, V> vc2, VC<K, V> vc3) {
        this.A02 = vc;
        this.A07 = k;
        this.A00 = 1;
        this.A01 = vc2;
        this.A03 = vc3;
        vc3.A01 = this;
        vc2.A03 = this;
    }
}
