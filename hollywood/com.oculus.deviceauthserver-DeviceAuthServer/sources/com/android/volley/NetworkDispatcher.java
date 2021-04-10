package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.android.org.bouncycastle.iana.AEADAlgorithm;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> queue, Network network, Cache cache, ResponseDelivery delivery) {
        this.mQueue = queue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = delivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @TargetApi(AEADAlgorithm.AEAD_AES_256_CCM_SHORT_12)
    private void addTrafficStatsTag(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException e) {
                if (this.mQuit) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void processRequest() throws InterruptedException {
        processRequest(this.mQueue.take());
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void processRequest(Request<?> request) {
        long startTimeMs = SystemClock.elapsedRealtime();
        request.sendEvent(3);
        try {
            request.addMarker("network-queue-take");
            if (request.isCanceled()) {
                request.finish("network-discard-cancelled");
                request.notifyListenerResponseNotUsable();
                request.sendEvent(4);
                return;
            }
            addTrafficStatsTag(request);
            NetworkResponse networkResponse = this.mNetwork.performRequest(request);
            request.addMarker("network-http-complete");
            if (!networkResponse.notModified || !request.hasHadResponseDelivered()) {
                Response<?> response = request.parseNetworkResponse(networkResponse);
                request.addMarker("network-parse-complete");
                if (request.shouldCache() && response.cacheEntry != null) {
                    this.mCache.put(request.getCacheKey(), response.cacheEntry);
                    request.addMarker("network-cache-written");
                }
                request.markDelivered();
                this.mDelivery.postResponse(request, response);
                request.notifyListenerResponseReceived(response);
                request.sendEvent(4);
                return;
            }
            request.finish("not-modified");
            request.notifyListenerResponseNotUsable();
            request.sendEvent(4);
        } catch (VolleyError volleyError) {
            volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
            parseAndDeliverNetworkError(request, volleyError);
            request.notifyListenerResponseNotUsable();
        } catch (Exception e) {
            VolleyLog.e(e, "Unhandled exception %s", e.toString());
            VolleyError volleyError2 = new VolleyError(e);
            volleyError2.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
            this.mDelivery.postError(request, volleyError2);
            request.notifyListenerResponseNotUsable();
        } catch (Throwable th) {
            request.sendEvent(4);
            throw th;
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError error) {
        this.mDelivery.postError(request, request.parseNetworkError(error));
    }
}
