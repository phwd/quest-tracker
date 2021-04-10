package com.google.common.cache;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public abstract class CacheLoader<K, V> {
    public abstract V load(K k) throws Exception;

    protected CacheLoader() {
    }

    @GwtIncompatible
    public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(oldValue);
        return Futures.immediateFuture(load(key));
    }

    public Map<K, V> loadAll(Iterable<? extends K> iterable) throws Exception {
        throw new UnsupportedLoadingOperationException();
    }

    public static <K, V> CacheLoader<K, V> from(Function<K, V> function) {
        return new FunctionToCacheLoader(function);
    }

    public static <V> CacheLoader<Object, V> from(Supplier<V> supplier) {
        return new SupplierToCacheLoader(supplier);
    }

    private static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Function<K, V> computingFunction;

        public FunctionToCacheLoader(Function<K, V> computingFunction2) {
            this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction2);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.base.Function<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.cache.CacheLoader
        public V load(K key) {
            return (V) this.computingFunction.apply(Preconditions.checkNotNull(key));
        }
    }

    @GwtIncompatible
    public static <K, V> CacheLoader<K, V> asyncReloading(CacheLoader<K, V> loader, final Executor executor) {
        Preconditions.checkNotNull(loader);
        Preconditions.checkNotNull(executor);
        return new CacheLoader<K, V>() {
            /* class com.google.common.cache.CacheLoader.AnonymousClass1 */

            @Override // com.google.common.cache.CacheLoader
            public V load(K key) throws Exception {
                return (V) CacheLoader.this.load(key);
            }

            @Override // com.google.common.cache.CacheLoader
            public ListenableFuture<V> reload(final K key, final V oldValue) throws Exception {
                ListenableFutureTask<V> task = ListenableFutureTask.create(new Callable<V>() {
                    /* class com.google.common.cache.CacheLoader.AnonymousClass1.AnonymousClass1 */

                    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.cache.CacheLoader */
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.concurrent.Callable
                    public V call() throws Exception {
                        return (V) CacheLoader.this.reload(key, oldValue).get();
                    }
                });
                executor.execute(task);
                return task;
            }

            @Override // com.google.common.cache.CacheLoader
            public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
                return CacheLoader.this.loadAll(keys);
            }
        };
    }

    private static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<V> computingSupplier;

        public SupplierToCacheLoader(Supplier<V> computingSupplier2) {
            this.computingSupplier = (Supplier) Preconditions.checkNotNull(computingSupplier2);
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(Object key) {
            Preconditions.checkNotNull(key);
            return this.computingSupplier.get();
        }
    }

    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
        UnsupportedLoadingOperationException() {
        }
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String message) {
            super(message);
        }
    }
}
