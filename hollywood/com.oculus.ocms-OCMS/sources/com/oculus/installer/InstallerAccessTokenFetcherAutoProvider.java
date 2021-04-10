package com.oculus.installer;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerAccessTokenFetcherAutoProvider extends AbstractProvider<InstallerAccessTokenFetcher> {
    @Override // javax.inject.Provider
    public InstallerAccessTokenFetcher get() {
        return new InstallerAccessTokenFetcher(this);
    }
}
