package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class MediaWidthFilter extends AbstractMediaItemFilter {
    public MediaWidthFilter(MediaItemFilterOperand operator, String width) {
        super(operator, Integer.valueOf(Integer.parseInt(width, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Integer.valueOf(item.getWidth());
    }
}
