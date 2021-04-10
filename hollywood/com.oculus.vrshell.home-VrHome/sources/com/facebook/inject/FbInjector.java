package com.facebook.inject;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import com.facebook.common.cache.CacheLoader;
import com.facebook.common.cache.WeakKeyWeakValueLoadingCache;
import com.facebook.common.process.ProcessName;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.MoreTypes;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public abstract class FbInjector implements InjectorLike {
    private static final Object LAZY_INSTANCE_NULL_SENTINEL = new Object();
    private static final WeakKeyWeakValueLoadingCache<Context, ContextScopeAwareInjector> sCachedInjectors = new WeakKeyWeakValueLoadingCache<>(getCacheLoader());
    @Nullable
    private static FbInjectorProvider sInjectorProvider;
    private static FbInjectorProvider sTestInjectorProvider;

    @DoNotStrip
    public static FbInjector get(Context context) {
        return sCachedInjectors.get(context);
    }

    private static CacheLoader<Context, ContextScopeAwareInjector> getCacheLoader() {
        return new CacheLoader<Context, ContextScopeAwareInjector>() {
            /* class com.facebook.inject.FbInjector.AnonymousClass1 */

            public ContextScopeAwareInjector load(Context context) {
                FbInjector injector = (FbInjector.sInjectorProvider != null ? FbInjector.sInjectorProvider : FbInjector.getInjectorProvider(context)).getInjector();
                if (injector != null) {
                    return new ContextScopeAwareInjector(injector, context);
                }
                throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
            }
        };
    }

    public static <T> Key<Set<T>> getMultiBindingKey(Key<T> key) {
        TypeLiteral<?> typeLiteral = TypeLiteral.get(new MoreTypes.ParameterizedTypeImpl(null, Set.class, key.getTypeLiteral().getType()));
        Annotation annotation = key.getAnnotation();
        Class<? extends Annotation> annotationType = key.getAnnotationType();
        if (annotation != null) {
            return Key.get(typeLiteral, annotation);
        }
        if (annotationType != null) {
            return Key.get(typeLiteral, annotationType);
        }
        return Key.get(typeLiteral);
    }

    public static <T> Key<List<T>> getListBindingKey(Key<T> key) {
        TypeLiteral<?> typeLiteral = TypeLiteral.get(new MoreTypes.ParameterizedTypeImpl(null, List.class, key.getTypeLiteral().getType()));
        Annotation annotation = key.getAnnotation();
        Class<? extends Annotation> annotationType = key.getAnnotationType();
        if (annotation != null) {
            return Key.get(typeLiteral, annotation);
        }
        if (annotationType != null) {
            return Key.get(typeLiteral, annotationType);
        }
        return Key.get(typeLiteral);
    }

    /* access modifiers changed from: private */
    public static FbInjectorProvider getInjectorProvider(Context context) {
        if (sTestInjectorProvider != null) {
            return sTestInjectorProvider;
        }
        Object provider = context.getApplicationContext();
        if ((provider instanceof Application) && !(provider instanceof FbInjectorProviderProvider)) {
            provider = ((Application) provider).getApplicationContext();
        }
        while (provider instanceof FbInjectorProviderProvider) {
            provider = ((FbInjectorProviderProvider) provider).getInjectorProvider();
        }
        if (provider instanceof FbInjectorProvider) {
            return (FbInjectorProvider) provider;
        }
        throw new UnsupportedOperationException("Injector is not supported in process " + ProcessName.current().getFullName() + ". Current thread is: " + Thread.currentThread() + " and current Looper is: " + Looper.myLooper());
    }

    public static <T> T lazyInstance(int localId, int bindingId, @Nullable InjectionContext context) {
        if (context == null) {
            return null;
        }
        return (T) lazyInstance(localId, bindingId, context.scopesSeenAtConstruction, context.injector, context.lazyCache);
    }

    private static <T> T lazyInstance(int localId, int bindingId, byte scopesSeenAtConstruction, ScopeAwareInjector scopeAwareInjector, AtomicReferenceArray cache) {
        Object obj;
        T instance = (T) cache.get(localId);
        if (instance == null) {
            ScopeSet scopeSet = ScopeSet.get();
            byte enterResult = scopeSet.enterScopes(scopesSeenAtConstruction);
            Object token = scopeAwareInjector.enterPreamble();
            try {
                Object instance2 = UL.factorymap.get(bindingId, scopeAwareInjector.getScopeUnawareInjector());
                scopeAwareInjector.exitPostamble(token);
                scopeSet.resetScopes(enterResult);
                if (instance2 == null) {
                    obj = LAZY_INSTANCE_NULL_SENTINEL;
                } else {
                    obj = instance2;
                }
                instance = cache.compareAndSet(localId, null, obj) ? instance2 : (T) cache.get(localId);
            } catch (RuntimeException e) {
                String failureId = Integer.toString(bindingId);
                Key bindingKey = RuntimeBindingIdUtils.bindingIdToKey(bindingId);
                if (bindingKey != null) {
                    failureId = bindingKey.toString();
                }
                throw new RuntimeException("Failed to lazy inject class " + failureId + " and localId " + localId, e);
            } catch (Throwable th) {
                scopeAwareInjector.exitPostamble(token);
                scopeSet.resetScopes(enterResult);
                throw th;
            }
        }
        if (instance == LAZY_INSTANCE_NULL_SENTINEL) {
            return null;
        }
        return instance;
    }
}
