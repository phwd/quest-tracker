package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Maps {

    /* access modifiers changed from: private */
    public enum EntryFunction implements Function<Map.Entry<?, ?>, Object> {
        KEY {
            @NullableDecl
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE {
            @NullableDecl
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        }
    }

    public interface EntryTransformer<K, V1, V2> {
        V2 transformEntry(@NullableDecl K k, @NullableDecl V1 v1);
    }

    private Maps() {
    }

    static <K> Function<Map.Entry<K, ?>, K> keyFunction() {
        return EntryFunction.KEY;
    }

    static <V> Function<Map.Entry<?, V>, V> valueFunction() {
        return EntryFunction.VALUE;
    }

    static <K, V> Iterator<K> keyIterator(Iterator<Map.Entry<K, V>> entryIterator) {
        return new TransformedIterator<Map.Entry<K, V>, K>(entryIterator) {
            /* class com.google.common.collect.Maps.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            public K transform(Map.Entry<K, V> entry) {
                return entry.getKey();
            }
        };
    }

    static <K, V> Iterator<V> valueIterator(Iterator<Map.Entry<K, V>> entryIterator) {
        return new TransformedIterator<Map.Entry<K, V>, V>(entryIterator) {
            /* class com.google.common.collect.Maps.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            public V transform(Map.Entry<K, V> entry) {
                return entry.getValue();
            }
        };
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.EnumMap */
    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <K extends Enum<K>, V> ImmutableMap<K, V> immutableEnumMap(Map<K, ? extends V> map) {
        if (map instanceof ImmutableEnumMap) {
            return (ImmutableEnumMap) map;
        }
        Iterator<? extends Map.Entry<K, ? extends V>> entryItr = map.entrySet().iterator();
        if (!entryItr.hasNext()) {
            return ImmutableMap.of();
        }
        Map.Entry<K, ? extends V> entry1 = entryItr.next();
        K key1 = entry1.getKey();
        Object value = entry1.getValue();
        CollectPreconditions.checkEntryNotNull(key1, value);
        EnumMap enumMap = new EnumMap(key1.getDeclaringClass());
        enumMap.put((Enum) key1, value);
        while (entryItr.hasNext()) {
            Map.Entry<K, ? extends V> entry = entryItr.next();
            K key = entry.getKey();
            Object value2 = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value2);
            enumMap.put((Enum) key, value2);
        }
        return ImmutableEnumMap.asImmutable(enumMap);
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return new HashMap<>(capacity(expectedSize));
    }

    static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
            return expectedSize + 1;
        } else if (expectedSize < 1073741824) {
            return (int) ((((float) expectedSize) / 0.75f) + 1.0f);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return new LinkedHashMap<>(capacity(expectedSize));
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap();
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
        return new TreeMap<>((SortedMap) map);
    }

    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(@NullableDecl Comparator<C> comparator) {
        return new TreeMap<>(comparator);
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> type) {
        return new EnumMap<>((Class) Preconditions.checkNotNull(type));
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right) {
        if (left instanceof SortedMap) {
            return difference((SortedMap) ((SortedMap) left), (Map) right);
        }
        return difference(left, right, Equivalence.equals());
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right, Equivalence<? super V> valueEquivalence) {
        Preconditions.checkNotNull(valueEquivalence);
        Map<K, V> onlyOnLeft = newLinkedHashMap();
        Map<K, V> onlyOnRight = new LinkedHashMap<>(right);
        Map<K, V> onBoth = newLinkedHashMap();
        Map<K, MapDifference.ValueDifference<V>> differences = newLinkedHashMap();
        doDifference(left, right, valueEquivalence, onlyOnLeft, onlyOnRight, onBoth, differences);
        return new MapDifferenceImpl(onlyOnLeft, onlyOnRight, onBoth, differences);
    }

    public static <K, V> SortedMapDifference<K, V> difference(SortedMap<K, ? extends V> left, Map<? extends K, ? extends V> right) {
        Preconditions.checkNotNull(left);
        Preconditions.checkNotNull(right);
        Comparator<? super K> comparator = orNaturalOrder(left.comparator());
        SortedMap<K, V> onlyOnLeft = newTreeMap(comparator);
        SortedMap<K, V> onlyOnRight = newTreeMap(comparator);
        onlyOnRight.putAll(right);
        SortedMap<K, V> onBoth = newTreeMap(comparator);
        SortedMap<K, MapDifference.ValueDifference<V>> differences = newTreeMap(comparator);
        doDifference(left, right, Equivalence.equals(), onlyOnLeft, onlyOnRight, onBoth, differences);
        return new SortedMapDifferenceImpl(onlyOnLeft, onlyOnRight, onBoth, differences);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.Map<K, V> */
    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: java.util.Map<K, V> */
    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: java.util.Map<K, com.google.common.collect.MapDifference$ValueDifference<V>> */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: ? super V */
    /* JADX WARN: Multi-variable type inference failed */
    private static <K, V> void doDifference(Map<? extends K, ? extends V> left, Map<? extends K, ? extends V> right, Equivalence<? super V> valueEquivalence, Map<K, V> onlyOnLeft, Map<K, V> onlyOnRight, Map<K, V> onBoth, Map<K, MapDifference.ValueDifference<V>> differences) {
        for (Map.Entry<? extends K, ? extends V> entry : left.entrySet()) {
            Object key = entry.getKey();
            Object obj = (Object) entry.getValue();
            if (right.containsKey(key)) {
                V rightValue = onlyOnRight.remove(key);
                if (valueEquivalence.equivalent(obj, rightValue)) {
                    onBoth.put(key, obj);
                } else {
                    differences.put(key, ValueDifferenceImpl.create(obj, rightValue));
                }
            } else {
                onlyOnLeft.put(key, obj);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <K, V> Map<K, V> unmodifiableMap(Map<K, ? extends V> map) {
        if (map instanceof SortedMap) {
            return Collections.unmodifiableSortedMap((SortedMap) map);
        }
        return Collections.unmodifiableMap(map);
    }

    /* access modifiers changed from: package-private */
    public static class MapDifferenceImpl<K, V> implements MapDifference<K, V> {
        final Map<K, MapDifference.ValueDifference<V>> differences;
        final Map<K, V> onBoth;
        final Map<K, V> onlyOnLeft;
        final Map<K, V> onlyOnRight;

        MapDifferenceImpl(Map<K, V> onlyOnLeft2, Map<K, V> onlyOnRight2, Map<K, V> onBoth2, Map<K, MapDifference.ValueDifference<V>> differences2) {
            this.onlyOnLeft = Maps.unmodifiableMap(onlyOnLeft2);
            this.onlyOnRight = Maps.unmodifiableMap(onlyOnRight2);
            this.onBoth = Maps.unmodifiableMap(onBoth2);
            this.differences = Maps.unmodifiableMap(differences2);
        }

        @Override // com.google.common.collect.MapDifference
        public boolean areEqual() {
            return this.onlyOnLeft.isEmpty() && this.onlyOnRight.isEmpty() && this.differences.isEmpty();
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnLeft() {
            return this.onlyOnLeft;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesOnlyOnRight() {
            return this.onlyOnRight;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, V> entriesInCommon() {
            return this.onBoth;
        }

        @Override // com.google.common.collect.MapDifference
        public Map<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return this.differences;
        }

        @Override // com.google.common.collect.MapDifference
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof MapDifference)) {
                return false;
            }
            MapDifference<?, ?> other = (MapDifference) object;
            return entriesOnlyOnLeft().equals(other.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(other.entriesOnlyOnRight()) && entriesInCommon().equals(other.entriesInCommon()) && entriesDiffering().equals(other.entriesDiffering());
        }

        @Override // com.google.common.collect.MapDifference
        public int hashCode() {
            return Objects.hashCode(entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering());
        }

        public String toString() {
            if (areEqual()) {
                return "equal";
            }
            StringBuilder result = new StringBuilder("not equal");
            if (!this.onlyOnLeft.isEmpty()) {
                result.append(": only on left=").append(this.onlyOnLeft);
            }
            if (!this.onlyOnRight.isEmpty()) {
                result.append(": only on right=").append(this.onlyOnRight);
            }
            if (!this.differences.isEmpty()) {
                result.append(": value differences=").append(this.differences);
            }
            return result.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public static class ValueDifferenceImpl<V> implements MapDifference.ValueDifference<V> {
        @NullableDecl
        private final V left;
        @NullableDecl
        private final V right;

        static <V> MapDifference.ValueDifference<V> create(@NullableDecl V left2, @NullableDecl V right2) {
            return new ValueDifferenceImpl(left2, right2);
        }

        private ValueDifferenceImpl(@NullableDecl V left2, @NullableDecl V right2) {
            this.left = left2;
            this.right = right2;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V leftValue() {
            return this.left;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public V rightValue() {
            return this.right;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public boolean equals(@NullableDecl Object object) {
            if (!(object instanceof MapDifference.ValueDifference)) {
                return false;
            }
            MapDifference.ValueDifference<?> that = (MapDifference.ValueDifference) object;
            if (!Objects.equal(this.left, that.leftValue()) || !Objects.equal(this.right, that.rightValue())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.MapDifference.ValueDifference
        public int hashCode() {
            return Objects.hashCode(this.left, this.right);
        }

        public String toString() {
            return "(" + ((Object) this.left) + ", " + ((Object) this.right) + ")";
        }
    }

    /* access modifiers changed from: package-private */
    public static class SortedMapDifferenceImpl<K, V> extends MapDifferenceImpl<K, V> implements SortedMapDifference<K, V> {
        SortedMapDifferenceImpl(SortedMap<K, V> onlyOnLeft, SortedMap<K, V> onlyOnRight, SortedMap<K, V> onBoth, SortedMap<K, MapDifference.ValueDifference<V>> differences) {
            super(onlyOnLeft, onlyOnRight, onBoth, differences);
        }

        @Override // com.google.common.collect.MapDifference, com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.SortedMapDifference
        public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return (SortedMap) super.entriesDiffering();
        }

        @Override // com.google.common.collect.MapDifference, com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.SortedMapDifference
        public SortedMap<K, V> entriesInCommon() {
            return (SortedMap) super.entriesInCommon();
        }

        @Override // com.google.common.collect.MapDifference, com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.SortedMapDifference
        public SortedMap<K, V> entriesOnlyOnLeft() {
            return (SortedMap) super.entriesOnlyOnLeft();
        }

        @Override // com.google.common.collect.MapDifference, com.google.common.collect.Maps.MapDifferenceImpl, com.google.common.collect.SortedMapDifference
        public SortedMap<K, V> entriesOnlyOnRight() {
            return (SortedMap) super.entriesOnlyOnRight();
        }
    }

    static <E> Comparator<? super E> orNaturalOrder(@NullableDecl Comparator<? super E> comparator) {
        return comparator != null ? comparator : Ordering.natural();
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new AsMapView(set, function);
    }

    public static <K, V> SortedMap<K, V> asMap(SortedSet<K> set, Function<? super K, V> function) {
        return new SortedAsMapView(set, function);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> asMap(NavigableSet<K> set, Function<? super K, V> function) {
        return new NavigableAsMapView(set, function);
    }

    /* access modifiers changed from: private */
    public static class AsMapView<K, V> extends ViewCachingAbstractMap<K, V> {
        final Function<? super K, V> function;
        private final Set<K> set;

        /* access modifiers changed from: package-private */
        public Set<K> backingSet() {
            return this.set;
        }

        AsMapView(Set<K> set2, Function<? super K, V> function2) {
            this.set = (Set) Preconditions.checkNotNull(set2);
            this.function = (Function) Preconditions.checkNotNull(function2);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return Maps.removeOnlySet(backingSet());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return Collections2.transform(this.set, this.function);
        }

        public int size() {
            return backingSet().size();
        }

        public boolean containsKey(@NullableDecl Object key) {
            return backingSet().contains(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object key) {
            if (Collections2.safeContains(backingSet(), key)) {
                return this.function.apply(key);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(@NullableDecl Object key) {
            if (backingSet().remove(key)) {
                return this.function.apply(key);
            }
            return null;
        }

        public void clear() {
            backingSet().clear();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() {
                /* class com.google.common.collect.Maps.AsMapView.AnonymousClass1EntrySetImpl */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return AsMapView.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, V>> iterator() {
                    return Maps.asMapEntryIterator(AsMapView.this.backingSet(), AsMapView.this.function);
                }
            };
        }
    }

    static <K, V> Iterator<Map.Entry<K, V>> asMapEntryIterator(Set<K> set, final Function<? super K, V> function) {
        return new TransformedIterator<K, Map.Entry<K, V>>(set.iterator()) {
            /* class com.google.common.collect.Maps.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.TransformedIterator
            public Map.Entry<K, V> transform(K key) {
                return Maps.immutableEntry(key, function.apply(key));
            }
        };
    }

    /* access modifiers changed from: private */
    public static class SortedAsMapView<K, V> extends AsMapView<K, V> implements SortedMap<K, V> {
        SortedAsMapView(SortedSet<K> set, Function<? super K, V> function) {
            super(set, function);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.AsMapView
        public SortedSet<K> backingSet() {
            return (SortedSet) super.backingSet();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return backingSet().comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.SortedMap
        public Set<K> keySet() {
            return Maps.removeOnlySortedSet(backingSet());
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return Maps.asMap((SortedSet) backingSet().subSet(fromKey, toKey), this.function);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return Maps.asMap((SortedSet) backingSet().headSet(toKey), this.function);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return Maps.asMap((SortedSet) backingSet().tailSet(fromKey), this.function);
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return backingSet().first();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return backingSet().last();
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static final class NavigableAsMapView<K, V> extends AbstractNavigableMap<K, V> {
        private final Function<? super K, V> function;
        private final NavigableSet<K> set;

        NavigableAsMapView(NavigableSet<K> ks, Function<? super K, V> vFunction) {
            this.set = (NavigableSet) Preconditions.checkNotNull(ks);
            this.function = (Function) Preconditions.checkNotNull(vFunction);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Maps.asMap((NavigableSet) this.set.subSet(fromKey, fromInclusive, toKey, toInclusive), (Function) this.function);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return Maps.asMap((NavigableSet) this.set.headSet(toKey, inclusive), (Function) this.function);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return Maps.asMap((NavigableSet) this.set.tailSet(fromKey, inclusive), (Function) this.function);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.set.comparator();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.AbstractNavigableMap
        @NullableDecl
        public V get(@NullableDecl Object key) {
            if (Collections2.safeContains(this.set, key)) {
                return this.function.apply(key);
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public void clear() {
            this.set.clear();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Maps.asMapEntryIterator(this.set, this.function);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return descendingMap().entrySet().iterator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Maps.removeOnlyNavigableSet(this.set);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public int size() {
            return this.set.size();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.asMap((NavigableSet) this.set.descendingSet(), (Function) this.function);
        }
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> removeOnlySet(final Set<E> set) {
        return new ForwardingSet<E>() {
            /* class com.google.common.collect.Maps.AnonymousClass4 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public Set<E> delegate() {
                return set;
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> removeOnlySortedSet(final SortedSet<E> set) {
        return new ForwardingSortedSet<E>() {
            /* class com.google.common.collect.Maps.AnonymousClass5 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public SortedSet<E> delegate() {
                return set;
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
            public SortedSet<E> headSet(E toElement) {
                return Maps.removeOnlySortedSet(super.headSet(toElement));
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
            public SortedSet<E> subSet(E fromElement, E toElement) {
                return Maps.removeOnlySortedSet(super.subSet(fromElement, toElement));
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
            public SortedSet<E> tailSet(E fromElement) {
                return Maps.removeOnlySortedSet(super.tailSet(fromElement));
            }
        };
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static <E> NavigableSet<E> removeOnlyNavigableSet(final NavigableSet<E> set) {
        return new ForwardingNavigableSet<E>() {
            /* class com.google.common.collect.Maps.AnonymousClass6 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public NavigableSet<E> delegate() {
                return set;
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
            public SortedSet<E> headSet(E toElement) {
                return Maps.removeOnlySortedSet(super.headSet(toElement));
            }

            @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
            public NavigableSet<E> headSet(E toElement, boolean inclusive) {
                return Maps.removeOnlyNavigableSet(super.headSet(toElement, inclusive));
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
            public SortedSet<E> subSet(E fromElement, E toElement) {
                return Maps.removeOnlySortedSet(super.subSet(fromElement, toElement));
            }

            @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
            public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
                return Maps.removeOnlyNavigableSet(super.subSet(fromElement, fromInclusive, toElement, toInclusive));
            }

            @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet, java.util.NavigableSet
            public SortedSet<E> tailSet(E fromElement) {
                return Maps.removeOnlySortedSet(super.tailSet(fromElement));
            }

            @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
            public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
                return Maps.removeOnlyNavigableSet(super.tailSet(fromElement, inclusive));
            }

            @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
            public NavigableSet<E> descendingSet() {
                return Maps.removeOnlyNavigableSet(super.descendingSet());
            }
        };
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterable<K> keys, Function<? super K, V> valueFunction) {
        return toMap(keys.iterator(), valueFunction);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterator<K> keys, Function<? super K, V> valueFunction) {
        Preconditions.checkNotNull(valueFunction);
        Map<K, V> builder = newLinkedHashMap();
        while (keys.hasNext()) {
            K key = keys.next();
            builder.put(key, valueFunction.apply(key));
        }
        return ImmutableMap.copyOf(builder);
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterable<V> values, Function<? super V, K> keyFunction) {
        return uniqueIndex(values.iterator(), keyFunction);
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> values, Function<? super V, K> keyFunction) {
        Preconditions.checkNotNull(keyFunction);
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        while (values.hasNext()) {
            V value = values.next();
            builder.put(keyFunction.apply(value), value);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException duplicateKeys) {
            throw new IllegalArgumentException(duplicateKeys.getMessage() + ". To index multiple values under a key, use Multimaps.index.");
        }
    }

    @GwtIncompatible
    public static ImmutableMap<String, String> fromProperties(Properties properties) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            builder.put(key, properties.getProperty(key));
        }
        return builder.build();
    }

    @GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
    public static <K, V> Map.Entry<K, V> immutableEntry(@NullableDecl K key, @NullableDecl V value) {
        return new ImmutableEntry(key, value);
    }

    static <K, V> Set<Map.Entry<K, V>> unmodifiableEntrySet(Set<Map.Entry<K, V>> entrySet) {
        return new UnmodifiableEntrySet(Collections.unmodifiableSet(entrySet));
    }

    static <K, V> Map.Entry<K, V> unmodifiableEntry(final Map.Entry<? extends K, ? extends V> entry) {
        Preconditions.checkNotNull(entry);
        return new AbstractMapEntry<K, V>() {
            /* class com.google.common.collect.Maps.AnonymousClass7 */

            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            public K getKey() {
                return (K) entry.getKey();
            }

            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            public V getValue() {
                return (V) entry.getValue();
            }
        };
    }

    static <K, V> UnmodifiableIterator<Map.Entry<K, V>> unmodifiableEntryIterator(final Iterator<Map.Entry<K, V>> entryIterator) {
        return new UnmodifiableIterator<Map.Entry<K, V>>() {
            /* class com.google.common.collect.Maps.AnonymousClass8 */

            public boolean hasNext() {
                return entryIterator.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return Maps.unmodifiableEntry((Map.Entry) entryIterator.next());
            }
        };
    }

    static class UnmodifiableEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        private final Collection<Map.Entry<K, V>> entries;

        UnmodifiableEntries(Collection<Map.Entry<K, V>> entries2) {
            this.entries = entries2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return Maps.unmodifiableEntryIterator(this.entries.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }
    }

    static class UnmodifiableEntrySet<K, V> extends UnmodifiableEntries<K, V> implements Set<Map.Entry<K, V>> {
        UnmodifiableEntrySet(Set<Map.Entry<K, V>> entries) {
            super(entries);
        }

        public boolean equals(@NullableDecl Object object) {
            return Sets.equalsImpl(this, object);
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    @Beta
    public static <A, B> Converter<A, B> asConverter(BiMap<A, B> bimap) {
        return new BiMapConverter(bimap);
    }

    private static final class BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final BiMap<A, B> bimap;

        BiMapConverter(BiMap<A, B> bimap2) {
            this.bimap = (BiMap) Preconditions.checkNotNull(bimap2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return (B) convert(this.bimap, a);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) convert(this.bimap.inverse(), b);
        }

        private static <X, Y> Y convert(BiMap<X, Y> bimap2, X input) {
            Y output = bimap2.get(input);
            Preconditions.checkArgument(output != null, "No non-null mapping present for input: %s", input);
            return output;
        }

        @Override // com.google.common.base.Function, com.google.common.base.Converter
        public boolean equals(@NullableDecl Object object) {
            if (object instanceof BiMapConverter) {
                return this.bimap.equals(((BiMapConverter) object).bimap);
            }
            return false;
        }

        public int hashCode() {
            return this.bimap.hashCode();
        }

        public String toString() {
            return "Maps.asConverter(" + this.bimap + ")";
        }
    }

    public static <K, V> BiMap<K, V> synchronizedBiMap(BiMap<K, V> bimap) {
        return Synchronized.biMap(bimap, null);
    }

    public static <K, V> BiMap<K, V> unmodifiableBiMap(BiMap<? extends K, ? extends V> bimap) {
        return new UnmodifiableBiMap(bimap, null);
    }

    private static class UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final BiMap<? extends K, ? extends V> delegate;
        @RetainedWith
        @MonotonicNonNullDecl
        BiMap<V, K> inverse;
        final Map<K, V> unmodifiableMap;
        @MonotonicNonNullDecl
        transient Set<V> values;

        UnmodifiableBiMap(BiMap<? extends K, ? extends V> delegate2, @NullableDecl BiMap<V, K> inverse2) {
            this.unmodifiableMap = Collections.unmodifiableMap(delegate2);
            this.delegate = delegate2;
            this.inverse = inverse2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<K, V> delegate() {
            return this.unmodifiableMap;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> result = this.inverse;
            if (result != null) {
                return result;
            }
            UnmodifiableBiMap unmodifiableBiMap = new UnmodifiableBiMap(this.delegate.inverse(), this);
            this.inverse = unmodifiableBiMap;
            return unmodifiableBiMap;
        }

        @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, java.util.Map
        public Set<V> values() {
            Set<V> result = this.values;
            if (result != null) {
                return result;
            }
            Set<V> result2 = Collections.unmodifiableSet(this.delegate.values());
            this.values = result2;
            return result2;
        }
    }

    public static <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> fromMap, Function<? super V1, V2> function) {
        return transformEntries(fromMap, asEntryTransformer(function));
    }

    public static <K, V1, V2> SortedMap<K, V2> transformValues(SortedMap<K, V1> fromMap, Function<? super V1, V2> function) {
        return transformEntries((SortedMap) fromMap, asEntryTransformer(function));
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformValues(NavigableMap<K, V1> fromMap, Function<? super V1, V2> function) {
        return transformEntries((NavigableMap) fromMap, asEntryTransformer(function));
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesMap(fromMap, transformer);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformEntries(SortedMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesSortedMap(fromMap, transformer);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformEntries(NavigableMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
        return new TransformedEntriesNavigableMap(fromMap, transformer);
    }

    static <K, V1, V2> EntryTransformer<K, V1, V2> asEntryTransformer(final Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return new EntryTransformer<K, V1, V2>() {
            /* class com.google.common.collect.Maps.AnonymousClass9 */

            @Override // com.google.common.collect.Maps.EntryTransformer
            public V2 transformEntry(K k, V1 value) {
                return (V2) Function.this.apply(value);
            }
        };
    }

    static <K, V1, V2> Function<V1, V2> asValueToValueFunction(final EntryTransformer<? super K, V1, V2> transformer, final K key) {
        Preconditions.checkNotNull(transformer);
        return new Function<V1, V2>() {
            /* class com.google.common.collect.Maps.AnonymousClass10 */

            @Override // com.google.common.base.Function
            public V2 apply(@NullableDecl V1 v1) {
                return (V2) EntryTransformer.this.transformEntry(key, v1);
            }
        };
    }

    static <K, V1, V2> Function<Map.Entry<K, V1>, V2> asEntryToValueFunction(final EntryTransformer<? super K, ? super V1, V2> transformer) {
        Preconditions.checkNotNull(transformer);
        return new Function<Map.Entry<K, V1>, V2>() {
            /* class com.google.common.collect.Maps.AnonymousClass11 */

            public V2 apply(Map.Entry<K, V1> entry) {
                return (V2) EntryTransformer.this.transformEntry(entry.getKey(), entry.getValue());
            }
        };
    }

    static <V2, K, V1> Map.Entry<K, V2> transformEntry(final EntryTransformer<? super K, ? super V1, V2> transformer, final Map.Entry<K, V1> entry) {
        Preconditions.checkNotNull(transformer);
        Preconditions.checkNotNull(entry);
        return new AbstractMapEntry<K, V2>() {
            /* class com.google.common.collect.Maps.AnonymousClass12 */

            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            public K getKey() {
                return (K) entry.getKey();
            }

            @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
            public V2 getValue() {
                return (V2) transformer.transformEntry(entry.getKey(), entry.getValue());
            }
        };
    }

    static <K, V1, V2> Function<Map.Entry<K, V1>, Map.Entry<K, V2>> asEntryToEntryFunction(final EntryTransformer<? super K, ? super V1, V2> transformer) {
        Preconditions.checkNotNull(transformer);
        return new Function<Map.Entry<K, V1>, Map.Entry<K, V2>>() {
            /* class com.google.common.collect.Maps.AnonymousClass13 */

            public Map.Entry<K, V2> apply(Map.Entry<K, V1> entry) {
                return Maps.transformEntry(EntryTransformer.this, entry);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public static class TransformedEntriesMap<K, V1, V2> extends IteratorBasedAbstractMap<K, V2> {
        final Map<K, V1> fromMap;
        final EntryTransformer<? super K, ? super V1, V2> transformer;

        TransformedEntriesMap(Map<K, V1> fromMap2, EntryTransformer<? super K, ? super V1, V2> transformer2) {
            this.fromMap = (Map) Preconditions.checkNotNull(fromMap2);
            this.transformer = (EntryTransformer) Preconditions.checkNotNull(transformer2);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public int size() {
            return this.fromMap.size();
        }

        public boolean containsKey(Object key) {
            return this.fromMap.containsKey(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 get(Object key) {
            V1 value = this.fromMap.get(key);
            if (value != null || this.fromMap.containsKey(key)) {
                return this.transformer.transformEntry(key, value);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 remove(Object key) {
            if (this.fromMap.containsKey(key)) {
                return this.transformer.transformEntry(key, this.fromMap.remove(key));
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public void clear() {
            this.fromMap.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.fromMap.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V2>> entryIterator() {
            return Iterators.transform(this.fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V2> values() {
            return new Values(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static class TransformedEntriesSortedMap<K, V1, V2> extends TransformedEntriesMap<K, V1, V2> implements SortedMap<K, V2> {
        /* access modifiers changed from: protected */
        public SortedMap<K, V1> fromMap() {
            return (SortedMap) this.fromMap;
        }

        TransformedEntriesSortedMap(SortedMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
            super(fromMap, transformer);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return fromMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return fromMap().firstKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> headMap(K toKey) {
            return Maps.transformEntries((SortedMap) fromMap().headMap(toKey), this.transformer);
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return fromMap().lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> subMap(K fromKey, K toKey) {
            return Maps.transformEntries((SortedMap) fromMap().subMap(fromKey, toKey), this.transformer);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> tailMap(K fromKey) {
            return Maps.transformEntries((SortedMap) fromMap().tailMap(fromKey), this.transformer);
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static class TransformedEntriesNavigableMap<K, V1, V2> extends TransformedEntriesSortedMap<K, V1, V2> implements NavigableMap<K, V2> {
        TransformedEntriesNavigableMap(NavigableMap<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer) {
            super(fromMap, transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> ceilingEntry(K key) {
            return transformEntry(fromMap().ceilingEntry(key));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return fromMap().ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return fromMap().descendingKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> descendingMap() {
            return Maps.transformEntries((NavigableMap) fromMap().descendingMap(), this.transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> firstEntry() {
            return transformEntry(fromMap().firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> floorEntry(K key) {
            return transformEntry(fromMap().floorEntry(key));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return fromMap().floorKey(key);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V2> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> headMap(K toKey, boolean inclusive) {
            return Maps.transformEntries((NavigableMap) fromMap().headMap(toKey, inclusive), this.transformer);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> higherEntry(K key) {
            return transformEntry(fromMap().higherEntry(key));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return fromMap().higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lastEntry() {
            return transformEntry(fromMap().lastEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> lowerEntry(K key) {
            return transformEntry(fromMap().lowerEntry(key));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return fromMap().lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return fromMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollFirstEntry() {
            return transformEntry(fromMap().pollFirstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V2> pollLastEntry() {
            return transformEntry(fromMap().pollLastEntry());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Maps.transformEntries((NavigableMap) fromMap().subMap(fromKey, fromInclusive, toKey, toInclusive), this.transformer);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V2> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, V2> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V2> tailMap(K fromKey, boolean inclusive) {
            return Maps.transformEntries((NavigableMap) fromMap().tailMap(fromKey, inclusive), this.transformer);
        }

        @NullableDecl
        private Map.Entry<K, V2> transformEntry(@NullableDecl Map.Entry<K, V1> entry) {
            if (entry == null) {
                return null;
            }
            return Maps.transformEntry(this.transformer, entry);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.TransformedEntriesSortedMap
        public NavigableMap<K, V1> fromMap() {
            return (NavigableMap) super.fromMap();
        }
    }

    static <K> Predicate<Map.Entry<K, ?>> keyPredicateOnEntries(Predicate<? super K> keyPredicate) {
        return Predicates.compose(keyPredicate, keyFunction());
    }

    static <V> Predicate<Map.Entry<?, V>> valuePredicateOnEntries(Predicate<? super V> valuePredicate) {
        return Predicates.compose(valuePredicate, valueFunction());
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        Preconditions.checkNotNull(keyPredicate);
        Predicate<Map.Entry<K, ?>> entryPredicate = keyPredicateOnEntries(keyPredicate);
        if (unfiltered instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) unfiltered, entryPredicate);
        }
        return new FilteredKeyMap((Map) Preconditions.checkNotNull(unfiltered), keyPredicate, entryPredicate);
    }

    public static <K, V> SortedMap<K, V> filterKeys(SortedMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        return filterEntries((SortedMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterKeys(NavigableMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        return filterEntries((NavigableMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    public static <K, V> BiMap<K, V> filterKeys(BiMap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
        Preconditions.checkNotNull(keyPredicate);
        return filterEntries((BiMap) unfiltered, keyPredicateOnEntries(keyPredicate));
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> SortedMap<K, V> filterValues(SortedMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((SortedMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterValues(NavigableMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((NavigableMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> BiMap<K, V> filterValues(BiMap<K, V> unfiltered, Predicate<? super V> valuePredicate) {
        return filterEntries((BiMap) unfiltered, valuePredicateOnEntries(valuePredicate));
    }

    public static <K, V> Map<K, V> filterEntries(Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(entryPredicate);
        if (unfiltered instanceof AbstractFilteredMap) {
            return filterFiltered((AbstractFilteredMap) unfiltered, entryPredicate);
        }
        return new FilteredEntryMap((Map) Preconditions.checkNotNull(unfiltered), entryPredicate);
    }

    public static <K, V> SortedMap<K, V> filterEntries(SortedMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(entryPredicate);
        if (unfiltered instanceof FilteredEntrySortedMap) {
            return filterFiltered((FilteredEntrySortedMap) unfiltered, (Predicate) entryPredicate);
        }
        return new FilteredEntrySortedMap((SortedMap) Preconditions.checkNotNull(unfiltered), entryPredicate);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterEntries(NavigableMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(entryPredicate);
        if (unfiltered instanceof FilteredEntryNavigableMap) {
            return filterFiltered((FilteredEntryNavigableMap) unfiltered, entryPredicate);
        }
        return new FilteredEntryNavigableMap((NavigableMap) Preconditions.checkNotNull(unfiltered), entryPredicate);
    }

    public static <K, V> BiMap<K, V> filterEntries(BiMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(entryPredicate);
        if (unfiltered instanceof FilteredEntryBiMap) {
            return filterFiltered((FilteredEntryBiMap) unfiltered, (Predicate) entryPredicate);
        }
        return new FilteredEntryBiMap(unfiltered, entryPredicate);
    }

    private static <K, V> Map<K, V> filterFiltered(AbstractFilteredMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryMap(map.unfiltered, Predicates.and(map.predicate, entryPredicate));
    }

    private static <K, V> SortedMap<K, V> filterFiltered(FilteredEntrySortedMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntrySortedMap(map.sortedMap(), Predicates.and(map.predicate, entryPredicate));
    }

    @GwtIncompatible
    private static <K, V> NavigableMap<K, V> filterFiltered(FilteredEntryNavigableMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryNavigableMap(((FilteredEntryNavigableMap) map).unfiltered, Predicates.and(((FilteredEntryNavigableMap) map).entryPredicate, entryPredicate));
    }

    private static <K, V> BiMap<K, V> filterFiltered(FilteredEntryBiMap<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate) {
        return new FilteredEntryBiMap(map.unfiltered(), Predicates.and(map.predicate, entryPredicate));
    }

    /* access modifiers changed from: private */
    public static abstract class AbstractFilteredMap<K, V> extends ViewCachingAbstractMap<K, V> {
        final Predicate<? super Map.Entry<K, V>> predicate;
        final Map<K, V> unfiltered;

        AbstractFilteredMap(Map<K, V> unfiltered2, Predicate<? super Map.Entry<K, V>> predicate2) {
            this.unfiltered = unfiltered2;
            this.predicate = predicate2;
        }

        /* access modifiers changed from: package-private */
        public boolean apply(@NullableDecl Object key, @NullableDecl V value) {
            return this.predicate.apply(Maps.immutableEntry(key, value));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, V value) {
            Preconditions.checkArgument(apply(key, value));
            return this.unfiltered.put(key, value);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.Maps$AbstractFilteredMap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                Preconditions.checkArgument(apply(entry.getKey(), entry.getValue()));
            }
            this.unfiltered.putAll(map);
        }

        public boolean containsKey(Object key) {
            return this.unfiltered.containsKey(key) && apply(key, this.unfiltered.get(key));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            V value = this.unfiltered.get(key);
            if (value == null || !apply(key, value)) {
                return null;
            }
            return value;
        }

        public boolean isEmpty() {
            return entrySet().isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            if (containsKey(key)) {
                return this.unfiltered.remove(key);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<V> createValues() {
            return new FilteredMapValues(this, this.unfiltered, this.predicate);
        }
    }

    private static final class FilteredMapValues<K, V> extends Values<K, V> {
        final Predicate<? super Map.Entry<K, V>> predicate;
        final Map<K, V> unfiltered;

        FilteredMapValues(Map<K, V> filteredMap, Map<K, V> unfiltered2, Predicate<? super Map.Entry<K, V>> predicate2) {
            super(filteredMap);
            this.unfiltered = unfiltered2;
            this.predicate = predicate2;
        }

        @Override // com.google.common.collect.Maps.Values
        public boolean remove(Object o) {
            Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();
            while (entryItr.hasNext()) {
                Map.Entry<K, V> entry = entryItr.next();
                if (this.predicate.apply(entry) && Objects.equal(entry.getValue(), o)) {
                    entryItr.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();
            boolean result = false;
            while (entryItr.hasNext()) {
                Map.Entry<K, V> entry = entryItr.next();
                if (this.predicate.apply(entry) && collection.contains(entry.getValue())) {
                    entryItr.remove();
                    result = true;
                }
            }
            return result;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();
            boolean result = false;
            while (entryItr.hasNext()) {
                Map.Entry<K, V> entry = entryItr.next();
                if (this.predicate.apply(entry) && !collection.contains(entry.getValue())) {
                    entryItr.remove();
                    result = true;
                }
            }
            return result;
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] array) {
            return (T[]) Lists.newArrayList(iterator()).toArray(array);
        }
    }

    private static class FilteredKeyMap<K, V> extends AbstractFilteredMap<K, V> {
        final Predicate<? super K> keyPredicate;

        FilteredKeyMap(Map<K, V> unfiltered, Predicate<? super K> keyPredicate2, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
            this.keyPredicate = keyPredicate2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), this.predicate);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
        }

        @Override // com.google.common.collect.Maps.AbstractFilteredMap
        public boolean containsKey(Object key) {
            return this.unfiltered.containsKey(key) && this.keyPredicate.apply(key);
        }
    }

    /* access modifiers changed from: package-private */
    public static class FilteredEntryMap<K, V> extends AbstractFilteredMap<K, V> {
        final Set<Map.Entry<K, V>> filteredEntrySet;

        FilteredEntryMap(Map<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
            this.filteredEntrySet = Sets.filter(unfiltered.entrySet(), this.predicate);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet();
        }

        private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
            private EntrySet() {
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public Set<Map.Entry<K, V>> delegate() {
                return FilteredEntryMap.this.filteredEntrySet;
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(FilteredEntryMap.this.filteredEntrySet.iterator()) {
                    /* class com.google.common.collect.Maps.FilteredEntryMap.EntrySet.AnonymousClass1 */

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.collect.TransformedIterator
                    public /* bridge */ /* synthetic */ Object transform(Object obj) {
                        return transform((Map.Entry) ((Map.Entry) obj));
                    }

                    /* access modifiers changed from: package-private */
                    public Map.Entry<K, V> transform(final Map.Entry<K, V> entry) {
                        return new ForwardingMapEntry<K, V>() {
                            /* class com.google.common.collect.Maps.FilteredEntryMap.EntrySet.AnonymousClass1.AnonymousClass1 */

                            /* access modifiers changed from: protected */
                            @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
                            public Map.Entry<K, V> delegate() {
                                return entry;
                            }

                            @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
                            public V setValue(V newValue) {
                                Preconditions.checkArgument(FilteredEntryMap.this.apply(getKey(), newValue));
                                return (V) super.setValue(newValue);
                            }
                        };
                    }
                };
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new KeySet();
        }

        static <K, V> boolean removeAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate, Collection<?> keyCollection) {
            Iterator<Map.Entry<K, V>> entryItr = map.entrySet().iterator();
            boolean result = false;
            while (entryItr.hasNext()) {
                Map.Entry<K, V> entry = entryItr.next();
                if (entryPredicate.apply(entry) && keyCollection.contains(entry.getKey())) {
                    entryItr.remove();
                    result = true;
                }
            }
            return result;
        }

        static <K, V> boolean retainAllKeys(Map<K, V> map, Predicate<? super Map.Entry<K, V>> entryPredicate, Collection<?> keyCollection) {
            Iterator<Map.Entry<K, V>> entryItr = map.entrySet().iterator();
            boolean result = false;
            while (entryItr.hasNext()) {
                Map.Entry<K, V> entry = entryItr.next();
                if (entryPredicate.apply(entry) && !keyCollection.contains(entry.getKey())) {
                    entryItr.remove();
                    result = true;
                }
            }
            return result;
        }

        class KeySet extends KeySet<K, V> {
            KeySet() {
                super(FilteredEntryMap.this);
            }

            @Override // com.google.common.collect.Maps.KeySet
            public boolean remove(Object o) {
                if (!FilteredEntryMap.this.containsKey(o)) {
                    return false;
                }
                FilteredEntryMap.this.unfiltered.remove(o);
                return true;
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                return FilteredEntryMap.removeAllKeys(FilteredEntryMap.this.unfiltered, FilteredEntryMap.this.predicate, collection);
            }

            @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return FilteredEntryMap.retainAllKeys(FilteredEntryMap.this.unfiltered, FilteredEntryMap.this.predicate, collection);
            }

            public Object[] toArray() {
                return Lists.newArrayList(iterator()).toArray();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] array) {
                return (T[]) Lists.newArrayList(iterator()).toArray(array);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class FilteredEntrySortedMap<K, V> extends FilteredEntryMap<K, V> implements SortedMap<K, V> {
        FilteredEntrySortedMap(SortedMap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> entryPredicate) {
            super(unfiltered, entryPredicate);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, V> sortedMap() {
            return (SortedMap) this.unfiltered;
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.SortedMap
        public SortedSet<K> keySet() {
            return (SortedSet) super.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.FilteredEntryMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<K> createKeySet() {
            return new SortedKeySet();
        }

        /* access modifiers changed from: package-private */
        public class SortedKeySet extends FilteredEntryMap<K, V>.KeySet implements SortedSet<K> {
            SortedKeySet() {
                super();
            }

            @Override // java.util.SortedSet
            public Comparator<? super K> comparator() {
                return FilteredEntrySortedMap.this.sortedMap().comparator();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> subSet(K fromElement, K toElement) {
                return (SortedSet) FilteredEntrySortedMap.this.subMap(fromElement, toElement).keySet();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> headSet(K toElement) {
                return (SortedSet) FilteredEntrySortedMap.this.headMap(toElement).keySet();
            }

            @Override // java.util.SortedSet
            public SortedSet<K> tailSet(K fromElement) {
                return (SortedSet) FilteredEntrySortedMap.this.tailMap(fromElement).keySet();
            }

            @Override // java.util.SortedSet
            public K first() {
                return (K) FilteredEntrySortedMap.this.firstKey();
            }

            @Override // java.util.SortedSet
            public K last() {
                return (K) FilteredEntrySortedMap.this.lastKey();
            }
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return keySet().iterator().next();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.Maps$FilteredEntrySortedMap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.SortedMap
        public K lastKey() {
            SortedMap<K, V> headMap = sortedMap();
            while (true) {
                K key = headMap.lastKey();
                if (apply(key, this.unfiltered.get(key))) {
                    return key;
                }
                headMap = sortedMap().headMap(key);
            }
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return new FilteredEntrySortedMap(sortedMap().headMap(toKey), this.predicate);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return new FilteredEntrySortedMap(sortedMap().subMap(fromKey, toKey), this.predicate);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return new FilteredEntrySortedMap(sortedMap().tailMap(fromKey), this.predicate);
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static class FilteredEntryNavigableMap<K, V> extends AbstractNavigableMap<K, V> {
        private final Predicate<? super Map.Entry<K, V>> entryPredicate;
        private final Map<K, V> filteredDelegate;
        private final NavigableMap<K, V> unfiltered;

        FilteredEntryNavigableMap(NavigableMap<K, V> unfiltered2, Predicate<? super Map.Entry<K, V>> entryPredicate2) {
            this.unfiltered = (NavigableMap) Preconditions.checkNotNull(unfiltered2);
            this.entryPredicate = entryPredicate2;
            this.filteredDelegate = new FilteredEntryMap(unfiltered2, entryPredicate2);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.unfiltered.comparator();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return new NavigableKeySet<K, V>(this) {
                /* class com.google.common.collect.Maps.FilteredEntryNavigableMap.AnonymousClass1 */

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMap.removeAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMap.retainAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            return new FilteredMapValues(this, this.unfiltered, this.entryPredicate);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return Iterators.filter(this.unfiltered.entrySet().iterator(), this.entryPredicate);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractNavigableMap
        public Iterator<Map.Entry<K, V>> descendingEntryIterator() {
            return Iterators.filter(this.unfiltered.descendingMap().entrySet().iterator(), this.entryPredicate);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public int size() {
            return this.filteredDelegate.size();
        }

        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.AbstractNavigableMap
        @NullableDecl
        public V get(@NullableDecl Object key) {
            return this.filteredDelegate.get(key);
        }

        public boolean containsKey(@NullableDecl Object key) {
            return this.filteredDelegate.containsKey(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, V value) {
            return this.filteredDelegate.put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(@NullableDecl Object key) {
            return this.filteredDelegate.remove(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> m) {
            this.filteredDelegate.putAll(m);
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public void clear() {
            this.filteredDelegate.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            return this.filteredDelegate.entrySet();
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return (Map.Entry) Iterables.removeFirstMatching(this.unfiltered.descendingMap().entrySet(), this.entryPredicate);
        }

        @Override // com.google.common.collect.AbstractNavigableMap, java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return Maps.filterEntries((NavigableMap) this.unfiltered.descendingMap(), (Predicate) this.entryPredicate);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.subMap(fromKey, fromInclusive, toKey, toInclusive), (Predicate) this.entryPredicate);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.headMap(toKey, inclusive), (Predicate) this.entryPredicate);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return Maps.filterEntries((NavigableMap) this.unfiltered.tailMap(fromKey, inclusive), (Predicate) this.entryPredicate);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class FilteredEntryBiMap<K, V> extends FilteredEntryMap<K, V> implements BiMap<K, V> {
        @RetainedWith
        private final BiMap<V, K> inverse;

        private static <K, V> Predicate<Map.Entry<V, K>> inversePredicate(final Predicate<? super Map.Entry<K, V>> forwardPredicate) {
            return new Predicate<Map.Entry<V, K>>() {
                /* class com.google.common.collect.Maps.FilteredEntryBiMap.AnonymousClass1 */

                @Override // com.google.common.base.Predicate
                public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                    return apply((Map.Entry) ((Map.Entry) obj));
                }

                public boolean apply(Map.Entry<V, K> input) {
                    return Predicate.this.apply(Maps.immutableEntry(input.getValue(), input.getKey()));
                }
            };
        }

        FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Map.Entry<K, V>> predicate) {
            super(delegate, predicate);
            this.inverse = new FilteredEntryBiMap(delegate.inverse(), inversePredicate(predicate), this);
        }

        private FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Map.Entry<K, V>> predicate, BiMap<V, K> inverse2) {
            super(delegate, predicate);
            this.inverse = inverse2;
        }

        /* access modifiers changed from: package-private */
        public BiMap<K, V> unfiltered() {
            return (BiMap) this.unfiltered;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(@NullableDecl K key, @NullableDecl V value) {
            Preconditions.checkArgument(apply(key, value));
            return unfiltered().forcePut(key, value);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            return this.inverse;
        }

        @Override // com.google.common.collect.BiMap, java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<V> values() {
            return this.inverse.keySet();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.NavigableMap<K, ? extends V> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> map) {
        Preconditions.checkNotNull(map);
        return map instanceof UnmodifiableNavigableMap ? map : new UnmodifiableNavigableMap(map);
    }

    /* access modifiers changed from: private */
    @NullableDecl
    public static <K, V> Map.Entry<K, V> unmodifiableOrNull(@NullableDecl Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return unmodifiableEntry(entry);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public static class UnmodifiableNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
        private final NavigableMap<K, ? extends V> delegate;
        @MonotonicNonNullDecl
        private transient UnmodifiableNavigableMap<K, V> descendingMap;

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> delegate2) {
            this.delegate = delegate2;
        }

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> delegate2, UnmodifiableNavigableMap<K, V> descendingMap2) {
            this.delegate = delegate2;
            this.descendingMap = descendingMap2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingSortedMap
        public SortedMap<K, V> delegate() {
            return Collections.unmodifiableSortedMap(this.delegate);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            return Maps.unmodifiableOrNull(this.delegate.lowerEntry(key));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return this.delegate.lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            return Maps.unmodifiableOrNull(this.delegate.floorEntry(key));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return this.delegate.floorKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(key));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return this.delegate.ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            return Maps.unmodifiableOrNull(this.delegate.higherEntry(key));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return this.delegate.higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return Maps.unmodifiableOrNull(this.delegate.firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return Maps.unmodifiableOrNull(this.delegate.lastEntry());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            UnmodifiableNavigableMap<K, V> result = this.descendingMap;
            if (result != null) {
                return result;
            }
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap = new UnmodifiableNavigableMap<>(this.delegate.descendingMap(), this);
            this.descendingMap = unmodifiableNavigableMap;
            return unmodifiableNavigableMap;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
        }

        @Override // java.util.NavigableMap, com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.subMap(fromKey, fromInclusive, toKey, toInclusive));
        }

        @Override // java.util.NavigableMap, com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.headMap(toKey, inclusive));
        }

        @Override // java.util.NavigableMap, com.google.common.collect.ForwardingSortedMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return Maps.unmodifiableNavigableMap(this.delegate.tailMap(fromKey, inclusive));
        }
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> navigableMap) {
        return Synchronized.navigableMap(navigableMap);
    }

    @GwtCompatible
    static abstract class ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {
        @MonotonicNonNullDecl
        private transient Set<Map.Entry<K, V>> entrySet;
        @MonotonicNonNullDecl
        private transient Set<K> keySet;
        @MonotonicNonNullDecl
        private transient Collection<V> values;

        /* access modifiers changed from: package-private */
        public abstract Set<Map.Entry<K, V>> createEntrySet();

        ViewCachingAbstractMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> result = this.entrySet;
            if (result != null) {
                return result;
            }
            Set<Map.Entry<K, V>> result2 = createEntrySet();
            this.entrySet = result2;
            return result2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            Set<K> result = this.keySet;
            if (result != null) {
                return result;
            }
            Set<K> result2 = createKeySet();
            this.keySet = result2;
            return result2;
        }

        /* access modifiers changed from: package-private */
        public Set<K> createKeySet() {
            return new KeySet(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            Collection<V> result = this.values;
            if (result != null) {
                return result;
            }
            Collection<V> result2 = createValues();
            this.values = result2;
            return result2;
        }

        /* access modifiers changed from: package-private */
        public Collection<V> createValues() {
            return new Values(this);
        }
    }

    static abstract class IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {
        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        public abstract int size();

        IteratorBasedAbstractMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new EntrySet<K, V>() {
                /* class com.google.common.collect.Maps.IteratorBasedAbstractMap.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return IteratorBasedAbstractMap.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedAbstractMap.this.entryIterator();
                }
            };
        }

        public void clear() {
            Iterators.clear(entryIterator());
        }
    }

    static <V> V safeGet(Map<?, V> map, @NullableDecl Object key) {
        Preconditions.checkNotNull(map);
        try {
            return map.get(key);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }

    static boolean safeContainsKey(Map<?, ?> map, Object key) {
        Preconditions.checkNotNull(map);
        try {
            return map.containsKey(key);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    static <V> V safeRemove(Map<?, V> map, Object key) {
        Preconditions.checkNotNull(map);
        try {
            return map.remove(key);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }

    static boolean containsKeyImpl(Map<?, ?> map, @NullableDecl Object key) {
        return Iterators.contains(keyIterator(map.entrySet().iterator()), key);
    }

    static boolean containsValueImpl(Map<?, ?> map, @NullableDecl Object value) {
        return Iterators.contains(valueIterator(map.entrySet().iterator()), value);
    }

    static <K, V> boolean containsEntryImpl(Collection<Map.Entry<K, V>> c, Object o) {
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        return c.contains(unmodifiableEntry((Map.Entry) o));
    }

    static <K, V> boolean removeEntryImpl(Collection<Map.Entry<K, V>> c, Object o) {
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        return c.remove(unmodifiableEntry((Map.Entry) o));
    }

    static boolean equalsImpl(Map<?, ?> map, Object object) {
        if (map == object) {
            return true;
        }
        if (object instanceof Map) {
            return map.entrySet().equals(((Map) object).entrySet());
        }
        return false;
    }

    static String toStringImpl(Map<?, ?> map) {
        StringBuilder sb = Collections2.newStringBuilderForCollection(map.size()).append('{');
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return sb.append('}').toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.Map<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    static <K, V> void putAllImpl(Map<K, V> self, Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            self.put(entry.getKey(), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public static class KeySet<K, V> extends Sets.ImprovedAbstractSet<K> {
        @Weak
        final Map<K, V> map;

        KeySet(Map<K, V> map2) {
            this.map = (Map) Preconditions.checkNotNull(map2);
        }

        /* access modifiers changed from: package-private */
        public Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return Maps.keyIterator(map().entrySet().iterator());
        }

        public int size() {
            return map().size();
        }

        public boolean isEmpty() {
            return map().isEmpty();
        }

        public boolean contains(Object o) {
            return map().containsKey(o);
        }

        public boolean remove(Object o) {
            if (!contains(o)) {
                return false;
            }
            map().remove(o);
            return true;
        }

        public void clear() {
            map().clear();
        }
    }

    @NullableDecl
    static <K> K keyOrNull(@NullableDecl Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    @NullableDecl
    static <V> V valueOrNull(@NullableDecl Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    static class SortedKeySet<K, V> extends KeySet<K, V> implements SortedSet<K> {
        SortedKeySet(SortedMap<K, V> map) {
            super(map);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.KeySet
        public SortedMap<K, V> map() {
            return (SortedMap) super.map();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return map().comparator();
        }

        @Override // java.util.SortedSet
        public SortedSet<K> subSet(K fromElement, K toElement) {
            return new SortedKeySet(map().subMap(fromElement, toElement));
        }

        @Override // java.util.SortedSet
        public SortedSet<K> headSet(K toElement) {
            return new SortedKeySet(map().headMap(toElement));
        }

        @Override // java.util.SortedSet
        public SortedSet<K> tailSet(K fromElement) {
            return new SortedKeySet(map().tailMap(fromElement));
        }

        @Override // java.util.SortedSet
        public K first() {
            return map().firstKey();
        }

        @Override // java.util.SortedSet
        public K last() {
            return map().lastKey();
        }
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public static class NavigableKeySet<K, V> extends SortedKeySet<K, V> implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, V> map) {
            super(map);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.SortedKeySet, com.google.common.collect.Maps.SortedKeySet, com.google.common.collect.Maps.KeySet
        public NavigableMap<K, V> map() {
            return (NavigableMap) this.map;
        }

        @Override // java.util.NavigableSet
        public K lower(K e) {
            return map().lowerKey(e);
        }

        @Override // java.util.NavigableSet
        public K floor(K e) {
            return map().floorKey(e);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K e) {
            return map().ceilingKey(e);
        }

        @Override // java.util.NavigableSet
        public K higher(K e) {
            return map().higherKey(e);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Maps.keyOrNull(map().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Maps.keyOrNull(map().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return map().descendingKeySet();
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive) {
            return map().subMap(fromElement, fromInclusive, toElement, toInclusive).navigableKeySet();
        }

        @Override // java.util.SortedSet, com.google.common.collect.Maps.SortedKeySet, java.util.NavigableSet
        public SortedSet<K> subSet(K fromElement, K toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K toElement, boolean inclusive) {
            return map().headMap(toElement, inclusive).navigableKeySet();
        }

        @Override // java.util.SortedSet, com.google.common.collect.Maps.SortedKeySet, java.util.NavigableSet
        public SortedSet<K> headSet(K toElement) {
            return headSet(toElement, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K fromElement, boolean inclusive) {
            return map().tailMap(fromElement, inclusive).navigableKeySet();
        }

        @Override // java.util.SortedSet, com.google.common.collect.Maps.SortedKeySet, java.util.NavigableSet
        public SortedSet<K> tailSet(K fromElement) {
            return tailSet(fromElement, true);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Values<K, V> extends AbstractCollection<V> {
        @Weak
        final Map<K, V> map;

        Values(Map<K, V> map2) {
            this.map = (Map) Preconditions.checkNotNull(map2);
        }

        /* access modifiers changed from: package-private */
        public final Map<K, V> map() {
            return this.map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Maps.valueIterator(map().entrySet().iterator());
        }

        public boolean remove(Object o) {
            try {
                return super.remove(o);
            } catch (UnsupportedOperationException e) {
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (Objects.equal(o, entry.getValue())) {
                        map().remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(c));
            } catch (UnsupportedOperationException e) {
                Set<K> toRemove = Sets.newHashSet();
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (c.contains(entry.getValue())) {
                        toRemove.add(entry.getKey());
                    }
                }
                return map().keySet().removeAll(toRemove);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(c));
            } catch (UnsupportedOperationException e) {
                Set<K> toRetain = Sets.newHashSet();
                for (Map.Entry<K, V> entry : map().entrySet()) {
                    if (c.contains(entry.getValue())) {
                        toRetain.add(entry.getKey());
                    }
                }
                return map().keySet().retainAll(toRetain);
            }
        }

        public int size() {
            return map().size();
        }

        public boolean isEmpty() {
            return map().isEmpty();
        }

        public boolean contains(@NullableDecl Object o) {
            return map().containsValue(o);
        }

        public void clear() {
            map().clear();
        }
    }

    static abstract class EntrySet<K, V> extends Sets.ImprovedAbstractSet<Map.Entry<K, V>> {
        /* access modifiers changed from: package-private */
        public abstract Map<K, V> map();

        EntrySet() {
        }

        public int size() {
            return map().size();
        }

        public void clear() {
            map().clear();
        }

        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object safeGet = Maps.safeGet(map(), key);
            if (!Objects.equal(safeGet, entry.getValue())) {
                return false;
            }
            if (safeGet != null || map().containsKey(key)) {
                return true;
            }
            return false;
        }

        public boolean isEmpty() {
            return map().isEmpty();
        }

        public boolean remove(Object o) {
            if (contains(o)) {
                return map().keySet().remove(((Map.Entry) o).getKey());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection<?> c) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(c));
            } catch (UnsupportedOperationException e) {
                return Sets.removeAllImpl(this, c.iterator());
            }
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(c));
            } catch (UnsupportedOperationException e) {
                Set<Object> keys = Sets.newHashSetWithExpectedSize(c.size());
                for (Object o : c) {
                    if (contains(o)) {
                        keys.add(((Map.Entry) o).getKey());
                    }
                }
                return map().keySet().retainAll(keys);
            }
        }
    }

    @GwtIncompatible
    static abstract class DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
        @MonotonicNonNullDecl
        private transient Comparator<? super K> comparator;
        @MonotonicNonNullDecl
        private transient Set<Map.Entry<K, V>> entrySet;
        @MonotonicNonNullDecl
        private transient NavigableSet<K> navigableKeySet;

        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> entryIterator();

        /* access modifiers changed from: package-private */
        public abstract NavigableMap<K, V> forward();

        DescendingMap() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public final Map<K, V> delegate() {
            return forward();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> result = this.comparator;
            if (result != null) {
                return result;
            }
            Comparator<? super K> forwardCmp = forward().comparator();
            if (forwardCmp == null) {
                forwardCmp = Ordering.natural();
            }
            Comparator<? super K> result2 = reverse(forwardCmp);
            this.comparator = result2;
            return result2;
        }

        private static <T> Ordering<T> reverse(Comparator<T> forward) {
            return Ordering.from(forward).reverse();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return forward().lastKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return forward().firstKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            return forward().higherEntry(key);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return forward().higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            return forward().ceilingEntry(key);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return forward().ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            return forward().floorEntry(key);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return forward().floorKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            return forward().lowerEntry(key);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return forward().lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return forward().lastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return forward().firstEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return forward().pollLastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return forward().pollFirstEntry();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return forward();
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> result = this.entrySet;
            if (result != null) {
                return result;
            }
            Set<Map.Entry<K, V>> result2 = createEntrySet();
            this.entrySet = result2;
            return result2;
        }

        /* access modifiers changed from: package-private */
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() {
                /* class com.google.common.collect.Maps.DescendingMap.AnonymousClass1EntrySetImpl */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, V> map() {
                    return DescendingMap.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, V>> iterator() {
                    return DescendingMap.this.entryIterator();
                }
            };
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> result = this.navigableKeySet;
            if (result != null) {
                return result;
            }
            NavigableSet<K> result2 = new NavigableKeySet<>(this);
            this.navigableKeySet = result2;
            return result2;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return forward().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return forward().subMap(toKey, toInclusive, fromKey, fromInclusive).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            return forward().tailMap(toKey, inclusive).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            return forward().headMap(fromKey, inclusive).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            return new Values(this);
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }
    }

    static <E> ImmutableMap<E, Integer> indexMap(Collection<E> list) {
        ImmutableMap.Builder<E, Integer> builder = new ImmutableMap.Builder<>(list.size());
        int i = 0;
        for (E e : list) {
            builder.put(e, Integer.valueOf(i));
            i++;
        }
        return builder.build();
    }

    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>, V> NavigableMap<K, V> subMap(NavigableMap<K, V> map, Range<K> range) {
        boolean z;
        boolean z2 = true;
        if (map.comparator() != null && map.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(map.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            if (range.lowerBoundType() == BoundType.CLOSED) {
                z = true;
            } else {
                z = false;
            }
            K upperEndpoint = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return map.subMap(lowerEndpoint, z, upperEndpoint, z2);
        } else if (range.hasLowerBound()) {
            K lowerEndpoint2 = range.lowerEndpoint();
            if (range.lowerBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return map.tailMap(lowerEndpoint2, z2);
        } else if (!range.hasUpperBound()) {
            return (NavigableMap) Preconditions.checkNotNull(map);
        } else {
            K upperEndpoint2 = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z2 = false;
            }
            return map.headMap(upperEndpoint2, z2);
        }
    }
}
