package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest;

public class RequestProgress {
    public final Handler callbackHandler;
    public long lastReportedProgress;
    public long maxProgress;
    public long progress;
    public final GraphRequest request;
    public final long threshold = FacebookSdk.getOnProgressThreshold();

    public void reportProgress() {
        final long j = this.progress;
        if (j > this.lastReportedProgress) {
            GraphRequest.Callback callback = this.request.callback;
            final long j2 = this.maxProgress;
            if (j2 > 0 && (callback instanceof GraphRequest.OnProgressCallback)) {
                final GraphRequest.OnProgressCallback onProgressCallback = (GraphRequest.OnProgressCallback) callback;
                Handler handler = this.callbackHandler;
                if (handler == null) {
                    onProgressCallback.onProgress(j, j2);
                } else {
                    handler.post(new Runnable() {
                        /* class com.facebook.RequestProgress.AnonymousClass1 */

                        public void run() {
                            onProgressCallback.onProgress(j, j2);
                        }
                    });
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }

    public void addProgress(long j) {
        long j2 = this.progress + j;
        this.progress = j2;
        if (j2 >= this.lastReportedProgress + this.threshold || j2 >= this.maxProgress) {
            reportProgress();
        }
    }

    public void addToMax(long j) {
        this.maxProgress += j;
    }

    public RequestProgress(Handler handler, GraphRequest graphRequest) {
        this.request = graphRequest;
        this.callbackHandler = handler;
    }

    public long getMaxProgress() {
        return this.maxProgress;
    }

    public long getProgress() {
        return this.progress;
    }
}
