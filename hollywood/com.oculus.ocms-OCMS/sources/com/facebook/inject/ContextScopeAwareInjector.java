package com.facebook.inject;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.common.util.context.wrapper.ContextWrapperUtils;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import javax.annotation.concurrent.GuardedBy;

public class ContextScopeAwareInjector extends BaseScopeAwareInjector implements ScopeAwareInjector, InjectableComponentWithoutContext {
    private InjectionContext _UL_mInjectionContext;
    @GuardedBy("this")
    private boolean isInjected = false;
    private final Context mContext;

    /* access modifiers changed from: package-private */
    @Inject
    public final void injectContextScopeAwareInjector() {
    }

    private static final void _UL_injectMe(Context context, ContextScopeAwareInjector contextScopeAwareInjector) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), contextScopeAwareInjector);
        } else {
            FbInjector.injectMe(ContextScopeAwareInjector.class, (InjectableComponentWithoutContext) contextScopeAwareInjector, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, ContextScopeAwareInjector contextScopeAwareInjector) {
        contextScopeAwareInjector._UL_mInjectionContext = new InjectionContext(0, injectorLike);
        contextScopeAwareInjector.injectContextScopeAwareInjector();
    }

    public ContextScopeAwareInjector(FbInjector fbInjector, Context context) {
        super(fbInjector);
        this.mContext = context;
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Context getInjectorContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.DelegatingInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> cls, T t) {
        InjectorThreadStack enterPreamble = enterPreamble();
        try {
            this.mDelegate.injectComponent(cls, t);
        } finally {
            exitPostamble(enterPreamble);
        }
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public InjectorThreadStack enterPreamble() {
        InjectorThreadStack injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.enterContext(getInjectorContext());
        injectorThreadStack.pushInjector(this);
        return injectorThreadStack;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object obj) {
        exitPostamble((InjectorThreadStack) obj);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public ViewerContextManager getViewerContextManager() {
        int i;
        InjectionContext injectionContext;
        ensureInjected();
        Activity activity = (Activity) ContextWrapperUtils.findContextOfType(getInjectorContext(), Activity.class);
        Service service = (Service) ContextWrapperUtils.findContextOfType(getInjectorContext(), Service.class);
        if (activity == null && service == null) {
            i = BundledAndroidModule.UL_id._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_BINDING_ID;
            injectionContext = this._UL_mInjectionContext;
        } else {
            i = BundledAndroidModule.UL_id._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_BINDING_ID;
            injectionContext = this._UL_mInjectionContext;
        }
        return (ViewerContextManager) FbInjector.localInstance(i, injectionContext);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Class<? extends Scope> getScope() {
        return ContextScope.class;
    }

    private synchronized void ensureInjected() {
        if (!this.isInjected) {
            _UL_injectMe(getInjectorContext(), this);
        }
        this.isInjected = true;
    }

    private void exitPostamble(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.popInjector();
        injectorThreadStack.exitContext();
    }
}
