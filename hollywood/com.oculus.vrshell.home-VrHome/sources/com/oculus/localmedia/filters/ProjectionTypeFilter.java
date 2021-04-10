package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class ProjectionTypeFilter extends AbstractMediaItemFilter {
    public ProjectionTypeFilter(MediaItemFilterOperand operator, String format) {
        super(operator, format);
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return item.getPresentationFormat().toLowerCase();
    }
}
