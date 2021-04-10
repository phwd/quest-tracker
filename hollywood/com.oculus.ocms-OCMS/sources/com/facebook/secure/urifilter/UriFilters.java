package com.facebook.secure.urifilter;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;

public class UriFilters {
    private static final UriFilter EMPTY_FILTER = new AndUriFilter(new UriFilter[0]);

    private UriFilters() {
    }

    public static UriFilter anyUri() {
        return EMPTY_FILTER;
    }

    public static BasicBuilder basic() {
        return new BasicBuilder();
    }

    public static class BasicBuilder {
        private boolean mBlacklist = false;
        private final List<UriFilter> mParts = new ArrayList(4);

        public BasicBuilder scheme(String... strArr) {
            return scheme(Arrays.asList(strArr));
        }

        public BasicBuilder scheme(Collection<String> collection) {
            if (!collection.isEmpty()) {
                this.mParts.add(new BasicUriFilter(collection));
                return this;
            }
            throw new IllegalArgumentException("Cannot set 0 schemes");
        }

        public BasicBuilder noHost() {
            this.mParts.add(new NoHostFilter());
            return this;
        }

        public BasicBuilder host(String... strArr) {
            if (strArr.length != 0) {
                this.mParts.add(new HostFilter(strArr, false));
                return this;
            }
            throw new IllegalArgumentException("Cannot set 0 hosts");
        }

        public BasicBuilder domains(String... strArr) {
            if (strArr.length != 0) {
                this.mParts.add(new HostFilter(strArr, true));
                return this;
            }
            throw new IllegalArgumentException("Cannot set 0 domains");
        }

        public BasicBuilder path(String... strArr) {
            this.mParts.add(new PathUriFilter(Arrays.asList(strArr)));
            return this;
        }

        public BasicBuilder parameter(String str, String str2) {
            this.mParts.add(new StringParameterUriFilter(str, str2));
            return this;
        }

        public BasicBuilder parameter(String str, UriFilter uriFilter) {
            this.mParts.add(new UriParameterUriFilter(str, uriFilter));
            return this;
        }

        public BasicBuilder and(UriFilter uriFilter) {
            this.mParts.add(uriFilter);
            return this;
        }

        public BasicBuilder blacklist() {
            this.mBlacklist = true;
            return this;
        }

        public UriFilter build() {
            UriFilter uriFilter;
            int size = this.mParts.size();
            if (size == 0) {
                uriFilter = UriFilters.EMPTY_FILTER;
            } else if (size != 1) {
                uriFilter = new AndUriFilter((UriFilter[]) this.mParts.toArray(new UriFilter[size]));
            } else {
                uriFilter = this.mParts.get(0);
            }
            return this.mBlacklist ? uriFilter.not() : uriFilter;
        }
    }

    private static class NoHostFilter extends UriFilter {
        public static final NoHostFilter INSTANCE = new NoHostFilter();

        private NoHostFilter() {
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isValid(@Nullable Uri uri) {
            return uri != null && TextUtils.isEmpty(uri.getHost());
        }
    }

    private static class HostFilter extends UriFilter {
        private final boolean mAcceptSubdomains;
        private final String[] mHosts;

        public HostFilter(String[] strArr, boolean z) {
            this.mHosts = strArr;
            this.mAcceptSubdomains = z;
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isValid(@Nullable Uri uri) {
            String host;
            if (uri == null || (host = uri.getHost()) == null) {
                return false;
            }
            String[] strArr = this.mHosts;
            for (String str : strArr) {
                if (host.equals(str)) {
                    return true;
                }
                if (this.mAcceptSubdomains) {
                    if (host.endsWith("." + str)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isEmpty() {
            return this.mHosts.length == 0;
        }
    }

    private static class StringParameterUriFilter extends UriFilter {
        private final String mName;
        private final String mValue;

        public StringParameterUriFilter(String str, String str2) {
            this.mName = str;
            this.mValue = str2;
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isValid(@Nullable Uri uri) {
            return uri != null && !uri.isOpaque() && this.mValue.equals(uri.getQueryParameter(this.mName));
        }
    }

    private static class UriParameterUriFilter extends UriFilter {
        private final UriFilter mFilter;
        private final String mName;

        public UriParameterUriFilter(String str, UriFilter uriFilter) {
            this.mName = str;
            this.mFilter = uriFilter;
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isValid(@Nullable Uri uri) {
            if (uri == null || uri.isOpaque()) {
                return false;
            }
            String queryParameter = uri.getQueryParameter(this.mName);
            if (TextUtils.isEmpty(queryParameter)) {
                return false;
            }
            return this.mFilter.isValid(Uri.parse(queryParameter));
        }

        @Override // com.facebook.secure.urifilter.UriFilter
        public boolean isEmpty() {
            return this.mFilter.isEmpty();
        }
    }
}
