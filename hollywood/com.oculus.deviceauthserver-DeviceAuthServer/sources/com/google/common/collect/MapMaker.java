package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.base.Ticker;
import com.google.common.collect.MapMakerInternalMap;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class MapMaker extends GenericMapMaker<Object, Object> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final int UNSET_INT = -1;
    int concurrencyLevel = -1;
    long expireAfterAccessNanos = -1;
    long expireAfterWriteNanos = -1;
    int initialCapacity = -1;
    Equivalence<Object> keyEquivalence;
    MapMakerInternalMap.Strength keyStrength;
    int maximumSize = -1;
    RemovalCause nullRemovalCause;
    Ticker ticker;
    boolean useCustomMap;
    MapMakerInternalMap.Strength valueStrength;

    /* access modifiers changed from: package-private */
    public enum RemovalCause {
        EXPLICIT {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMaker.RemovalCause
            public boolean wasEvicted() {
                return false;
            }
        },
        REPLACED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMaker.RemovalCause
            public boolean wasEvicted() {
                return false;
            }
        },
        COLLECTED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMaker.RemovalCause
            public boolean wasEvicted() {
                return true;
            }
        },
        EXPIRED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMaker.RemovalCause
            public boolean wasEvicted() {
                return true;
            }
        },
        SIZE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMaker.RemovalCause
            public boolean wasEvicted() {
                return true;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract boolean wasEvicted();
    }

    /* access modifiers changed from: package-private */
    public interface RemovalListener<K, V> {
        void onRemoval(RemovalNotification<K, V> removalNotification);
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("To be supported")
    public GenericMapMaker<Object, Object> keyEquivalence(Equivalence<Object> equivalence) {
        Preconditions.checkState(this.keyEquivalence == null, "key equivalence was already set to %s", this.keyEquivalence);
        this.keyEquivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
        this.useCustomMap = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> getKeyEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    @Override // com.google.common.collect.GenericMapMaker
    public GenericMapMaker<Object, Object> initialCapacity(int initialCapacity2) {
        boolean z = true;
        Preconditions.checkState(this.initialCapacity == -1, "initial capacity was already set to %s", Integer.valueOf(this.initialCapacity));
        if (initialCapacity2 < 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.initialCapacity = initialCapacity2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int getInitialCapacity() {
        int i = this.initialCapacity;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @Deprecated
    public GenericMapMaker<Object, Object> maximumSize(int size) {
        boolean z = false;
        Preconditions.checkState(this.maximumSize == -1, "maximum size was already set to %s", Integer.valueOf(this.maximumSize));
        if (size >= 0) {
            z = true;
        }
        Preconditions.checkArgument(z, "maximum size must not be negative");
        this.maximumSize = size;
        this.useCustomMap = true;
        if (this.maximumSize == 0) {
            this.nullRemovalCause = RemovalCause.SIZE;
        }
        return this;
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    @Override // com.google.common.collect.GenericMapMaker
    public GenericMapMaker<Object, Object> concurrencyLevel(int concurrencyLevel2) {
        boolean z = true;
        Preconditions.checkState(this.concurrencyLevel == -1, "concurrency level was already set to %s", Integer.valueOf(this.concurrencyLevel));
        if (concurrencyLevel2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.concurrencyLevel = concurrencyLevel2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int getConcurrencyLevel() {
        int i = this.concurrencyLevel;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("java.lang.ref.WeakReference")
    public GenericMapMaker<Object, Object> weakKeys() {
        return setKeyStrength(MapMakerInternalMap.Strength.WEAK);
    }

    /* access modifiers changed from: package-private */
    public MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
        boolean z = false;
        Preconditions.checkState(this.keyStrength == null, "Key strength was already set to %s", this.keyStrength);
        this.keyStrength = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (this.keyStrength != MapMakerInternalMap.Strength.SOFT) {
            z = true;
        }
        Preconditions.checkArgument(z, "Soft keys are not supported");
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength getKeyStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("java.lang.ref.WeakReference")
    public GenericMapMaker<Object, Object> weakValues() {
        return setValueStrength(MapMakerInternalMap.Strength.WEAK);
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("java.lang.ref.SoftReference")
    @Deprecated
    public GenericMapMaker<Object, Object> softValues() {
        return setValueStrength(MapMakerInternalMap.Strength.SOFT);
    }

    /* access modifiers changed from: package-private */
    public MapMaker setValueStrength(MapMakerInternalMap.Strength strength) {
        Preconditions.checkState(this.valueStrength == null, "Value strength was already set to %s", this.valueStrength);
        this.valueStrength = (MapMakerInternalMap.Strength) Preconditions.checkNotNull(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength getValueStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @Deprecated
    public GenericMapMaker<Object, Object> expireAfterWrite(long duration, TimeUnit unit) {
        checkExpiration(duration, unit);
        this.expireAfterWriteNanos = unit.toNanos(duration);
        if (duration == 0 && this.nullRemovalCause == null) {
            this.nullRemovalCause = RemovalCause.EXPIRED;
        }
        this.useCustomMap = true;
        return this;
    }

    private void checkExpiration(long duration, TimeUnit unit) {
        Preconditions.checkState(this.expireAfterWriteNanos == -1, "expireAfterWrite was already set to %s ns", Long.valueOf(this.expireAfterWriteNanos));
        Preconditions.checkState(this.expireAfterAccessNanos == -1, "expireAfterAccess was already set to %s ns", Long.valueOf(this.expireAfterAccessNanos));
        Preconditions.checkArgument(duration >= 0, "duration cannot be negative: %s %s", Long.valueOf(duration), unit);
    }

    /* access modifiers changed from: package-private */
    public long getExpireAfterWriteNanos() {
        long j = this.expireAfterWriteNanos;
        if (j == -1) {
            return 0;
        }
        return j;
    }

    /* Return type fixed from 'com.google.common.collect.MapMaker' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("To be supported")
    @Deprecated
    public GenericMapMaker<Object, Object> expireAfterAccess(long duration, TimeUnit unit) {
        checkExpiration(duration, unit);
        this.expireAfterAccessNanos = unit.toNanos(duration);
        if (duration == 0 && this.nullRemovalCause == null) {
            this.nullRemovalCause = RemovalCause.EXPIRED;
        }
        this.useCustomMap = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public long getExpireAfterAccessNanos() {
        long j = this.expireAfterAccessNanos;
        if (j == -1) {
            return 0;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public Ticker getTicker() {
        return (Ticker) MoreObjects.firstNonNull(this.ticker, Ticker.systemTicker());
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("To be supported")
    @Deprecated
    public <K, V> GenericMapMaker<K, V> removalListener(RemovalListener<K, V> listener) {
        Preconditions.checkState(this.removalListener == null);
        this.removalListener = (RemovalListener) Preconditions.checkNotNull(listener);
        this.useCustomMap = true;
        return this;
    }

    @Override // com.google.common.collect.GenericMapMaker
    public <K, V> ConcurrentMap<K, V> makeMap() {
        ConcurrentMap<K, V> concurrentMap;
        if (!this.useCustomMap) {
            return new ConcurrentHashMap(getInitialCapacity(), 0.75f, getConcurrencyLevel());
        }
        if (this.nullRemovalCause == null) {
            concurrentMap = new MapMakerInternalMap<>(this);
        } else {
            concurrentMap = new NullConcurrentMap<>(this);
        }
        return concurrentMap;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @GwtIncompatible("MapMakerInternalMap")
    public <K, V> MapMakerInternalMap<K, V> makeCustomMap() {
        return new MapMakerInternalMap<>(this);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.GenericMapMaker
    @Deprecated
    public <K, V> ConcurrentMap<K, V> makeComputingMap(Function<? super K, ? extends V> computingFunction) {
        ConcurrentMap<K, V> concurrentMap;
        if (this.nullRemovalCause == null) {
            concurrentMap = new ComputingMapAdapter<>(this, computingFunction);
        } else {
            concurrentMap = new NullComputingConcurrentMap<>(this, computingFunction);
        }
        return concurrentMap;
    }

    public String toString() {
        MoreObjects.ToStringHelper s = MoreObjects.toStringHelper(this);
        int i = this.initialCapacity;
        if (i != -1) {
            s.add("initialCapacity", i);
        }
        int i2 = this.concurrencyLevel;
        if (i2 != -1) {
            s.add("concurrencyLevel", i2);
        }
        int i3 = this.maximumSize;
        if (i3 != -1) {
            s.add("maximumSize", i3);
        }
        if (this.expireAfterWriteNanos != -1) {
            s.add("expireAfterWrite", this.expireAfterWriteNanos + "ns");
        }
        if (this.expireAfterAccessNanos != -1) {
            s.add("expireAfterAccess", this.expireAfterAccessNanos + "ns");
        }
        MapMakerInternalMap.Strength strength = this.keyStrength;
        if (strength != null) {
            s.add("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            s.add("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            s.addValue("keyEquivalence");
        }
        if (this.removalListener != null) {
            s.addValue("removalListener");
        }
        return s.toString();
    }

    /* access modifiers changed from: package-private */
    public static final class RemovalNotification<K, V> extends ImmutableEntry<K, V> {
        private static final long serialVersionUID = 0;
        private final RemovalCause cause;

        RemovalNotification(@Nullable K key, @Nullable V value, RemovalCause cause2) {
            super(key, value);
            this.cause = cause2;
        }

        public RemovalCause getCause() {
            return this.cause;
        }

        public boolean wasEvicted() {
            return this.cause.wasEvicted();
        }
    }

    /* access modifiers changed from: package-private */
    public static class NullConcurrentMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        private final RemovalCause removalCause;
        private final RemovalListener<K, V> removalListener;

        NullConcurrentMap(MapMaker mapMaker) {
            this.removalListener = mapMaker.getRemovalListener();
            this.removalCause = mapMaker.nullRemovalCause;
        }

        public boolean containsKey(@Nullable Object key) {
            return false;
        }

        public boolean containsValue(@Nullable Object value) {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@Nullable Object key) {
            return null;
        }

        /* access modifiers changed from: package-private */
        public void notifyRemoval(K key, V value) {
            this.removalListener.onRemoval(new RemovalNotification<>(key, value, this.removalCause));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, V value) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
            notifyRemoval(key, value);
            return null;
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public V putIfAbsent(K key, V value) {
            return put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(@Nullable Object key) {
            return null;
        }

        public boolean remove(@Nullable Object key, @Nullable Object value) {
            return false;
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public V replace(K key, V value) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
            return null;
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public boolean replace(K key, @Nullable V v, V newValue) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(newValue);
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return Collections.emptySet();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class NullComputingConcurrentMap<K, V> extends NullConcurrentMap<K, V> {
        private static final long serialVersionUID = 0;
        final Function<? super K, ? extends V> computingFunction;

        NullComputingConcurrentMap(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction2) {
            super(mapMaker);
            this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction2);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object */
        /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractMap, com.google.common.collect.MapMaker.NullConcurrentMap, java.util.Map
        public V get(Object k) {
            V value = compute(k);
            Preconditions.checkNotNull(value, "%s returned null for key %s.", this.computingFunction, k);
            notifyRemoval(k, value);
            return value;
        }

        private V compute(K key) {
            Preconditions.checkNotNull(key);
            try {
                return (V) this.computingFunction.apply(key);
            } catch (ComputationException e) {
                throw e;
            } catch (Throwable t) {
                throw new ComputationException(t);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ComputingMapAdapter<K, V> extends ComputingConcurrentHashMap<K, V> implements Serializable {
        private static final long serialVersionUID = 0;

        ComputingMapAdapter(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction) {
            super(mapMaker, computingFunction);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.MapMakerInternalMap
        public V get(Object key) {
            try {
                V value = getOrCompute(key);
                if (value != null) {
                    return value;
                }
                throw new NullPointerException(this.computingFunction + " returned null for key " + key + ".");
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                Throwables.propagateIfInstanceOf(cause, ComputationException.class);
                throw new ComputationException(cause);
            }
        }
    }
}
