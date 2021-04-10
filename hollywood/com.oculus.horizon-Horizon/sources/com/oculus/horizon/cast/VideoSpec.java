package com.oculus.horizon.cast;

public class VideoSpec {
    public final int mFps;
    public final int mHeight;
    public final int mWidth;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{height=");
        sb.append(this.mHeight);
        sb.append(", width=");
        sb.append(this.mWidth);
        sb.append(", fps=");
        sb.append(this.mFps);
        sb.append("}");
        return sb.toString();
    }

    public VideoSpec(int i, int i2, int i3) {
        this.mHeight = i;
        this.mWidth = i2;
        this.mFps = i3;
    }
}
