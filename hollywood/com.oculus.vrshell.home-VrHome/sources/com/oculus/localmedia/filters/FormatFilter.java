package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FormatFilter extends AbstractMediaItemFilter {
    public FormatFilter(MediaItemFilterOperand operator, String format) {
        super(operator, format);
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return item.getFormat().toLowerCase();
    }
}
