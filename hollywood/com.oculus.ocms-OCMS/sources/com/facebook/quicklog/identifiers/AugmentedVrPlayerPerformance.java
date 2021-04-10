package com.facebook.quicklog.identifiers;

public class AugmentedVrPlayerPerformance {
    public static final short MODULE_ID = 664;
    public static final int VR_APP_FRAME = 43515905;
    public static final int VR_AR_ENGINE_RENDERING = 43515907;
    public static final int VR_BACKGROUND_RENDERING = 43515906;
    public static final int VR_UI_RENDERING = 43515908;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "AUGMENTED_VR_PLAYER_PERFORMANCE_VR_UI_RENDERING" : "AUGMENTED_VR_PLAYER_PERFORMANCE_VR_AR_ENGINE_RENDERING" : "AUGMENTED_VR_PLAYER_PERFORMANCE_VR_BACKGROUND_RENDERING" : "AUGMENTED_VR_PLAYER_PERFORMANCE_VR_APP_FRAME";
    }
}
