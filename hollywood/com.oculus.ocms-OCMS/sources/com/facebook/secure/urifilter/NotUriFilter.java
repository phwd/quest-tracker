package com.facebook.secure.urifilter;

import android.net.Uri;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class NotUriFilter extends UriFilter {
    private final UriFilter mDelegate;

    public NotUriFilter(UriFilter uriFilter) {
        this.mDelegate = uriFilter;
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isValid(@Nullable Uri uri) {
        return !this.mDelegate.isValid(uri);
    }
}
