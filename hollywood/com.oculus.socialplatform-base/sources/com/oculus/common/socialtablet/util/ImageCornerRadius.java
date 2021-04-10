package com.oculus.common.socialtablet.util;

public class ImageCornerRadius {
    public final int mBottomLeft;
    public final int mBottomRight;
    public final int mTopLeft;
    public final int mTopRight;

    public int getBottomLeft() {
        return this.mBottomLeft;
    }

    public int getBottomRight() {
        return this.mBottomRight;
    }

    public int getTopLeft() {
        return this.mTopLeft;
    }

    public int getTopRight() {
        return this.mTopRight;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mTopLeft);
        sb.append(", ");
        sb.append(this.mTopRight);
        sb.append(", ");
        sb.append(this.mBottomRight);
        sb.append(", ");
        sb.append(this.mBottomLeft);
        return sb.toString();
    }

    public ImageCornerRadius(int i, int i2, int i3, int i4) {
        this.mTopLeft = i;
        this.mTopRight = i2;
        this.mBottomRight = i3;
        this.mBottomLeft = i4;
    }
}
