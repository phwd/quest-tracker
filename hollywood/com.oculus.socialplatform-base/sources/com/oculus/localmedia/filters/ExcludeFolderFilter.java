package com.oculus.localmedia.filters;

import com.oculus.localmedia.MediaItem;

public class ExcludeFolderFilter implements MediaItemFilter {
    public String mPath;

    @Override // com.oculus.localmedia.filters.MediaItemFilter
    public boolean match(MediaItem mediaItem) {
        return !mediaItem.mFolderPath.contains(this.mPath);
    }

    public ExcludeFolderFilter(String str) {
        this.mPath = str;
    }
}
