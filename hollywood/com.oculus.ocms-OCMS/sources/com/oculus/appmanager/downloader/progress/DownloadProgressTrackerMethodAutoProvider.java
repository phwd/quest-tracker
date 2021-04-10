package com.oculus.appmanager.downloader.progress;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.downloader.progress.DownloadProgressTracker;

@Generated({"By: InjectorProcessor"})
public class DownloadProgressTrackerMethodAutoProvider extends AbstractProvider<DownloadProgressTracker> {
    @Override // javax.inject.Provider
    public DownloadProgressTracker get() {
        return OuculusDownloadProgressTrackerModule.provideProgressTracker(OculusDownloadProgressTracker._UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_ACCESS_METHOD(this));
    }
}
