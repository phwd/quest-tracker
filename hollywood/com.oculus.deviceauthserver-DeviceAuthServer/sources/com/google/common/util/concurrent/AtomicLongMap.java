package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible
public final class AtomicLongMap<K> {
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, AtomicLong> map;

    private AtomicLongMap(ConcurrentHashMap<K, AtomicLong> map2) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(map2);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> m) {
        AtomicLongMap<K> result = create();
        result.putAll(m);
        return result;
    }

    public long get(K key) {
        AtomicLong atomic = this.map.get(key);
        if (atomic == null) {
            return 0;
        }
        return atomic.get();
    }

    public long incrementAndGet(K key) {
        return addAndGet(key, 1);
    }

    public long decrementAndGet(K key) {
        return addAndGet(key, -1);
    }

    public long addAndGet(K key, long delta) {
        AtomicLong atomic;
        do {
            atomic = this.map.get(key);
            if (atomic == null && (atomic = this.map.putIfAbsent(key, new AtomicLong(delta))) == null) {
                return delta;
            }
            while (true) {
                long oldValue = atomic.get();
                if (oldValue != 0) {
                    long newValue = oldValue + delta;
                    if (atomic.compareAndSet(oldValue, newValue)) {
                        return newValue;
                    }
                }
            }
        } while (!this.map.replace(key, atomic, new AtomicLong(delta)));
        return delta;
    }

    public long getAndIncrement(K key) {
        return getAndAdd(key, 1);
    }

    public long getAndDecrement(K key) {
        return getAndAdd(key, -1);
    }

    public long getAndAdd(K key, long delta) {
        AtomicLong atomic;
        do {
            atomic = this.map.get(key);
            if (atomic == null && (atomic = this.map.putIfAbsent(key, new AtomicLong(delta))) == null) {
                return 0;
            }
            while (true) {
                long oldValue = atomic.get();
                if (oldValue != 0) {
                    if (atomic.compareAndSet(oldValue, oldValue + delta)) {
                        return oldValue;
                    }
                }
            }
        } while (!this.map.replace(key, atomic, new AtomicLong(delta)));
        return 0;
    }

    public long put(K key, long newValue) {
        AtomicLong atomic;
        do {
            atomic = this.map.get(key);
            if (atomic == null && (atomic = this.map.putIfAbsent(key, new AtomicLong(newValue))) == null) {
                return 0;
            }
            while (true) {
                long oldValue = atomic.get();
                if (oldValue != 0) {
                    if (atomic.compareAndSet(oldValue, newValue)) {
                        return oldValue;
                    }
                }
            }
        } while (!this.map.replace(key, atomic, new AtomicLong(newValue)));
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.util.concurrent.AtomicLongMap<K> */
    /* JADX WARN: Multi-variable type inference failed */
    public void putAll(Map<? extends K, ? extends Long> m) {
        for (Map.Entry<? extends K, ? extends Long> entry : m.entrySet()) {
            put(entry.getKey(), ((Long) entry.getValue()).longValue());
        }
    }

    public long remove(K key) {
        long oldValue;
        AtomicLong atomic = this.map.get(key);
        if (atomic == null) {
            return 0;
        }
        do {
            oldValue = atomic.get();
            if (oldValue == 0) {
                break;
            }
        } while (!atomic.compareAndSet(oldValue, 0));
        this.map.remove(key, atomic);
        return oldValue;
    }

    public void removeAllZeros() {
        for (K key : this.map.keySet()) {
            AtomicLong atomic = this.map.get(key);
            if (atomic != null && atomic.get() == 0) {
                this.map.remove(key, atomic);
            }
        }
    }

    public long sum() {
        long sum = 0;
        for (AtomicLong value : this.map.values()) {
            sum += value.get();
        }
        return sum;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> result = this.asMap;
        if (result != null) {
            return result;
        }
        Map<K, Long> createAsMap = createAsMap();
        this.asMap = createAsMap;
        return createAsMap;
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>() {
            /* class com.google.common.util.concurrent.AtomicLongMap.AnonymousClass1 */

            public Long apply(AtomicLong atomic) {
                return Long.valueOf(atomic.get());
            }
        }));
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void clear() {
        this.map.clear();
    }

    public String toString() {
        return this.map.toString();
    }

    /* access modifiers changed from: package-private */
    public long putIfAbsent(K key, long newValue) {
        AtomicLong atomic;
        do {
            atomic = this.map.get(key);
            if (atomic == null && (atomic = this.map.putIfAbsent(key, new AtomicLong(newValue))) == null) {
                return 0;
            }
            long oldValue = atomic.get();
            if (oldValue != 0) {
                return oldValue;
            }
        } while (!this.map.replace(key, atomic, new AtomicLong(newValue)));
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean replace(K key, long expectedOldValue, long newValue) {
        if (expectedOldValue != 0) {
            AtomicLong atomic = this.map.get(key);
            if (atomic == null) {
                return false;
            }
            return atomic.compareAndSet(expectedOldValue, newValue);
        } else if (putIfAbsent(key, newValue) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean remove(K key, long value) {
        AtomicLong atomic = this.map.get(key);
        if (atomic == null) {
            return false;
        }
        long oldValue = atomic.get();
        if (oldValue != value) {
            return false;
        }
        if (oldValue != 0 && !atomic.compareAndSet(oldValue, 0)) {
            return false;
        }
        this.map.remove(key, atomic);
        return true;
    }
}
