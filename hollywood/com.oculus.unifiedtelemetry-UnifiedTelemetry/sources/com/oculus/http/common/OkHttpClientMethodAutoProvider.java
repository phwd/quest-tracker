package com.oculus.http.common;

import X.AbstractC0097Hv;
import X.C00228l;
import X.LD;
import com.facebook.annotations.Generated;
import com.oculus.http.socketconfig.SocketConfigModule;

@Generated({"By: InjectorProcessor"})
public class OkHttpClientMethodAutoProvider extends AbstractC0097Hv<LD> {
    public final Object get() {
        SocketConfigModule.A01(this);
        return HttpModule.A08(HttpModule.A01(this), HttpModule.A05(this), C00228l.A00(this));
    }
}
