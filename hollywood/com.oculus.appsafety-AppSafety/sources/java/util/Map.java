package java.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Map<K, V> {
    void clear();

    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    Set<Entry<K, V>> entrySet();

    boolean equals(Object obj);

    V get(Object obj);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    V put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    V remove(Object obj);

    int size();

    Collection<V> values();

    public interface Entry<K, V> {
        boolean equals(Object obj);

        K getKey();

        V getValue();

        int hashCode();

        V setValue(V v);

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        private static /* synthetic */ default Object $deserializeLambda$(SerializedLambda lambda) {
            char c;
            String implMethodName = lambda.getImplMethodName();
            switch (implMethodName.hashCode()) {
                case -724508797:
                    if (implMethodName.equals("lambda$comparingByKey$bbdbfea9$1")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -524237750:
                    if (implMethodName.equals("lambda$comparingByValue$1065357e$1")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 864375927:
                    if (implMethodName.equals("lambda$comparingByValue$827a17d5$1")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1397794443:
                    if (implMethodName.equals("lambda$comparingByKey$6d558cbf$1")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c == 3 && lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Map$Entry") && lambda.getImplMethodSignature().equals("(Ljava/util/Comparator;Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I")) {
                            return new Object() {
                                /* class java.util.$$Lambda$Map$Entry$g8sc1MgjjhwTaK8zHulzMasixMw */

                                @Override // java.util.Comparator
                                public final int compare(Object obj, Object obj2) {
                                    return Comparator.this.compare(((Map.Entry) obj).getKey(), ((Map.Entry) obj2).getKey());
                                }
                            };
                        }
                    } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Map$Entry") && lambda.getImplMethodSignature().equals("(Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I")) {
                        return $$Lambda$Map$Entry$acJOHw6hO1wh4v9r2vtUuCFe5vI.INSTANCE;
                    }
                } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Map$Entry") && lambda.getImplMethodSignature().equals("(Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I")) {
                    return $$Lambda$Map$Entry$zJtjVuaqJl6rzQLvCcTd4dnXnnw.INSTANCE;
                }
            } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Map$Entry") && lambda.getImplMethodSignature().equals("(Ljava/util/Comparator;Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I")) {
                return new Object() {
                    /* class java.util.$$Lambda$Map$Entry$Y3nKRmSXx8yzU_ApvOwqAqvBas */

                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return Comparator.this.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
                    }
                };
            }
            throw new IllegalArgumentException("Invalid lambda deserialization");
        }

        static default <K extends Comparable<? super K>, V> Comparator<Entry<K, V>> comparingByKey() {
            return $$Lambda$Map$Entry$zJtjVuaqJl6rzQLvCcTd4dnXnnw.INSTANCE;
        }

        static default <K, V extends Comparable<? super V>> Comparator<Entry<K, V>> comparingByValue() {
            return $$Lambda$Map$Entry$acJOHw6hO1wh4v9r2vtUuCFe5vI.INSTANCE;
        }

        static default <K, V> Comparator<Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return new Object() {
                /* class java.util.$$Lambda$Map$Entry$g8sc1MgjjhwTaK8zHulzMasixMw */

                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Comparator.this.compare(((Map.Entry) obj).getKey(), ((Map.Entry) obj2).getKey());
                }
            };
        }

        static default <K, V> Comparator<Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return new Object() {
                /* class java.util.$$Lambda$Map$Entry$Y3nKRmSXx8yzU_ApvOwqAqvBas */

                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Comparator.this.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
                }
            };
        }
    }

    default V getOrDefault(Object key, V defaultValue) {
        V v = get(key);
        return (v != null || containsKey(key)) ? v : defaultValue;
    }

    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Entry<K, V> entry : entrySet()) {
            try {
                action.accept(entry.getKey(), entry.getValue());
            } catch (IllegalStateException ise) {
                throw new ConcurrentModificationException(ise);
            }
        }
    }

    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (Entry<K, V> entry : entrySet()) {
            try {
                try {
                    entry.setValue((V) function.apply(entry.getKey(), entry.getValue()));
                } catch (IllegalStateException ise) {
                    throw new ConcurrentModificationException(ise);
                }
            } catch (IllegalStateException ise2) {
                throw new ConcurrentModificationException(ise2);
            }
        }
    }

    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            return put(key, value);
        }
        return v;
    }

    default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value)) {
            return false;
        }
        if (curValue == null && !containsKey(key)) {
            return false;
        }
        remove(key);
        return true;
    }

    default boolean replace(K key, V oldValue, V newValue) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, oldValue)) {
            return false;
        }
        if (curValue == null && !containsKey(key)) {
            return false;
        }
        put(key, newValue);
        return true;
    }

    default V replace(K key, V value) {
        V curValue = get(key);
        if (curValue != null || containsKey(key)) {
            return put(key, value);
        }
        return curValue;
    }

    default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V newValue;
        Objects.requireNonNull(mappingFunction);
        V v = get(key);
        if (v != null || (newValue = (V) mappingFunction.apply(key)) == null) {
            return v;
        }
        put(key, newValue);
        return newValue;
    }

    default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);
        if (oldValue == null) {
            return null;
        }
        V newValue = (V) remappingFunction.apply(key, oldValue);
        if (newValue != null) {
            put(key, newValue);
            return newValue;
        }
        remove(key);
        return null;
    }

    default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);
        V newValue = (V) remappingFunction.apply(key, oldValue);
        if (newValue != null) {
            put(key, newValue);
            return newValue;
        } else if (oldValue == null && !containsKey(key)) {
            return null;
        } else {
            remove(key);
            return null;
        }
    }

    default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = oldValue == null ? value : (V) remappingFunction.apply(oldValue, value);
        if (newValue == null) {
            remove(key);
        } else {
            put(key, newValue);
        }
        return newValue;
    }
}
