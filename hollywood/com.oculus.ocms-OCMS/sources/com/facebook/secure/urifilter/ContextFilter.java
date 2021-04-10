package com.facebook.secure.urifilter;

import android.net.Uri;
import javax.annotation.Nullable;

public abstract class ContextFilter extends UriFilter {
    /* access modifiers changed from: protected */
    public abstract boolean isEnabled();

    @Override // com.facebook.secure.urifilter.UriFilter
    public final boolean isValid(@Nullable Uri uri) {
        return isEnabled();
    }
}
