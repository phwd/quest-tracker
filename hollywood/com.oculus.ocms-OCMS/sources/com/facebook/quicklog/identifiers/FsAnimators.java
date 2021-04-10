package com.facebook.quicklog.identifiers;

public class FsAnimators {
    public static final int FS_KEYFRAME_ANIMATION = 14286849;
    public static final int FS_PARTICLE_ANIMATION = 14286850;
    public static final short MODULE_ID = 218;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FS_ANIMATORS_FS_PARTICLE_ANIMATION" : "FS_ANIMATORS_FS_KEYFRAME_ANIMATION";
    }
}
