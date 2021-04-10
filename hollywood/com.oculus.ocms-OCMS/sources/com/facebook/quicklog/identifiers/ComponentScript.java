package com.facebook.quicklog.identifiers;

public class ComponentScript {
    public static final int COMPONENT_SCRIPT_INITIALIZER = 11665413;
    public static final int CREATE_LAYOUT = 11665416;
    public static final int GET_MEMORY_METRICS = 11665419;
    public static final int MOBILE_LAB_TTI = 11665423;
    public static final short MODULE_ID = 178;
    public static final int NATIVE_COMPONENT_LAYOUT = 11665422;
    public static final int TTI = 11665420;

    public static String getMarkerName(int i) {
        return i != 5 ? i != 8 ? i != 11 ? i != 12 ? i != 14 ? i != 15 ? "UNDEFINED_QPL_EVENT" : "COMPONENT_SCRIPT_MOBILE_LAB_TTI" : "COMPONENT_SCRIPT_NATIVE_COMPONENT_LAYOUT" : "COMPONENT_SCRIPT_TTI" : "COMPONENT_SCRIPT_GET_MEMORY_METRICS" : "COMPONENT_SCRIPT_CREATE_LAYOUT" : "COMPONENT_SCRIPT_COMPONENT_SCRIPT_INITIALIZER";
    }
}
