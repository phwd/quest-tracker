package com.facebook.quicklog.identifiers;

public class ReactClass {
    public static final int CONSTRUCTOR = 7929858;
    public static final int CREATE = 7929857;
    public static final short MODULE_ID = 121;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "REACT_CLASS_CONSTRUCTOR" : "REACT_CLASS_CREATE";
    }
}
