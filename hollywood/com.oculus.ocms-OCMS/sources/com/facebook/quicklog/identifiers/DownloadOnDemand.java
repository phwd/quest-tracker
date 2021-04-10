package com.facebook.quicklog.identifiers;

public class DownloadOnDemand {
    public static final int GET_RESOURCE_FROM_NETWORK = 28447843;
    public static final short MODULE_ID = 434;
    public static final int PREFETCH_METADATA_DOWNLOADED = 28442626;
    public static final int RESOURCE_USED = 28442625;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 5219 ? "UNDEFINED_QPL_EVENT" : "DOWNLOAD_ON_DEMAND_GET_RESOURCE_FROM_NETWORK" : "DOWNLOAD_ON_DEMAND_PREFETCH_METADATA_DOWNLOADED" : "DOWNLOAD_ON_DEMAND_RESOURCE_USED";
    }
}
