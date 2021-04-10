package com.facebook.quicklog.identifiers;

public class GltfScene {
    public static final int GET_EXTRA_ANDROID = 39649282;
    public static final int GLTF_PREFETCH = 39649283;
    public static final int GLTF_RENDER_ANDROID = 39649281;
    public static final short MODULE_ID = 605;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "GLTF_SCENE_GLTF_PREFETCH" : "GLTF_SCENE_GET_EXTRA_ANDROID" : "GLTF_SCENE_GLTF_RENDER_ANDROID";
    }
}
