package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class ProjectionTypeFilter extends AbstractMediaItemFilter {
    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return mediaItem.getPresentationFormat().toLowerCase();
    }

    public ProjectionTypeFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, str);
    }
}
