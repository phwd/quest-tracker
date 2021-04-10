package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public interface MediaItemFilter {
    boolean match(MediaItem mediaItem);
}
