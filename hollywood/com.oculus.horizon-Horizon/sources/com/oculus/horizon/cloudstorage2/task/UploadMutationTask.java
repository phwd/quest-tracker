package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.api.cloudstorage2.RemoveUserCloudFile;
import com.oculus.horizon.api.cloudstorage2.UploadAndRemoveUserCloudFilesResponse;
import com.oculus.horizon.api.cloudstorage2.UploadUserCloudFile;
import com.oculus.horizon.cloudstorage2.Reporter;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID"})
public class UploadMutationTask extends AsyncWork<UploadAndRemoveUserCloudFilesResponse> {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final String mAppGroupingId;
    public final RemoveUserCloudFile[] mRemoves;
    public final UploadUserCloudFile[] mUploads;

    @Inject
    public UploadMutationTask(AbstractC06640p5 r3, @Assisted String str, @Assisted UploadUserCloudFile[] uploadUserCloudFileArr, @Assisted RemoveUserCloudFile[] removeUserCloudFileArr, @Assisted Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mAppGroupingId = str;
        this.mUploads = uploadUserCloudFileArr;
        this.mRemoves = removeUserCloudFileArr;
    }
}
