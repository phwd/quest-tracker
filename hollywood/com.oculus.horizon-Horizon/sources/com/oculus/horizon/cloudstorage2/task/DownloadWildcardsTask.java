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
import com.oculus.horizon.api.cloudstorage2.UserCloudFileWildcardsRequest;
import com.oculus.horizon.api.cloudstorage2.UserCloudFileWildcardsResponse;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import java.util.ArrayList;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID"})
public class DownloadWildcardsTask extends AsyncWork<String[]> {
    public static final String[] WILDCARDS_DEFAULT_ALL = {"/**/*"};
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppGroupingId;

    /* JADX DEBUG: Type inference failed for r0v3. Raw type applied. Possible types: X.0DC<TResult>, X.0DC<java.lang.String[]> */
    public final AnonymousClass0DC<String[]> A00() {
        final AnonymousClass0DD r3 = new AnonymousClass0DD();
        ((ApiRequestManager) AnonymousClass0J2.A03(0, 407, this._UL_mInjectionContext)).post(new UserCloudFileWildcardsRequest(this.mAppGroupingId), new ApiCallback<UserCloudFileWildcardsResponse>() {
            /* class com.oculus.horizon.cloudstorage2.task.DownloadWildcardsTask.AnonymousClass1 */

            @Override // com.oculus.http.core.base.ApiCallback
            public final void onError(ApiError apiError) {
                r3.A01(apiError);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.http.core.base.ApiCallback
            public final void onResponse(UserCloudFileWildcardsResponse userCloudFileWildcardsResponse) {
                UserCloudFileWildcardsResponse.UserCloudFileWildcard[] userCloudFileWildcardArr = userCloudFileWildcardsResponse.cloud_file_wildcards.nodes;
                int length = userCloudFileWildcardArr.length;
                if (length == 0) {
                    r3.A02(DownloadWildcardsTask.WILDCARDS_DEFAULT_ALL);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (UserCloudFileWildcardsResponse.UserCloudFileWildcard userCloudFileWildcard : userCloudFileWildcardArr) {
                    arrayList.add(userCloudFileWildcard.wildcard);
                }
                r3.A02(arrayList.toArray(new String[arrayList.size()]));
            }
        });
        return (AnonymousClass0DC<TResult>) r3.A00;
    }

    @Inject
    public DownloadWildcardsTask(AbstractC06640p5 r3, @Assisted String str, @Assisted Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mAppGroupingId = str;
    }
}
