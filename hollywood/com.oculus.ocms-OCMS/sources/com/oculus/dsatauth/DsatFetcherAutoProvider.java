package com.oculus.dsatauth;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DsatFetcherAutoProvider extends AbstractProvider<DsatFetcher> {
    @Override // javax.inject.Provider
    public DsatFetcher get() {
        return new DsatFetcher(this);
    }
}
