package com.facebook.auth.viewercontext;

import android.content.Intent;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ViewerContextManager {
    public static final String VIEWER_CONTEXT_EXTRA = "com.facebook.orca.auth.OVERRIDDEN_VIEWER_CONTEXT";

    Intent getIntentForViewerContext();

    ViewerContext getLoggedInUserViewerContext();

    ViewerContext getOriginalViewerContext();

    @Nullable
    ViewerContext getOverriddenViewerContext();

    ViewerContext getViewerContext();

    @Nullable
    ViewerContext getViewerContextForTransferBetweenThreads();

    Intent maybeAddOverriddenViewerContext(@Nullable Intent intent);

    void popViewerContext();

    PushedViewerContext pushViewerContext(@Nullable ViewerContext viewerContext);

    void setOverriddenViewerContext(@Nullable ViewerContext viewerContext);
}
