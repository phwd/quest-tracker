package com.oculus.http.common;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import okhttp3.ConnectionSpec;

@Generated({"By: InjectorProcessor"})
public class ConnectionSpecMethodAutoProvider extends AbstractProvider<ConnectionSpec> {
    @Override // javax.inject.Provider
    public ConnectionSpec get() {
        return HttpModule.provideConnetionSpec();
    }
}
