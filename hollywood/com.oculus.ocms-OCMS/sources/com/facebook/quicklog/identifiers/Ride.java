package com.facebook.quicklog.identifiers;

public class Ride {
    public static final short MODULE_ID = 11164;
    public static final int RIDE_ON_DEMAND_FEEDBACK_INTERACTION_FUNNEL = 731650369;

    public static String getMarkerName(int i) {
        return i != 6465 ? "UNDEFINED_QPL_EVENT" : "RIDE_RIDE_ON_DEMAND_FEEDBACK_INTERACTION_FUNNEL";
    }
}
