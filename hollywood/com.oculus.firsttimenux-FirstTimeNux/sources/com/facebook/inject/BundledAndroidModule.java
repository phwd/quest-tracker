package com.facebook.inject;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContextManager;
import com.facebook.auth.viewercontext.ViewerContextManagerForApp;
import com.facebook.auth.viewercontext.ViewerContextManagerForContext;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.infer.annotation.Assertions;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

@InjectorModule
public class BundledAndroidModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
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

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_content_Context$x3E$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForBundledAndroidModule {
        AutoGeneratedBindingsForBundledAndroidModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.bindScope(ApplicationScoped.class, new ApplicationScope((FbInjector) binder.getInjector()));
                binder.bindScope(ContextScoped.class, new ContextScope((FbInjector) binder.getInjector()));
                binder.bind(Context.class).annotatedWith(ForAppContext.class).toProvider(new Context_com_facebook_inject_ForAppContextMethodAutoProvider());
                binder.bind(Context.class).annotatedWith(UnsafeContextInjection.class).toProvider(new Context_com_facebook_inject_UnsafeContextInjectionMethodAutoProvider());
                binder.bindDefault(ViewerContextManager.class).annotatedWith(ViewerContextManagerForApp.class).toProvider(new ViewerContextManager_com_facebook_auth_viewercontext_ViewerContextManagerForAppMethodAutoProvider());
                binder.bindDefault(ViewerContextManager.class).annotatedWith(ViewerContextManagerForContext.class).toProvider(new ViewerContextManager_com_facebook_auth_viewercontext_ViewerContextManagerForContextMethodAutoProvider());
                binder.bind(FbInjector.class).toInstance((FbInjector) binder.getInjector());
                binder.bind(InjectorLike.class).toProvider(new InjectorLikeMethodAutoProvider());
                binder.bindComponent(ApplicationScopeAwareInjector.class).toProvider(new ApplicationScopeAwareInjectorAutoProvider());
                binder.bindComponent(ContextScopeAwareInjector.class).toProvider(new ContextScopeAwareInjectorAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Context) UL.factorymap.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedFactoryMethod
    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideAppContext($ul_$xXXcom_facebook_inject_InjectorLike$xXXACCESS_METHOD($ul_injector));
    }

    @AutoGeneratedAccessMethod
    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (Context) UL.factorymap.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedFactoryMethod
    public static final Context $ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideUnsafeCurrentContext($ul_$xXXcom_facebook_inject_InjectorLike$xXXACCESS_METHOD($ul_injector));
    }

    @AutoGeneratedAccessMethod
    public static final ViewerContextManager $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (ViewerContextManager) UL.factorymap.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedFactoryMethod
    public static final ViewerContextManager $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return assertViewerContextManagerForApp();
    }

    @AutoGeneratedAccessMethod
    public static final ViewerContextManager $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (ViewerContextManager) UL.factorymap.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedFactoryMethod
    public static final ViewerContextManager $ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return assertViewerContextManagerForContext();
    }

    @AutoGeneratedAccessMethod
    public static final FbInjector $ul_$xXXcom_facebook_inject_FbInjector$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (FbInjector) UL.factorymap.get(UL_id.$ul_$xXXcom_facebook_inject_FbInjector$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedFactoryMethod
    public static final FbInjector $ul_$xXXcom_facebook_inject_FbInjector$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return assertFbInjector();
    }

    @AutoGeneratedAccessMethod
    public static final InjectorLike $ul_$xXXcom_facebook_inject_InjectorLike$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return $ul_injector;
    }

    @AutoGeneratedFactoryMethod
    public static final InjectorLike $ul_$xXXcom_facebook_inject_InjectorLike$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return assertInjectorLike();
    }

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Candroid_content_Context$x3E$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_facebook_auth_viewercontext_ViewerContextManager$x3E$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_facebook_auth_viewercontext_ViewerContextManager$x3E$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_facebook_inject_FbInjector$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_facebook_inject_FbInjector$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_facebook_inject_InjectorLike$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_facebook_inject_InjectorLike$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_content_Context$x3E$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Candroid_content_Context$x3E$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_facebook_auth_viewercontext_ViewerContextManager$x3E$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForApp$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_facebook_auth_viewercontext_ViewerContextManager$x3E$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_facebook_auth_viewercontext_ViewerContextManager$xXXcom_facebook_auth_viewercontext_ViewerContextManagerForContext$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_facebook_inject_FbInjector$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_facebook_inject_FbInjector$xXXBINDING_ID, $ul_injector);
    }

    @AutoGeneratedAccessMethod
    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_facebook_inject_InjectorLike$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_facebook_inject_InjectorLike$xXXBINDING_ID, $ul_injector);
    }

    @ProviderMethod
    static FbInjector assertFbInjector() {
        throw Assertions.assertUnreachable();
    }

    @ProviderMethod
    static InjectorLike assertInjectorLike() {
        throw Assertions.assertUnreachable();
    }

    @ViewerContextManagerForContext
    @ProviderMethod(asDefault = BuildConfig.IS_INTERNAL_BUILD)
    static ViewerContextManager assertViewerContextManagerForContext() {
        throw Assertions.assertUnreachable();
    }

    @ProviderMethod(asDefault = BuildConfig.IS_INTERNAL_BUILD)
    @ViewerContextManagerForApp
    static ViewerContextManager assertViewerContextManagerForApp() {
        throw Assertions.assertUnreachable();
    }

    @UnsafeContextInjection
    @ProviderMethod
    static Context provideUnsafeCurrentContext(InjectorLike injector) {
        return getContext(injector);
    }

    private static Context getContext(InjectorLike injector) {
        Context context = injector.getScopeAwareInjector().getInjectorContext();
        if (context == null) {
            throw new RuntimeException();
        } else if (context == context.getApplicationContext() || !ScopeSet.get().hasScope((byte) 1)) {
            return context;
        } else {
            throw new ProvisioningException("Should not call getContext in singleton creation. Can lead to memory leaks.");
        }
    }

    @ForAppContext
    @ProviderMethod
    static Context provideAppContext(InjectorLike injector) {
        Context context = injector.getScopeAwareInjector().getInjectorContext();
        if (context != null) {
            return context.getApplicationContext();
        }
        throw new RuntimeException();
    }
}