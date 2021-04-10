package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class VisibilityFilter extends AbstractMediaItemFilter {
    public boolean includePrivate() {
        if (this.mOperator == MediaItemFilterOperand.EQUALS && this.mFormat.compareTo("private") == 0) {
            return true;
        }
        if (this.mOperator == MediaItemFilterOperand.DIFFERENT && this.mFormat.compareTo("public") == 0) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        return mediaItem.getVisibility().toLowerCase();
    }

    public VisibilityFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, str);
    }
}
