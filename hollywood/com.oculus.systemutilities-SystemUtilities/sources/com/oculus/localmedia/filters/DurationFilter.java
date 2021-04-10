package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class DurationFilter extends AbstractMediaItemFilter {
    public DurationFilter(MediaItemFilterOperand operator, String duration) {
        super(operator, Integer.valueOf(Integer.parseInt(duration, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Integer.valueOf(item.getDurationSecs());
    }
}
