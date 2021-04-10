package com.facebook.secure.urifilter;

import android.net.Uri;
import javax.annotation.Nullable;

public abstract class UriFilter {
    public abstract boolean isValid(@Nullable Uri uri);
}
