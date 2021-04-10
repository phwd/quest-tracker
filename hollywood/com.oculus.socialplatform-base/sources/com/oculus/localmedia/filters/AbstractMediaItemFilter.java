package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public abstract class AbstractMediaItemFilter implements MediaItemFilter {
    public Comparable mFormat;
    public MediaItemFilterOperand mOperator;

    public abstract Comparable getValue(MediaItem mediaItem);

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem mediaItem) {
        return MediaItemFilterOperand.evaluate(this.mOperator, getValue(mediaItem), this.mFormat);
    }

    public AbstractMediaItemFilter(MediaItemFilterOperand mediaItemFilterOperand, Comparable comparable) {
        this.mOperator = mediaItemFilterOperand;
        this.mFormat = comparable;
    }
}
