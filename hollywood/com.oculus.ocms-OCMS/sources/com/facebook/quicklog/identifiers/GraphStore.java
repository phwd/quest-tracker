package com.facebook.quicklog.identifiers;

public class GraphStore {
    public static final int CACHE_GET = 6619138;
    public static final int CACHE_PUT = 6619137;
    public static final short MODULE_ID = 101;
    public static final int PERF_IS_DEEP_EQUAL = 6619141;
    public static final int TREE_IS_DEEP_EQUAL = 6619142;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "GRAPH_STORE_TREE_IS_DEEP_EQUAL" : "GRAPH_STORE_PERF_IS_DEEP_EQUAL" : "GRAPH_STORE_CACHE_GET" : "GRAPH_STORE_CACHE_PUT";
    }
}
