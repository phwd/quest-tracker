package com.facebook.mobileconfig.troubleshooting;

public class MobileConfigResponseResult implements MobileConfigResponseCallback {
    private boolean mDone = false;
    private String mResponse;
    private boolean mSuccess = false;

    @Override // com.facebook.mobileconfig.troubleshooting.MobileConfigResponseCallback
    public synchronized void onResponse(boolean z, String str) {
        this.mDone = true;
        this.mSuccess = z;
        this.mResponse = str;
        notifyAll();
    }

    public synchronized boolean isDone() {
        return this.mDone;
    }

    public synchronized boolean waitFor(long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + j;
        while (!this.mDone && currentTimeMillis > System.currentTimeMillis()) {
            wait(j);
        }
        return this.mDone;
    }

    public synchronized boolean isSuccess() {
        return this.mSuccess;
    }

    public synchronized String getResponse() {
        return this.mResponse;
    }
}
