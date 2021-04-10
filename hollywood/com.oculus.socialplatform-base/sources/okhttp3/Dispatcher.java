package okhttp3;

import X.AnonymousClass006;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.RealCall;

public final class Dispatcher {
    public ExecutorService executorService;
    public Runnable idleCallback;
    public int maxRequests = 64;
    public int maxRequestsPerHost = 5;
    public final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    public final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
    public final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public synchronized void cancelAll() {
        for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
            RealCall.this.cancel();
        }
        for (RealCall.AsyncCall asyncCall2 : this.runningAsyncCalls) {
            RealCall.this.cancel();
        }
        for (RealCall realCall : this.runningSyncCalls) {
            realCall.cancel();
        }
    }

    public synchronized void enqueue(RealCall.AsyncCall asyncCall) {
        if (this.runningAsyncCalls.size() >= this.maxRequests || runningCallsForHost(asyncCall) >= this.maxRequestsPerHost) {
            this.readyAsyncCalls.add(asyncCall);
        } else {
            this.runningAsyncCalls.add(asyncCall);
            executorService().execute(asyncCall);
        }
    }

    public synchronized void executed(RealCall realCall) {
        this.runningSyncCalls.add(realCall);
    }

    public synchronized ExecutorService executorService() {
        ExecutorService executorService2;
        executorService2 = this.executorService;
        if (executorService2 == null) {
            executorService2 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory("OkHttp Dispatcher", false) {
                /* class okhttp3.internal.Util.AnonymousClass1 */

                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable, r1);
                    thread.setDaemon(r2);
                    return thread;
                }
            });
            this.executorService = executorService2;
        }
        return executorService2;
    }

    public synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    public synchronized List<Call> queuedCalls() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
            arrayList.add(RealCall.this);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int queuedCallsCount() {
        return this.readyAsyncCalls.size();
    }

    public synchronized List<Call> runningCalls() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.runningSyncCalls);
        for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
            arrayList.add(RealCall.this);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }

    public synchronized void setIdleCallback(Runnable runnable) {
        this.idleCallback = runnable;
    }

    public synchronized void setMaxRequests(int i) {
        if (i >= 1) {
            this.maxRequests = i;
            promoteCalls();
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A03("max < 1: ", i));
        }
    }

    public synchronized void setMaxRequestsPerHost(int i) {
        if (i >= 1) {
            this.maxRequestsPerHost = i;
            promoteCalls();
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A03("max < 1: ", i));
        }
    }

    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequests && !this.readyAsyncCalls.isEmpty()) {
            Iterator<RealCall.AsyncCall> it = this.readyAsyncCalls.iterator();
            while (it.hasNext()) {
                RealCall.AsyncCall next = it.next();
                if (runningCallsForHost(next) < this.maxRequestsPerHost) {
                    it.remove();
                    this.runningAsyncCalls.add(next);
                    executorService().execute(next);
                }
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(RealCall.AsyncCall asyncCall) {
        int i = 0;
        for (RealCall.AsyncCall asyncCall2 : this.runningAsyncCalls) {
            if (asyncCall2.host().equals(asyncCall.host())) {
                i++;
            }
        }
        return i;
    }

    public Dispatcher() {
    }

    public Dispatcher(ExecutorService executorService2) {
        this.executorService = executorService2;
    }

    private <T> void finished(Deque<T> deque, T t, boolean z) {
        int runningCallsCount;
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    promoteCalls();
                }
                runningCallsCount = runningCallsCount();
                runnable = this.idleCallback;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (runningCallsCount == 0 && runnable != null) {
            runnable.run();
        }
    }

    public void finished(RealCall.AsyncCall asyncCall) {
        finished(this.runningAsyncCalls, asyncCall, true);
    }

    public void finished(RealCall realCall) {
        finished(this.runningSyncCalls, realCall, false);
    }
}
