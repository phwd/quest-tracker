package com.oculus.appmanager.downloader.progress;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusDownloadProgressTrackerAutoProvider extends AbstractProvider<OculusDownloadProgressTracker> {
    @Override // javax.inject.Provider
    public OculusDownloadProgressTracker get() {
        return new OculusDownloadProgressTracker(this);
    }
}
