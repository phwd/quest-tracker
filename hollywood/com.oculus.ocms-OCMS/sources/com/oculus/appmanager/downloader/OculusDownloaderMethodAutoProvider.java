package com.oculus.appmanager.downloader;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.downloader.OculusDownloader;

@Generated({"By: InjectorProcessor"})
public class OculusDownloaderMethodAutoProvider extends AbstractProvider<OculusDownloader> {
    @Override // javax.inject.Provider
    public OculusDownloader get() {
        return OculusDownloaderModule.provideProgressTracker(OculusFileDownloader._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_ACCESS_METHOD(this));
    }
}
