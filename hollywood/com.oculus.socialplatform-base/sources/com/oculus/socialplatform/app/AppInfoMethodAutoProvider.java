package com.oculus.socialplatform.app;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.base.app.AppInfo;

@Generated({"By: InjectorProcessor"})
public class AppInfoMethodAutoProvider extends AnonymousClass0VG<AppInfo> {
    public AppInfo get() {
        return SocialPlatformModule.provideAppInfo();
    }
}
