package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FolderFilter implements MediaItemFilter {
    private String mPath;

    public FolderFilter(String folderPath) {
        this.mPath = folderPath;
    }

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem item) {
        return this.mPath.equalsIgnoreCase(item.getFolderPath());
    }
}
