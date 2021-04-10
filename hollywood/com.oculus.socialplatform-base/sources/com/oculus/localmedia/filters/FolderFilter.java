package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class FolderFilter implements MediaItemFilter {
    public String mPath;

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem mediaItem) {
        return this.mPath.equalsIgnoreCase(mediaItem.mFolderPath);
    }

    public FolderFilter(String str) {
        this.mPath = str;
    }
}
