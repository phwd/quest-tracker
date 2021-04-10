package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.RealCall;
import okhttp3.internal.Util;

public final class Dispatcher {
    static final /* synthetic */ boolean $assertionsDisabled = (!Dispatcher.class.desiredAssertionStatus());
    private ExecutorService executorService;
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    /* access modifiers changed from: package-private */
    public void enqueue(RealCall.AsyncCall call) {
        synchronized (this) {
            this.readyAsyncCalls.add(call);
        }
        promoteAndExecute();
    }

    private boolean promoteAndExecute() {
        boolean isRunning;
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            List<RealCall.AsyncCall> executableCalls = new ArrayList<>();
            synchronized (this) {
                Iterator<RealCall.AsyncCall> i = this.readyAsyncCalls.iterator();
                while (i.hasNext()) {
                    RealCall.AsyncCall asyncCall = i.next();
                    if (this.runningAsyncCalls.size() >= this.maxRequests) {
                        break;
                    } else if (runningCallsForHost(asyncCall) < this.maxRequestsPerHost) {
                        i.remove();
                        executableCalls.add(asyncCall);
                        this.runningAsyncCalls.add(asyncCall);
                    }
                }
                isRunning = runningCallsCount() > 0;
            }
            int size = executableCalls.size();
            for (int i2 = 0; i2 < size; i2++) {
                executableCalls.get(i2).executeOn(executorService());
            }
            return isRunning;
        }
        throw new AssertionError();
    }

    private int runningCallsForHost(RealCall.AsyncCall call) {
        int result = 0;
        for (RealCall.AsyncCall c : this.runningAsyncCalls) {
            if (!c.get().forWebSocket && c.host().equals(call.host())) {
                result++;
            }
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public void finished(RealCall.AsyncCall call) {
        finished(this.runningAsyncCalls, call);
    }

    private <T> void finished(Deque<T> calls, T call) {
        Runnable idleCallback2;
        synchronized (this) {
            if (!calls.remove(call)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            idleCallback2 = this.idleCallback;
        }
        if (!promoteAndExecute() && idleCallback2 != null) {
            idleCallback2.run();
        }
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }
}
