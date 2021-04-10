package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ExecutionException;

@GwtIncompatible
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.cache.ForwardingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
    public abstract LoadingCache<K, V> delegate();

    protected ForwardingLoadingCache() {
    }

    @Override // com.google.common.cache.LoadingCache
    public V get(K key) throws ExecutionException {
        return delegate().get(key);
    }

    @Override // com.google.common.cache.LoadingCache
    public V getUnchecked(K key) {
        return delegate().getUnchecked(key);
    }

    @Override // com.google.common.cache.LoadingCache
    public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        return delegate().getAll(keys);
    }

    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    public V apply(K key) {
        return delegate().apply(key);
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K key) {
        delegate().refresh(key);
    }

    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        private final LoadingCache<K, V> delegate;

        protected SimpleForwardingLoadingCache(LoadingCache<K, V> delegate2) {
            this.delegate = (LoadingCache) Preconditions.checkNotNull(delegate2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public final LoadingCache<K, V> delegate() {
            return this.delegate;
        }
    }
}
