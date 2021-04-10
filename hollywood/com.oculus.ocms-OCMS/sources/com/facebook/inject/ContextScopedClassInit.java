package com.facebook.inject;

import android.content.Context;
import com.facebook.common.propertybag.PropertyBag;
import javax.annotation.Nullable;

public final class ContextScopedClassInit {
    @Nullable
    private ContextScope mContextScope;
    @Nullable
    private Byte mEnterResult;
    @Nullable
    private InjectorThreadStack mInjectorThreadStack;
    @Nullable
    public Object mInstance;
    @Nullable
    private PropertyBag mPropertyBag;
    @Nullable
    private ScopeSet mScopeSet;
    @Nullable
    private Object mValueInAppContext;

    public static ContextScopedClassInit get(@Nullable ContextScopedClassInit contextScopedClassInit) {
        if (contextScopedClassInit == null) {
            contextScopedClassInit = new ContextScopedClassInit();
        }
        if (contextScopedClassInit.mScopeSet == null) {
            return contextScopedClassInit;
        }
        throw new IllegalStateException("reentrant injection or failed cleanup detected");
    }

    public final boolean prepareForCreateInstance(InjectorLike injectorLike) {
        this.mScopeSet = ScopeSet.get();
        this.mEnterResult = Byte.valueOf(this.mScopeSet.enterScopes((byte) 8));
        Context injectorContext = injectorLike.getScopeAwareInjector().getInjectorContext();
        if (injectorContext != null) {
            this.mContextScope = (ContextScope) injectorLike.getScope(ContextScoped.class);
            this.mPropertyBag = this.mContextScope.getPropertyBagForContext(injectorContext);
            PropertyBag propertyBag = this.mPropertyBag;
            if (propertyBag != null) {
                this.mInstance = propertyBag.getProperty(this);
            } else {
                this.mInstance = this.mValueInAppContext;
            }
            if (this.mInstance != null) {
                return false;
            }
            this.mInjectorThreadStack = injectorLike.getInjectorThreadStack();
            this.mContextScope.enterScope(injectorContext, this.mInjectorThreadStack);
            return true;
        }
        throw new ProvisioningException("Called context scoped provider outside of context scope");
    }

    public final ScopeAwareInjector getScopeAwareInjector() {
        return this.mInjectorThreadStack.getScopeAwareInjector();
    }

    public final void finish() {
        try {
            if (this.mInjectorThreadStack != null) {
                this.mContextScope.exitScope(this.mInjectorThreadStack);
            }
            if (this.mPropertyBag != null) {
                if (!(this.mInstance == null || this.mInjectorThreadStack == null)) {
                    this.mPropertyBag.setProperty(this, this.mInstance);
                }
            } else if (this.mValueInAppContext == null) {
                this.mValueInAppContext = this.mInstance;
            }
            if (this.mEnterResult != null) {
                this.mScopeSet.resetScopes(this.mEnterResult.byteValue());
            }
        } finally {
            this.mInjectorThreadStack = null;
            this.mContextScope = null;
            this.mPropertyBag = null;
            this.mEnterResult = null;
            this.mScopeSet = null;
            this.mInstance = null;
        }
    }
}
