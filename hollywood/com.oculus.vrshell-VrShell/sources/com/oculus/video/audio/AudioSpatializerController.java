package com.oculus.video.audio;

public interface AudioSpatializerController {
    boolean getFocusEnabled();

    float getFocusWidthDegrees();

    float[] getHeadOrientationMatrix();

    float getOffFocusLeveldB();

    void onBufferUnderrun(int i);
}
