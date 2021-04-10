package X;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

public final class N1<K, V> extends C0118Qq<K, V> {
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        if (r1.A0E() == false) goto L_0x0043;
     */
    /* renamed from: A03 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.common.collect.ImmutableSetMultimap<K, V> A02() {
        /*
            r6 = this;
            java.util.Map<K, java.util.Collection<V>> r0 = r6.A00
            java.util.Set r1 = r0.entrySet()
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L_0x000f
            com.google.common.collect.EmptyImmutableSetMultimap r1 = com.google.common.collect.EmptyImmutableSetMultimap.A00
            return r1
        L_0x000f:
            int r0 = r1.size()
            com.google.common.collect.ImmutableMap$Builder r5 = new com.google.common.collect.ImmutableMap$Builder
            r5.<init>(r0)
            java.util.Iterator r4 = r1.iterator()
        L_0x001c:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x005a
            java.lang.Object r0 = r4.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r3 = r0.getKey()
            java.lang.Object r2 = r0.getValue()
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r0 = r2 instanceof com.google.common.collect.ImmutableSet
            if (r0 == 0) goto L_0x0050
            boolean r0 = r2 instanceof java.util.SortedSet
            if (r0 != 0) goto L_0x0050
            r1 = r2
            com.google.common.collect.ImmutableCollection r1 = (com.google.common.collect.ImmutableCollection) r1
            boolean r0 = r1.A0E()
            if (r0 != 0) goto L_0x0050
        L_0x0043:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x001c
            r5.put(r3, r1)
            r1.size()
            goto L_0x001c
        L_0x0050:
            java.lang.Object[] r1 = r2.toArray()
            int r0 = r1.length
            com.google.common.collect.ImmutableSet r1 = com.google.common.collect.ImmutableSet.A06(r0, r1)
            goto L_0x0043
        L_0x005a:
            com.google.common.collect.ImmutableMap r0 = r5.build()
            com.google.common.collect.ImmutableSetMultimap r1 = new com.google.common.collect.ImmutableSetMultimap
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: X.N1.A02():com.google.common.collect.ImmutableSetMultimap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.C0118Qq
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ C0118Qq A00(Object obj, Iterable iterable) {
        super.A00(obj, iterable);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.C0118Qq
    @CanIgnoreReturnValue
    public final C0118Qq A01(Object obj, Object obj2) {
        super.A01(obj, obj2);
        return this;
    }

    /* JADX WARN: Incorrect return type in method signature: (TK;Ljava/lang/Iterable<+TV;>;)LX/N1<TK;TV;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public final void A04(Object obj, Iterable iterable) {
        super.A00(obj, iterable);
    }
}
