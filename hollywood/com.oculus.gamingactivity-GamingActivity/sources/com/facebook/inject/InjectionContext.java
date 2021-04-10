package com.facebook.inject;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public final class InjectionContext {
    public final ScopeAwareInjector injector;
    @Nullable
    public final AtomicReferenceArray lazyCache;
    public final byte scopesSeenAtConstruction = ScopeSet.get().getSeenScopes();

    public InjectionContext(int cacheSize, InjectorLike injector2) {
        this.injector = injector2.getScopeAwareInjector();
        if (cacheSize > 0) {
            this.lazyCache = new AtomicReferenceArray(cacheSize);
        } else {
            this.lazyCache = null;
        }
    }
}
