package com.facebook.inject;

import android.content.Context;
import com.facebook.common.cache.CacheLoader;
import com.facebook.common.cache.WeakKeyWeakValueLoadingCache;
import com.facebook.common.propertybag.PropertyBag;
import com.facebook.common.util.context.wrapper.ContextWrapperUtils;
import com.google.inject.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;

@ContextScoped
public class ContextScope implements Scope {
    private static final Map<Context, Map<Integer, Object>> contextKeyedScopeCache = Collections.synchronizedMap(new HashMap());
    private final Context mAppContext;
    private final WeakKeyWeakValueLoadingCache<Context, ContextScopeAwareInjector> mCachedInjectors = new WeakKeyWeakValueLoadingCache<>(new CacheLoader<Context, ContextScopeAwareInjector>() {
        /* class com.facebook.inject.ContextScope.AnonymousClass1 */

        public ContextScopeAwareInjector load(Context context) {
            return new ContextScopeAwareInjector(ContextScope.this.mFbInjector, context);
        }
    });
    private final FbInjector mFbInjector;

    public ContextScope(FbInjector fbInjector) {
        this.mFbInjector = fbInjector;
        this.mAppContext = fbInjector.getInjectorThreadStack().getContext();
    }

    public InjectorThreadStack getInjectorThreadStack() {
        return this.mFbInjector.getInjectorThreadStack();
    }

    @Override // com.facebook.inject.Scope
    public <T> Provider<T> scope(Key<T> key, Provider<T> provider) {
        return new ContextScopedProvider(this, provider);
    }

    public void enterScope(Context context, InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.pushInjector(this.mCachedInjectors.get(context));
    }

    public void exitScope(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.popInjector();
    }

    @Nullable
    public PropertyBag getPropertyBagForContext(Context context) {
        return (PropertyBag) ContextWrapperUtils.findContextOfType(context, PropertyBag.class);
    }
}
