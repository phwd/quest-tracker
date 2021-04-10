package com.facebook.quicklog.identifiers;

public class FleetBeacon {
    public static final int FLEET_BEACON_INSTANCE_CREATE = 52625409;
    public static final short MODULE_ID = 803;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FLEET_BEACON_FLEET_BEACON_INSTANCE_CREATE";
    }
}
