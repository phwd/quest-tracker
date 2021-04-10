package com.facebook.quicklog.identifiers;

public class Bloks {
    public static final int ASYNC_ACTION = 36707139;
    public static final int BIND_RESOLVE = 36710724;
    public static final int EVALUATE = 36714637;
    public static final int LAYOUT = 36700163;
    public static final int LISPY = 36728441;
    public static final int LISPY_PARSE = 36712530;
    public static final short MODULE_ID = 560;
    public static final int MOUNT = 36700164;
    public static final int MUTATE = 36700165;
    public static final int NETWORK = 36710139;
    public static final int PARSE = 36700161;
    public static final int SCROLL_PERF = 36712094;
    public static final int TTI = 36700162;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6979 ? i != 9979 ? i != 10564 ? i != 11934 ? i != 12370 ? i != 14477 ? i != 28281 ? "UNDEFINED_QPL_EVENT" : "BLOKS_LISPY" : "BLOKS_EVALUATE" : "BLOKS_LISPY_PARSE" : "BLOKS_SCROLL_PERF" : "BLOKS_BIND_RESOLVE" : "BLOKS_NETWORK" : "BLOKS_ASYNC_ACTION" : "BLOKS_MUTATE" : "BLOKS_MOUNT" : "BLOKS_LAYOUT" : "BLOKS_TTI" : "BLOKS_PARSE";
    }
}
