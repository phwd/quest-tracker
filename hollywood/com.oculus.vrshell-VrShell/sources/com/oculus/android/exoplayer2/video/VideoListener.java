package com.oculus.android.exoplayer2.video;

public interface VideoListener {
    void onRenderedFirstFrame();

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
