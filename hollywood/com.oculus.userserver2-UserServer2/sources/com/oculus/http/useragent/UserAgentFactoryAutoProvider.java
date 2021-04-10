package com.oculus.http.useragent;

import X.AbstractC0029Ba;
import X.BW;
import X.IX;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class UserAgentFactoryAutoProvider extends AbstractC0029Ba<UserAgentFactory> {
    public final Object get() {
        return new UserAgentFactory(this, (Context) IX.A00(1, this), (String) IX.A00(37, this), (PackageInfo) IX.A00(22, this), new BW(68, this));
    }
}
