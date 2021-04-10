package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<HttpUrlConnectionNetworkFetchState> {
    private static final String FETCH_TIME = "fetch_time";
    public static final int HTTP_DEFAULT_TIMEOUT = 30000;
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private static final String IMAGE_SIZE = "image_size";
    private static final int MAX_REDIRECTS = 5;
    private static final int NUM_NETWORK_THREADS = 3;
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final ExecutorService mExecutorService;
    private int mHttpConnectionTimeout;
    private final MonotonicClock mMonotonicClock;
    @Nullable
    private final Map<String, String> mRequestHeaders;
    @Nullable
    private String mUserAgent;

    private static boolean isHttpRedirect(int i) {
        if (i == 307 || i == 308) {
            return true;
        }
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    private static boolean isHttpSuccess(int i) {
        return i >= 200 && i < 300;
    }

    public static class HttpUrlConnectionNetworkFetchState extends FetchState {
        private long fetchCompleteTime;
        private long responseTime;
        private long submitTime;

        public HttpUrlConnectionNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public HttpUrlConnectionNetworkFetcher() {
        this((String) null, (Map<String, String>) null, RealtimeSinceBootClock.get());
    }

    public HttpUrlConnectionNetworkFetcher(int i) {
        this((String) null, (Map<String, String>) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i;
    }

    public HttpUrlConnectionNetworkFetcher(String str, int i) {
        this(str, (Map<String, String>) null, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i;
    }

    public HttpUrlConnectionNetworkFetcher(String str, @Nullable Map<String, String> map, int i) {
        this(str, map, RealtimeSinceBootClock.get());
        this.mHttpConnectionTimeout = i;
    }

    @VisibleForTesting
    HttpUrlConnectionNetworkFetcher(@Nullable String str, @Nullable Map<String, String> map, MonotonicClock monotonicClock) {
        this.mExecutorService = Executors.newFixedThreadPool(3);
        this.mMonotonicClock = monotonicClock;
        this.mRequestHeaders = map;
        this.mUserAgent = str;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public HttpUrlConnectionNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new HttpUrlConnectionNetworkFetchState(consumer, producerContext);
    }

    public void fetch(final HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, final NetworkFetcher.Callback callback) {
        httpUrlConnectionNetworkFetchState.submitTime = this.mMonotonicClock.now();
        final Future<?> submit = this.mExecutorService.submit(new Runnable() {
            /* class com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.AnonymousClass1 */

            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(httpUrlConnectionNetworkFetchState, callback);
            }
        });
        httpUrlConnectionNetworkFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {
            /* class com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (submit.cancel(false)) {
                    callback.onCancellation();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0031 A[SYNTHETIC, Splitter:B:20:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003d A[SYNTHETIC, Splitter:B:27:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    @com.facebook.common.internal.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fetchSync(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.HttpUrlConnectionNetworkFetchState r5, com.facebook.imagepipeline.producers.NetworkFetcher.Callback r6) {
        /*
            r4 = this;
            r0 = 0
            android.net.Uri r1 = r5.getUri()     // Catch:{ IOException -> 0x002a, all -> 0x0027 }
            r2 = 5
            java.net.HttpURLConnection r1 = r4.downloadFrom(r1, r2)     // Catch:{ IOException -> 0x002a, all -> 0x0027 }
            com.facebook.common.time.MonotonicClock r2 = r4.mMonotonicClock     // Catch:{ IOException -> 0x0025 }
            long r2 = r2.now()     // Catch:{ IOException -> 0x0025 }
            com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.HttpUrlConnectionNetworkFetchState.access$102(r5, r2)     // Catch:{ IOException -> 0x0025 }
            if (r1 == 0) goto L_0x001d
            java.io.InputStream r0 = r1.getInputStream()     // Catch:{ IOException -> 0x0025 }
            r5 = -1
            r6.onResponse(r0, r5)     // Catch:{ IOException -> 0x0025 }
        L_0x001d:
            if (r0 == 0) goto L_0x0022
            r0.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            if (r1 == 0) goto L_0x0039
            goto L_0x0036
        L_0x0025:
            r5 = move-exception
            goto L_0x002c
        L_0x0027:
            r5 = move-exception
            r1 = r0
            goto L_0x003b
        L_0x002a:
            r5 = move-exception
            r1 = r0
        L_0x002c:
            r6.onFailure(r5)     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            if (r1 == 0) goto L_0x0039
        L_0x0036:
            r1.disconnect()
        L_0x0039:
            return
        L_0x003a:
            r5 = move-exception
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            r0.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.disconnect()
        L_0x0045:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.fetchSync(com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void");
    }

    private HttpURLConnection downloadFrom(Uri uri, int i) throws IOException {
        Uri uri2;
        String str;
        HttpURLConnection openConnectionTo = openConnectionTo(uri);
        String str2 = this.mUserAgent;
        if (str2 != null) {
            openConnectionTo.setRequestProperty(HttpHeaders.USER_AGENT, str2);
        }
        Map<String, String> map = this.mRequestHeaders;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                openConnectionTo.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        openConnectionTo.setConnectTimeout(this.mHttpConnectionTimeout);
        int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (isHttpRedirect(responseCode)) {
            String headerField = openConnectionTo.getHeaderField(HttpHeaders.LOCATION);
            openConnectionTo.disconnect();
            if (headerField == null) {
                uri2 = null;
            } else {
                uri2 = Uri.parse(headerField);
            }
            String scheme = uri.getScheme();
            if (!(i <= 0 || uri2 == null || uri2.getScheme().equals(scheme))) {
                return downloadFrom(uri2, i - 1);
            }
            if (i == 0) {
                str = error("URL %s follows too many redirects", uri.toString());
            } else {
                str = error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(str);
        }
        openConnectionTo.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), Integer.valueOf(responseCode)));
    }

    @VisibleForTesting
    static HttpURLConnection openConnectionTo(Uri uri) throws IOException {
        return (HttpURLConnection) UriUtil.uriToUrl(uri).openConnection();
    }

    public void onFetchCompletion(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i) {
        httpUrlConnectionNetworkFetchState.fetchCompleteTime = this.mMonotonicClock.now();
    }

    private static String error(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }

    public Map<String, String> getExtraMap(HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState, int i) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(QUEUE_TIME, Long.toString(httpUrlConnectionNetworkFetchState.responseTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put(FETCH_TIME, Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.responseTime));
        hashMap.put(TOTAL_TIME, Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put(IMAGE_SIZE, Integer.toString(i));
        return hashMap;
    }
}
