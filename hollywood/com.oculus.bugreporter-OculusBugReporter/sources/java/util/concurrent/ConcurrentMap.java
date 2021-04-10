package java.util.concurrent;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface ConcurrentMap<K, V> extends Map<K, V> {
    @Override // java.util.Map
    V putIfAbsent(K k, V v);

    @Override // java.util.Map
    boolean remove(Object obj, Object obj2);

    @Override // java.util.Map
    V replace(K k, V v);

    @Override // java.util.Map
    boolean replace(K k, V v, V v2);

    @Override // java.util.Map
    default V getOrDefault(Object key, V defaultValue) {
        V v = get(key);
        return v != null ? v : defaultValue;
    }

    @Override // java.util.Map
    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            try {
                action.accept(entry.getKey(), entry.getValue());
            } catch (IllegalStateException e) {
            }
        }
    }

    @Override // java.util.Map
    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        forEach(new BiConsumer(function) {
            /* class java.util.concurrent.$$Lambda$ConcurrentMap$T12JRbgGLhxGbYCuTfff6_dTrMk */
            private final /* synthetic */ BiFunction f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ConcurrentMap.lambda$replaceAll$0(ConcurrentMap.this, this.f$1, obj, obj2);
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.ConcurrentMap */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r1v0 java.util.concurrent.ConcurrentMap: [D('_this' java.util.concurrent.ConcurrentMap), D('this' java.util.concurrent.ConcurrentMap<K, V>)] */
    static /* synthetic */ default void lambda$replaceAll$0(ConcurrentMap _this, BiFunction function, Object k, Object v) {
        while (!_this.replace(k, v, function.apply(k, v))) {
            Object obj = _this.get(k);
            v = obj;
            if (obj == null) {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V newValue;
        Objects.requireNonNull(mappingFunction);
        V oldValue = get(key);
        if (oldValue == null) {
            Object apply = mappingFunction.apply(key);
            newValue = apply;
            if (apply != 0) {
                V oldValue2 = putIfAbsent(key, newValue);
                if (oldValue2 != null) {
                    newValue = oldValue2;
                }
                return newValue;
            }
        }
        newValue = oldValue;
        return newValue;
    }

    @Override // java.util.Map
    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V newValue;
        Objects.requireNonNull(remappingFunction);
        while (true) {
            V oldValue = get(key);
            if (oldValue == null) {
                return null;
            }
            newValue = (V) remappingFunction.apply(key, oldValue);
            if (newValue == null) {
                if (remove(key, oldValue)) {
                    break;
                }
            } else if (replace(key, oldValue, newValue)) {
                break;
            }
        }
        return newValue;
    }

    @Override // java.util.Map
    default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        while (true) {
            V oldValue = get(key);
            while (true) {
                V newValue = (V) remappingFunction.apply(key, oldValue);
                if (newValue != null) {
                    if (oldValue == null) {
                        V putIfAbsent = putIfAbsent(key, newValue);
                        oldValue = putIfAbsent;
                        if (putIfAbsent == null) {
                            return newValue;
                        }
                    } else if (replace(key, oldValue, newValue)) {
                        return newValue;
                    }
                } else if (oldValue == null || remove(key, oldValue)) {
                    return null;
                }
            }
        }
    }

    @Override // java.util.Map
    default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        while (true) {
            V oldValue = get(key);
            while (oldValue == null) {
                V newValue = putIfAbsent(key, value);
                oldValue = newValue;
                if (newValue == null) {
                    return value;
                }
            }
            V newValue2 = (V) remappingFunction.apply(oldValue, value);
            if (newValue2 != null) {
                if (replace(key, oldValue, newValue2)) {
                    return newValue2;
                }
            } else if (remove(key, oldValue)) {
                return null;
            }
        }
    }
}
