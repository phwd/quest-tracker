package com.squareup.okhttp;

import X.AnonymousClass006;
import com.oculus.tablet.utils.ImageLoader;
import com.squareup.okhttp.internal.http.HeaderParser;
import java.util.concurrent.TimeUnit;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE;
    public static final CacheControl FORCE_NETWORK;
    public String headerValue;
    public final boolean isPrivate;
    public final boolean isPublic;
    public final int maxAgeSeconds;
    public final int maxStaleSeconds;
    public final int minFreshSeconds;
    public final boolean mustRevalidate;
    public final boolean noCache;
    public final boolean noStore;
    public final boolean noTransform;
    public final boolean onlyIfCached;
    public final int sMaxAgeSeconds;

    public static final class Builder {
        public int maxAgeSeconds = -1;
        public int maxStaleSeconds = -1;
        public int minFreshSeconds = -1;
        public boolean noCache;
        public boolean noStore;
        public boolean noTransform;
        public boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            int i2;
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                if (seconds > 2147483647L) {
                    i2 = Integer.MAX_VALUE;
                } else {
                    i2 = (int) seconds;
                }
                this.maxAgeSeconds = i2;
                return this;
            }
            throw new IllegalArgumentException(AnonymousClass006.A03("maxAge < 0: ", i));
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            int i2;
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                if (seconds > 2147483647L) {
                    i2 = Integer.MAX_VALUE;
                } else {
                    i2 = (int) seconds;
                }
                this.maxStaleSeconds = i2;
                return this;
            }
            throw new IllegalArgumentException(AnonymousClass006.A03("maxStale < 0: ", i));
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            int i2;
            if (i >= 0) {
                long seconds = timeUnit.toSeconds((long) i);
                if (seconds > 2147483647L) {
                    i2 = Integer.MAX_VALUE;
                } else {
                    i2 = (int) seconds;
                }
                this.minFreshSeconds = i2;
                return this;
            }
            throw new IllegalArgumentException(AnonymousClass006.A03("minFresh < 0: ", i));
        }
    }

    static {
        Builder builder = new Builder();
        builder.noCache = true;
        FORCE_NETWORK = new CacheControl(builder);
        Builder builder2 = new Builder();
        builder2.onlyIfCached = true;
        builder2.maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS);
        FORCE_CACHE = new CacheControl(builder2);
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        int i = this.maxAgeSeconds;
        if (i != -1) {
            sb.append("max-age=");
            sb.append(i);
            sb.append(", ");
        }
        int i2 = this.sMaxAgeSeconds;
        if (i2 != -1) {
            sb.append("s-maxage=");
            sb.append(i2);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        int i3 = this.maxStaleSeconds;
        if (i3 != -1) {
            sb.append("max-stale=");
            sb.append(i3);
            sb.append(", ");
        }
        int i4 = this.minFreshSeconds;
        if (i4 != -1) {
            sb.append("min-fresh=");
            sb.append(i4);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public static CacheControl parse(Headers headers) {
        String str;
        int length = headers.namesAndValues.length >> 1;
        boolean z = true;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = -1;
        int i2 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z7 = false;
        boolean z8 = false;
        for (int i5 = 0; i5 < length; i5++) {
            String name = headers.name(i5);
            String value = headers.value(i5);
            if (name.equalsIgnoreCase(ImageLoader.CACHE_CONTROL_HEADER_NAME)) {
                if (str2 == null) {
                    str2 = value;
                }
                z = false;
            } else {
                if (!name.equalsIgnoreCase("Pragma")) {
                }
                z = false;
            }
            int i6 = 0;
            while (i6 < value.length()) {
                int skipUntil = HeaderParser.skipUntil(value, i6, "=,;");
                String trim = value.substring(i6, skipUntil).trim();
                if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                    i6 = skipUntil + 1;
                    str = null;
                } else {
                    int skipWhitespace = HeaderParser.skipWhitespace(value, skipUntil + 1);
                    if (skipWhitespace >= value.length() || value.charAt(skipWhitespace) != '\"') {
                        i6 = HeaderParser.skipUntil(value, skipWhitespace, ",;");
                        str = value.substring(skipWhitespace, i6).trim();
                    } else {
                        int i7 = skipWhitespace + 1;
                        int skipUntil2 = HeaderParser.skipUntil(value, i7, "\"");
                        str = value.substring(i7, skipUntil2);
                        i6 = skipUntil2 + 1;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i = HeaderParser.parseSeconds(str, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i2 = HeaderParser.parseSeconds(str, -1);
                } else if ("private".equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i3 = HeaderParser.parseSeconds(str, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i4 = HeaderParser.parseSeconds(str, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z8 = true;
                }
            }
        }
        if (!z) {
            str2 = null;
        }
        return new CacheControl(z2, z3, i, i2, z4, z5, z6, i3, i4, z7, z8, str2);
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue2 = headerValue();
        this.headerValue = headerValue2;
        return headerValue2;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
    }

    public CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.headerValue = str;
    }
}
