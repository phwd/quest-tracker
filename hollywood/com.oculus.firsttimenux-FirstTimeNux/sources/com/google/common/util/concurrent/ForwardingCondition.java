package com.google.common.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

abstract class ForwardingCondition implements Condition {
    /* access modifiers changed from: package-private */
    public abstract Condition delegate();

    ForwardingCondition() {
    }

    @Override // java.util.concurrent.locks.Condition
    public void await() throws InterruptedException {
        delegate().await();
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean await(long time, TimeUnit unit) throws InterruptedException {
        return delegate().await(time, unit);
    }

    public void awaitUninterruptibly() {
        delegate().awaitUninterruptibly();
    }

    @Override // java.util.concurrent.locks.Condition
    public long awaitNanos(long nanosTimeout) throws InterruptedException {
        return delegate().awaitNanos(nanosTimeout);
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean awaitUntil(Date deadline) throws InterruptedException {
        return delegate().awaitUntil(deadline);
    }

    public void signal() {
        delegate().signal();
    }

    public void signalAll() {
        delegate().signalAll();
    }
}
