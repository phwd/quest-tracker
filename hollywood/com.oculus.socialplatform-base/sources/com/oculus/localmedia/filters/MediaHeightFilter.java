package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class MediaHeightFilter extends AbstractMediaItemFilter {
    public MediaHeightFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, Integer.valueOf(Integer.parseInt(str, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return Integer.valueOf(mediaItem.getHeight());
    }
}
