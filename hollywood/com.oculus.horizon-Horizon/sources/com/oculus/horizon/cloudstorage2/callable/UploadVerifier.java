package com.oculus.horizon.cloudstorage2.callable;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import X.C08780ya;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.api.cloudstorage2.UserCloudFilesResponse;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2FileMetadata;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.library.model.App;
import java.io.IOException;
import java.util.Map;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID"})
public class UploadVerifier extends SyncWork<CloudStorage2Metadata> {
    public final App mApp;
    @Inject
    @Eager
    public final CloudStorageManager mCloudStorageManager;
    public String[] mPatterns;
    public final UserCloudFilesResponse mRemote;

    /* renamed from: A00 */
    public final CloudStorage2Metadata call() throws IOException {
        String str;
        C08780ya r4 = new C08780ya();
        CloudStorage2Metadata A00 = CloudStorage2Metadata.A00(this.mRemote);
        CloudStorage2Metadata cloudStorage2Metadata = (CloudStorage2Metadata) r4.A05(this.mCloudStorageManager.A03(this.mApp, this.mPatterns), CloudStorage2Metadata.class);
        String[] nativeGetCloudStorage2UploadConflictFiles = PlatformPluginManager.nativeGetCloudStorage2UploadConflictFiles(r4.A06(A00), r4.A06(cloudStorage2Metadata));
        int length = nativeGetCloudStorage2UploadConflictFiles.length;
        if (length == 0) {
            return A00;
        }
        Map<String, CloudStorage2FileMetadata> A01 = A00.A01();
        Map<String, CloudStorage2FileMetadata> A012 = cloudStorage2Metadata.A01();
        int i = 0;
        do {
            String str2 = nativeGetCloudStorage2UploadConflictFiles[i];
            CloudStorage2FileMetadata cloudStorage2FileMetadata = A01.get(str2);
            CloudStorage2FileMetadata cloudStorage2FileMetadata2 = A012.get(str2);
            Object[] objArr = new Object[3];
            objArr[0] = str2;
            String str3 = "";
            if (cloudStorage2FileMetadata2 == null) {
                str = str3;
            } else {
                str = cloudStorage2FileMetadata2.sha1;
            }
            objArr[1] = str;
            if (cloudStorage2FileMetadata != null) {
                str3 = cloudStorage2FileMetadata.sha1;
            }
            objArr[2] = str3;
            i++;
        } while (i < length);
        throw new IllegalStateException("Upload verification failed!");
    }

    @Inject
    public UploadVerifier(AbstractC06640p5 r2, @Assisted App app, @Assisted UserCloudFilesResponse userCloudFilesResponse, @Assisted String[] strArr, @Assisted Reporter reporter) {
        super(reporter);
        this.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
        this.mApp = app;
        this.mRemote = userCloudFilesResponse;
        this.mPatterns = strArr;
    }
}
