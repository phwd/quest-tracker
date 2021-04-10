package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class AlphaFilter extends AbstractMediaItemFilter {
    public AlphaFilter(MediaItemFilterOperand operator, String letter) {
        super(operator, letter);
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return item.getDisplayName() != null ? item.getDisplayName().toLowerCase() : " ";
    }
}
