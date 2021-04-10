package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Executor;

public class WorkQueue {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    public final Executor executor;
    public final int maxConcurrent;
    public WorkNode pendingJobs;
    public int runningCount;
    public WorkNode runningJobs;
    public final Object workLock;

    public interface WorkItem {
        boolean cancel();

        boolean isRunning();

        void moveToFront();
    }

    public class WorkNode implements WorkItem {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public final Runnable callback;
        public boolean isRunning;
        public WorkNode next;
        public WorkNode prev;

        public WorkNode removeFromList(WorkNode workNode) {
            if (workNode == this && (workNode = this.next) == this) {
                workNode = null;
            }
            WorkNode workNode2 = this.next;
            workNode2.prev = this.prev;
            this.prev.next = workNode2;
            this.prev = null;
            this.next = null;
            return workNode;
        }

        public void verify(boolean z) {
        }

        public WorkNode(Runnable runnable) {
            this.callback = runnable;
        }

        public WorkNode addToList(WorkNode workNode, boolean z) {
            if (workNode == null) {
                this.prev = this;
                this.next = this;
                workNode = this;
            } else {
                this.next = workNode;
                WorkNode workNode2 = workNode.prev;
                this.prev = workNode2;
                workNode2.next = this;
                workNode.prev = this;
            }
            if (z) {
                return this;
            }
            return workNode;
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        public boolean cancel() {
            synchronized (WorkQueue.this.workLock) {
                if (isRunning()) {
                    return false;
                }
                WorkQueue workQueue = WorkQueue.this;
                workQueue.pendingJobs = removeFromList(workQueue.pendingJobs);
                return true;
            }
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        public void moveToFront() {
            synchronized (WorkQueue.this.workLock) {
                if (!isRunning()) {
                    WorkQueue workQueue = WorkQueue.this;
                    WorkNode removeFromList = removeFromList(workQueue.pendingJobs);
                    workQueue.pendingJobs = removeFromList;
                    workQueue.pendingJobs = addToList(removeFromList, true);
                }
            }
        }

        public Runnable getCallback() {
            return this.callback;
        }

        public WorkNode getNext() {
            return this.next;
        }

        @Override // com.facebook.internal.WorkQueue.WorkItem
        public boolean isRunning() {
            return this.isRunning;
        }

        public void setIsRunning(boolean z) {
            this.isRunning = z;
        }
    }

    private void startItem() {
        finishItemAndStartNew(null);
    }

    private void execute(final WorkNode workNode) {
        this.executor.execute(new Runnable() {
            /* class com.facebook.internal.WorkQueue.AnonymousClass1 */

            public void run() {
                try {
                    workNode.callback.run();
                } finally {
                    WorkQueue.this.finishItemAndStartNew(workNode);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void finishItemAndStartNew(WorkNode workNode) {
        WorkNode workNode2;
        synchronized (this.workLock) {
            if (workNode != null) {
                this.runningJobs = workNode.removeFromList(this.runningJobs);
                this.runningCount--;
            }
            int i = this.runningCount;
            if (i < this.maxConcurrent) {
                workNode2 = this.pendingJobs;
                if (workNode2 != null) {
                    this.pendingJobs = workNode2.removeFromList(workNode2);
                    this.runningJobs = workNode2.addToList(this.runningJobs, false);
                    this.runningCount = i + 1;
                    workNode2.isRunning = true;
                }
            } else {
                workNode2 = null;
            }
        }
        if (workNode2 != null) {
            execute(workNode2);
        }
    }

    public void validate() {
        synchronized (this.workLock) {
            WorkNode workNode = this.runningJobs;
            if (workNode != null) {
                do {
                    workNode = workNode.next;
                } while (workNode != workNode);
            }
        }
    }

    public WorkQueue() {
        this(8);
    }

    public WorkQueue(int i) {
        this(i, FacebookSdk.getExecutor());
    }

    public WorkQueue(int i, Executor executor2) {
        this.workLock = new Object();
        this.runningJobs = null;
        this.runningCount = 0;
        this.maxConcurrent = i;
        this.executor = executor2;
    }

    public WorkItem addActiveWorkItem(Runnable runnable) {
        return addActiveWorkItem(runnable, true);
    }

    public WorkItem addActiveWorkItem(Runnable runnable, boolean z) {
        WorkNode workNode = new WorkNode(runnable);
        synchronized (this.workLock) {
            this.pendingJobs = workNode.addToList(this.pendingJobs, z);
        }
        finishItemAndStartNew(null);
        return workNode;
    }
}
