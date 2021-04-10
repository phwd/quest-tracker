package com.facebook.inject;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface ScopeAwareInjector extends BasicScopeAwareInjector {
    Context getInjectorContext();

    Class<? extends Scope> getScope();

    ScopeUnawareInjector getScopeUnawareInjector();

    ViewerContextManager getViewerContextManager();
}
