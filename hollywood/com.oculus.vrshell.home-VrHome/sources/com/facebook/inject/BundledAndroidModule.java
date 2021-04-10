package com.facebook.inject;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.auth.viewercontext.ViewerContextManagerForApp;
import com.facebook.auth.viewercontext.ViewerContextManagerForContext;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public class BundledAndroidModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID : UL.id.dynamicId(Key.get(Context.class, (Class<? extends Annotation>) ForAppContext.class)));
        public static final int $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID : UL.id.dynamicId(Key.get(Context.class, (Class<? extends Annotation>) UnsafeContextInjection.class)));
        public static final int $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID : UL.id.dynamicId(Key.get(ViewerContextManager.class, (Class<? extends Annotation>) ViewerContextManagerForApp.class)));
        public static final int $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID : UL.id.dynamicId(Key.get(ViewerContextManager.class, (Class<? extends Annotation>) ViewerContextManagerForContext.class)));
        public static final int $ul_$xXXcom_facebook_inject_ApplicationScope$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_inject_ApplicationScope$xXXBINDING_ID : UL.id.dynamicId(Key.get(ApplicationScope.class)));
        public static final int $ul_$xXXcom_facebook_inject_ContextScope$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_inject_ContextScope$xXXBINDING_ID : UL.id.dynamicId(Key.get(ContextScope.class)));
        public static final int $ul_$xXXcom_facebook_inject_FbInjector$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_inject_FbInjector$xXXBINDING_ID : UL.id.dynamicId(Key.get(FbInjector.class)));
        public static final int $ul_$xXXcom_facebook_inject_InjectorLike$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_inject_InjectorLike$xXXBINDING_ID : UL.id.dynamicId(Key.get(InjectorLike.class)));
    }

    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Context) UL.factorymap.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, $ul_injector);
    }

    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Context) UL.factorymap.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID, $ul_injector);
    }
}
