package com.facebook.inject;

import com.facebook.annotations.Generated;
import com.facebook.auth.viewercontext.ViewerContextManager;

@Generated({"By: InjectorProcessor"})
public class ViewerContextManager_com_facebook_auth_viewercontext_ViewerContextManagerForAppMethodAutoProvider extends AbstractProvider<ViewerContextManager> {
    @Override // javax.inject.Provider
    public ViewerContextManager get() {
        return BundledAndroidModule.assertViewerContextManagerForApp();
    }
}
