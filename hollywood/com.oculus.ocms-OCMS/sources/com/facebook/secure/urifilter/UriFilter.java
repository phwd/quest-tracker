package com.facebook.secure.urifilter;

import android.net.Uri;
import javax.annotation.Nullable;

public abstract class UriFilter {
    public boolean isEmpty() {
        return false;
    }

    public abstract boolean isValid(@Nullable Uri uri);

    public final UriFilter and(UriFilter uriFilter) {
        return new AndUriFilter(this, uriFilter);
    }

    public final UriFilter not() {
        return new NotUriFilter(this);
    }
}
