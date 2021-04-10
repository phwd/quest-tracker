package com.facebook.quicklog.identifiers;

public class PerceptionCamera {
    public static final short MODULE_ID = 175;
    public static final int PARTICLE_EMITTER_PROCESS_FRAME = 11468802;
    public static final int PARTICLE_EMITTER_QUEUE_FRAME = 11468805;
    public static final int PARTICLE_EMITTER_UPDATE = 11468801;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "PERCEPTION_CAMERA_PARTICLE_EMITTER_QUEUE_FRAME" : "PERCEPTION_CAMERA_PARTICLE_EMITTER_PROCESS_FRAME" : "PERCEPTION_CAMERA_PARTICLE_EMITTER_UPDATE";
    }
}
