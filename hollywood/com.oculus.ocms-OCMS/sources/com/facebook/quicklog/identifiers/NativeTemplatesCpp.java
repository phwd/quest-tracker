package com.facebook.quicklog.identifiers;

public class NativeTemplatesCpp {
    public static final int BENCHMARK_1 = 31850497;
    public static final int CPP_TTI = 31850498;
    public static final short MODULE_ID = 486;
    public static final int RENDER_CPP = 31850501;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "NATIVE_TEMPLATES_CPP_RENDER_CPP" : "NATIVE_TEMPLATES_CPP_CPP_TTI" : "NATIVE_TEMPLATES_CPP_BENCHMARK_1";
    }
}
