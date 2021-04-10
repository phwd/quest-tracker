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

    public ContextScopedProvider(ContextScope contextScope, Provider<T> provider) {
        this.mContextScope = contextScope;
        this.mUnscopedProvider = provider;
    }

    @Override // javax.inject.Provider
    public T get() {
        T t;
        ScopeSet scopeSet = ScopeSet.get();
        InjectorThreadStack injectorThreadStack = this.mContextScope.getInjectorThreadStack();
        Context context = injectorThreadStack.getContext();
        if (context != null) {
            if (!(context instanceof Application)) {
                scopeSet.assertNoInvalidScopesInSet((byte) 8, INVALID_SCOPES);
            }
            byte enterScopes = scopeSet.enterScopes((byte) 8);
            try {
                PropertyBag propertyBagForContext = this.mContextScope.getPropertyBagForContext(context);
                synchronized (this) {
                    t = propertyBagForContext != null ? (T) propertyBagForContext.getProperty(this) : this.mValueInAppContext;
                    if (t == null) {
                        this.mContextScope.enterScope(context, injectorThreadStack);
                        try {
                            t = this.mUnscopedProvider.get();
                            if (propertyBagForContext != null) {
                                propertyBagForContext.setProperty(this, t);
                            } else {
                                this.mValueInAppContext = t;
                            }
                        } finally {
                            this.mContextScope.exitScope(injectorThreadStack);
                        }
                    }
                }
                return t;
            } finally {
                scopeSet.resetScopes(enterScopes);
            }
        } else {
            throw new ProvisioningException("Called context scoped provider outside of context scope");
        }
    }
}
