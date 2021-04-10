package com.facebook.inject;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.cache.CacheLoader;
import com.facebook.common.cache.WeakKeyWeakValueLoadingCache;
import com.facebook.common.process.ProcessName;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.ultralight.UL;
import com.facebook.ultralight.UltralightRuntime;
import com.google.common.base.Preconditions;
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
    private static Context sApplication;
    private static final WeakKeyWeakValueLoadingCache<Context, ContextScopeAwareInjector> sCachedInjectors = new WeakKeyWeakValueLoadingCache<>(getCacheLoader());
    @Nullable
    private static FbInjectorProvider sInjectorProvider;
    private static FbInjectorProvider sTestInjectorProvider;

    public abstract List<Class<?>> getRequiredModulesForTool();

    /* access modifiers changed from: protected */
    public abstract <T> void injectComponent(Class<T> cls, T t);

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

    @VisibleForTesting
    public static void clearCachedInjectors() {
        sCachedInjectors.clear();
    }

    public static FbInjector createForApp(Context context, FbInjectorProvider injectorProvider, List<? extends PrivateModule> modules) {
        if (sInjectorProvider != null) {
            throw new RuntimeException("you cannot call createForApp twice");
        }
        sInjectorProvider = injectorProvider;
        sApplication = context.getApplicationContext();
        return new FbInjectorImpl(context, modules);
    }

    public static FbInjector createForApp(Context context, List<? extends PrivateModule> modules) {
        return createForApp(context, null, modules);
    }

    public static FbInjector createForTest(Context context, List<? extends PrivateModule> modules) {
        return new FbInjectorImpl(context, modules);
    }

    public static void updateForTest(FbInjector fbInjector, List<? extends PrivateModule> modules) {
        Preconditions.checkArgument(fbInjector instanceof FbInjectorImpl, "Must use the injector that was returned via FbInjector#createForTest!");
        ((FbInjectorImpl) fbInjector).updateWithModules(modules);
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

    public static void throwForInvalidAnnotation(String annotationName) {
        throw new ProvisioningException("Invalid annotation: " + annotationName);
    }

    @Deprecated
    public static <T> void injectMe(Class<T> type, T instance, Context context) {
        injectMeInternal(type, instance, context);
    }

    public static <T extends InjectableComponentWithoutContext> void injectMe(Class<T> type, T instance, Context context) {
        injectMeInternal(type, instance, context);
    }

    private static <T> void injectMeInternal(Class<T> type, T instance, Context context) {
        get(context).injectComponent(type, instance);
    }

    public static <T extends Context> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance);
    }

    public static <T extends InjectableComponentWithContext> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance.getContext());
    }

    public static <T extends View> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance.getContext());
    }

    public static <T extends PreferenceGroup> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance.getContext());
    }

    public static <T extends Preference> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance.getContext());
    }

    public static <T extends Dialog> void injectMe(Class<T> type, T instance) {
        injectMeInternal(type, instance, instance.getContext());
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

    @VisibleForTesting
    public static void setTestInjectorProvider(FbInjectorProvider injectorProvider) {
        sTestInjectorProvider = injectorProvider;
        clearCachedInjectors();
    }

    public static <T> T localApplicationInstance(int bindingId, @Nullable InjectionContext context) {
        return context == null ? (T) UltralightRuntime.throwLocalInjectionTooEarly() : (T) localInstance(bindingId, context.injector.getScopeUnawareInjector().getApplicationInjector().getScopeAwareInjector());
    }

    public static <T> T localInstance(int bindingId, @Nullable InjectionContext context) {
        return context == null ? (T) UltralightRuntime.throwLocalInjectionTooEarly() : (T) localInstance(bindingId, context.injector);
    }

    private static <T> T localInstance(int bindingId, ScopeAwareInjector scopeAwareInjector) {
        Object token = scopeAwareInjector.enterPreamble();
        try {
            T t = (T) UL.factorymap.get(bindingId, scopeAwareInjector.getScopeUnawareInjector());
            scopeAwareInjector.exitPostamble(token);
            return t;
        } catch (RuntimeException e) {
            String failureId = Integer.toString(bindingId);
            Key bindingKey = RuntimeBindingIdUtils.bindingIdToKey(bindingId);
            if (bindingKey != null) {
                failureId = bindingKey.toString();
            }
            throw new RuntimeException("Failed to local inject class " + failureId, e);
        } catch (Throwable th) {
            scopeAwareInjector.exitPostamble(token);
            throw th;
        }
    }

    public static <T> T lazyApplicationInstance(int localId, int bindingId, @Nullable InjectionContext context) {
        if (context == null) {
            return null;
        }
        return (T) lazyInstance(localId, bindingId, context.scopesSeenAtConstruction, context.injector.getScopeUnawareInjector().getApplicationInjector().getScopeAwareInjector(), context.lazyCache);
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

    @Nullable
    public static Context getApplication() {
        return sApplication;
    }
}
