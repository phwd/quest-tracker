package com.facebook.secure.urifilter;

import android.net.Uri;
import javax.annotation.Nullable;

final class AndUriFilter extends UriFilter {
    final UriFilter[] mParts;

    public AndUriFilter(UriFilter... uriFilterArr) {
        this.mParts = uriFilterArr;
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isValid(@Nullable Uri uri) {
        if (uri == null) {
            return false;
        }
        for (UriFilter uriFilter : this.mParts) {
            if (!uriFilter.isValid(uri)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isEmpty() {
        return this.mParts.length == 0;
    }
}
