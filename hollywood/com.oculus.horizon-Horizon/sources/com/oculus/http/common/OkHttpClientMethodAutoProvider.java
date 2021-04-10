package com.oculus.http.common;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.C003309b;
import com.facebook.annotations.Generated;
import com.oculus.http.socketconfig.SocketConfigModule;

@Generated({"By: InjectorProcessor"})
public class OkHttpClientMethodAutoProvider extends AnonymousClass0J3<AnonymousClass0N1> {
    public final Object get() {
        SocketConfigModule.A01(this);
        return HttpModule.A07(HttpModule.A00(this), HttpModule.A04(this), C003309b.A00(this));
    }
}
