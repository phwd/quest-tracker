package com.oculus.http.core.endpoint;

import android.text.TextUtils;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.RequiresBinding;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.http.core.annotations.FacebookApiEndpoint;
import com.oculus.http.core.annotations.FacebookGraphEndpoint;
import com.oculus.http.core.annotations.FacebookGraphVideoEndpoint;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

@InjectorModule
public abstract class EndpointModule extends AbstractLibraryModule {
    public static final String API_ENDPOINT_FACEBOOK = "https://api.facebook.com/";
    public static final String ENDPOINT_OCULUS = "https://graph.oculus.com/";
    public static final String GRAPH_ENDPOINT_FACEBOOK = "https://graph.facebook.com/";
    public static final String GRAPH_VIDEO_ENDPOINT_FACEBOOK = "https://graph-video.facebook.com/";

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) FacebookApiEndpoint.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) FacebookGraphEndpoint.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) FacebookGraphVideoEndpoint.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) OculusApiEndpoint.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @FacebookApiEndpoint
    @ProviderMethod
    static String provideFacebookApiEndpoint() {
        return API_ENDPOINT_FACEBOOK;
    }

    @ProviderMethod
    @FacebookGraphVideoEndpoint
    static String provideFacebookGraphVideoEndpoint() {
        return GRAPH_VIDEO_ENDPOINT_FACEBOOK;
    }

    /* access modifiers changed from: package-private */
    @RequiresBinding
    @OculusApiEndpoint
    public abstract String assertOculusApiEndpoint();

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForEndpointModule {
        AutoGeneratedBindingsForEndpointModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(String.class, OculusApiEndpoint.class);
                binder.require(BundledAndroidModule.class);
                binder.bind(String.class).annotatedWith(FacebookApiEndpoint.class).toProvider(new String_com_oculus_http_core_annotations_FacebookApiEndpointMethodAutoProvider());
                binder.bind(String.class).annotatedWith(FacebookGraphEndpoint.class).toProvider(new String_com_oculus_http_core_annotations_FacebookGraphEndpointMethodAutoProvider());
                binder.bind(String.class).annotatedWith(FacebookGraphVideoEndpoint.class).toProvider(new String_com_oculus_http_core_annotations_FacebookGraphVideoEndpointMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideFacebookApiEndpoint();
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideFacebookGraphEndpoint();
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideFacebookGraphVideoEndpoint();
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID, injectorLike);
    }

    public static String getEndpointOculus() {
        String str = SystemPropertiesInternal.get("debug.oculus.graphtier");
        if (TextUtils.isEmpty(str)) {
            return ENDPOINT_OCULUS;
        }
        return "https://graph." + str + ".oculus.com";
    }

    @FacebookGraphEndpoint
    @ProviderMethod
    static String provideFacebookGraphEndpoint() {
        String str = SystemPropertiesInternal.get("debug.oculus.graphtier");
        if (TextUtils.isEmpty(str)) {
            return GRAPH_ENDPOINT_FACEBOOK;
        }
        return "https://graph." + str + ".facebook.com";
    }
}
