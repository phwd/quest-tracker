package com.facebook.quicklog.identifiers;

public class AndroidEfficiencyModule {
    public static final int ANDROID_CACHE_EFFICIENCY = 46411206;
    public static final int ANDROID_FETCH_EFFICIENCY_EVENT = 46399489;
    public static final int ANDROID_IMAGE_EFFICIENCY_EVENT = 46399491;
    public static final int ANDROID_REFETCH_EFFICIENCY_EVENT = 46399490;
    public static final short MODULE_ID = 708;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 11718 ? "UNDEFINED_QPL_EVENT" : "ANDROID_EFFICIENCY_MODULE_ANDROID_CACHE_EFFICIENCY" : "ANDROID_EFFICIENCY_MODULE_ANDROID_IMAGE_EFFICIENCY_EVENT" : "ANDROID_EFFICIENCY_MODULE_ANDROID_REFETCH_EFFICIENCY_EVENT" : "ANDROID_EFFICIENCY_MODULE_ANDROID_FETCH_EFFICIENCY_EVENT";
    }
}
