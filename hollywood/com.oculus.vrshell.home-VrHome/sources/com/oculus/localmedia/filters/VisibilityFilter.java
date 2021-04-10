package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class VisibilityFilter extends AbstractMediaItemFilter {
    public VisibilityFilter(MediaItemFilterOperand operator, String format) {
        super(operator, format);
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return item.getVisibility().toLowerCase();
    }

    public boolean includePrivate() {
        return (this.mOperator == MediaItemFilterOperand.EQUALS && this.mFormat.compareTo("private") == 0) || (this.mOperator == MediaItemFilterOperand.DIFFERENT && this.mFormat.compareTo("public") == 0);
    }
}
