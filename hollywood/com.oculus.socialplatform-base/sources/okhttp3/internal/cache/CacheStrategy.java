package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

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

        private CacheStrategy getCandidate() {
            Request request2;
            long j;
            int i;
            Response response = this.cacheResponse;
            if (response != null) {
                request2 = this.request;
                if (!request2.url.isHttps() || response.handshake != null) {
                    if (CacheStrategy.isCacheable(response, request2)) {
                        CacheControl cacheControl = this.request.cacheControl();
                        if (!cacheControl.noCache && !hasConditions(this.request)) {
                            long cacheResponseAge = cacheResponseAge();
                            long computeFreshnessLifetime = computeFreshnessLifetime();
                            int i2 = cacheControl.maxAgeSeconds;
                            if (i2 != -1) {
                                computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) i2));
                            }
                            int i3 = cacheControl.minFreshSeconds;
                            long j2 = 0;
                            if (i3 != -1) {
                                j = TimeUnit.SECONDS.toMillis((long) i3);
                            } else {
                                j = 0;
                            }
                            CacheControl cacheControl2 = this.cacheResponse.cacheControl();
                            if (!cacheControl2.mustRevalidate && (i = cacheControl.maxStaleSeconds) != -1) {
                                j2 = TimeUnit.SECONDS.toMillis((long) i);
                            }
                            if (!cacheControl2.noCache) {
                                long j3 = j + cacheResponseAge;
                                if (j3 < j2 + computeFreshnessLifetime) {
                                    Response.Builder newBuilder = this.cacheResponse.newBuilder();
                                    if (j3 >= computeFreshnessLifetime) {
                                        newBuilder.headers.add("Warning", "110 HttpURLConnection \"Response is stale\"");
                                    }
                                    if (cacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                                        newBuilder.headers.add("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                                    }
                                    return new CacheStrategy(null, newBuilder.build());
                                }
                            }
                            String str = this.etag;
                            String str2 = "If-Modified-Since";
                            if (str != null) {
                                str2 = "If-None-Match";
                            } else if (this.lastModified != null) {
                                str = this.lastModifiedString;
                            } else if (this.servedDate != null) {
                                str = this.servedDateString;
                            }
                            Headers.Builder newBuilder2 = this.request.headers.newBuilder();
                            Internal.instance.addLenient(newBuilder2, str2, str);
                            Request.Builder newBuilder3 = this.request.newBuilder();
                            newBuilder3.headers = new Headers(newBuilder2).newBuilder();
                            return new CacheStrategy(newBuilder3.build(), this.cacheResponse);
                        }
                    }
                }
                return new CacheStrategy(request2, null);
            }
            request2 = this.request;
            return new CacheStrategy(request2, null);
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
                this.sentRequestMillis = response.sentRequestAtMillis;
                this.receivedResponseMillis = response.receivedResponseAtMillis;
                Headers headers = response.headers;
                int length = headers.namesAndValues.length >> 1;
                for (int i = 0; i < length; i++) {
                    String str = headers.namesAndValues[i << 1];
                    String value = headers.value(i);
                    if ("Date".equalsIgnoreCase(str)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    } else if ("Expires".equalsIgnoreCase(str)) {
                        this.expires = HttpDate.parse(value);
                    } else if ("Last-Modified".equalsIgnoreCase(str)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    } else if ("ETag".equalsIgnoreCase(str)) {
                        this.etag = value;
                    } else if ("Age".equalsIgnoreCase(str)) {
                        this.ageSeconds = HttpHeaders.parseSeconds(value, -1);
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

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004e, code lost:
        if (r3.cacheControl().isPrivate == false) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isCacheable(okhttp3.Response r3, okhttp3.Request r4) {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.isCacheable(okhttp3.Response, okhttp3.Request):boolean");
    }

    public CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }
}
