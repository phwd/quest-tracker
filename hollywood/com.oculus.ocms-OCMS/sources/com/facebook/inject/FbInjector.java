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

    public static FbInjector createForApp(Context context, FbInjectorProvider fbInjectorProvider, List<? extends PrivateModule> list) {
        if (sInjectorProvider == null) {
            sInjectorProvider = fbInjectorProvider;
            sApplication = context.getApplicationContext();
            return new FbInjectorImpl(context, list);
        }
        throw new RuntimeException("you cannot call createForApp twice");
    }

    public static FbInjector createForApp(Context context, List<? extends PrivateModule> list) {
        return createForApp(context, null, list);
    }

    public static FbInjector createForTest(Context context, List<? extends PrivateModule> list) {
        return new FbInjectorImpl(context, list);
    }

    public static void updateForTest(FbInjector fbInjector, List<? extends PrivateModule> list) {
        Preconditions.checkArgument(fbInjector instanceof FbInjectorImpl, "Must use the injector that was returned via FbInjector#createForTest!");
        ((FbInjectorImpl) fbInjector).updateWithModules(list);
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

    public static void throwForInvalidAnnotation(String str) {
        throw new ProvisioningException("Invalid annotation: " + str);
    }

    @Deprecated
    public static <T> void injectMe(Class<T> cls, T t, Context context) {
        injectMeInternal(cls, t, context);
    }

    public static <T extends InjectableComponentWithoutContext> void injectMe(Class<T> cls, T t, Context context) {
        injectMeInternal(cls, t, context);
    }

    private static <T> void injectMeInternal(Class<T> cls, T t, Context context) {
        get(context).injectComponent(cls, t);
    }

    public static <T extends Context> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t);
    }

    public static <T extends InjectableComponentWithContext> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t.getContext());
    }

    public static <T extends View> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t.getContext());
    }

    public static <T extends PreferenceGroup> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t.getContext());
    }

    public static <T extends Preference> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t.getContext());
    }

    public static <T extends Dialog> void injectMe(Class<T> cls, T t) {
        injectMeInternal(cls, t, t.getContext());
    }

    /* access modifiers changed from: private */
    public static FbInjectorProvider getInjectorProvider(Context context) {
        FbInjectorProvider fbInjectorProvider = sTestInjectorProvider;
        if (fbInjectorProvider != null) {
            return fbInjectorProvider;
        }
        Object applicationContext = context.getApplicationContext();
        if ((applicationContext instanceof Application) && !(applicationContext instanceof FbInjectorProviderProvider)) {
            applicationContext = ((Application) applicationContext).getApplicationContext();
        }
        while (applicationContext instanceof FbInjectorProviderProvider) {
            applicationContext = ((FbInjectorProviderProvider) applicationContext).getInjectorProvider();
        }
        if (applicationContext instanceof FbInjectorProvider) {
            return (FbInjectorProvider) applicationContext;
        }
        throw new UnsupportedOperationException("Injector is not supported in process " + ProcessName.current().getFullName() + ". Current thread is: " + Thread.currentThread() + " and current Looper is: " + Looper.myLooper());
    }

    @VisibleForTesting
    public static void setTestInjectorProvider(FbInjectorProvider fbInjectorProvider) {
        sTestInjectorProvider = fbInjectorProvider;
        clearCachedInjectors();
    }

    public static <T> T localApplicationInstance(int i, @Nullable InjectionContext injectionContext) {
        return injectionContext == null ? (T) UltralightRuntime.throwLocalInjectionTooEarly() : (T) localInstance(i, injectionContext.injector.getScopeUnawareInjector().getApplicationInjector().getScopeAwareInjector());
    }

    public static <T> T localInstance(int i, @Nullable InjectionContext injectionContext) {
        return injectionContext == null ? (T) UltralightRuntime.throwLocalInjectionTooEarly() : (T) localInstance(i, injectionContext.injector);
    }

    private static <T> T localInstance(int i, ScopeAwareInjector scopeAwareInjector) {
        Object enterPreamble = scopeAwareInjector.enterPreamble();
        try {
            T t = (T) UL.factorymap.get(i, scopeAwareInjector.getScopeUnawareInjector());
            scopeAwareInjector.exitPostamble(enterPreamble);
            return t;
        } catch (RuntimeException e) {
            String num = Integer.toString(i);
            Key bindingIdToKey = RuntimeBindingIdUtils.bindingIdToKey(i);
            if (bindingIdToKey != null) {
                num = bindingIdToKey.toString();
            }
            throw new RuntimeException("Failed to local inject class " + num, e);
        } catch (Throwable th) {
            scopeAwareInjector.exitPostamble(enterPreamble);
            throw th;
        }
    }

    public static <T> T lazyApplicationInstance(int i, int i2, @Nullable InjectionContext injectionContext) {
        if (injectionContext == null) {
            return null;
        }
        return (T) lazyInstance(i, i2, injectionContext.scopesSeenAtConstruction, injectionContext.injector.getScopeUnawareInjector().getApplicationInjector().getScopeAwareInjector(), injectionContext.lazyCache);
    }

    public static <T> T lazyInstance(int i, int i2, @Nullable InjectionContext injectionContext) {
        if (injectionContext == null) {
            return null;
        }
        return (T) lazyInstance(i, i2, injectionContext.scopesSeenAtConstruction, injectionContext.injector, injectionContext.lazyCache);
    }

    private static <T> T lazyInstance(int i, int i2, byte b, ScopeAwareInjector scopeAwareInjector, AtomicReferenceArray atomicReferenceArray) {
        T t = (T) atomicReferenceArray.get(i);
        if (t == null) {
            ScopeSet scopeSet = ScopeSet.get();
            byte enterScopes = scopeSet.enterScopes(b);
            Object enterPreamble = scopeAwareInjector.enterPreamble();
            try {
                Object obj = UL.factorymap.get(i2, scopeAwareInjector.getScopeUnawareInjector());
                scopeAwareInjector.exitPostamble(enterPreamble);
                scopeSet.resetScopes(enterScopes);
                t = atomicReferenceArray.compareAndSet(i, null, obj == null ? LAZY_INSTANCE_NULL_SENTINEL : obj) ? obj : (T) atomicReferenceArray.get(i);
            } catch (RuntimeException e) {
                String num = Integer.toString(i2);
                Key bindingIdToKey = RuntimeBindingIdUtils.bindingIdToKey(i2);
                if (bindingIdToKey != null) {
                    num = bindingIdToKey.toString();
                }
                throw new RuntimeException("Failed to lazy inject class " + num + " and localId " + i, e);
            } catch (Throwable th) {
                scopeAwareInjector.exitPostamble(enterPreamble);
                scopeSet.resetScopes(enterScopes);
                throw th;
            }
        }
        if (t == LAZY_INSTANCE_NULL_SENTINEL) {
            return null;
        }
        return t;
    }

    @Nullable
    public static Context getApplication() {
        return sApplication;
    }
}
