package com.facebook.proxygen;

import X.AbstractC0119Bm;
import X.Ec;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.proxygen.HTTPRequestError;
import com.google.common.base.Preconditions;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.InputStreamEntity;

public class LigerHttpResponseHandler implements HTTPResponseHandler {
    public static final String DEFAULT_REASON = "empty";
    public static final String ERROR_ON_BODY = "error_on_body";
    public static final String ERROR_ON_EOM = "error_on_eom";
    public static final String ERROR_ON_ERROR = "error_on_error";
    public static final String ERROR_ON_RESPONSE = "error_on_response";
    public static final long MAX_WAIT_TIME_MILLIS = 60000;
    public final ReadBuffer mBuffer;
    public ReadBufferInputStream mBufferInputStream;
    public final AbstractC0119Bm mErrorReporter;
    public TraceEventHandler mEventHandler;
    public final Object mHeadersArrivedCondition;
    public final String mHostname;
    public final Ec mHttpFlowStatistics;
    public long mLoomLogId;
    public int mLoomMatchId;
    public HttpNetworkException mNetworkException;
    public volatile ReadState mReadState;
    public final RequestStatsObserver mRequestStatsObserver;
    public HttpResponse mResponse;

    public enum ReadState {
        NO_RESPONSE,
        HEADERS_ARRIVED,
        BODY_ARRIVED,
        RESPONSE_COMPLETED,
        ERROR
    }

    private void handleOnResponse(int i, String str, Header[] headerArr) {
        verifyState(ReadState.NO_RESPONSE);
        this.mLoomMatchId = -1;
        if (str == null) {
            str = DEFAULT_REASON;
        }
        if (headerArr == null) {
            headerArr = new Header[0];
        }
        this.mResponse = new LigerResponse(HttpVersion.HTTP_1_1, i, str);
        for (Header header : headerArr) {
            this.mResponse.addHeader(header);
        }
        long contentLength = getContentLength(this.mResponse);
        ReadBufferInputStream readBufferInputStream = new ReadBufferInputStream(this.mBuffer);
        this.mBufferInputStream = readBufferInputStream;
        InputStreamEntity inputStreamEntity = new InputStreamEntity(readBufferInputStream, contentLength);
        Header firstHeader = this.mResponse.getFirstHeader("Content-Encoding");
        if (firstHeader != null) {
            inputStreamEntity.setContentEncoding(firstHeader.getValue());
        }
        Header firstHeader2 = this.mResponse.getFirstHeader(HttpRequestMultipart.CONTENT_TYPE);
        if (firstHeader2 != null) {
            inputStreamEntity.setContentType(firstHeader2.getValue());
        }
        this.mResponse.setEntity(inputStreamEntity);
        this.mReadState = ReadState.HEADERS_ARRIVED;
    }

    private long getContentLength(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Content-Length");
        if (firstHeader == null) {
            return -1;
        }
        try {
            return Long.parseLong(firstHeader.getValue());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private void handleBody() {
        if (this.mBufferInputStream != null) {
            ReadState readState = ReadState.BODY_ARRIVED;
            verifyState(ReadState.HEADERS_ARRIVED, readState);
            this.mBufferInputStream.onBody();
            this.mReadState = readState;
            return;
        }
        throw null;
    }

    private void handleEOM() {
        if (this.mHttpFlowStatistics != null) {
            throw new NullPointerException("setRequestStatus");
        } else if (this.mBufferInputStream != null) {
            verifyState(ReadState.HEADERS_ARRIVED, ReadState.BODY_ARRIVED);
            this.mReadState = ReadState.RESPONSE_COMPLETED;
            this.mBufferInputStream.onEOM();
            RequestStats requestStats = this.mRequestStatsObserver.getRequestStats();
            long j = this.mLoomLogId;
            this.mLoomMatchId = -1;
            if (requestStats != null) {
                this.mEventHandler.decorateStatistics(requestStats, j);
            }
        } else {
            throw null;
        }
    }

    private void handleError(HTTPRequestError hTTPRequestError) {
        if (hTTPRequestError == null) {
            hTTPRequestError = new HTTPRequestError("Error is null", HTTPRequestError.HTTPRequestStage.Unknown, HTTPRequestError.ProxygenError.Unknown);
        }
        if (this.mHttpFlowStatistics != null) {
            HTTPRequestError.ProxygenError proxygenError = hTTPRequestError.mErrCode;
            HTTPRequestError.ProxygenError proxygenError2 = HTTPRequestError.ProxygenError.Canceled;
            NullPointerException nullPointerException = new NullPointerException("setRequestStatus");
            if (proxygenError == proxygenError2) {
                throw nullPointerException;
            }
            throw nullPointerException;
        }
        RequestStats requestStats = this.mRequestStatsObserver.getRequestStats();
        long j = this.mLoomLogId;
        this.mLoomMatchId = -1;
        if (requestStats != null) {
            this.mEventHandler.decorateStatistics(requestStats, j);
        }
        this.mReadState = ReadState.ERROR;
        HttpNetworkException httpNetworkException = new HttpNetworkException(hTTPRequestError);
        this.mNetworkException = httpNetworkException;
        ReadBufferInputStream readBufferInputStream = this.mBufferInputStream;
        if (readBufferInputStream != null) {
            readBufferInputStream.setError(httpNetworkException);
        }
    }

    private void verifyState(ReadState... readStateArr) {
        boolean z = false;
        if (this.mReadState != ReadState.ERROR) {
            z = true;
        }
        Preconditions.checkState(z);
        boolean z2 = false;
        for (ReadState readState : readStateArr) {
            boolean z3 = false;
            if (this.mReadState == readState) {
                z3 = true;
            }
            z2 |= z3;
        }
        Preconditions.checkState(z2);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0003 A[LOOP:0: B:2:0x0003->B:25:0x0003, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void waitForHeaders() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.mHeadersArrivedCondition
            monitor-enter(r3)
        L_0x0003:
            com.facebook.proxygen.LigerHttpResponseHandler$ReadState r1 = r4.mReadState     // Catch:{ all -> 0x0044 }
            com.facebook.proxygen.LigerHttpResponseHandler$ReadState r0 = com.facebook.proxygen.LigerHttpResponseHandler.ReadState.HEADERS_ARRIVED     // Catch:{ all -> 0x0044 }
            int r0 = r1.compareTo(r0)     // Catch:{ all -> 0x0044 }
            if (r0 >= 0) goto L_0x0016
            java.lang.Object r2 = r4.mHeadersArrivedCondition     // Catch:{ InterruptedException -> 0x0003 }
            r0 = 60000(0xea60, double:2.9644E-319)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0003 }
            goto L_0x0003
        L_0x0016:
            monitor-exit(r3)
            com.facebook.proxygen.HttpNetworkException r0 = r4.mNetworkException
            if (r0 != 0) goto L_0x0043
            org.apache.http.HttpResponse r0 = r4.mResponse
            if (r0 == 0) goto L_0x0026
            org.apache.http.StatusLine r0 = r0.getStatusLine()
            if (r0 == 0) goto L_0x0026
            return
        L_0x0026:
            org.apache.http.HttpResponse r0 = r4.mResponse
            if (r0 != 0) goto L_0x0040
            java.lang.String r0 = "null response received at: "
        L_0x002c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            com.facebook.proxygen.LigerHttpResponseHandler$ReadState r0 = r4.mReadState
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            X.EX r0 = new X.EX
            r0.<init>(r1)
            throw r0
        L_0x0040:
            java.lang.String r0 = "null response status line received: "
            goto L_0x002c
        L_0x0043:
            throw r0
        L_0x0044:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.proxygen.LigerHttpResponseHandler.waitForHeaders():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        r0 = r2.mHeadersArrivedCondition;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0007 */
    @Override // com.facebook.proxygen.HTTPResponseHandler
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onError(com.facebook.proxygen.HTTPRequestError r3) {
        /*
            r2 = this;
            java.lang.Object r1 = r2.mHeadersArrivedCondition
            monitor-enter(r1)
            r2.handleError(r3)     // Catch:{ all -> 0x0007 }
            goto L_0x000a
        L_0x0007:
            java.lang.Object r0 = r2.mHeadersArrivedCondition     // Catch:{ all -> 0x0011 }
            goto L_0x000c
        L_0x000a:
            java.lang.Object r0 = r2.mHeadersArrivedCondition     // Catch:{ all -> 0x0011 }
        L_0x000c:
            r0.notifyAll()     // Catch:{ all -> 0x0011 }
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            return
        L_0x0011:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.proxygen.LigerHttpResponseHandler.onError(com.facebook.proxygen.HTTPRequestError):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        r0 = r2.mHeadersArrivedCondition;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0007 */
    @Override // com.facebook.proxygen.HTTPResponseHandler
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResponse(int r3, java.lang.String r4, org.apache.http.Header[] r5) {
        /*
            r2 = this;
            java.lang.Object r1 = r2.mHeadersArrivedCondition
            monitor-enter(r1)
            r2.handleOnResponse(r3, r4, r5)     // Catch:{ all -> 0x0007 }
            goto L_0x000a
        L_0x0007:
            java.lang.Object r0 = r2.mHeadersArrivedCondition     // Catch:{ all -> 0x0011 }
            goto L_0x000c
        L_0x000a:
            java.lang.Object r0 = r2.mHeadersArrivedCondition     // Catch:{ all -> 0x0011 }
        L_0x000c:
            r0.notifyAll()     // Catch:{ all -> 0x0011 }
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            return
        L_0x0011:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.proxygen.LigerHttpResponseHandler.onResponse(int, java.lang.String, org.apache.http.Header[]):void");
    }

    public HttpResponse getResponse() {
        waitForHeaders();
        HttpResponse httpResponse = this.mResponse;
        if (httpResponse != null) {
            return httpResponse;
        }
        throw null;
    }

    @Override // com.facebook.proxygen.HTTPResponseHandler
    public void onBody() {
        try {
            handleBody();
        } catch (Throwable unused) {
        }
    }

    @Override // com.facebook.proxygen.HTTPResponseHandler
    public void onEOM() {
        try {
            handleEOM();
        } catch (Throwable unused) {
        }
    }

    private void logException(String str, Throwable th) {
    }

    public LigerHttpResponseHandler(String str, ReadBuffer readBuffer, TraceEventHandler traceEventHandler, AbstractC0119Bm bm, RequestStatsObserver requestStatsObserver, Ec ec) {
        this.mReadState = ReadState.NO_RESPONSE;
        this.mHeadersArrivedCondition = new Object();
        boolean z = true;
        Preconditions.checkState(readBuffer != null);
        Preconditions.checkState(traceEventHandler == null ? false : z);
        this.mHostname = str;
        this.mBuffer = readBuffer;
        this.mEventHandler = traceEventHandler;
        this.mErrorReporter = bm;
        this.mRequestStatsObserver = requestStatsObserver;
        this.mHttpFlowStatistics = ec;
    }

    public LigerHttpResponseHandler(String str, ReadBuffer readBuffer, TraceEventHandler traceEventHandler, AbstractC0119Bm bm, RequestStatsObserver requestStatsObserver, Ec ec, int i, String str2) {
        this(str, readBuffer, traceEventHandler, bm, requestStatsObserver, ec);
        this.mLoomLogId = (long) i;
        this.mLoomMatchId = -1;
        this.mLoomMatchId = -1;
        this.mLoomMatchId = -1;
        this.mLoomMatchId = -1;
    }
}
