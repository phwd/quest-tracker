package com.facebook.secure.urifilter;

import android.net.Uri;
import java.util.Collection;
import javax.annotation.Nullable;

public class BasicUriFilter extends UriFilter {
    private final ListType mListType;
    private final Collection<String> mSchemes;

    public enum ListType {
        ALLOW,
        DENY
    }

    public BasicUriFilter(Collection<String> collection) {
        this(collection, ListType.ALLOW);
    }

    public BasicUriFilter(Collection<String> collection, ListType listType) {
        this.mListType = listType;
        this.mSchemes = collection;
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isValid(@Nullable Uri uri) {
        if (uri == null) {
            return false;
        }
        if (this.mListType == ListType.ALLOW) {
            return this.mSchemes.contains(uri.getScheme());
        }
        if (this.mListType == ListType.DENY) {
            return !this.mSchemes.contains(uri.getScheme());
        }
        return false;
    }

    @Override // com.facebook.secure.urifilter.UriFilter
    public boolean isEmpty() {
        return this.mSchemes.isEmpty();
    }
}
