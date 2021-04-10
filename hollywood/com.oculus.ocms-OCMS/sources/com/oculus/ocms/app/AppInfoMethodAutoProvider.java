package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.base.app.AppInfo;

@Generated({"By: InjectorProcessor"})
public class AppInfoMethodAutoProvider extends AbstractProvider<AppInfo> {
    @Override // javax.inject.Provider
    public AppInfo get() {
        return OCMSAppModule.provideAppInfo();
    }
}
