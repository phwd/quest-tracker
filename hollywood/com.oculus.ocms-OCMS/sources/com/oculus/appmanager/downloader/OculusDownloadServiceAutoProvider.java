package com.oculus.appmanager.downloader;

import com.facebook.inject.AbstractComponentProvider;

public class OculusDownloadServiceAutoProvider extends AbstractComponentProvider<OculusDownloadService> {
    public void inject(OculusDownloadService oculusDownloadService) {
        OculusDownloadService._UL_staticInjectMe(this, oculusDownloadService);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusDownloadServiceAutoProvider;
    }
}
