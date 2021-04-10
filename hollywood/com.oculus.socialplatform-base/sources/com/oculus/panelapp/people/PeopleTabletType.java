package com.oculus.panelapp.people;

import com.oculus.common.sociallogger.TabletType;

public enum PeopleTabletType {
    FACEBOOK(TabletType.FB_PEOPLE),
    OCULUS(TabletType.OC_PEOPLE);
    
    public final TabletType mTabletType;

    public TabletType getLoggingTabletType() {
        return this.mTabletType;
    }

    /* access modifiers changed from: public */
    PeopleTabletType(TabletType tabletType) {
        this.mTabletType = tabletType;
    }
}
