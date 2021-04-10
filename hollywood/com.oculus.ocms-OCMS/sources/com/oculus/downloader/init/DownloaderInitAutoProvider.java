package com.oculus.downloader.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DownloaderInitAutoProvider extends AbstractProvider<DownloaderInit> {
    @Override // javax.inject.Provider
    public DownloaderInit get() {
        return new DownloaderInit(this);
    }
}
