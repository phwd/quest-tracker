package com.squareup.okhttp;

import X.AnonymousClass06;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Dispatcher {
    public final Deque<Call> executedCalls = new ArrayDeque();
    public ExecutorService executorService;
    public int maxRequests = 64;
    public int maxRequestsPerHost = 5;
    public final Deque<Call.AsyncCall> readyCalls = new ArrayDeque();
    public final Deque<Call.AsyncCall> runningCalls = new ArrayDeque();

    public synchronized void cancel(Object obj) {
        for (Call.AsyncCall asyncCall : this.readyCalls) {
            if (Util.equal(obj, asyncCall.tag())) {
                asyncCall.cancel();
            }
        }
        for (Call.AsyncCall asyncCall2 : this.runningCalls) {
            if (Util.equal(obj, asyncCall2.tag())) {
                Call.this.canceled = true;
                HttpEngine httpEngine = Call.this.engine;
                if (httpEngine != null) {
                    httpEngine.cancel();
                }
            }
        }
        for (Call call : this.executedCalls) {
            if (Util.equal(obj, call.originalRequest.tag)) {
                call.cancel();
            }
        }
    }

    public synchronized void enqueue(Call.AsyncCall asyncCall) {
        if (this.runningCalls.size() >= this.maxRequests || runningCallsForHost(asyncCall) >= this.maxRequestsPerHost) {
            this.readyCalls.add(asyncCall);
        } else {
            this.runningCalls.add(asyncCall);
            getExecutorService().execute(asyncCall);
        }
    }

    public synchronized void executed(Call call) {
        this.executedCalls.add(call);
    }

    public synchronized ExecutorService getExecutorService() {
        ExecutorService executorService2;
        executorService2 = this.executorService;
        if (executorService2 == null) {
            executorService2 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory("OkHttp Dispatcher", false) {
                /* class com.squareup.okhttp.internal.Util.AnonymousClass1 */

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

    public synchronized int getQueuedCallCount() {
        return this.readyCalls.size();
    }

    public synchronized int getRunningCallCount() {
        return this.runningCalls.size();
    }

    public synchronized void setMaxRequests(int i) {
        if (i >= 1) {
            this.maxRequests = i;
            promoteCalls();
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A01("max < 1: ", i));
        }
    }

    public synchronized void setMaxRequestsPerHost(int i) {
        if (i >= 1) {
            this.maxRequestsPerHost = i;
            promoteCalls();
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A01("max < 1: ", i));
        }
    }

    private void promoteCalls() {
        if (this.runningCalls.size() < this.maxRequests && !this.readyCalls.isEmpty()) {
            Iterator<Call.AsyncCall> it = this.readyCalls.iterator();
            while (it.hasNext()) {
                Call.AsyncCall next = it.next();
                if (runningCallsForHost(next) < this.maxRequestsPerHost) {
                    it.remove();
                    this.runningCalls.add(next);
                    getExecutorService().execute(next);
                }
                if (this.runningCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(Call.AsyncCall asyncCall) {
        int i = 0;
        for (Call.AsyncCall asyncCall2 : this.runningCalls) {
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

    public synchronized void finished(Call.AsyncCall asyncCall) {
        if (this.runningCalls.remove(asyncCall)) {
            promoteCalls();
        } else {
            throw new AssertionError("AsyncCall wasn't running!");
        }
    }

    public synchronized void finished(Call call) {
        if (!this.executedCalls.remove(call)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
    }
}
