package com.oculus.appmanager.downloader;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusFileDownloaderAutoProvider extends AbstractProvider<OculusFileDownloader> {
    @Override // javax.inject.Provider
    public OculusFileDownloader get() {
        return new OculusFileDownloader(this);
    }
}
