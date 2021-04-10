package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream {
    public int batchMax;
    public final Handler callbackHandler;
    public GraphRequest currentRequest;
    public RequestProgress currentRequestProgress;
    public final Map<GraphRequest, RequestProgress> progressMap = new HashMap();

    public void addProgress(long j) {
        if (this.currentRequestProgress == null) {
            RequestProgress requestProgress = new RequestProgress(this.callbackHandler, this.currentRequest);
            this.currentRequestProgress = requestProgress;
            this.progressMap.put(this.currentRequest, requestProgress);
        }
        this.currentRequestProgress.addToMax(j);
        this.batchMax = (int) (((long) this.batchMax) + j);
    }

    @Override // com.facebook.RequestOutputStream
    public void setCurrentRequest(GraphRequest graphRequest) {
        RequestProgress requestProgress;
        this.currentRequest = graphRequest;
        if (graphRequest != null) {
            requestProgress = this.progressMap.get(graphRequest);
        } else {
            requestProgress = null;
        }
        this.currentRequestProgress = requestProgress;
    }

    public ProgressNoopOutputStream(Handler handler) {
        this.callbackHandler = handler;
    }

    public int getMaxProgress() {
        return this.batchMax;
    }

    public Map<GraphRequest, RequestProgress> getProgressMap() {
        return this.progressMap;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        addProgress(1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        addProgress((long) bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        addProgress((long) i2);
    }
}
