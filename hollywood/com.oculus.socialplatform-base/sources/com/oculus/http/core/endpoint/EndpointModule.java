package com.oculus.http.core.endpoint;

import X.AbstractC03180ld;
import X.AnonymousClass006;
import X.AnonymousClass0GR;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C01150Rm;
import android.text.TextUtils;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.RequiresBinding;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.core.annotations.FacebookApiEndpoint;
import com.oculus.http.core.annotations.FacebookGraphEndpoint;
import com.oculus.http.core.annotations.FacebookGraphVideoEndpoint;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import javax.inject.Provider;

@InjectorModule
public abstract class EndpointModule extends AnonymousClass0VI {
    public static final String API_ENDPOINT_FACEBOOK = "https://api.facebook.com/";
    public static final String ENDPOINT_OCULUS = "https://graph.oculus.com/";
    public static final String GRAPH_ENDPOINT_FACEBOOK = "https://graph.facebook.com/";
    public static final String GRAPH_VIDEO_ENDPOINT_FACEBOOK = "https://graph-video.facebook.com/";

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = 112;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = 30;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = 86;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = 43;
    }

    @FacebookApiEndpoint
    @ProviderMethod
    public static String provideFacebookApiEndpoint() {
        return API_ENDPOINT_FACEBOOK;
    }

    @ProviderMethod
    @FacebookGraphVideoEndpoint
    public static String provideFacebookGraphVideoEndpoint() {
        return GRAPH_VIDEO_ENDPOINT_FACEBOOK;
    }

    @RequiresBinding
    @OculusApiEndpoint
    public abstract String assertOculusApiEndpoint();

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(112, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(30, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(86, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(43, r1);
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (String) AnonymousClass1TK.A00(112, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        C01150Rm.A00(API_ENDPOINT_FACEBOOK, r1);
        return API_ENDPOINT_FACEBOOK;
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (String) AnonymousClass1TK.A00(30, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (String) AnonymousClass1TK.A00(86, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        C01150Rm.A00(GRAPH_VIDEO_ENDPOINT_FACEBOOK, r1);
        return GRAPH_VIDEO_ENDPOINT_FACEBOOK;
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (String) AnonymousClass1TK.A00(43, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(112, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(30, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(86, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(43, r1);
    }

    public static String getEndpointOculus() {
        String A02 = AnonymousClass0GR.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass006.A09("https://graph.", A02, ".oculus.com");
        }
        return ENDPOINT_OCULUS;
    }

    @FacebookGraphEndpoint
    @ProviderMethod
    public static String provideFacebookGraphEndpoint() {
        String A02 = AnonymousClass0GR.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass006.A09("https://graph.", A02, ".facebook.com");
        }
        return GRAPH_ENDPOINT_FACEBOOK;
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForEndpointModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        String provideFacebookGraphEndpoint = provideFacebookGraphEndpoint();
        C01150Rm.A00(provideFacebookGraphEndpoint, r1);
        return provideFacebookGraphEndpoint;
    }
}
