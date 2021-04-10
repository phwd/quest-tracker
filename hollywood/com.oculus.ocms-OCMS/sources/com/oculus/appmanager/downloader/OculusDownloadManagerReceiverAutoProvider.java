package com.oculus.appmanager.downloader;

import com.facebook.inject.AbstractComponentProvider;

public class OculusDownloadManagerReceiverAutoProvider extends AbstractComponentProvider<OculusDownloadManagerReceiver> {
    public void inject(OculusDownloadManagerReceiver oculusDownloadManagerReceiver) {
        OculusDownloadManagerReceiver._UL_staticInjectMe(this, oculusDownloadManagerReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusDownloadManagerReceiverAutoProvider;
    }
}
