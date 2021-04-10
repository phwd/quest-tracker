package com.oculus.installer;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DownloadAndInstallApiAutoProvider extends AbstractProvider<DownloadAndInstallApi> {
    @Override // javax.inject.Provider
    public DownloadAndInstallApi get() {
        return new DownloadAndInstallApi(this);
    }
}
