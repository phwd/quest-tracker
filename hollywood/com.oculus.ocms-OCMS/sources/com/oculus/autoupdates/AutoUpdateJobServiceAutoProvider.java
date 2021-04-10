package com.oculus.autoupdates;

import com.facebook.inject.AbstractComponentProvider;

public class AutoUpdateJobServiceAutoProvider extends AbstractComponentProvider<AutoUpdateJobService> {
    public void inject(AutoUpdateJobService autoUpdateJobService) {
        AutoUpdateJobService._UL_staticInjectMe(this, autoUpdateJobService);
    }

    public boolean equals(Object obj) {
        return obj instanceof AutoUpdateJobServiceAutoProvider;
    }
}
