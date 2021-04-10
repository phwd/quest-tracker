package com.oculus.downloader.extras;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DownloadExtrasAutoProvider extends AbstractProvider<DownloadExtras> {
    @Override // javax.inject.Provider
    public DownloadExtras get() {
        return new DownloadExtras(this);
    }
}
