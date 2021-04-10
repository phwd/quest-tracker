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
    private InjectionContext $ul_mInjectionContext;
    @GuardedBy("this")
    private boolean isInjected = false;
    private final Context mContext;

    private static final void $ul_injectMe(Context $ul_context, ContextScopeAwareInjector $ul_instance) {
        if (UL.USE_STATIC_DI) {
            $ul_staticInjectMe(FbInjector.get($ul_context), $ul_instance);
        } else {
            FbInjector.injectMe(ContextScopeAwareInjector.class, (InjectableComponentWithoutContext) $ul_instance, $ul_context);
        }
    }

    public static final void $ul_staticInjectMe(InjectorLike $ul_injector, ContextScopeAwareInjector $ul_instance) {
        $ul_instance.$ul_mInjectionContext = new InjectionContext(0, $ul_injector);
        $ul_instance.injectContextScopeAwareInjector();
    }

    public ContextScopeAwareInjector(FbInjector delegate, Context context) {
        super(delegate);
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    @Inject
    public final void injectContextScopeAwareInjector() {
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Context getInjectorContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.inject.AbstractInjector, com.facebook.inject.DelegatingInjector, com.facebook.inject.FbInjector
    public <T> void injectComponent(Class<T> type, T instance) {
        InjectorThreadStack injectorThreadStack = enterPreamble();
        try {
            this.mDelegate.injectComponent(type, instance);
        } finally {
            exitPostamble(injectorThreadStack);
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
    public void exitPostamble(Object token) {
        exitPostamble((InjectorThreadStack) token);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public ViewerContextManager getViewerContextManager() {
        ensureInjected();
        return (((Activity) ContextWrapperUtils.findContextOfType(getInjectorContext(), Activity.class)) == null && ((Service) ContextWrapperUtils.findContextOfType(getInjectorContext(), Service.class)) == null) ? (ViewerContextManager) FbInjector.localInstance(BundledAndroidModule.UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID, this.$ul_mInjectionContext) : (ViewerContextManager) FbInjector.localInstance(BundledAndroidModule.UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID, this.$ul_mInjectionContext);
    }

    @Override // com.facebook.inject.ScopeAwareInjector
    public Class<? extends Scope> getScope() {
        return ContextScope.class;
    }

    private synchronized void ensureInjected() {
        if (!this.isInjected) {
            $ul_injectMe(getInjectorContext(), this);
        }
        this.isInjected = true;
    }

    private void exitPostamble(InjectorThreadStack injectorThreadStack) {
        injectorThreadStack.popInjector();
        injectorThreadStack.exitContext();
    }
}
