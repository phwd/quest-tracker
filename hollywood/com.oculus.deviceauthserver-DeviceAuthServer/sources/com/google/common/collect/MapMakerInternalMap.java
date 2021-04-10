package com.google.common.collect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.common.collect.GenericMapMaker;
import com.google.common.collect.MapMaker;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* access modifiers changed from: package-private */
public class MapMakerInternalMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final long CLEANUP_EXECUTOR_DELAY_SECS = 60;
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final Queue<? extends Object> DISCARDING_QUEUE = new AbstractQueue<Object>() {
        /* class com.google.common.collect.MapMakerInternalMap.AnonymousClass2 */

        @Override // java.util.Queue
        public boolean offer(Object o) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return Iterators.emptyIterator();
        }
    };
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>() {
        /* class com.google.common.collect.MapMakerInternalMap.AnonymousClass1 */

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public Object get() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ReferenceEntry<Object, Object> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, @Nullable Object value, ReferenceEntry<Object, Object> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public Object waitForValue() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(ValueReference<Object, Object> valueReference) {
        }
    };
    private static final Logger logger = Logger.getLogger(MapMakerInternalMap.class.getName());
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient EntryFactory entryFactory;
    transient Set<Map.Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final Equivalence<Object> keyEquivalence;
    transient Set<K> keySet;
    final Strength keyStrength;
    final int maximumSize;
    final MapMaker.RemovalListener<K, V> removalListener;
    final Queue<MapMaker.RemovalNotification<K, V>> removalNotificationQueue;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V>[] segments;
    final Ticker ticker;
    final Equivalence<Object> valueEquivalence = this.valueStrength.defaultEquivalence();
    final Strength valueStrength;
    transient Collection<V> values;

    /* access modifiers changed from: package-private */
    public interface ReferenceEntry<K, V> {
        long getExpirationTime();

        int getHash();

        K getKey();

        ReferenceEntry<K, V> getNext();

        ReferenceEntry<K, V> getNextEvictable();

        ReferenceEntry<K, V> getNextExpirable();

        ReferenceEntry<K, V> getPreviousEvictable();

        ReferenceEntry<K, V> getPreviousExpirable();

        ValueReference<K, V> getValueReference();

        void setExpirationTime(long j);

        void setNextEvictable(ReferenceEntry<K, V> referenceEntry);

        void setNextExpirable(ReferenceEntry<K, V> referenceEntry);

        void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry);

        void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry);

        void setValueReference(ValueReference<K, V> valueReference);
    }

    /* access modifiers changed from: package-private */
    public enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V value) {
                return new StrongValueReference(value);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        SOFT {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value) {
                return new SoftValueReference(segment.valueReferenceQueue, value, entry);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value) {
                return new WeakValueReference(segment.valueReferenceQueue, value, entry);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> defaultEquivalence();

        /* access modifiers changed from: package-private */
        public abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v);
    }

    /* access modifiers changed from: package-private */
    public interface ValueReference<K, V> {
        void clear(@Nullable ValueReference<K, V> valueReference);

        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, ReferenceEntry<K, V> referenceEntry);

        V get();

        ReferenceEntry<K, V> getEntry();

        boolean isComputingReference();

        V waitForValue() throws ExecutionException;
    }

    MapMakerInternalMap(MapMaker builder) {
        Queue<MapMaker.RemovalNotification<K, V>> queue;
        this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), (int) MAX_SEGMENTS);
        this.keyStrength = builder.getKeyStrength();
        this.valueStrength = builder.getValueStrength();
        this.keyEquivalence = builder.getKeyEquivalence();
        this.maximumSize = builder.maximumSize;
        this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
        this.entryFactory = EntryFactory.getFactory(this.keyStrength, expires(), evictsBySize());
        this.ticker = builder.getTicker();
        this.removalListener = builder.getRemovalListener();
        if (this.removalListener == GenericMapMaker.NullListener.INSTANCE) {
            queue = discardingQueue();
        } else {
            queue = new ConcurrentLinkedQueue<>();
        }
        this.removalNotificationQueue = queue;
        int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
        initialCapacity = evictsBySize() ? Math.min(initialCapacity, this.maximumSize) : initialCapacity;
        int segmentShift2 = 0;
        int segmentCount = 1;
        while (segmentCount < this.concurrencyLevel && (!evictsBySize() || segmentCount * 2 <= this.maximumSize)) {
            segmentShift2++;
            segmentCount <<= 1;
        }
        this.segmentShift = 32 - segmentShift2;
        this.segmentMask = segmentCount - 1;
        this.segments = newSegmentArray(segmentCount);
        int segmentCapacity = initialCapacity / segmentCount;
        int segmentSize = 1;
        while (segmentSize < (segmentCapacity * segmentCount < initialCapacity ? segmentCapacity + 1 : segmentCapacity)) {
            segmentSize <<= 1;
        }
        if (evictsBySize()) {
            int i = this.maximumSize;
            int maximumSegmentSize = (i / segmentCount) + 1;
            int remainder = i % segmentCount;
            for (int i2 = 0; i2 < this.segments.length; i2++) {
                if (i2 == remainder) {
                    maximumSegmentSize--;
                }
                this.segments[i2] = createSegment(segmentSize, maximumSegmentSize);
            }
            return;
        }
        int i3 = 0;
        while (true) {
            Segment<K, V>[] segmentArr = this.segments;
            if (i3 < segmentArr.length) {
                segmentArr[i3] = createSegment(segmentSize, -1);
                i3++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean evictsBySize() {
        return this.maximumSize != -1;
    }

    /* access modifiers changed from: package-private */
    public boolean expires() {
        return expiresAfterWrite() || expiresAfterAccess();
    }

    /* access modifiers changed from: package-private */
    public boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    public boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    public enum EntryFactory {
        STRONG {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongEntry(key, hash, next);
            }
        },
        STRONG_EXPIRABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongExpirableEntry(key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyExpirableEntry(original, newEntry);
                return newEntry;
            }
        },
        STRONG_EVICTABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongEvictableEntry(key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyEvictableEntry(original, newEntry);
                return newEntry;
            }
        },
        STRONG_EXPIRABLE_EVICTABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new StrongExpirableEvictableEntry(key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyExpirableEntry(original, newEntry);
                copyEvictableEntry(original, newEntry);
                return newEntry;
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakEntry(segment.keyReferenceQueue, key, hash, next);
            }
        },
        WEAK_EXPIRABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakExpirableEntry(segment.keyReferenceQueue, key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyExpirableEntry(original, newEntry);
                return newEntry;
            }
        },
        WEAK_EVICTABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakEvictableEntry(segment.keyReferenceQueue, key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyEvictableEntry(original, newEntry);
                return newEntry;
            }
        },
        WEAK_EXPIRABLE_EVICTABLE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
                return new WeakExpirableEvictableEntry(segment.keyReferenceQueue, key, hash, next);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.EntryFactory
            public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
                ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
                copyExpirableEntry(original, newEntry);
                copyEvictableEntry(original, newEntry);
                return newEntry;
            }
        };
        
        static final int EVICTABLE_MASK = 2;
        static final int EXPIRABLE_MASK = 1;
        static final EntryFactory[][] factories = {new EntryFactory[]{STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE}, new EntryFactory[0], new EntryFactory[]{WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE}};

        /* access modifiers changed from: package-private */
        public abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry);

        static EntryFactory getFactory(Strength keyStrength, boolean expireAfterWrite, boolean evictsBySize) {
            return factories[keyStrength.ordinal()][(evictsBySize ? 2 : 0) | (expireAfterWrite ? 1 : 0)];
        }

        /* access modifiers changed from: package-private */
        public <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            return newEntry(segment, original.getKey(), original.getHash(), newNext);
        }

        /* access modifiers changed from: package-private */
        public <K, V> void copyExpirableEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
            newEntry.setExpirationTime(original.getExpirationTime());
            MapMakerInternalMap.connectExpirables(original.getPreviousExpirable(), newEntry);
            MapMakerInternalMap.connectExpirables(newEntry, original.getNextExpirable());
            MapMakerInternalMap.nullifyExpirable(original);
        }

        /* access modifiers changed from: package-private */
        public <K, V> void copyEvictableEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
            MapMakerInternalMap.connectEvictables(original.getPreviousEvictable(), newEntry);
            MapMakerInternalMap.connectEvictables(newEntry, original.getNextEvictable());
            MapMakerInternalMap.nullifyEvictable(original);
        }
    }

    static <K, V> ValueReference<K, V> unset() {
        return (ValueReference<K, V>) UNSET;
    }

    /* access modifiers changed from: private */
    public enum NullEntry implements ReferenceEntry<Object, Object> {
        INSTANCE;

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ValueReference<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setValueReference(ValueReference<Object, Object> valueReference) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<Object, Object> getNext() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public int getHash() {
            return 0;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public Object getKey() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            return 0;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextExpirable() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousExpirable() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<Object, Object> getNextEvictable() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<Object, Object> referenceEntry) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<Object, Object> getPreviousEvictable() {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<Object, Object> referenceEntry) {
        }
    }

    static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
        AbstractReferenceEntry() {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }
    }

    static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    static <E> Queue<E> discardingQueue() {
        return (Queue<E>) DISCARDING_QUEUE;
    }

    static class StrongEntry<K, V> implements ReferenceEntry<K, V> {
        final int hash;
        final K key;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        StrongEntry(K key2, int hash2, @Nullable ReferenceEntry<K, V> next2) {
            this.key = key2;
            this.hash = hash2;
            this.next = next2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference2) {
            ValueReference<K, V> previous = this.valueReference;
            this.valueReference = valueReference2;
            previous.clear(valueReference2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class StrongExpirableEntry<K, V> extends StrongEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        StrongExpirableEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }
    }

    static final class StrongEvictableEntry<K, V> extends StrongEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        StrongEvictableEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static final class StrongExpirableEvictableEntry<K, V> extends StrongEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        StrongExpirableEvictableEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.StrongEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static class SoftEntry<K, V> extends SoftReference<K> implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        SoftEntry(ReferenceQueue<K> queue, K key, int hash2, @Nullable ReferenceEntry<K, V> next2) {
            super(key, queue);
            this.hash = hash2;
            this.next = next2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public K getKey() {
            return (K) get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference2) {
            ValueReference<K, V> previous = this.valueReference;
            this.valueReference = valueReference2;
            previous.clear(valueReference2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class SoftExpirableEntry<K, V> extends SoftEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        SoftExpirableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }
    }

    static final class SoftEvictableEntry<K, V> extends SoftEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        SoftEvictableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static final class SoftExpirableEvictableEntry<K, V> extends SoftEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        SoftExpirableEvictableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.SoftEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
        final int hash;
        final ReferenceEntry<K, V> next;
        volatile ValueReference<K, V> valueReference = MapMakerInternalMap.unset();

        WeakEntry(ReferenceQueue<K> queue, K key, int hash2, @Nullable ReferenceEntry<K, V> next2) {
            super(key, queue);
            this.hash = hash2;
            this.next = next2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public K getKey() {
            return (K) get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> referenceEntry) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ValueReference<K, V> getValueReference() {
            return this.valueReference;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setValueReference(ValueReference<K, V> valueReference2) {
            ValueReference<K, V> previous = this.valueReference;
            this.valueReference = valueReference2;
            previous.clear(valueReference2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public int getHash() {
            return this.hash;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNext() {
            return this.next;
        }
    }

    static final class WeakExpirableEntry<K, V> extends WeakEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        WeakExpirableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }
    }

    static final class WeakEvictableEntry<K, V> extends WeakEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();

        WeakEvictableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static final class WeakExpirableEvictableEntry<K, V> extends WeakEntry<K, V> implements ReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousEvictable = MapMakerInternalMap.nullEntry();
        ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
        volatile long time = Long.MAX_VALUE;

        WeakExpirableEvictableEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            super(queue, key, hash, next);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public long getExpirationTime() {
            return this.time;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setExpirationTime(long time2) {
            this.time = time2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextExpirable() {
            return this.nextExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextExpirable(ReferenceEntry<K, V> next) {
            this.nextExpirable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousExpirable() {
            return this.previousExpirable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
            this.previousExpirable = previous;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getNextEvictable() {
            return this.nextEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setNextEvictable(ReferenceEntry<K, V> next) {
            this.nextEvictable = next;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public ReferenceEntry<K, V> getPreviousEvictable() {
            return this.previousEvictable;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.WeakEntry, com.google.common.collect.MapMakerInternalMap.ReferenceEntry
        public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
            this.previousEvictable = previous;
        }
    }

    static final class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        WeakValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry2) {
            super(referent, queue);
            this.entry = entry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(ValueReference<K, V> valueReference) {
            clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry2) {
            return new WeakValueReference(queue, value, entry2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    static final class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
        final ReferenceEntry<K, V> entry;

        SoftValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry2) {
            super(referent, queue);
            this.entry = entry2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return this.entry;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(ValueReference<K, V> valueReference) {
            clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry2) {
            return new SoftValueReference(queue, value, entry2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    static final class StrongValueReference<K, V> implements ValueReference<K, V> {
        final V referent;

        StrongValueReference(V referent2) {
            this.referent = referent2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V get() {
            return this.referent;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() {
            return get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(ValueReference<K, V> valueReference) {
        }
    }

    static int rehash(int h) {
        int h2 = h + ((h << 15) ^ -12931);
        int h3 = h2 ^ (h2 >>> 10);
        int h4 = h3 + (h3 << 3);
        int h5 = h4 ^ (h4 >>> 6);
        int h6 = h5 + (h5 << 2) + (h5 << 14);
        return (h6 >>> 16) ^ h6;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        return segmentFor(hash).newEntry(key, hash, next);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
        return segmentFor(original.getHash()).copyEntry(original, newNext);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ValueReference<K, V> newValueReference(ReferenceEntry<K, V> entry, V value) {
        return this.valueStrength.referenceValue(segmentFor(entry.getHash()), entry, value);
    }

    /* access modifiers changed from: package-private */
    public int hash(Object key) {
        return rehash(this.keyEquivalence.hash(key));
    }

    /* access modifiers changed from: package-private */
    public void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    /* access modifiers changed from: package-private */
    public void reclaimKey(ReferenceEntry<K, V> entry) {
        int hash = entry.getHash();
        segmentFor(hash).reclaimKey(entry, hash);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isLive(ReferenceEntry<K, V> entry) {
        return segmentFor(entry.getHash()).getLiveValue(entry) != null;
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> segmentFor(int hash) {
        return this.segments[(hash >>> this.segmentShift) & this.segmentMask];
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> createSegment(int initialCapacity, int maxSegmentSize) {
        return new Segment<>(this, initialCapacity, maxSegmentSize);
    }

    /* access modifiers changed from: package-private */
    public V getLiveValue(ReferenceEntry<K, V> entry) {
        V value;
        if (entry.getKey() == null || (value = entry.getValueReference().get()) == null) {
            return null;
        }
        if (!expires() || !isExpired(entry)) {
            return value;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isExpired(ReferenceEntry<K, V> entry) {
        return isExpired(entry, this.ticker.read());
    }

    /* access modifiers changed from: package-private */
    public boolean isExpired(ReferenceEntry<K, V> entry, long now) {
        return now - entry.getExpirationTime() > 0;
    }

    static <K, V> void connectExpirables(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextExpirable(next);
        next.setPreviousExpirable(previous);
    }

    static <K, V> void nullifyExpirable(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = nullEntry();
        nulled.setNextExpirable(nullEntry);
        nulled.setPreviousExpirable(nullEntry);
    }

    /* access modifiers changed from: package-private */
    public void processPendingNotifications() {
        while (true) {
            MapMaker.RemovalNotification<K, V> notification = this.removalNotificationQueue.poll();
            if (notification != null) {
                try {
                    this.removalListener.onRemoval(notification);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception thrown by removal listener", (Throwable) e);
                }
            } else {
                return;
            }
        }
    }

    static <K, V> void connectEvictables(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextEvictable(next);
        next.setPreviousEvictable(previous);
    }

    static <K, V> void nullifyEvictable(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = nullEntry();
        nulled.setNextEvictable(nullEntry);
        nulled.setPreviousEvictable(nullEntry);
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V>[] newSegmentArray(int ssize) {
        return new Segment[ssize];
    }

    /* access modifiers changed from: package-private */
    public static class Segment<K, V> extends ReentrantLock {
        volatile int count;
        @GuardedBy("Segment.this")
        final Queue<ReferenceEntry<K, V>> evictionQueue;
        @GuardedBy("Segment.this")
        final Queue<ReferenceEntry<K, V>> expirationQueue;
        final ReferenceQueue<K> keyReferenceQueue;
        final MapMakerInternalMap<K, V> map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        final ReferenceQueue<V> valueReferenceQueue;

        Segment(MapMakerInternalMap<K, V> map2, int initialCapacity, int maxSegmentSize2) {
            Queue<ReferenceEntry<K, V>> queue;
            Queue<ReferenceEntry<K, V>> queue2;
            Queue<ReferenceEntry<K, V>> queue3;
            this.map = map2;
            this.maxSegmentSize = maxSegmentSize2;
            initTable(newEntryArray(initialCapacity));
            ReferenceQueue<V> referenceQueue = null;
            this.keyReferenceQueue = map2.usesKeyReferences() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = map2.usesValueReferences() ? new ReferenceQueue<>() : referenceQueue;
            if (map2.evictsBySize() || map2.expiresAfterAccess()) {
                queue = new ConcurrentLinkedQueue<>();
            } else {
                queue = MapMakerInternalMap.discardingQueue();
            }
            this.recencyQueue = queue;
            if (map2.evictsBySize()) {
                queue2 = new EvictionQueue<>();
            } else {
                queue2 = MapMakerInternalMap.discardingQueue();
            }
            this.evictionQueue = queue2;
            if (map2.expires()) {
                queue3 = new ExpirationQueue<>();
            } else {
                queue3 = MapMakerInternalMap.discardingQueue();
            }
            this.expirationQueue = queue3;
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int size) {
            return new AtomicReferenceArray<>(size);
        }

        /* access modifiers changed from: package-private */
        public void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> newTable) {
            this.threshold = (newTable.length() * 3) / 4;
            int i = this.threshold;
            if (i == this.maxSegmentSize) {
                this.threshold = i + 1;
            }
            this.table = newTable;
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return this.map.entryFactory.newEntry(this, key, hash, next);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            if (original.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = valueReference.get();
            if (value == null && !valueReference.isComputingReference()) {
                return null;
            }
            ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
            newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
            return newEntry;
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void setValue(ReferenceEntry<K, V> entry, V value) {
            entry.setValueReference(this.map.valueStrength.referenceValue(this, entry, value));
            recordWrite(entry);
        }

        /* access modifiers changed from: package-private */
        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                drainValueReferenceQueue();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void drainKeyReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends K> ref = this.keyReferenceQueue.poll();
                if (ref != null) {
                    this.map.reclaimKey((ReferenceEntry) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void drainValueReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends V> ref = this.valueReferenceQueue.poll();
                if (ref != null) {
                    this.map.reclaimValue((ValueReference) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                clearValueReferenceQueue();
            }
        }

        /* access modifiers changed from: package-private */
        public void clearKeyReferenceQueue() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void clearValueReferenceQueue() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void recordRead(ReferenceEntry<K, V> entry) {
            if (this.map.expiresAfterAccess()) {
                recordExpirationTime(entry, this.map.expireAfterAccessNanos);
            }
            this.recencyQueue.add(entry);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void recordLockedRead(ReferenceEntry<K, V> entry) {
            this.evictionQueue.add(entry);
            if (this.map.expiresAfterAccess()) {
                recordExpirationTime(entry, this.map.expireAfterAccessNanos);
                this.expirationQueue.add(entry);
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void recordWrite(ReferenceEntry<K, V> entry) {
            long expiration;
            drainRecencyQueue();
            this.evictionQueue.add(entry);
            if (this.map.expires()) {
                if (this.map.expiresAfterAccess()) {
                    expiration = this.map.expireAfterAccessNanos;
                } else {
                    expiration = this.map.expireAfterWriteNanos;
                }
                recordExpirationTime(entry, expiration);
                this.expirationQueue.add(entry);
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void drainRecencyQueue() {
            while (true) {
                ReferenceEntry<K, V> e = this.recencyQueue.poll();
                if (e != null) {
                    if (this.evictionQueue.contains(e)) {
                        this.evictionQueue.add(e);
                    }
                    if (this.map.expiresAfterAccess() && this.expirationQueue.contains(e)) {
                        this.expirationQueue.add(e);
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void recordExpirationTime(ReferenceEntry<K, V> entry, long expirationNanos) {
            entry.setExpirationTime(this.map.ticker.read() + expirationNanos);
        }

        /* access modifiers changed from: package-private */
        public void tryExpireEntries() {
            if (tryLock()) {
                try {
                    expireEntries();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void expireEntries() {
            ReferenceEntry<K, V> e;
            drainRecencyQueue();
            if (!this.expirationQueue.isEmpty()) {
                long now = this.map.ticker.read();
                do {
                    e = this.expirationQueue.peek();
                    if (e == null || !this.map.isExpired(e, now)) {
                        return;
                    }
                } while (removeEntry(e, e.getHash(), MapMaker.RemovalCause.EXPIRED));
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public void enqueueNotification(ReferenceEntry<K, V> entry, MapMaker.RemovalCause cause) {
            enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference().get(), cause);
        }

        /* access modifiers changed from: package-private */
        public void enqueueNotification(@Nullable K key, int hash, @Nullable V value, MapMaker.RemovalCause cause) {
            if (this.map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE) {
                this.map.removalNotificationQueue.offer(new MapMaker.RemovalNotification<>(key, value, cause));
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public boolean evictEntries() {
            if (!this.map.evictsBySize() || this.count < this.maxSegmentSize) {
                return false;
            }
            drainRecencyQueue();
            ReferenceEntry<K, V> e = this.evictionQueue.remove();
            if (removeEntry(e, e.getHash(), MapMaker.RemovalCause.SIZE)) {
                return true;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public ReferenceEntry<K, V> getFirst(int hash) {
            AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
            return table2.get((table2.length() - 1) & hash);
        }

        /* access modifiers changed from: package-private */
        public ReferenceEntry<K, V> getEntry(Object key, int hash) {
            if (this.count == 0) {
                return null;
            }
            for (ReferenceEntry<K, V> e = getFirst(hash); e != null; e = e.getNext()) {
                if (e.getHash() == hash) {
                    K entryKey = e.getKey();
                    if (entryKey == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(key, entryKey)) {
                        return e;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public ReferenceEntry<K, V> getLiveEntry(Object key, int hash) {
            ReferenceEntry<K, V> e = getEntry(key, hash);
            if (e == null) {
                return null;
            }
            if (!this.map.expires() || !this.map.isExpired(e)) {
                return e;
            }
            tryExpireEntries();
            return null;
        }

        /* access modifiers changed from: package-private */
        public V get(Object key, int hash) {
            try {
                ReferenceEntry<K, V> e = getLiveEntry(key, hash);
                if (e == null) {
                    return null;
                }
                V value = e.getValueReference().get();
                if (value != null) {
                    recordRead(e);
                } else {
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return value;
            } finally {
                postReadCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean containsKey(Object key, int hash) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    ReferenceEntry<K, V> e = getLiveEntry(key, hash);
                    if (e == null) {
                        return false;
                    }
                    if (e.getValueReference().get() != null) {
                        z = true;
                    }
                    postReadCleanup();
                    return z;
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public boolean containsValue(Object value) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                    int length = table2.length();
                    for (int i = 0; i < length; i++) {
                        for (ReferenceEntry<K, V> e = table2.get(i); e != null; e = e.getNext()) {
                            V entryValue = getLiveValue(e);
                            if (entryValue != null) {
                                if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                                    postReadCleanup();
                                    return true;
                                }
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public V put(K key, int hash, V value, boolean onlyIfAbsent) {
            lock();
            try {
                preWriteCleanup();
                int newCount = this.count + 1;
                if (newCount > this.threshold) {
                    expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue == null) {
                            this.modCount++;
                            setValue(e, value);
                            if (!valueReference.isComputingReference()) {
                                enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                                newCount = this.count;
                            } else if (evictEntries()) {
                                newCount = this.count + 1;
                            }
                            this.count = newCount;
                            return null;
                        } else if (onlyIfAbsent) {
                            recordLockedRead(e);
                            unlock();
                            postWriteCleanup();
                            return entryValue;
                        } else {
                            this.modCount++;
                            enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                            setValue(e, value);
                            unlock();
                            postWriteCleanup();
                            return entryValue;
                        }
                    }
                }
                this.modCount++;
                ReferenceEntry<K, V> newEntry = newEntry(key, hash, first);
                setValue(newEntry, value);
                table2.set(index, newEntry);
                if (evictEntries()) {
                    newCount = this.count + 1;
                }
                this.count = newCount;
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> oldTable = this.table;
            int oldCapacity = oldTable.length();
            if (oldCapacity < 1073741824) {
                int newCount = this.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> newTable = newEntryArray(oldCapacity << 1);
                this.threshold = (newTable.length() * 3) / 4;
                int newMask = newTable.length() - 1;
                for (int oldIndex = 0; oldIndex < oldCapacity; oldIndex++) {
                    ReferenceEntry<K, V> head = oldTable.get(oldIndex);
                    if (head != null) {
                        ReferenceEntry<K, V> next = head.getNext();
                        int headIndex = head.getHash() & newMask;
                        if (next == null) {
                            newTable.set(headIndex, head);
                        } else {
                            ReferenceEntry<K, V> tail = head;
                            int tailIndex = headIndex;
                            for (ReferenceEntry<K, V> e = next; e != null; e = e.getNext()) {
                                int newIndex = e.getHash() & newMask;
                                if (newIndex != tailIndex) {
                                    tailIndex = newIndex;
                                    tail = e;
                                }
                            }
                            newTable.set(tailIndex, tail);
                            for (ReferenceEntry<K, V> e2 = head; e2 != tail; e2 = e2.getNext()) {
                                int newIndex2 = e2.getHash() & newMask;
                                ReferenceEntry<K, V> newFirst = copyEntry(e2, newTable.get(newIndex2));
                                if (newFirst != null) {
                                    newTable.set(newIndex2, newFirst);
                                } else {
                                    removeCollectedEntry(e2);
                                    newCount--;
                                }
                            }
                        }
                    }
                }
                this.table = newTable;
                this.count = newCount;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean replace(K key, int hash, V oldValue, V newValue) {
            Throwable th;
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue == null) {
                            if (isCollected(valueReference)) {
                                int i = this.count - 1;
                                this.modCount++;
                                enqueueNotification(entryKey, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                                table2.set(index, removeFromChain(first, e));
                                this.count--;
                            }
                            unlock();
                            postWriteCleanup();
                            return false;
                        }
                        try {
                            if (this.map.valueEquivalence.equivalent(oldValue, entryValue)) {
                                this.modCount++;
                                enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                                try {
                                    setValue(e, newValue);
                                    unlock();
                                    postWriteCleanup();
                                    return true;
                                } catch (Throwable th2) {
                                    th = th2;
                                    unlock();
                                    postWriteCleanup();
                                    throw th;
                                }
                            } else {
                                recordLockedRead(e);
                                unlock();
                                postWriteCleanup();
                                return false;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            unlock();
                            postWriteCleanup();
                            throw th;
                        }
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th4) {
                th = th4;
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public V replace(K key, int hash, V newValue) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue == null) {
                            if (isCollected(valueReference)) {
                                int i = this.count - 1;
                                this.modCount++;
                                enqueueNotification(entryKey, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                                table2.set(index, removeFromChain(first, e));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                        setValue(e, newValue);
                        unlock();
                        postWriteCleanup();
                        return entryValue;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public V remove(Object key, int hash) {
            MapMaker.RemovalCause cause;
            lock();
            try {
                preWriteCleanup();
                int i = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue != null) {
                            cause = MapMaker.RemovalCause.EXPLICIT;
                        } else if (isCollected(valueReference)) {
                            cause = MapMaker.RemovalCause.COLLECTED;
                        } else {
                            unlock();
                            postWriteCleanup();
                            return null;
                        }
                        this.modCount++;
                        enqueueNotification(entryKey, hash, entryValue, cause);
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return entryValue;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(Object key, int hash, Object value) {
            Throwable th;
            MapMaker.RemovalCause cause;
            lock();
            try {
                preWriteCleanup();
                int i = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null) {
                        try {
                            if (this.map.keyEquivalence.equivalent(key, entryKey)) {
                                ValueReference<K, V> valueReference = e.getValueReference();
                                V entryValue = valueReference.get();
                                try {
                                    if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                                        cause = MapMaker.RemovalCause.EXPLICIT;
                                    } else if (isCollected(valueReference)) {
                                        cause = MapMaker.RemovalCause.COLLECTED;
                                    } else {
                                        unlock();
                                        postWriteCleanup();
                                        return false;
                                    }
                                    this.modCount++;
                                    enqueueNotification(entryKey, hash, entryValue, cause);
                                    table2.set(index, removeFromChain(first, e));
                                    this.count--;
                                    boolean z = cause == MapMaker.RemovalCause.EXPLICIT;
                                    unlock();
                                    postWriteCleanup();
                                    return z;
                                } catch (Throwable th2) {
                                    th = th2;
                                    unlock();
                                    postWriteCleanup();
                                    throw th;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            unlock();
                            postWriteCleanup();
                            throw th;
                        }
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th4) {
                th = th4;
                unlock();
                postWriteCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                    if (this.map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE) {
                        for (int i = 0; i < table2.length(); i++) {
                            for (ReferenceEntry<K, V> e = table2.get(i); e != null; e = e.getNext()) {
                                if (!e.getValueReference().isComputingReference()) {
                                    enqueueNotification(e, MapMaker.RemovalCause.EXPLICIT);
                                }
                            }
                        }
                    }
                    for (int i2 = 0; i2 < table2.length(); i2++) {
                        table2.set(i2, null);
                    }
                    clearReferenceQueues();
                    this.evictionQueue.clear();
                    this.expirationQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public ReferenceEntry<K, V> removeFromChain(ReferenceEntry<K, V> first, ReferenceEntry<K, V> entry) {
            this.evictionQueue.remove(entry);
            this.expirationQueue.remove(entry);
            int newCount = this.count;
            ReferenceEntry<K, V> newFirst = entry.getNext();
            for (ReferenceEntry<K, V> e = first; e != entry; e = e.getNext()) {
                ReferenceEntry<K, V> next = copyEntry(e, newFirst);
                if (next != null) {
                    newFirst = next;
                } else {
                    removeCollectedEntry(e);
                    newCount--;
                }
            }
            this.count = newCount;
            return newFirst;
        }

        /* access modifiers changed from: package-private */
        public void removeCollectedEntry(ReferenceEntry<K, V> entry) {
            enqueueNotification(entry, MapMaker.RemovalCause.COLLECTED);
            this.evictionQueue.remove(entry);
            this.expirationQueue.remove(entry);
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimKey(ReferenceEntry<K, V> entry, int hash) {
            lock();
            try {
                int i = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    if (e == entry) {
                        this.modCount++;
                        enqueueNotification(e.getKey(), hash, e.getValueReference().get(), MapMaker.RemovalCause.COLLECTED);
                        table2.set(index, removeFromChain(first, e));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimValue(K key, int hash, ValueReference<K, V> valueReference) {
            lock();
            try {
                int i = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        if (e.getValueReference() == valueReference) {
                            this.modCount++;
                            enqueueNotification(key, hash, valueReference.get(), MapMaker.RemovalCause.COLLECTED);
                            table2.set(index, removeFromChain(first, e));
                            this.count--;
                            return true;
                        } else {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                postWriteCleanup();
                            }
                            return false;
                        }
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean clearValue(K key, int hash, ValueReference<K, V> valueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
                int index = (table2.length() - 1) & hash;
                ReferenceEntry<K, V> first = table2.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        if (e.getValueReference() == valueReference) {
                            table2.set(index, removeFromChain(first, e));
                            return true;
                        } else {
                            unlock();
                            postWriteCleanup();
                            return false;
                        }
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public boolean removeEntry(ReferenceEntry<K, V> entry, int hash, MapMaker.RemovalCause cause) {
            int i = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table2 = this.table;
            int index = (table2.length() - 1) & hash;
            ReferenceEntry<K, V> first = table2.get(index);
            for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                if (e == entry) {
                    this.modCount++;
                    enqueueNotification(e.getKey(), hash, e.getValueReference().get(), cause);
                    table2.set(index, removeFromChain(first, e));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean isCollected(ValueReference<K, V> valueReference) {
            if (!valueReference.isComputingReference() && valueReference.get() == null) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public V getLiveValue(ReferenceEntry<K, V> entry) {
            if (entry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V value = entry.getValueReference().get();
            if (value == null) {
                tryDrainReferenceQueues();
                return null;
            } else if (!this.map.expires() || !this.map.isExpired(entry)) {
                return value;
            } else {
                tryExpireEntries();
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & MapMakerInternalMap.DRAIN_THRESHOLD) == 0) {
                runCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("Segment.this")
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void postWriteCleanup() {
            runUnlockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runCleanup() {
            runLockedCleanup();
            runUnlockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void runUnlockedCleanup() {
            if (!isHeldByCurrentThread()) {
                this.map.processPendingNotifications();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class EvictionQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() {
            /* class com.google.common.collect.MapMakerInternalMap.EvictionQueue.AnonymousClass1 */
            ReferenceEntry<K, V> nextEvictable = this;
            ReferenceEntry<K, V> previousEvictable = this;

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public ReferenceEntry<K, V> getNextEvictable() {
                return this.nextEvictable;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public void setNextEvictable(ReferenceEntry<K, V> next) {
                this.nextEvictable = next;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public ReferenceEntry<K, V> getPreviousEvictable() {
                return this.previousEvictable;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public void setPreviousEvictable(ReferenceEntry<K, V> previous) {
                this.previousEvictable = previous;
            }
        };

        EvictionQueue() {
        }

        @Override // java.util.Queue
        public /* bridge */ /* synthetic */ boolean offer(Object obj) {
            return offer((ReferenceEntry) ((ReferenceEntry) obj));
        }

        public boolean offer(ReferenceEntry<K, V> entry) {
            MapMakerInternalMap.connectEvictables(entry.getPreviousEvictable(), entry.getNextEvictable());
            MapMakerInternalMap.connectEvictables(this.head.getPreviousEvictable(), entry);
            MapMakerInternalMap.connectEvictables(entry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> next = this.head.getNextEvictable();
            if (next == this.head) {
                return null;
            }
            return next;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> next = this.head.getNextEvictable();
            if (next == this.head) {
                return null;
            }
            remove(next);
            return next;
        }

        public boolean remove(Object o) {
            ReferenceEntry<K, V> e = (ReferenceEntry) o;
            ReferenceEntry<K, V> previous = e.getPreviousEvictable();
            ReferenceEntry<K, V> next = e.getNextEvictable();
            MapMakerInternalMap.connectEvictables(previous, next);
            MapMakerInternalMap.nullifyEvictable(e);
            return next != NullEntry.INSTANCE;
        }

        public boolean contains(Object o) {
            return ((ReferenceEntry) o).getNextEvictable() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.head.getNextEvictable() == this.head;
        }

        public int size() {
            int size = 0;
            for (ReferenceEntry<K, V> e = this.head.getNextEvictable(); e != this.head; e = e.getNextEvictable()) {
                size++;
            }
            return size;
        }

        public void clear() {
            ReferenceEntry<K, V> e = this.head.getNextEvictable();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (e != referenceEntry) {
                    ReferenceEntry<K, V> next = e.getNextEvictable();
                    MapMakerInternalMap.nullifyEvictable(e);
                    e = next;
                } else {
                    referenceEntry.setNextEvictable(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousEvictable(referenceEntry2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                /* class com.google.common.collect.MapMakerInternalMap.EvictionQueue.AnonymousClass2 */

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractSequentialIterator
                public /* bridge */ /* synthetic */ Object computeNext(Object obj) {
                    return computeNext((ReferenceEntry) ((ReferenceEntry) obj));
                }

                /* access modifiers changed from: protected */
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                    ReferenceEntry<K, V> next = previous.getNextEvictable();
                    if (next == EvictionQueue.this.head) {
                        return null;
                    }
                    return next;
                }
            };
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ExpirationQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
        final ReferenceEntry<K, V> head = new AbstractReferenceEntry<K, V>() {
            /* class com.google.common.collect.MapMakerInternalMap.ExpirationQueue.AnonymousClass1 */
            ReferenceEntry<K, V> nextExpirable = this;
            ReferenceEntry<K, V> previousExpirable = this;

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public long getExpirationTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public void setExpirationTime(long time) {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public ReferenceEntry<K, V> getNextExpirable() {
                return this.nextExpirable;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public void setNextExpirable(ReferenceEntry<K, V> next) {
                this.nextExpirable = next;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public ReferenceEntry<K, V> getPreviousExpirable() {
                return this.previousExpirable;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.ReferenceEntry, com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
            public void setPreviousExpirable(ReferenceEntry<K, V> previous) {
                this.previousExpirable = previous;
            }
        };

        ExpirationQueue() {
        }

        @Override // java.util.Queue
        public /* bridge */ /* synthetic */ boolean offer(Object obj) {
            return offer((ReferenceEntry) ((ReferenceEntry) obj));
        }

        public boolean offer(ReferenceEntry<K, V> entry) {
            MapMakerInternalMap.connectExpirables(entry.getPreviousExpirable(), entry.getNextExpirable());
            MapMakerInternalMap.connectExpirables(this.head.getPreviousExpirable(), entry);
            MapMakerInternalMap.connectExpirables(entry, this.head);
            return true;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> peek() {
            ReferenceEntry<K, V> next = this.head.getNextExpirable();
            if (next == this.head) {
                return null;
            }
            return next;
        }

        @Override // java.util.Queue
        public ReferenceEntry<K, V> poll() {
            ReferenceEntry<K, V> next = this.head.getNextExpirable();
            if (next == this.head) {
                return null;
            }
            remove(next);
            return next;
        }

        public boolean remove(Object o) {
            ReferenceEntry<K, V> e = (ReferenceEntry) o;
            ReferenceEntry<K, V> previous = e.getPreviousExpirable();
            ReferenceEntry<K, V> next = e.getNextExpirable();
            MapMakerInternalMap.connectExpirables(previous, next);
            MapMakerInternalMap.nullifyExpirable(e);
            return next != NullEntry.INSTANCE;
        }

        public boolean contains(Object o) {
            return ((ReferenceEntry) o).getNextExpirable() != NullEntry.INSTANCE;
        }

        public boolean isEmpty() {
            return this.head.getNextExpirable() == this.head;
        }

        public int size() {
            int size = 0;
            for (ReferenceEntry<K, V> e = this.head.getNextExpirable(); e != this.head; e = e.getNextExpirable()) {
                size++;
            }
            return size;
        }

        public void clear() {
            ReferenceEntry<K, V> e = this.head.getNextExpirable();
            while (true) {
                ReferenceEntry<K, V> referenceEntry = this.head;
                if (e != referenceEntry) {
                    ReferenceEntry<K, V> next = e.getNextExpirable();
                    MapMakerInternalMap.nullifyExpirable(e);
                    e = next;
                } else {
                    referenceEntry.setNextExpirable(referenceEntry);
                    ReferenceEntry<K, V> referenceEntry2 = this.head;
                    referenceEntry2.setPreviousExpirable(referenceEntry2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<ReferenceEntry<K, V>> iterator() {
            return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
                /* class com.google.common.collect.MapMakerInternalMap.ExpirationQueue.AnonymousClass2 */

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractSequentialIterator
                public /* bridge */ /* synthetic */ Object computeNext(Object obj) {
                    return computeNext((ReferenceEntry) ((ReferenceEntry) obj));
                }

                /* access modifiers changed from: protected */
                public ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                    ReferenceEntry<K, V> next = previous.getNextExpirable();
                    if (next == ExpirationQueue.this.head) {
                        return null;
                    }
                    return next;
                }
            };
        }
    }

    static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?>> mapReference;

        public CleanupMapTask(MapMakerInternalMap<?, ?> map) {
            this.mapReference = new WeakReference<>(map);
        }

        public void run() {
            MapMakerInternalMap<?, ?> map = this.mapReference.get();
            if (map != null) {
                for (Segment<K, V> segment : map.segments) {
                    segment.runCleanup();
                }
                return;
            }
            throw new CancellationException();
        }
    }

    public boolean isEmpty() {
        long sum = 0;
        Segment<K, V>[] segments2 = this.segments;
        for (int i = 0; i < segments2.length; i++) {
            if (segments2[i].count != 0) {
                return false;
            }
            sum += (long) segments2[i].modCount;
        }
        if (sum == 0) {
            return true;
        }
        for (int i2 = 0; i2 < segments2.length; i2++) {
            if (segments2[i2].count != 0) {
                return false;
            }
            sum -= (long) segments2[i2].modCount;
        }
        if (sum != 0) {
            return false;
        }
        return true;
    }

    public int size() {
        Segment<K, V>[] segments2;
        long sum = 0;
        for (Segment<K, V> segment : this.segments) {
            sum += (long) segment.count;
        }
        return Ints.saturatedCast(sum);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).get(key, hash);
    }

    /* access modifiers changed from: package-private */
    public ReferenceEntry<K, V> getEntry(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).getEntry(key, hash);
    }

    public boolean containsKey(@Nullable Object key) {
        if (key == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).containsKey(key, hash);
    }

    public boolean containsValue(@Nullable Object value) {
        Segment<K, V>[] segments2;
        int i = 0;
        if (value == null) {
            return false;
        }
        Segment<K, V>[] segments3 = this.segments;
        long last = -1;
        int i2 = 0;
        while (i2 < 3) {
            int length = segments3.length;
            long sum = 0;
            int i3 = i;
            while (i3 < length) {
                Segment<K, V> segment = segments3[i3];
                int i4 = segment.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = segment.table;
                for (int j = 0; j < table.length(); j++) {
                    ReferenceEntry<K, V> e = table.get(j);
                    while (e != null) {
                        V v = segment.getLiveValue(e);
                        if (v != null) {
                            segments2 = segments3;
                            if (this.valueEquivalence.equivalent(value, v)) {
                                return true;
                            }
                        } else {
                            segments2 = segments3;
                        }
                        e = e.getNext();
                        segments3 = segments2;
                    }
                }
                sum += (long) segment.modCount;
                i3++;
                segments3 = segments3;
            }
            if (sum == last) {
                return false;
            }
            last = sum;
            i2++;
            segments3 = segments3;
            i = 0;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.MapMakerInternalMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash);
    }

    public boolean remove(@Nullable Object key, @Nullable Object value) {
        if (key == null || value == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash, value);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K key, @Nullable V oldValue, V newValue) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        if (oldValue == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, value);
    }

    public void clear() {
        for (Segment<K, V> segment : this.segments) {
            segment.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Values values2 = new Values();
        this.values = values2;
        return values2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    abstract class HashIterator<E> implements Iterator<E> {
        Segment<K, V> currentSegment;
        AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
        MapMakerInternalMap<K, V>.WriteThroughEntry lastReturned;
        ReferenceEntry<K, V> nextEntry;
        MapMakerInternalMap<K, V>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        @Override // java.util.Iterator
        public abstract E next();

        HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            advance();
        }

        /* access modifiers changed from: package-private */
        public final void advance() {
            this.nextExternal = null;
            if (!nextInChain() && !nextInTable()) {
                while (this.nextSegmentIndex >= 0) {
                    Segment<K, V>[] segmentArr = MapMakerInternalMap.this.segments;
                    int i = this.nextSegmentIndex;
                    this.nextSegmentIndex = i - 1;
                    this.currentSegment = segmentArr[i];
                    if (this.currentSegment.count != 0) {
                        this.currentTable = this.currentSegment.table;
                        this.nextTableIndex = this.currentTable.length() - 1;
                        if (nextInTable()) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInChain() {
            ReferenceEntry<K, V> referenceEntry = this.nextEntry;
            if (referenceEntry == null) {
                return false;
            }
            while (true) {
                this.nextEntry = referenceEntry.getNext();
                ReferenceEntry<K, V> referenceEntry2 = this.nextEntry;
                if (referenceEntry2 == null) {
                    return false;
                }
                if (advanceTo(referenceEntry2)) {
                    return true;
                }
                referenceEntry = this.nextEntry;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInTable() {
            while (true) {
                int i = this.nextTableIndex;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i - 1;
                ReferenceEntry<K, V> referenceEntry = atomicReferenceArray.get(i);
                this.nextEntry = referenceEntry;
                if (referenceEntry != null && (advanceTo(this.nextEntry) || nextInChain())) {
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean advanceTo(ReferenceEntry<K, V> entry) {
            try {
                K key = entry.getKey();
                Object liveValue = MapMakerInternalMap.this.getLiveValue(entry);
                if (liveValue != null) {
                    this.nextExternal = new WriteThroughEntry(key, liveValue);
                    return true;
                }
                this.currentSegment.postReadCleanup();
                return false;
            } finally {
                this.currentSegment.postReadCleanup();
            }
        }

        public boolean hasNext() {
            return this.nextExternal != null;
        }

        /* access modifiers changed from: package-private */
        public MapMakerInternalMap<K, V>.WriteThroughEntry nextEntry() {
            MapMakerInternalMap<K, V>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry != null) {
                this.lastReturned = writeThroughEntry;
                advance();
                return this.lastReturned;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.lastReturned != null);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    final class KeyIterator extends MapMakerInternalMap<K, V>.HashIterator {
        KeyIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public K next() {
            return (K) nextEntry().getKey();
        }
    }

    final class ValueIterator extends MapMakerInternalMap<K, V>.HashIterator {
        ValueIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public V next() {
            return (V) nextEntry().getValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final class WriteThroughEntry extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K key2, V value2) {
            this.key = key2;
            this.value = value2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry
        public boolean equals(@Nullable Object object) {
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> that = (Map.Entry) object;
            if (!this.key.equals(that.getKey()) || !this.value.equals(that.getValue())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.AbstractMapEntry
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V newValue) {
            V oldValue = (V) MapMakerInternalMap.this.put(this.key, newValue);
            this.value = newValue;
            return oldValue;
        }
    }

    final class EntryIterator extends MapMakerInternalMap<K, V>.HashIterator {
        EntryIterator() {
            super();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.HashIterator, java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object o) {
            return MapMakerInternalMap.this.containsKey(o);
        }

        public boolean remove(Object o) {
            return MapMakerInternalMap.this.remove(o) != null;
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object o) {
            return MapMakerInternalMap.this.containsValue(o);
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object o) {
            Map.Entry<?, ?> e;
            Object key;
            Object obj;
            if ((o instanceof Map.Entry) && (key = (e = (Map.Entry) o).getKey()) != null && (obj = MapMakerInternalMap.this.get(key)) != null && MapMakerInternalMap.this.valueEquivalence.equivalent(e.getValue(), obj)) {
                return true;
            }
            return false;
        }

        public boolean remove(Object o) {
            Map.Entry<?, ?> e;
            Object key;
            if ((o instanceof Map.Entry) && (key = (e = (Map.Entry) o).getKey()) != null && MapMakerInternalMap.this.remove(key, e.getValue())) {
                return true;
            }
            return false;
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this);
    }

    static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final int maximumSize;
        final MapMaker.RemovalListener<? super K, ? super V> removalListener;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength keyStrength2, Strength valueStrength2, Equivalence<Object> keyEquivalence2, Equivalence<Object> valueEquivalence2, long expireAfterWriteNanos2, long expireAfterAccessNanos2, int maximumSize2, int concurrencyLevel2, MapMaker.RemovalListener<? super K, ? super V> removalListener2, ConcurrentMap<K, V> delegate2) {
            this.keyStrength = keyStrength2;
            this.valueStrength = valueStrength2;
            this.keyEquivalence = keyEquivalence2;
            this.valueEquivalence = valueEquivalence2;
            this.expireAfterWriteNanos = expireAfterWriteNanos2;
            this.expireAfterAccessNanos = expireAfterAccessNanos2;
            this.maximumSize = maximumSize2;
            this.concurrencyLevel = concurrencyLevel2;
            this.removalListener = removalListener2;
            this.delegate = delegate2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void writeMapTo(ObjectOutputStream out) throws IOException {
            out.writeInt(this.delegate.size());
            for (Map.Entry<K, V> entry : this.delegate.entrySet()) {
                out.writeObject(entry.getKey());
                out.writeObject(entry.getValue());
            }
            out.writeObject(null);
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.collect.MapMaker] */
        /* JADX WARN: Type inference failed for: r1v4, types: [com.google.common.collect.MapMaker] */
        /* JADX WARN: Type inference failed for: r1v5, types: [com.google.common.collect.MapMaker] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.collect.MapMaker readMapMaker(java.io.ObjectInputStream r8) throws java.io.IOException {
            /*
                r7 = this;
                int r0 = r8.readInt()
                com.google.common.collect.MapMaker r1 = new com.google.common.collect.MapMaker
                r1.<init>()
                com.google.common.collect.MapMaker r1 = r1.initialCapacity(r0)
                com.google.common.collect.MapMakerInternalMap$Strength r2 = r7.keyStrength
                com.google.common.collect.MapMaker r1 = r1.setKeyStrength(r2)
                com.google.common.collect.MapMakerInternalMap$Strength r2 = r7.valueStrength
                com.google.common.collect.MapMaker r1 = r1.setValueStrength(r2)
                com.google.common.base.Equivalence<java.lang.Object> r2 = r7.keyEquivalence
                com.google.common.collect.MapMaker r1 = r1.keyEquivalence(r2)
                int r2 = r7.concurrencyLevel
                com.google.common.collect.MapMaker r1 = r1.concurrencyLevel(r2)
                com.google.common.collect.MapMaker$RemovalListener<? super K, ? super V> r2 = r7.removalListener
                r1.removalListener(r2)
                long r2 = r7.expireAfterWriteNanos
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 <= 0) goto L_0x0037
                java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS
                r1.expireAfterWrite(r2, r6)
            L_0x0037:
                long r2 = r7.expireAfterAccessNanos
                int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r4 <= 0) goto L_0x0042
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS
                r1.expireAfterAccess(r2, r4)
            L_0x0042:
                int r2 = r7.maximumSize
                r3 = -1
                if (r2 == r3) goto L_0x004a
                r1.maximumSize(r2)
            L_0x004a:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.AbstractSerializationProxy.readMapMaker(java.io.ObjectInputStream):com.google.common.collect.MapMaker");
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.ConcurrentMap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void readEntries(ObjectInputStream in) throws IOException, ClassNotFoundException {
            while (true) {
                Object readObject = in.readObject();
                if (readObject != null) {
                    this.delegate.put(readObject, in.readObject());
                } else {
                    return;
                }
            }
        }
    }

    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, int maximumSize, int concurrencyLevel, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> delegate) {
            super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, expireAfterWriteNanos, expireAfterAccessNanos, maximumSize, concurrencyLevel, removalListener, delegate);
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            writeMapTo(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.delegate = readMapMaker(in).makeMap();
            readEntries(in);
        }

        private Object readResolve() {
            return this.delegate;
        }
    }
}
