package com.facebook.inject;

import android.content.Context;
import android.os.Looper;
import com.facebook.common.build.BuildConstants;
import com.facebook.debug.tracer.Tracer;
import com.facebook.infer.annotation.Assertions;
import com.facebook.inject.FbInjectorInitializer;
import com.facebook.inject.ProvisioningDebugStack;
import com.facebook.ultralight.UL;
import com.google.common.base.Preconditions;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class FbInjectorImpl extends AbstractInjector implements ScopeUnawareInjector, StaticBindingInterceptor {
    @Nullable
    private static final Thread MAIN_THREAD;
    private static InjectorThreadStack sInjectorThreadStackForMainThread;
    private final Context mAppContext;
    private final ContextScopeAwareInjector mAppContextScopeAwareInjector;
    private Map<Integer, Binding> mBindingMap;
    private boolean mClearedOutForTest = false;
    private Map<Key, ComponentBinding> mComponentBindingMap;
    private boolean mInitialized;
    private final ThreadLocal<InjectorThreadStack> mInjectorStack = new ThreadLocal<InjectorThreadStack>() {
        /* class com.facebook.inject.FbInjectorImpl.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public InjectorThreadStack initialValue() {
            return new InjectorThreadStack(FbInjectorImpl.this.mAppContext);
        }
    };
    private final boolean mIsDebugMode;
    @Nullable
    private Set<Class<?>> mModulesInstalled;
    @Nullable
    private Map<Key, MultiBinder> mMultiBindings;
    private Map<Class<? extends Annotation>, Scope> mScopeMap;

    static {
        Thread thread;
        if (Looper.getMainLooper() == null) {
            thread = null;
        } else {
            thread = Looper.getMainLooper().getThread();
        }
        MAIN_THREAD = thread;
    }

    FbInjectorImpl(Context appContext, List<? extends PrivateModule> modules) {
        boolean z = true;
        Tracer.startTracer("FbInjectorImpl.init");
        try {
            this.mAppContext = appContext;
            this.mIsDebugMode = BuildConstants.isInternalBuild();
            sInjectorThreadStackForMainThread = new InjectorThreadStack(this.mAppContext);
            this.mAppContextScopeAwareInjector = new ContextScopeAwareInjector(this, appContext);
            Preconditions.checkArgument(appContext != appContext.getApplicationContext() ? false : z);
            FbInjectorInitializer.Result result = new FbInjectorInitializer(this, modules, this.mIsDebugMode).init();
            this.mBindingMap = result.bindingMap;
            this.mScopeMap = result.scopeMap;
            this.mComponentBindingMap = result.componentBindingMap;
            if (this.mIsDebugMode) {
                this.mModulesInstalled = result.modulesInstalled;
                this.mMultiBindings = result.multiBindings;
            } else {
                this.mModulesInstalled = null;
                this.mMultiBindings = null;
            }
            this.mInitialized = true;
        } finally {
            Tracer.stopTracer();
        }
    }

    public void updateWithModules(List<? extends PrivateModule> modules) {
        ensureInitialized();
        FbInjectorInitializer.Result result = new FbInjectorInitializer(this, modules, this.mIsDebugMode, this.mBindingMap, this.mScopeMap, this.mComponentBindingMap, this.mModulesInstalled, this.mMultiBindings).update();
        this.mBindingMap = result.bindingMap;
        this.mScopeMap = result.scopeMap;
        this.mComponentBindingMap = result.componentBindingMap;
        this.mModulesInstalled = result.modulesInstalled;
        this.mMultiBindings = result.multiBindings;
    }

    @Override // com.facebook.inject.Injector
    public <T extends Scope> T getScope(Class<? extends Annotation> type) {
        return (T) ((Scope) Assertions.assertNotNull(this.mScopeMap.get(type)));
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Key<T> key) {
        return (T) getInstance(key, getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int key) {
        return getInstance(key, getInjectorThreadStack().getContext());
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> T getInstance(Key<T> key, Context context) {
        return getProvider(key, context).get();
    }

    @Override // com.facebook.inject.Injector
    public Object getInstance(int key, Context context) {
        ensureInitialized();
        if (this.mBindingMap.containsKey(Integer.valueOf(key))) {
            return ((Binding) Assertions.assertNotNull(this.mBindingMap.get(Integer.valueOf(key)))).getProvider().get();
        }
        Key realKey = getKeyFromId(key);
        throw new ProvisioningException("No provider bound for " + (realKey.getAnnotation() != null ? "@" + realKey.getAnnotation() + " " : "") + realKey.getTypeLiteral() + ", Map has # bindings: " + this.mBindingMap.size());
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> Provider<T> getProvider(Key<T> key, Context context) {
        ensureInitialized();
        if (this.mIsDebugMode) {
            ProvisioningDebugStack.push(ProvisioningDebugStack.StackType.PROVIDER_GET, key);
        }
        try {
            Binding binding = this.mBindingMap.get(Integer.valueOf(UL.id.dynamicId(key)));
            if (binding == null) {
                throw new ProvisioningException("No provider bound for " + key);
            }
            return binding.getProvider();
        } finally {
            if (this.mIsDebugMode) {
                ProvisioningDebugStack.pop();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> type, T instance) {
        ensureInitialized();
        Key<T> key = Key.get((Class) type);
        if (this.mIsDebugMode) {
            ProvisioningDebugStack.push(ProvisioningDebugStack.StackType.INJECT_COMPONENT, key);
        }
        try {
            ComponentBinding<T> binding = this.mComponentBindingMap.get(key);
            if (binding == null) {
                throw new ProvisioningException("No provider bound for " + key);
            }
            binding.getProvider().inject(instance);
        } finally {
            if (this.mIsDebugMode) {
                ProvisioningDebugStack.pop();
            }
        }
    }

    private Key getKeyFromId(int id) {
        Key fullKey = UL.id.intToKey(id);
        if (fullKey == null) {
            return RuntimeBindingIdUtils.bindingIdToKey(id);
        }
        return fullKey;
    }

    @Override // com.facebook.inject.FbInjector
    public List<Class<?>> getRequiredModulesForTool() {
        return Collections.emptyList();
    }

    @Override // com.facebook.inject.InjectorLike
    public InjectorThreadStack getInjectorThreadStack() {
        Assertions.assertNotNull(MAIN_THREAD);
        if (Thread.currentThread() == MAIN_THREAD) {
            return sInjectorThreadStackForMainThread;
        }
        return this.mInjectorStack.get();
    }

    public static InjectorThreadStack getMainThreadInjectorThreadStack() {
        return sInjectorThreadStackForMainThread;
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.Injector
    public <T> Lazy<T> getLazy(Key<T> key, Context context) {
        return ProviderLazy.fromProvider(getProvider(key, context), getScopeAwareInjector());
    }

    @Override // com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeAwareInjector getScopeAwareInjector() {
        ScopeAwareInjector contextAware = getScopeAwareInjectorOrNull();
        if (contextAware != null) {
            return contextAware;
        }
        throw new IllegalStateException("Should never call getScopeAwareInjector without an active ThreadStack");
    }

    @Nullable
    private ScopeAwareInjector getScopeAwareInjectorOrNull() {
        ensureInitialized();
        return getInjectorThreadStack().getScopeAwareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
    public ScopeUnawareInjector getScopeUnawareInjector() {
        return this;
    }

    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.InjectorLike
    public InjectorLike getApplicationInjector() {
        return this.mAppContextScopeAwareInjector;
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            throw new RuntimeException("Called injector during binding");
        } else if (this.mClearedOutForTest) {
            throw new IllegalStateException("It looks like the bindings map has been cleared. This might happen insome tests that use AppInjectors.clearBindings(), but also havebackground threads or asynchronous handlers that are accessing theinjector after that call.\n");
        }
    }

    @Override // com.facebook.inject.StaticBindingInterceptor
    public Object getObjectForBindingId(int bindingId) {
        return null;
    }
}
