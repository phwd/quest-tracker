package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public final class GeneralRange<T> implements Serializable {
    public final Comparator<? super T> comparator;
    public final boolean hasLowerBound;
    public final boolean hasUpperBound;
    public final BoundType lowerBoundType;
    @NullableDecl
    public final T lowerEndpoint;
    public final BoundType upperBoundType;
    @NullableDecl
    public final T upperEndpoint;

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        if (r9.upperBoundType == com.google.common.collect.BoundType.OPEN) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
        if (r9.lowerBoundType == com.google.common.collect.BoundType.OPEN) goto L_0x0015;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.common.collect.GeneralRange<T> A00(com.google.common.collect.GeneralRange<T> r9) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.GeneralRange.A00(com.google.common.collect.GeneralRange):com.google.common.collect.GeneralRange");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (r0 != 0) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(@org.checkerframework.checker.nullness.compatqual.NullableDecl T r6) {
        /*
            r5 = this;
            boolean r1 = r5.hasUpperBound
            r0 = 0
            if (r1 != 0) goto L_0x0006
            return r0
        L_0x0006:
            T r1 = r5.upperEndpoint
            java.util.Comparator<? super T> r0 = r5.comparator
            int r0 = r0.compare(r6, r1)
            r4 = 1
            r3 = 1
            if (r0 > 0) goto L_0x0016
            r3 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            com.google.common.collect.BoundType r1 = r5.upperBoundType
            com.google.common.collect.BoundType r0 = com.google.common.collect.BoundType.OPEN
            if (r1 == r0) goto L_0x001e
            r4 = 0
        L_0x001e:
            r2 = r2 & r4
            r2 = r2 | r3
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.GeneralRange.A01(java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (r0 != 0) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(@org.checkerframework.checker.nullness.compatqual.NullableDecl T r6) {
        /*
            r5 = this;
            boolean r1 = r5.hasLowerBound
            r0 = 0
            if (r1 != 0) goto L_0x0006
            return r0
        L_0x0006:
            T r1 = r5.lowerEndpoint
            java.util.Comparator<? super T> r0 = r5.comparator
            int r0 = r0.compare(r6, r1)
            r4 = 1
            r3 = 1
            if (r0 < 0) goto L_0x0016
            r3 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            com.google.common.collect.BoundType r1 = r5.lowerBoundType
            com.google.common.collect.BoundType r0 = com.google.common.collect.BoundType.OPEN
            if (r1 == r0) goto L_0x001e
            r4 = 0
        L_0x001e:
            r2 = r2 & r4
            r2 = r2 | r3
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.GeneralRange.A02(java.lang.Object):boolean");
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof GeneralRange)) {
            return false;
        }
        GeneralRange generalRange = (GeneralRange) obj;
        if (!this.comparator.equals(generalRange.comparator) || this.hasLowerBound != generalRange.hasLowerBound || this.hasUpperBound != generalRange.hasUpperBound || !this.lowerBoundType.equals(generalRange.lowerBoundType) || !this.upperBoundType.equals(generalRange.upperBoundType) || !Objects.equal(this.lowerEndpoint, generalRange.lowerEndpoint) || !Objects.equal(this.upperEndpoint, generalRange.upperEndpoint)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.comparator, this.lowerEndpoint, this.lowerBoundType, this.upperEndpoint, this.upperBoundType});
    }

    public final String toString() {
        Object obj;
        Object obj2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        BoundType boundType = this.lowerBoundType;
        BoundType boundType2 = BoundType.CLOSED;
        char c = '(';
        if (boundType == boundType2) {
            c = '[';
        }
        sb.append(c);
        if (this.hasLowerBound) {
            obj = this.lowerEndpoint;
        } else {
            obj = "-∞";
        }
        sb.append(obj);
        sb.append(',');
        if (this.hasUpperBound) {
            obj2 = this.upperEndpoint;
        } else {
            obj2 = "∞";
        }
        sb.append(obj2);
        char c2 = ')';
        if (this.upperBoundType == boundType2) {
            c2 = ']';
        }
        sb.append(c2);
        return sb.toString();
    }

    public GeneralRange(Comparator<? super T> comparator2, boolean z, @NullableDecl T t, BoundType boundType, boolean z2, @NullableDecl T t2, BoundType boundType2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            this.hasLowerBound = z;
            this.hasUpperBound = z2;
            this.lowerEndpoint = t;
            if (boundType != null) {
                this.lowerBoundType = boundType;
                this.upperEndpoint = t2;
                if (boundType2 != null) {
                    this.upperBoundType = boundType2;
                    if (z) {
                        comparator2.compare(t, t);
                    }
                    if (z2) {
                        comparator2.compare(t2, t2);
                    }
                    if (z && z2) {
                        int compare = comparator2.compare(t, t2);
                        boolean z3 = true;
                        Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", t, t2);
                        if (compare == 0) {
                            BoundType boundType3 = BoundType.OPEN;
                            Preconditions.checkArgument((boundType != boundType3) | (boundType2 == boundType3 ? false : z3));
                            return;
                        }
                        return;
                    }
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    public final boolean A03(@NullableDecl T t) {
        if (A02(t) || A01(t)) {
            return false;
        }
        return true;
    }
}
