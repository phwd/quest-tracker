package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FieldOfViewFilter extends AbstractMediaItemFilter {
    public FieldOfViewFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, Float.valueOf(Float.parseFloat(str)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return Float.valueOf(mediaItem.getFovX());
    }
}
