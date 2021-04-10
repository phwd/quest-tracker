package com.facebook.imagepipeline.image;

import java.util.Map;
import javax.annotation.Nonnull;

public interface HasImageMetadata {
    @Nonnull
    Map<String, Object> getExtras();
}
