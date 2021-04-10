package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.service.OVRService;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID"})
public class GetCloudStorage2UserDirectoryPathResultBuilder extends ResultBuilder {
    @Inject
    @Eager
    public final CloudStorageManager mCloudStorageManager;
    @Inject
    @Eager
    public final OVRLibrary mOVRLibrary;

    @Inject
    public GetCloudStorage2UserDirectoryPathResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService) {
        super(oVRService);
        this.mOVRLibrary = OVRLibraryModule.A00(r2);
        this.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
    }
}
