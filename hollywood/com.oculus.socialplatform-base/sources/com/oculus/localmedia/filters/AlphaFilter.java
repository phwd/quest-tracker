package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;
import org.apache.commons.cli.HelpFormatter;

public class AlphaFilter extends AbstractMediaItemFilter {
    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem mediaItem) {
        String displayName = mediaItem.getDisplayName();
        if (displayName != null) {
            return displayName.toLowerCase();
        }
        return HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR;
    }

    public AlphaFilter(MediaItemFilterOperand mediaItemFilterOperand, String str) {
        super(mediaItemFilterOperand, str);
    }
}
