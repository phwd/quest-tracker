package com.oculus.dsatauth;

import com.facebook.inject.AbstractProvider;

public class DsatFetcherAutoProvider extends AbstractProvider<DsatFetcher> {
    @Override // javax.inject.Provider
    public DsatFetcher get() {
        return new DsatFetcher(this);
    }
}
