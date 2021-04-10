package com.facebook.inject;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import javax.annotation.concurrent.GuardedBy;

public class ApplicationScopeAwareInjector extends BaseScopeAwareInjector implements ScopeAwareInjector, InjectableComponentWithoutContext {
    private InjectionContext $ul_mInjectionContext;
    @GuardedBy("this")
    private boolean isInjected = false;
    private final ApplicationScope mApplicationScopeScope;
    private final FbInjector mDelegate;

    private static final void $ul_injectMe(Context $ul_context, ApplicationScopeAwareInjector $ul_instance) {
        if (UL.USE_STATIC_DI) {
            $ul_staticInjectMe(FbInjector.get($ul_context), $ul_instance);
        } else {
            FbInjector.injectMe(ApplicationScopeAwareInjector.class, (InjectableComponentWithoutContext) $ul_instance, $ul_context);
        }
    }

    public static final void $ul_staticInjectMe(InjectorLike $ul_injector, ApplicationScopeAwareInjector $ul_instance) {
        $ul_instance.$ul_mInjectionContext = new InjectionContext(0, $ul_injector);
        $ul_instance.injectApplicationScopeAwareInjector();
    }

    public ApplicationScopeAwareInjector(FbInjector delegate, ApplicationScope ApplicationScopeScope) {
        super(delegate);
        this.mDelegate = delegate;
        this.mApplicationScopeScope = ApplicationScopeScope;
    }

    /* access modifiers changed from: package-private */
    @Inject
    public final void injectApplicationScopeAwareInjector() {
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public Object enterPreamble() {
        return this.mApplicationScopeScope.enterScope();
    }

    @Override // com.facebook.inject.BasicScopeAwareInjector
    public void exitPostamble(Object token) {
        this.mApplicationScopeScope.exitScope((InjectorThreadStack) token);
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
        return (ViewerContextManager) FbInjector.localInstance(BundledAndroidModule.UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID, this.$ul_mInjectionContext);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Class<? extends Scope> getScope() {
        return ApplicationScope.class;
    }

    private synchronized void ensureInjected() {
        if (!this.isInjected) {
            $ul_injectMe(this.mApplicationScopeScope.getContext(), this);
        }
        this.isInjected = true;
    }
}
