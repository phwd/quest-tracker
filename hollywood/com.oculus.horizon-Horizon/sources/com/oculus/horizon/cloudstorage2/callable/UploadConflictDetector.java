package com.oculus.horizon.cloudstorage2.callable;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import X.C08780ya;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.library.model.App;
import java.io.IOException;
import java.util.Arrays;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID"})
public class UploadConflictDetector extends SyncWork<CloudStorage2Metadata> {
    public final App mApp;
    @Inject
    @Eager
    public final CloudStorageManager mCloudStorageManager;
    public final UserCloudFilesResponse mRemote;

    /* renamed from: A00 */
    public final CloudStorage2Metadata call() throws IOException {
        C08780ya r3 = new C08780ya();
        CloudStorage2Metadata A00 = CloudStorage2Metadata.A00(this.mRemote);
        String[] nativeGetCloudStorage2UploadConflictFiles = PlatformPluginManager.nativeGetCloudStorage2UploadConflictFiles(r3.A06(A00), r3.A06(this.mCloudStorageManager.A01(this.mApp)));
        if (nativeGetCloudStorage2UploadConflictFiles.length == 0) {
            return A00;
        }
        throw new IllegalStateException(StringFormatUtil.formatStrLocaleSafe("Upload conflicts detected: %s", Arrays.toString(nativeGetCloudStorage2UploadConflictFiles)));
    }

    @Inject
    public UploadConflictDetector(AbstractC06640p5 r2, @Assisted App app, @Assisted UserCloudFilesResponse userCloudFilesResponse, @Assisted Reporter reporter) {
        super(reporter);
        this.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
        this.mApp = app;
        this.mRemote = userCloudFilesResponse;
    }
}
