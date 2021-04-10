package com.facebook.quicklog.identifiers;

public class ReactNativeNativeModules {
    public static final int ASYNC_METHOD_CALL = 60882950;
    public static final int ASYNC_METHOD_CALL_BATCH_PREPROCESS = 60882951;
    public static final int ASYNC_METHOD_CALL_EXECUTION = 60882952;
    public static final int MODULE_CREATE = 60882945;
    public static final int MODULE_DATA_CREATE = 60882946;
    public static final short MODULE_ID = 929;
    public static final int MODULE_JS_REQUIRE_BEGINNING = 60882947;
    public static final int MODULE_JS_REQUIRE_ENDING = 60882948;
    public static final int SYNC_METHOD_CALL = 60882949;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "REACT_NATIVE_NATIVE_MODULES_MODULE_CREATE";
            case 2:
                return "REACT_NATIVE_NATIVE_MODULES_MODULE_DATA_CREATE";
            case 3:
                return "REACT_NATIVE_NATIVE_MODULES_MODULE_JS_REQUIRE_BEGINNING";
            case 4:
                return "REACT_NATIVE_NATIVE_MODULES_MODULE_JS_REQUIRE_ENDING";
            case 5:
                return "REACT_NATIVE_NATIVE_MODULES_SYNC_METHOD_CALL";
            case 6:
                return "REACT_NATIVE_NATIVE_MODULES_ASYNC_METHOD_CALL";
            case 7:
                return "REACT_NATIVE_NATIVE_MODULES_ASYNC_METHOD_CALL_BATCH_PREPROCESS";
            case 8:
                return "REACT_NATIVE_NATIVE_MODULES_ASYNC_METHOD_CALL_EXECUTION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
