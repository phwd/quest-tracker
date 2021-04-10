package com.oculus.panelapp.people.views;

import com.oculus.common.sociallogger.SurfaceType;

public enum PeopleViewType {
    ALL_CONNECTIONS(SurfaceType.ALL_CONNECTIONS),
    ALL_NEARBY(SurfaceType.ALL_NEARBY),
    FRIENDS(SurfaceType.FRIENDS),
    REQUESTS(SurfaceType.REQUESTS),
    SEARCH(SurfaceType.SEARCH),
    SUGGESTIONS(SurfaceType.SUGGESTIONS),
    PEOPLE_NEARBY(SurfaceType.PEOPLE_NEARBY);
    
    public final SurfaceType mSurfaceType;

    public SurfaceType getLoggingSurfaceType() {
        return this.mSurfaceType;
    }

    /* access modifiers changed from: public */
    PeopleViewType(SurfaceType surfaceType) {
        this.mSurfaceType = surfaceType;
    }
}
