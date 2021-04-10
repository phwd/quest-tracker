package com.oculus.downloader.init;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.downloader.progress.DownloadProgressTracker;

@Dependencies({"_UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID"})
public class DownloaderInit implements INeedInit {
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        ((DownloadProgressTracker) AnonymousClass0J2.A04(81, this._UL_mInjectionContext)).A9M();
    }

    @Inject
    public DownloaderInit(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(0, r3);
    }
}
