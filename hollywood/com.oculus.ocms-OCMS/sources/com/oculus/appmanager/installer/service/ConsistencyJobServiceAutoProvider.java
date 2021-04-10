package com.oculus.appmanager.installer.service;

import com.facebook.inject.AbstractComponentProvider;

public class ConsistencyJobServiceAutoProvider extends AbstractComponentProvider<ConsistencyJobService> {
    public void inject(ConsistencyJobService consistencyJobService) {
        ConsistencyJobService._UL_staticInjectMe(this, consistencyJobService);
    }

    public boolean equals(Object obj) {
        return obj instanceof ConsistencyJobServiceAutoProvider;
    }
}
