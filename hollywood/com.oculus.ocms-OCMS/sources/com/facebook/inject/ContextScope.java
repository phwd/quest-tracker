package com.facebook.inject;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.common.cache.CacheLoader;
import com.facebook.common.cache.WeakKeyWeakValueLoadingCache;
import com.facebook.common.propertybag.PropertyBag;
import com.facebook.common.util.context.wrapper.ContextWrapperUtils;
import com.facebook.infer.annotation.Assertions;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
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

    @AutoGeneratedAccessMethod
    public static final ContextScope _UL__ULSEP_com_facebook_inject_ContextScope_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ContextScope) UL.factorymap.get(BundledAndroidModule.UL_id._UL__ULSEP_com_facebook_inject_ContextScope_ULSEP_BINDING_ID, injectorLike);
    }

    public ContextScope(FbInjector fbInjector) {
        this.mFbInjector = fbInjector;
        this.mAppContext = fbInjector.getInjectorThreadStack().getContext();
    }

    public Context getAppContext() {
        return this.mAppContext;
    }

    public InjectorThreadStack getInjectorThreadStack() {
        return this.mFbInjector.getInjectorThreadStack();
    }

    @Override // com.facebook.inject.Scope
    public <T> Provider<T> scope(Key<T> key, Provider<T> provider) {
        return new ContextScopedProvider(this, provider);
    }

    @Override // com.facebook.inject.Scope
    public Class<? extends Annotation> annotationType() {
        return ContextScoped.class;
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

    public static <T extends Scoped<ContextScope>> T get(Class<? extends T> cls, Context context) {
        return (T) get(cls, null, context);
    }

    public static <T extends Scoped<ContextScope>> T get(Class<? extends T> cls, @Nullable Class cls2, Context context) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return (T) get(RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2), context);
    }

    @SuppressLint({"BadArgument-FbInjector#get-0"})
    public static <T extends Scoped<ContextScope>> T get(int i, Context context) {
        Map<Integer, Object> map;
        synchronized (contextKeyedScopeCache) {
            map = contextKeyedScopeCache.get(context);
            if (map == null) {
                map = Collections.synchronizedMap(new HashMap());
                contextKeyedScopeCache.put(context, map);
            }
        }
        T t = (T) ((Scoped) map.get(Integer.valueOf(i)));
        if (t != null) {
            return t;
        }
        T t2 = (T) ((Scoped) Ultralight.get(i, context));
        map.put(Integer.valueOf(i), t2);
        return t2;
    }

    public static <T extends Scoped<ContextScope>> Lazy<T> lazy(Class<? extends Scoped<ContextScope>> cls, Context context) {
        return lazy(cls, null, context);
    }

    public static <T extends Scoped<ContextScope>> Lazy<T> lazy(Class<? extends Scoped<ContextScope>> cls, @Nullable Class cls2, Context context) {
        Assertions.assertUnreachable("Fallback was called and not implemented yet");
        return new Lazy(RuntimeBindingIdUtils.getBindingIdFromClasses(cls, cls2), context);
    }

    public static <T extends Scoped<ContextScope>> Lazy<T> lazy(int i, Context context) {
        return new Lazy(i, context);
    }

    /* access modifiers changed from: private */
    public static class Lazy<T extends Scoped<ContextScope>> implements Lazy<T> {
        private final int mBindingId;
        private final Context mContext;
        private T mInstance;

        private Lazy(int i, Context context) {
            this.mBindingId = i;
            this.mContext = context;
        }

        @Override // com.facebook.inject.Lazy, javax.inject.Provider
        public T get() {
            if (this.mInstance == null) {
                this.mInstance = (T) ContextScope.get(this.mBindingId, this.mContext);
            }
            return this.mInstance;
        }
    }
}
