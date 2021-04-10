package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public abstract class AbstractMediaItemFilter implements MediaItemFilter {
    protected Comparable mFormat;
    protected MediaItemFilterOperand mOperator;

    public abstract Comparable getValue(MediaItem mediaItem);

    public AbstractMediaItemFilter(MediaItemFilterOperand operator, Comparable format) {
        this.mOperator = operator;
        this.mFormat = format;
    }

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem item) {
        return MediaItemFilterOperand.evaluate(this.mOperator, getValue(item), this.mFormat);
    }
}
