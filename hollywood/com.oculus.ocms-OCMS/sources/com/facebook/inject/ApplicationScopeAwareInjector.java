package com.facebook.inject;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import javax.annotation.concurrent.GuardedBy;

public class ApplicationScopeAwareInjector extends BaseScopeAwareInjector implements ScopeAwareInjector, InjectableComponentWithoutContext {
    private InjectionContext _UL_mInjectionContext;
    @GuardedBy("this")
    private boolean isInjected = false;
    private final ApplicationScope mApplicationScopeScope;
    private final FbInjector mDelegate;

    /* access modifiers changed from: package-private */
    @Inject
    public final void injectApplicationScopeAwareInjector() {
    }

    private static final void _UL_injectMe(Context context, ApplicationScopeAwareInjector applicationScopeAwareInjector) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), applicationScopeAwareInjector);
        } else {
            FbInjector.injectMe(ApplicationScopeAwareInjector.class, (InjectableComponentWithoutContext) applicationScopeAwareInjector, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, ApplicationScopeAwareInjector applicationScopeAwareInjector) {
        applicationScopeAwareInjector._UL_mInjectionContext = new InjectionContext(0, injectorLike);
        applicationScopeAwareInjector.injectApplicationScopeAwareInjector();
    }

    public ApplicationScopeAwareInjector(FbInjector fbInjector, ApplicationScope applicationScope) {
        super(fbInjector);
        this.mDelegate = fbInjector;
        this.mApplicationScopeScope = applicationScope;
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public Object enterPreamble() {
        return this.mApplicationScopeScope.enterScope();
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object obj) {
        this.mApplicationScopeScope.exitScope((InjectorThreadStack) obj);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Context getInjectorContext() {
        return this.mApplicationScopeScope.getContext();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.DelegatingInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> cls, T t) {
        throw new IllegalStateException("injectComponent should only be called on ContextScope");
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public ViewerContextManager getViewerContextManager() {
        ensureInjected();
        return (ViewerContextManager) FbInjector.localInstance(BundledAndroidModule.UL_id._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_BINDING_ID, this._UL_mInjectionContext);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Class<? extends Scope> getScope() {
        return ApplicationScope.class;
    }

    private synchronized void ensureInjected() {
        if (!this.isInjected) {
            _UL_injectMe(this.mApplicationScopeScope.getContext(), this);
        }
        this.isInjected = true;
    }
}
