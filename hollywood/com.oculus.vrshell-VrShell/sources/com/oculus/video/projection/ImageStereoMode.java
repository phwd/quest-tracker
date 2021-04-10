package com.oculus.video.projection;

public enum ImageStereoMode {
    SM_2D,
    SM_LEFT_RIGHT_3D,
    SM_RIGHT_LEFT_3D,
    SM_TOP_BOTTOM_3D,
    SM_BOTTOM_TOP_3D,
    UNKNOWN;

    public static ImageStereoMode fromExoPlayer(int i) {
        if (i == 0) {
            return SM_2D;
        }
        if (i == 1) {
            return SM_TOP_BOTTOM_3D;
        }
        if (i == 2 || i == 3) {
            return SM_LEFT_RIGHT_3D;
        }
        return UNKNOWN;
    }
}
