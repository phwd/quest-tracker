package com.oculus.mediaupload.api;

import X.AnonymousClass0J5;
import X.AnonymousClass0N1;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.core.base.ApiErrorCodes;
import retrofit.RestAdapter;

@InjectorModule
public class ApiModule extends AnonymousClass0J5 {
    public static volatile AnonymousClass0N1 _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_mediaupload_api_FacebookHttpClient_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_mediaupload_api_FacebookHttpClient_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_mediaupload_api_FacebookGraphRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_mediaupload_api_FacebookGraphRestAdapter_ULSEP_LOCK = new Object();

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForApiModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_mediaupload_FacebookMediaUploaderServiceManager_ULSEP_BINDING_ID = 580;
        public static final int _UL__ULSEP_com_oculus_mediaupload_MediaUploaderRequestRunner_ULSEP_BINDING_ID = 459;
        public static final int _UL__ULSEP_com_oculus_mediaupload_MediaUploaderServiceManager_ULSEP_BINDING_ID = 154;
        public static final int _UL__ULSEP_com_oculus_mediaupload_OculusMediaUploaderServiceManager_ULSEP_BINDING_ID = 416;
        public static final int _UL__ULSEP_com_oculus_mediaupload_api_FacebookShareMethods_ULSEP_BINDING_ID = ApiErrorCodes.ERROR_CODE_RATE_LIMITED;
        public static final int _UL__ULSEP_com_oculus_mediaupload_api_MediaUploaderMetadataHelper_ULSEP_BINDING_ID = 197;
        public static final int _UL__ULSEP_com_oculus_mediaupload_api_OculusShareMethods_ULSEP_BINDING_ID = 11;
        public static final int _UL__ULSEP_com_oculus_mediaupload_io_FacebookGamingProfileTokenManager_ULSEP_BINDING_ID = 460;
        public static final int _UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderDB_ULSEP_BINDING_ID = 411;
        public static final int _UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderNotifications_ULSEP_BINDING_ID = 317;
        public static final int _UL__ULSEP_com_oculus_mediaupload_logging_UploadEventFactory_ULSEP_BINDING_ID = 16;
        public static final int _UL__ULSEP_com_oculus_mediaupload_model_MediaUploaderIntentValidator_ULSEP_BINDING_ID = 26;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_mediaupload_api_FacebookHttpClient_ULSEP_BINDING_ID = 473;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_mediaupload_api_FacebookGraphRestAdapter_ULSEP_BINDING_ID = 482;
    }
}