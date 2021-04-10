package com.facebook.inject;

import androidx.annotation.VisibleForTesting;
import com.facebook.debug.tracer.Tracer;
import com.google.common.collect.Lists;
import com.google.inject.Key;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public class MultiBinderProvider<T> implements Provider<Set<T>> {
    private final FbInjector mInjector;
    private final Key<? extends T>[] mKeys;
    private final Key<? extends T> mProvidedKey;

    MultiBinderProvider(FbInjector injector, List<Key<? extends T>> keys, Key<? extends T> providedKey) {
        this.mInjector = injector;
        this.mKeys = (Key[]) keys.toArray(new Key[keys.size()]);
        this.mProvidedKey = providedKey;
    }

    @VisibleForTesting
    public Collection<Key<? extends T>> getKeys() {
        return Lists.newArrayList(this.mKeys);
    }

    @Override // javax.inject.Provider
    public Set<T> get() {
        Tracer.startTracer("MultiBinderProvider: %s", this.mProvidedKey);
        try {
            final ScopeAwareInjector scopeAwareInjector = this.mInjector.getScopeAwareInjector();
            return new MultiBinderSet(scopeAwareInjector, new MultiBindIndexedProvider<T>() {
                /* class com.facebook.inject.MultiBinderProvider.AnonymousClass1 */

                @Override // com.facebook.inject.MultiBindIndexedProvider
                public T provide(Injector injector, int index) {
                    return (T) injector.getInstance(MultiBinderProvider.this.mKeys[index], scopeAwareInjector.getInjectorContext());
                }

                @Override // com.facebook.inject.MultiBindIndexedProvider
                public int size() {
                    return MultiBinderProvider.this.mKeys.length;
                }
            });
        } finally {
            Tracer.stopTracer();
        }
    }
}
