package java.lang.reflect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/* access modifiers changed from: package-private */
public final class WeakCache<K, P, V> {
    private final ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map = new ConcurrentHashMap();
    private final ReferenceQueue<K> refQueue = new ReferenceQueue<>();
    private final ConcurrentMap<Supplier<V>, Boolean> reverseMap = new ConcurrentHashMap();
    private final BiFunction<K, P, ?> subKeyFactory;
    private final BiFunction<K, P, V> valueFactory;

    private interface Value<V> extends Supplier<V> {
    }

    public WeakCache(BiFunction<K, P, ?> subKeyFactory2, BiFunction<K, P, V> valueFactory2) {
        this.subKeyFactory = (BiFunction) Objects.requireNonNull(subKeyFactory2);
        this.valueFactory = (BiFunction) Objects.requireNonNull(valueFactory2);
    }

    public V get(K key, P parameter) {
        Objects.requireNonNull(parameter);
        expungeStaleEntries();
        Object cacheKey = CacheKey.valueOf(key, this.refQueue);
        ConcurrentMap<Object, Supplier<V>> valuesMap = this.map.get(cacheKey);
        if (valuesMap == null) {
            ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> concurrentMap = this.map;
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            valuesMap = concurrentHashMap;
            ConcurrentMap<Object, Supplier<V>> oldValuesMap = concurrentMap.putIfAbsent(cacheKey, concurrentHashMap);
            if (oldValuesMap != null) {
                valuesMap = oldValuesMap;
            }
        }
        Object subKey = Objects.requireNonNull(this.subKeyFactory.apply(key, parameter));
        Supplier<V> supplier = valuesMap.get(subKey);
        WeakCache<K, P, V>.Factory factory = null;
        while (true) {
            if (supplier != null) {
                V value = supplier.get();
                if (value != null) {
                    return value;
                }
            }
            if (factory == null) {
                factory = new Factory(key, parameter, subKey, valuesMap);
            }
            if (supplier == null) {
                supplier = valuesMap.putIfAbsent(subKey, factory);
                if (supplier == null) {
                    supplier = factory;
                }
            } else if (valuesMap.replace(subKey, supplier, factory)) {
                supplier = factory;
            } else {
                supplier = valuesMap.get(subKey);
            }
        }
    }

    public boolean containsValue(V value) {
        Objects.requireNonNull(value);
        expungeStaleEntries();
        return this.reverseMap.containsKey(new LookupValue(value));
    }

    public int size() {
        expungeStaleEntries();
        return this.reverseMap.size();
    }

    private void expungeStaleEntries() {
        while (true) {
            CacheKey<K> cacheKey = (CacheKey) this.refQueue.poll();
            if (cacheKey != null) {
                cacheKey.expungeFrom(this.map, this.reverseMap);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public final class Factory implements Supplier<V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final K key;
        private final P parameter;
        private final Object subKey;
        private final ConcurrentMap<Object, Supplier<V>> valuesMap;

        Factory(K key2, P parameter2, Object subKey2, ConcurrentMap<Object, Supplier<V>> valuesMap2) {
            this.key = key2;
            this.parameter = parameter2;
            this.subKey = subKey2;
            this.valuesMap = valuesMap2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v6, resolved type: java.util.concurrent.ConcurrentMap */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.function.Supplier
        public synchronized V get() {
            if (this.valuesMap.get(this.subKey) != this) {
                return null;
            }
            V value = null;
            try {
                V value2 = (V) Objects.requireNonNull(WeakCache.this.valueFactory.apply(this.key, this.parameter));
                CacheValue<V> cacheValue = new CacheValue<>(value2);
                if (this.valuesMap.replace(this.subKey, this, cacheValue)) {
                    WeakCache.this.reverseMap.put(cacheValue, Boolean.TRUE);
                    return value2;
                }
                throw new AssertionError((Object) "Should not reach here");
            } finally {
                if (value == null) {
                    this.valuesMap.remove(this.subKey, this);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class LookupValue<V> implements Value<V> {
        private final V value;

        LookupValue(V value2) {
            this.value = value2;
        }

        @Override // java.util.function.Supplier
        public V get() {
            return this.value;
        }

        public int hashCode() {
            return System.identityHashCode(this.value);
        }

        public boolean equals(Object obj) {
            return obj == this || ((obj instanceof Value) && this.value == ((Value) obj).get());
        }
    }

    private static final class CacheValue<V> extends WeakReference<V> implements Value<V> {
        private final int hash;

        CacheValue(V value) {
            super(value);
            this.hash = System.identityHashCode(value);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            Object obj2;
            return obj == this || ((obj instanceof Value) && (obj2 = get()) != null && obj2 == ((Value) obj).get());
        }
    }

    /* access modifiers changed from: private */
    public static final class CacheKey<K> extends WeakReference<K> {
        private static final Object NULL_KEY = new Object();
        private final int hash;

        static <K> Object valueOf(K key, ReferenceQueue<K> refQueue) {
            if (key == null) {
                return NULL_KEY;
            }
            return new CacheKey(key, refQueue);
        }

        private CacheKey(K key, ReferenceQueue<K> refQueue) {
            super(key, refQueue);
            this.hash = System.identityHashCode(key);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            Object obj2;
            if (obj == this || (obj != null && obj.getClass() == getClass() && (obj2 = get()) != null && obj2 == ((CacheKey) obj).get())) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void expungeFrom(ConcurrentMap<?, ? extends ConcurrentMap<?, ?>> map, ConcurrentMap<?, Boolean> reverseMap) {
            ConcurrentMap<?, ?> valuesMap = (ConcurrentMap) map.remove(this);
            if (valuesMap != null) {
                Iterator<?> it = valuesMap.values().iterator();
                while (it.hasNext()) {
                    reverseMap.remove(it.next());
                }
            }
        }
    }
}
