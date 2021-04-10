package com.facebook.inject;

import com.facebook.debug.tracer.Tracer;
import com.google.inject.Key;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public class MultiBinderProvider<T> implements Provider<Set<T>> {
    private final FbInjector mInjector;
    private final Key<? extends T>[] mKeys;
    private final Key<? extends T> mProvidedKey;

    MultiBinderProvider(FbInjector fbInjector, List<Key<? extends T>> list, Key<? extends T> key) {
        this.mInjector = fbInjector;
        this.mKeys = (Key[]) list.toArray(new Key[list.size()]);
        this.mProvidedKey = key;
    }

    @Override // javax.inject.Provider
    public Set<T> get() {
        Tracer.startTracer("MultiBinderProvider: %s", this.mProvidedKey);
        try {
            final ScopeAwareInjector scopeAwareInjector = this.mInjector.getScopeAwareInjector();
            return new MultiBinderSet(scopeAwareInjector, new MultiBindIndexedProvider<T>() {
                /* class com.facebook.inject.MultiBinderProvider.AnonymousClass1 */

                @Override // com.facebook.inject.MultiBindIndexedProvider
                public T provide(Injector injector, int i) {
                    return (T) injector.getInstance(MultiBinderProvider.this.mKeys[i], scopeAwareInjector.getInjectorContext());
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
