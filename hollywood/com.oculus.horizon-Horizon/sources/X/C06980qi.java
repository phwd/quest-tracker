package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible(emulated = true)
/* renamed from: X.0qi  reason: invalid class name and case insensitive filesystem */
public final class C06980qi {
    public int A00 = -1;
    public int A01 = -1;
    @MonotonicNonNullDecl
    public Equivalence<Object> A02;
    @MonotonicNonNullDecl
    public MapMakerInternalMap.Strength A03;
    @MonotonicNonNullDecl
    public MapMakerInternalMap.Strength A04;
    public boolean A05;

    public final <K, V> ConcurrentMap<K, V> A00() {
        if (!this.A05) {
            int i = this.A01;
            if (i == -1) {
                i = 16;
            }
            int i2 = this.A00;
            if (i2 == -1) {
                i2 = 4;
            }
            return new ConcurrentHashMap(i, 0.75f, i2);
        }
        MapMakerInternalMap.Strength strength = this.A03;
        MapMakerInternalMap.Strength strength2 = MapMakerInternalMap.Strength.STRONG;
        if (MoreObjects.firstNonNull(strength, strength2) == strength2 && MoreObjects.firstNonNull(this.A04, strength2) == strength2) {
            return new MapMakerInternalMap(this, C03580dg.A00);
        }
        if (MoreObjects.firstNonNull(strength, strength2) == strength2 && MoreObjects.firstNonNull(this.A04, strength2) == MapMakerInternalMap.Strength.WEAK) {
            return new MapMakerInternalMap(this, AnonymousClass0de.A00);
        }
        Object firstNonNull = MoreObjects.firstNonNull(strength, strength2);
        MapMakerInternalMap.Strength strength3 = MapMakerInternalMap.Strength.WEAK;
        if (firstNonNull == strength3 && MoreObjects.firstNonNull(this.A04, strength2) == strength2) {
            return new MapMakerInternalMap(this, C03550da.A00);
        }
        if (MoreObjects.firstNonNull(strength, strength2) == strength3 && MoreObjects.firstNonNull(this.A04, strength2) == strength3) {
            return new MapMakerInternalMap(this, AnonymousClass0dY.A00);
        }
        throw new AssertionError();
    }

    public final void A01(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.A03;
        boolean z = false;
        if (strength2 == null) {
            z = true;
        }
        Preconditions.checkState(z, "Key strength was already set to %s", strength2);
        if (strength != null) {
            this.A03 = strength;
            if (strength != MapMakerInternalMap.Strength.STRONG) {
                this.A05 = true;
                return;
            }
            return;
        }
        throw null;
    }

    public final String toString() {
        MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper(getClass().getSimpleName());
        int i = this.A01;
        if (i != -1) {
            toStringHelper.add("initialCapacity", i);
        }
        int i2 = this.A00;
        if (i2 != -1) {
            toStringHelper.add("concurrencyLevel", i2);
        }
        MapMakerInternalMap.Strength strength = this.A03;
        if (strength != null) {
            toStringHelper.add("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.A04;
        if (strength2 != null) {
            toStringHelper.add("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.A02 != null) {
            MoreObjects.ToStringHelper.ValueHolder valueHolder = new MoreObjects.ToStringHelper.ValueHolder();
            toStringHelper.holderTail.next = valueHolder;
            toStringHelper.holderTail = valueHolder;
            valueHolder.value = "keyEquivalence";
        }
        return toStringHelper.toString();
    }
}
