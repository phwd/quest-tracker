package com.facebook.quicklog.identifiers;

public class NativeTemplateJs {
    public static final int EVAL_JS = 20316161;
    public static final int EVAL_MS = 20316163;
    public static final int INIT_JS_VM = 20316162;
    public static final short MODULE_ID = 310;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "NATIVE_TEMPLATE_JS_EVAL_MS" : "NATIVE_TEMPLATE_JS_INIT_JS_VM" : "NATIVE_TEMPLATE_JS_EVAL_JS";
    }
}
