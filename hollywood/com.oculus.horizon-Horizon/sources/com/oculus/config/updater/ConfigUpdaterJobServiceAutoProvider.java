package com.oculus.config.updater;

import X.AnonymousClass0J9;

public class ConfigUpdaterJobServiceAutoProvider extends AnonymousClass0J9<ConfigUpdaterJobService> {
    public boolean equals(Object obj) {
        return obj instanceof ConfigUpdaterJobServiceAutoProvider;
    }

    public void inject(ConfigUpdaterJobService configUpdaterJobService) {
        ConfigUpdaterJobService._UL_staticInjectMe(this, configUpdaterJobService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        ConfigUpdaterJobService._UL_staticInjectMe(this, (ConfigUpdaterJobService) obj);
    }
}
