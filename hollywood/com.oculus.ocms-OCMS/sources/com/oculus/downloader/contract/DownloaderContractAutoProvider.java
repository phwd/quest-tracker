package com.oculus.downloader.contract;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DownloaderContractAutoProvider extends AbstractProvider<DownloaderContract> {
    @Override // javax.inject.Provider
    public DownloaderContract get() {
        return new DownloaderContract(this);
    }
}
