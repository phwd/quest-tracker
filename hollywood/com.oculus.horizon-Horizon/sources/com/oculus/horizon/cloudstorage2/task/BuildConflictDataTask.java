package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.cloudstorage2.ConflictData;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.horizon.cloudstorage2.model.CloudStorage2Metadata;
import com.oculus.library.model.App;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_CloudStorageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadMetadataTaskProvider_ULSEP_BINDING_ID"})
public class BuildConflictDataTask extends AsyncWork<ConflictData> {
    public final App mApp;
    @Inject
    @Eager
    public final CloudStorageManager mCloudStorageManager;
    @Inject
    @Eager
    public final DownloadMetadataTaskProvider mDownloadMetadataProvider;
    public final CloudStorage2Metadata mLocalMeta;

    @Inject
    public BuildConflictDataTask(AbstractC06640p5 r2, @Assisted App app, @Assisted CloudStorage2Metadata cloudStorage2Metadata, @Assisted Reporter reporter) {
        super(reporter);
        this.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
        this.mDownloadMetadataProvider = (DownloadMetadataTaskProvider) AnonymousClass117.A00(31, r2);
        this.mApp = app;
        this.mLocalMeta = cloudStorage2Metadata;
    }
}
