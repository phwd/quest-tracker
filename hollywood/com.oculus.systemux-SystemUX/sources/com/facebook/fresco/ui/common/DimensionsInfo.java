package com.facebook.fresco.ui.common;

public class DimensionsInfo {
    private final int mDecodedImageHeight;
    private final int mDecodedImageWidth;
    private final int mEncodedImageHeight;
    private final int mEncodedImageWidth;
    private final String mScaleType;
    private final int mViewportHeight;
    private final int mViewportWidth;

    public DimensionsInfo(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        this.mViewportWidth = i;
        this.mViewportHeight = i2;
        this.mEncodedImageWidth = i3;
        this.mEncodedImageHeight = i4;
        this.mDecodedImageWidth = i5;
        this.mDecodedImageHeight = i6;
        this.mScaleType = str;
    }

    public int getViewportWidth() {
        return this.mViewportWidth;
    }

    public int getViewportHeight() {
        return this.mViewportHeight;
    }

    public int getEncodedImageWidth() {
        return this.mEncodedImageWidth;
    }

    public int getEncodedImageHeight() {
        return this.mEncodedImageHeight;
    }

    public int getDecodedImageWidth() {
        return this.mDecodedImageWidth;
    }

    public int getDecodedImageHeight() {
        return this.mDecodedImageHeight;
    }

    public String getScaleType() {
        return this.mScaleType;
    }

    public String toString() {
        return "DimensionsInfo{mViewportWidth=" + this.mViewportWidth + ", mViewportHeight=" + this.mViewportHeight + ", mEncodedImageWidth=" + this.mEncodedImageWidth + ", mEncodedImageHeight=" + this.mEncodedImageHeight + ", mDecodedImageWidth=" + this.mDecodedImageWidth + ", mDecodedImageHeight=" + this.mDecodedImageHeight + ", mScaleType='" + this.mScaleType + '\'' + '}';
    }
}
