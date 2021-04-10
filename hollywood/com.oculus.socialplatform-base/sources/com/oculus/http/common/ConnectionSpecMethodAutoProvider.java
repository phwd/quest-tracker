package com.oculus.http.common;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import okhttp3.ConnectionSpec;

@Generated({"By: InjectorProcessor"})
public class ConnectionSpecMethodAutoProvider extends AnonymousClass0VG<ConnectionSpec> {
    public ConnectionSpec get() {
        return HttpModule.provideConnetionSpec();
    }
}
