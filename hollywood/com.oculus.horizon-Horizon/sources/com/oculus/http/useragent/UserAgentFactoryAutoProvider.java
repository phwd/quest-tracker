package com.oculus.http.useragent;

import X.AnonymousClass0J3;
import X.C003108z;
import X.C003809k;
import com.facebook.annotations.Generated;
import com.oculus.locale.LocaleModule;

@Generated({"By: InjectorProcessor"})
public class UserAgentFactoryAutoProvider extends AnonymousClass0J3<UserAgentFactory> {
    public final Object get() {
        return new UserAgentFactory(this, C003108z.A00(this), UserAgentModule.A00(this), C003809k.A02(this), LocaleModule.A01(this));
    }
}
