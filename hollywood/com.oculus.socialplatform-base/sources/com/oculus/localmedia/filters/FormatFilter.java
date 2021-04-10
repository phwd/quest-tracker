package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FormatFilter extends AbstractMediaItemFilter {
    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return mediaItem.getFormat().toLowerCase();
    }

    public FormatFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, str);
    }
}
