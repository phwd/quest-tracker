package com.oculus.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderDB_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_io_MediaUploaderNotifications_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_MediaUploaderServiceManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_logging_UploadEventFactory_ULSEP_BINDING_ID"})
public class MediaUploaderRequestRunner {
    public static final String TAG = "MediaUploaderRequestRunner";
    public static final int VIDEO_UPLOAD_CHUNK_SIZE = 2000000;
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public MediaUploaderRequestRunner(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
    }
}
