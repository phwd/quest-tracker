package com.oculus.ocms.locationservices;

public @interface FetchLocationType {
    public static final int IP_AND_TIMEZONE = 2;
    public static final int IP_NO_TIMEZONE = 1;
    public static final int NO_IP_NO_TIMEZONE = 0;
    public static final int PRECISE = 3;
}
