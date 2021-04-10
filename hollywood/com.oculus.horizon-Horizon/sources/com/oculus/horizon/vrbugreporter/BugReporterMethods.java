package com.oculus.horizon.vrbugreporter;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.oculus.http.core.annotations.OculusRestAdapter;
import javax.inject.Inject;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class BugReporterMethods {
    public static final String OCULUS_THIRD_PARTY_CATEGORY_ID = "1581534861914814";
    public static final String TAG = "BugReporterMethods";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Methods mMethods;

    public interface Methods {
        @POST("/report_bug")
        @Multipart
        Response sendBugReport(@Part("app_id") String str, @Part("category_id") String str2, @Part("session_id") String str3, @Part("description") String str4, @Part("log") TypedFile typedFile, @Part("screenshot") TypedFile typedFile2, @Part("audio_clip") TypedFile typedFile3, @Part("details") TypedFile typedFile4, @Part("packages") TypedFile typedFile5);
    }

    @Inject
    public BugReporterMethods(AbstractC06640p5 r3, @OculusRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
