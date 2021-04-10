package com.oculus.extras;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;

public class Extras {
    public final ImmutableMap<String, String> mData;

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Extras)) {
            return false;
        }
        return Objects.equal(this.mData, ((Extras) obj).mData);
    }

    public final long A00(String str, long j) {
        if (this.mData.containsKey(str)) {
            try {
                return Long.parseLong(this.mData.get(str));
            } catch (NumberFormatException unused) {
            }
        }
        return j;
    }

    public final String A01(String str, String str2) {
        if (this.mData.containsKey(str)) {
            return this.mData.get(str);
        }
        return str2;
    }

    public final int hashCode() {
        return this.mData.hashCode();
    }

    public String toString() {
        return this.mData.toString();
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: com.google.common.collect.ImmutableMap<java.lang.Object, java.lang.Object>, com.google.common.collect.ImmutableMap<java.lang.String, java.lang.String> */
    public Extras() {
        this.mData = RegularImmutableMap.A03;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r1.A08() == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Extras(java.util.Map<java.lang.String, java.lang.String> r4) {
        /*
            r3 = this;
            r3.<init>()
            boolean r0 = r4 instanceof com.google.common.collect.ImmutableMap
            if (r0 == 0) goto L_0x0017
            boolean r0 = r4 instanceof java.util.SortedMap
            if (r0 != 0) goto L_0x0017
            r1 = r4
            com.google.common.collect.ImmutableMap r1 = (com.google.common.collect.ImmutableMap) r1
            boolean r0 = r1.A08()
            if (r0 != 0) goto L_0x0017
        L_0x0014:
            r3.mData = r1
            return
        L_0x0017:
            java.util.Set r2 = r4.entrySet()
            boolean r0 = r2 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0030
            int r1 = r2.size()
        L_0x0023:
            com.google.common.collect.ImmutableMap$Builder r0 = new com.google.common.collect.ImmutableMap$Builder
            r0.<init>(r1)
            r0.A00(r2)
            com.google.common.collect.ImmutableMap r1 = r0.build()
            goto L_0x0014
        L_0x0030:
            r1 = 4
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.extras.Extras.<init>(java.util.Map):void");
    }
}
