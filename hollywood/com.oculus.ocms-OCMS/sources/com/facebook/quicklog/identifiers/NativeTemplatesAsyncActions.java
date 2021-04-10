package com.facebook.quicklog.identifiers;

public class NativeTemplatesAsyncActions {
    public static final int ASYNC_ACTION = 62717953;
    public static final int ASYNC_ACTION_ROOMS = 62717954;
    public static final short MODULE_ID = 957;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "NATIVE_TEMPLATES_ASYNC_ACTIONS_ASYNC_ACTION_ROOMS" : "NATIVE_TEMPLATES_ASYNC_ACTIONS_ASYNC_ACTION";
    }
}
