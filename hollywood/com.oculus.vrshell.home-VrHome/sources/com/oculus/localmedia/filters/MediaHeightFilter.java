package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class MediaHeightFilter extends AbstractMediaItemFilter {
    public MediaHeightFilter(MediaItemFilterOperand operator, String height) {
        super(operator, Integer.valueOf(Integer.parseInt(height, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Integer.valueOf(item.getHeight());
    }
}
