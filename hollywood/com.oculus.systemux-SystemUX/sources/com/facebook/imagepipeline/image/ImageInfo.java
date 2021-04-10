package com.facebook.imagepipeline.image;

public interface ImageInfo extends HasImageMetadata {
    int getHeight();

    QualityInfo getQualityInfo();

    int getWidth();
}
