package com.facebook.secure.urifilter;

import android.net.Uri;
import java.util.Collection;
import javax.annotation.Nullable;

class PathUriFilter extends UriFilter {
    private final Collection<String> mPaths;

    public PathUriFilter(Collection<String> collection) {
        if (!collection.isEmpty()) {
            this.mPaths = collection;
            return;
        }
        throw new IllegalArgumentException("You need to pass at least one path");
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isValid(@Nullable Uri uri) {
        return uri != null && this.mPaths.contains(uri.getPath());
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isEmpty() {
        return this.mPaths.isEmpty();
    }
}
