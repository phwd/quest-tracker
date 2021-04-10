package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
@Beta
public final class ElementOrder<T> {
    @NullableDecl
    private final Comparator<T> comparator;
    private final Type type;

    public enum Type {
        UNORDERED,
        INSERTION,
        SORTED
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.graph.ElementOrder<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }

    private ElementOrder(Type type2, @NullableDecl Comparator<T> comparator2) {
        this.type = (Type) Preconditions.checkNotNull(type2);
        this.comparator = comparator2;
        Preconditions.checkState((type2 == Type.SORTED) != (comparator2 != null) ? false : true);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, null);
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator2) {
        return new ElementOrder<>(Type.SORTED, comparator2);
    }

    public Type type() {
        return this.type;
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        if (this.type != elementOrder.type || !Objects.equal(this.comparator, elementOrder.comparator)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.comparator);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("type", this.type);
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            add.add("comparator", comparator2);
        }
        return add.toString();
    }

    /* renamed from: com.google.common.graph.ElementOrder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type = new int[Type.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.google.common.graph.ElementOrder$Type[] r0 = com.google.common.graph.ElementOrder.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.common.graph.ElementOrder.AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type = r0
                int[] r0 = com.google.common.graph.ElementOrder.AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.common.graph.ElementOrder$Type r1 = com.google.common.graph.ElementOrder.Type.UNORDERED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.google.common.graph.ElementOrder.AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.common.graph.ElementOrder$Type r1 = com.google.common.graph.ElementOrder.Type.INSERTION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.google.common.graph.ElementOrder.AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.common.graph.ElementOrder$Type r1 = com.google.common.graph.ElementOrder.Type.SORTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.ElementOrder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public <K extends T, V> Map<K, V> createMap(int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$common$graph$ElementOrder$Type[this.type.ordinal()];
        if (i2 == 1) {
            return Maps.newHashMapWithExpectedSize(i);
        }
        if (i2 == 2) {
            return Maps.newLinkedHashMapWithExpectedSize(i);
        }
        if (i2 == 3) {
            return Maps.newTreeMap(comparator());
        }
        throw new AssertionError();
    }
}
