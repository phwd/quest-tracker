package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequestBatch;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ProgressOutputStream extends FilterOutputStream implements RequestOutputStream {
    public long batchProgress;
    public RequestProgress currentRequestProgress;
    public long lastReportedProgress;
    public long maxProgress;
    public final Map<GraphRequest, RequestProgress> progressMap;
    public final GraphRequestBatch requests;
    public final long threshold = FacebookSdk.getOnProgressThreshold();

    private void addProgress(long j) {
        RequestProgress requestProgress = this.currentRequestProgress;
        if (requestProgress != null) {
            requestProgress.addProgress(j);
        }
        long j2 = this.batchProgress + j;
        this.batchProgress = j2;
        if (j2 >= this.lastReportedProgress + this.threshold || j2 >= this.maxProgress) {
            reportBatchProgress();
        }
    }

    private void reportBatchProgress() {
        if (this.batchProgress > this.lastReportedProgress) {
            for (GraphRequestBatch.Callback callback : this.requests.callbacks) {
                if (callback instanceof GraphRequestBatch.OnProgressCallback) {
                    GraphRequestBatch graphRequestBatch = this.requests;
                    Handler handler = graphRequestBatch.callbackHandler;
                    final GraphRequestBatch.OnProgressCallback onProgressCallback = (GraphRequestBatch.OnProgressCallback) callback;
                    if (handler == null) {
                        onProgressCallback.onBatchProgress(graphRequestBatch, this.batchProgress, this.maxProgress);
                    } else {
                        handler.post(new Runnable() {
                            /* class com.facebook.ProgressOutputStream.AnonymousClass1 */

                            public void run() {
                                GraphRequestBatch.OnProgressCallback onProgressCallback = onProgressCallback;
                                ProgressOutputStream progressOutputStream = ProgressOutputStream.this;
                                onProgressCallback.onBatchProgress(progressOutputStream.requests, progressOutputStream.batchProgress, progressOutputStream.maxProgress);
                            }
                        });
                    }
                }
            }
            this.lastReportedProgress = this.batchProgress;
        }
    }

    @Override // com.facebook.RequestOutputStream
    public void setCurrentRequest(GraphRequest graphRequest) {
        RequestProgress requestProgress;
        if (graphRequest != null) {
            requestProgress = this.progressMap.get(graphRequest);
        } else {
            requestProgress = null;
        }
        this.currentRequestProgress = requestProgress;
    }

    public ProgressOutputStream(OutputStream outputStream, GraphRequestBatch graphRequestBatch, Map<GraphRequest, RequestProgress> map, long j) {
        super(outputStream);
        this.requests = graphRequestBatch;
        this.progressMap = map;
        this.maxProgress = j;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        for (RequestProgress requestProgress : this.progressMap.values()) {
            requestProgress.reportProgress();
        }
        reportBatchProgress();
    }

    public long getBatchProgress() {
        return this.batchProgress;
    }

    public long getMaxProgress() {
        return this.maxProgress;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
        addProgress(1);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        addProgress((long) bArr.length);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        addProgress((long) i2);
    }
}
