package com.oculus.ocms.defaultapps;

import com.facebook.inject.AbstractComponentProvider;

public class DefaultAppsHighPriorityJobServiceAutoProvider extends AbstractComponentProvider<DefaultAppsHighPriorityJobService> {
    public void inject(DefaultAppsHighPriorityJobService defaultAppsHighPriorityJobService) {
        DefaultAppsHighPriorityJobService._UL_staticInjectMe(this, defaultAppsHighPriorityJobService);
    }

    public boolean equals(Object obj) {
        return obj instanceof DefaultAppsHighPriorityJobServiceAutoProvider;
    }
}
