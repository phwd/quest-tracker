package com.oculus.http.core.endpoint;

import X.AbstractC0031Bc;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public abstract class EndpointModule extends AbstractC0031Bc {
    public static final String API_ENDPOINT_FACEBOOK = "https://api.facebook.com/";
    public static final String ENDPOINT_OCULUS = "https://graph.oculus.com/";
    public static final String GRAPH_ENDPOINT_FACEBOOK = "https://graph.facebook.com/";
    public static final String GRAPH_VIDEO_ENDPOINT_FACEBOOK = "https://graph-video.facebook.com/";

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForEndpointModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = 70;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = 47;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = 31;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = 60;
    }
}