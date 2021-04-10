package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class ExcludeFolderFilter implements MediaItemFilter {
    private String mPath;

    public ExcludeFolderFilter(String folderPath) {
        this.mPath = folderPath;
    }

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem item) {
        return !item.getFolderPath().contains(this.mPath);
    }
}
