package com.oculus.common.sociallogger;

public enum TabletType {
    PARTIES("PARTIES_TABLET"),
    FB_PEOPLE("FB_PEOPLE_TABLET"),
    OC_PEOPLE("OC_PEOPLE_TABLET"),
    FB_MESSENGER("FB_MESSENGER_TABLET"),
    OC_MESSENGER("OC_MESSENGER_TABLET"),
    REAUTH("REAUTH_TABLET"),
    SETTINGS("SETTINGS_TABLET"),
    DIALOG("DIALOG"),
    OVERLAY_PANEL("OVERLAY_PANEL");
    
    private final String mTabletType;

    private TabletType(String str) {
        this.mTabletType = str;
    }

    public String getTelemetryTabletType() {
        return this.mTabletType;
    }
}
