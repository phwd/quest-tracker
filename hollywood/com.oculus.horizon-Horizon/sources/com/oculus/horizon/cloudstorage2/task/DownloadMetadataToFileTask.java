package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.Reporter;
import com.oculus.library.model.App;
import java.io.File;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cloudstorage2_task_DownloadMetadataTaskProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cloudstorage2_callable_WriteMetadataToFileProvider_ULSEP_BINDING_ID"})
public class DownloadMetadataToFileTask extends AsyncWork<File> {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final App mApp;

    @Inject
    public DownloadMetadataToFileTask(AbstractC06640p5 r3, @Assisted App app, @Assisted Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(0, r3);
        this.mApp = app;
    }
}
