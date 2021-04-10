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
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class FbInjectorImpl extends AbstractInjector implements ScopeUnawareInjector {
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

    @Override // com.facebook.inject.InjectorLike
    public ScopeUnawareInjector getScopeUnawareInjector() {
        return this;
    }

    static {
        Thread thread;
        if (Looper.getMainLooper() == null) {
            thread = null;
        } else {
            thread = Looper.getMainLooper().getThread();
        }
        MAIN_THREAD = thread;
    }

    FbInjectorImpl(Context context, List<? extends PrivateModule> list) {
        boolean z = false;
        Tracer.startTracer("FbInjectorImpl.init");
        try {
            this.mAppContext = context;
            this.mIsDebugMode = BuildConstants.isInternalBuild();
            sInjectorThreadStackForMainThread = new InjectorThreadStack(this.mAppContext);
            this.mAppContextScopeAwareInjector = new ContextScopeAwareInjector(this, context);
            Preconditions.checkArgument(context == context.getApplicationContext() ? true : z);
            FbInjectorInitializer.Result init = new FbInjectorInitializer(this, list, this.mIsDebugMode).init();
            this.mBindingMap = init.bindingMap;
            this.mScopeMap = init.scopeMap;
            this.mComponentBindingMap = init.componentBindingMap;
            if (this.mIsDebugMode) {
                this.mModulesInstalled = init.modulesInstalled;
                this.mMultiBindings = init.multiBindings;
            } else {
                this.mModulesInstalled = null;
                this.mMultiBindings = null;
            }
            this.mInitialized = true;
        } finally {
            Tracer.stopTracer();
        }
    }

    @Override // com.facebook.inject.Injector
    public <T extends Scope> T getScope(Class<? extends Annotation> cls) {
        return (T) ((Scope) Assertions.assertNotNull(this.mScopeMap.get(cls)));
    }

    @Override // com.facebook.inject.Injector
    public <T> T getInstance(Key<T> key, Context context) {
        return getProvider(key, context).get();
    }

    public <T> Provider<T> getProvider(Key<T> key, Context context) {
        ensureInitialized();
        if (this.mIsDebugMode) {
            ProvisioningDebugStack.push(ProvisioningDebugStack.StackType.PROVIDER_GET, key);
        }
        try {
            Binding binding = this.mBindingMap.get(Integer.valueOf(UL.id.dynamicId(key)));
            if (binding != null) {
                return binding.getProvider();
            }
            throw new ProvisioningException("No provider bound for " + key);
        } finally {
            if (this.mIsDebugMode) {
                ProvisioningDebugStack.pop();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> cls, T t) {
        boolean z;
        ensureInitialized();
        Key key = Key.get((Class) cls);
        if (this.mIsDebugMode) {
            ProvisioningDebugStack.push(ProvisioningDebugStack.StackType.INJECT_COMPONENT, key);
        }
        try {
            ComponentBinding componentBinding = this.mComponentBindingMap.get(key);
            if (componentBinding != null) {
                componentBinding.getProvider().inject(t);
                if (!z) {
                    return;
                }
                return;
            }
            throw new ProvisioningException("No provider bound for " + key);
        } finally {
            if (this.mIsDebugMode) {
                ProvisioningDebugStack.pop();
            }
        }
    }

    @Override // com.facebook.inject.InjectorLike
    public InjectorThreadStack getInjectorThreadStack() {
        Assertions.assertNotNull(MAIN_THREAD);
        if (Thread.currentThread() == MAIN_THREAD) {
            return sInjectorThreadStackForMainThread;
        }
        return this.mInjectorStack.get();
    }

    @Override // com.facebook.inject.InjectorLike
    @Deprecated
    public ScopeAwareInjector getScopeAwareInjector() {
        ScopeAwareInjector scopeAwareInjectorOrNull = getScopeAwareInjectorOrNull();
        if (scopeAwareInjectorOrNull != null) {
            return scopeAwareInjectorOrNull;
        }
        throw new IllegalStateException("Should never call getScopeAwareInjector without an active ThreadStack");
    }

    @Nullable
    private ScopeAwareInjector getScopeAwareInjectorOrNull() {
        ensureInitialized();
        return getInjectorThreadStack().getScopeAwareInjector();
    }

    @Override // com.facebook.inject.InjectorLike
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
}
