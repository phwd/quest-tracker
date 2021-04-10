package com.facebook.imagepipeline.drawable;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.image.CloseableImage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface DrawableFactory {
    @Nullable
    Drawable createDrawable(@Nonnull CloseableImage closeableImage);

    boolean supportsImageType(@Nonnull CloseableImage closeableImage);
}
