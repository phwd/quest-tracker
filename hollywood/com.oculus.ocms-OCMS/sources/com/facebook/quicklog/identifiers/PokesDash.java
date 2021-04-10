package com.facebook.quicklog.identifiers;

public class PokesDash {
    public static final short MODULE_ID = 115;
    public static final int POKES_REACT_NATIVE_TTI = 7536641;
    public static final int POKES_TTRC = 7551657;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 15017 ? "UNDEFINED_QPL_EVENT" : "POKES_DASH_POKES_TTRC" : "POKES_DASH_POKES_REACT_NATIVE_TTI";
    }
}
