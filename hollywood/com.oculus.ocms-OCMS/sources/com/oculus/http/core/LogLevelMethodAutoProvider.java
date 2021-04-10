package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class LogLevelMethodAutoProvider extends AbstractProvider<RestAdapter.LogLevel> {
    @Override // javax.inject.Provider
    public RestAdapter.LogLevel get() {
        return ApiModule.provideLogLevel();
    }
}
