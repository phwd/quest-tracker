package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FileSizeFilter extends AbstractMediaItemFilter {
    public FileSizeFilter(MediaItemFilterOperand operator, String size) {
        super(operator, Long.valueOf(Long.parseLong(size, 10)));
    }

    @Override // com.oculus.localmedia.filters.AbstractMediaItemFilter
    public Comparable getValue(MediaItem item) {
        return Long.valueOf(item.getFileSize());
    }
}
