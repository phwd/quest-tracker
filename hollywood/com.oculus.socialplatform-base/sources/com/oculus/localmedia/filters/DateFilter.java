package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class DateFilter extends AbstractMediaItemFilter {
    public DateFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, Long.valueOf(Long.parseLong(str, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return Long.valueOf(mediaItem.mDateAdded);
    }
}
