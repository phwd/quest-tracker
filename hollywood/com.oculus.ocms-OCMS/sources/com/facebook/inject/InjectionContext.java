package com.facebook.inject;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public final class InjectionContext {
    public final ScopeAwareInjector injector;
    @Nullable
    public final AtomicReferenceArray lazyCache;
    public final byte scopesSeenAtConstruction = ScopeSet.get().getSeenScopes();

    public InjectionContext(int i, InjectorLike injectorLike) {
        this.injector = injectorLike.getScopeAwareInjector();
        if (i > 0) {
            this.lazyCache = new AtomicReferenceArray(i);
        } else {
            this.lazyCache = null;
        }
    }
}
