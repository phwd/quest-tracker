package com.facebook.quicklog.identifiers;

public class FaceModelManager {
    public static final short MODULE_ID = 463;
    public static final int TTI_RN = 30343169;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FACE_MODEL_MANAGER_TTI_RN";
    }
}
