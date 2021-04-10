package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import javax.annotation.Nonnull;

public interface ImageDecoder {
    CloseableImage decode(@Nonnull EncodedImage encodedImage, int i, @Nonnull QualityInfo qualityInfo, @Nonnull ImageDecodeOptions imageDecodeOptions);
}
