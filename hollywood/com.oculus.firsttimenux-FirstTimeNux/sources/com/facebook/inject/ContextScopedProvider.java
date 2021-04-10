package com.facebook.inject;

import android.app.Application;
import android.content.Context;
import com.facebook.common.propertybag.PropertyBag;
import javax.inject.Provider;

public class ContextScopedProvider<T> implements Provider<T> {
    private static final byte[] INVALID_SCOPES = {1};
    private final ContextScope mContextScope;
    private final Provider<T> mUnscopedProvider;
    private T mValueInAppContext;

    public ContextScopedProvider(ContextScope contextScope, Provider<T> unscopedProvider) {
        this.mContextScope = contextScope;
        this.mUnscopedProvider = unscopedProvider;
    }

    @Override // javax.inject.Provider
    public T get() {
        T instance;
        ScopeSet scopeSet = ScopeSet.get();
        InjectorThreadStack injectorThreadStack = this.mContextScope.getInjectorThreadStack();
        Context context = injectorThreadStack.getContext();
        if (context == null) {
            throw new ProvisioningException("Called context scoped provider outside of context scope");
        }
        if (!(context instanceof Application)) {
            scopeSet.assertNoInvalidScopesInSet((byte) 8, INVALID_SCOPES);
        }
        byte enterResult = scopeSet.enterScopes((byte) 8);
        try {
            PropertyBag propertyBag = this.mContextScope.getPropertyBagForContext(context);
            synchronized (this) {
                instance = propertyBag != null ? (T) propertyBag.getProperty(this) : this.mValueInAppContext;
                if (instance == null) {
                    this.mContextScope.enterScope(context, injectorThreadStack);
                    try {
                        instance = this.mUnscopedProvider.get();
                        if (propertyBag != null) {
                            propertyBag.setProperty(this, instance);
                        } else {
                            this.mValueInAppContext = instance;
                        }
                    } finally {
                        this.mContextScope.exitScope(injectorThreadStack);
                    }
                }
            }
            return instance;
        } finally {
            scopeSet.resetScopes(enterResult);
        }
    }
}
