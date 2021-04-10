package com.facebook.voltron.common;

public @interface AppModuleDownloadResult {
    public static final int DISABLED = 6;
    public static final int EMPTY_REQUEST = 3;
    public static final int FAIL = 2;
    public static final int INCOMPLETE_METADATA = 5;
    public static final int LOGGED_OUT = 7;
    public static final int METERED_CONNECTION = 8;
    public static final int NO_METADATA = 4;
    public static final int SUCCESS = 1;
}
