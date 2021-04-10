package com.oculus.appmanager.installer.analytics;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerAnalyticsAutoProvider extends AbstractProvider<InstallerAnalytics> {
    @Override // javax.inject.Provider
    public InstallerAnalytics get() {
        return new InstallerAnalytics(this);
    }
}
