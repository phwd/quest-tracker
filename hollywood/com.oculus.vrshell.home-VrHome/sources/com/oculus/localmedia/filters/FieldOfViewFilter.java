package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FieldOfViewFilter extends AbstractMediaItemFilter {
    public FieldOfViewFilter(MediaItemFilterOperand operator, String fovX) {
        super(operator, Float.valueOf(Float.parseFloat(fovX)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Float.valueOf(item.getFovX());
    }
}
