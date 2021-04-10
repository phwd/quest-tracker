package com.oculus.http.core.endpoint;

import X.AbstractC0247Xu;
import X.AnonymousClass06;
import X.C0092Hd;
import X.C0515sp;
import X.I0;
import android.text.TextUtils;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public abstract class EndpointModule extends I0 {
    public static final String API_ENDPOINT_FACEBOOK = "https://api.facebook.com/";
    public static final String ENDPOINT_OCULUS = "https://graph.oculus.com/";
    public static final String GRAPH_ENDPOINT_FACEBOOK = "https://graph.facebook.com/";
    public static final String GRAPH_VIDEO_ENDPOINT_FACEBOOK = "https://graph-video.facebook.com/";

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForEndpointModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = 129;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = 34;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = 100;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = 47;
    }

    public static String A00() {
        String A02 = C0092Hd.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass06.A05("https://graph.", A02, ".oculus.com");
        }
        return ENDPOINT_OCULUS;
    }

    @AutoGeneratedFactoryMethod
    public static final String A02() {
        String A02 = C0092Hd.A02("debug.oculus.graphtier");
        if (!TextUtils.isEmpty(A02)) {
            return AnonymousClass06.A05("https://graph.", A02, ".facebook.com");
        }
        return GRAPH_ENDPOINT_FACEBOOK;
    }

    @AutoGeneratedAccessMethod
    public static final String A04(AbstractC0247Xu xu) {
        return (String) C0515sp.A00(129, xu);
    }

    @AutoGeneratedAccessMethod
    public static final String A05(AbstractC0247Xu xu) {
        return (String) C0515sp.A00(34, xu);
    }

    @AutoGeneratedAccessMethod
    public static final String A06(AbstractC0247Xu xu) {
        return (String) C0515sp.A00(100, xu);
    }

    @AutoGeneratedAccessMethod
    public static final String A07(AbstractC0247Xu xu) {
        return (String) C0515sp.A00(47, xu);
    }

    @AutoGeneratedFactoryMethod
    public static final String A01() {
        return API_ENDPOINT_FACEBOOK;
    }

    @AutoGeneratedFactoryMethod
    public static final String A03() {
        return GRAPH_VIDEO_ENDPOINT_FACEBOOK;
    }
}
