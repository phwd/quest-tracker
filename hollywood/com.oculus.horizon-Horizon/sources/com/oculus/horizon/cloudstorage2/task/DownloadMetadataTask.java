package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesRequest;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID"})
public class DownloadMetadataTask extends AsyncWork<UserCloudFilesResponse> {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppGroupingId;

    public final AnonymousClass0DC<UserCloudFilesResponse> A00() {
        final AnonymousClass0DD r3 = new AnonymousClass0DD();
        ((ApiRequestManager) AnonymousClass0J2.A03(0, 407, this._UL_mInjectionContext)).post(new UserCloudFilesRequest(this.mAppGroupingId), new ApiCallback<UserCloudFilesResponse>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadMetadataTask.AnonymousClass1 */

            @Override // com.oculus.http.core.base.ApiCallback
            public final void onError(ApiError apiError) {
                r3.A01(apiError);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.http.core.base.ApiCallback
            public final void onResponse(UserCloudFilesResponse userCloudFilesResponse) {
                r3.A02(userCloudFilesResponse);
            }
        });
        return r3.A00;
    }

    @Inject
    public DownloadMetadataTask(AbstractC06640p5 r3, @Assisted String str, @Assisted Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mAppGroupingId = str;
    }
}
