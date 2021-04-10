package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class CacheStrategy {
    public final Response cacheResponse;
    public final Request networkRequest;

    public static class Factory {
        public int ageSeconds = -1;
        public final Response cacheResponse;
        public String etag;
        public Date expires;
        public Date lastModified;
        public String lastModifiedString;
        public final long nowMillis;
        public long receivedResponseMillis;
        public final Request request;
        public long sentRequestMillis;
        public Date servedDate;
        public String servedDateString;

        private long cacheResponseAge() {
            Date date = this.servedDate;
            long j = 0;
            if (date != null) {
                j = Math.max(0L, this.receivedResponseMillis - date.getTime());
            }
            int i = this.ageSeconds;
            if (i != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) i));
            }
            long j2 = this.receivedResponseMillis;
            return j + (j2 - this.sentRequestMillis) + (this.nowMillis - j2);
        }

        private long computeFreshnessLifetime() {
            long j;
            long j2;
            int i = this.cacheResponse.cacheControl().maxAgeSeconds;
            if (i != -1) {
                return TimeUnit.SECONDS.toMillis((long) i);
            }
            if (this.expires != null) {
                Date date = this.servedDate;
                if (date != null) {
                    j2 = date.getTime();
                } else {
                    j2 = this.receivedResponseMillis;
                }
                long time = this.expires.getTime() - j2;
                if (time > 0) {
                    return time;
                }
            } else if (this.lastModified != null && this.cacheResponse.request.url.query() == null) {
                Date date2 = this.servedDate;
                if (date2 != null) {
                    j = date2.getTime();
                } else {
                    j = this.sentRequestMillis;
                }
                long time2 = j - this.lastModified.getTime();
                if (time2 > 0) {
                    return time2 / 10;
                }
            }
            return 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x00ca  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00e2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.squareup.okhttp.internal.http.CacheStrategy getCandidate() {
            /*
            // Method dump skipped, instructions count: 232
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.CacheStrategy.Factory.getCandidate():com.squareup.okhttp.internal.http.CacheStrategy");
        }

        public static boolean hasConditions(Request request2) {
            if (request2.header("If-Modified-Since") == null && request2.header("If-None-Match") == null) {
                return false;
            }
            return true;
        }

        private boolean isFreshnessLifetimeHeuristic() {
            if (this.cacheResponse.cacheControl().maxAgeSeconds == -1 && this.expires == null) {
                return true;
            }
            return false;
        }

        public Factory(long j, Request request2, Response response) {
            this.nowMillis = j;
            this.request = request2;
            this.cacheResponse = response;
            if (response != null) {
                Headers headers = response.headers;
                int length = headers.namesAndValues.length >> 1;
                for (int i = 0; i < length; i++) {
                    String name = headers.name(i);
                    String value = headers.value(i);
                    if ("Date".equalsIgnoreCase(name)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    } else if ("Expires".equalsIgnoreCase(name)) {
                        this.expires = HttpDate.parse(value);
                    } else if ("Last-Modified".equalsIgnoreCase(name)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    } else if ("ETag".equalsIgnoreCase(name)) {
                        this.etag = value;
                    } else if ("Age".equalsIgnoreCase(name)) {
                        this.ageSeconds = HeaderParser.parseSeconds(value, -1);
                    } else if (OkHeaders.SENT_MILLIS.equalsIgnoreCase(name)) {
                        this.sentRequestMillis = Long.parseLong(value);
                    } else if (OkHeaders.RECEIVED_MILLIS.equalsIgnoreCase(name)) {
                        this.receivedResponseMillis = Long.parseLong(value);
                    }
                }
            }
        }

        public CacheStrategy get() {
            CacheStrategy candidate = getCandidate();
            if (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached) {
                return candidate;
            }
            return new CacheStrategy(null, null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004f, code lost:
        if (r3.cacheControl().isPrivate == false) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCacheable(com.squareup.okhttp.Response r3, com.squareup.okhttp.Request r4) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.CacheStrategy.isCacheable(com.squareup.okhttp.Response, com.squareup.okhttp.Request):boolean");
    }

    public CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }
}
