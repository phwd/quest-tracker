package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class DateFilter extends AbstractMediaItemFilter {
    public DateFilter(MediaItemFilterOperand operator, String duration) {
        super(operator, Long.valueOf(Long.parseLong(duration, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Long.valueOf(item.getDateAdded());
    }
}
