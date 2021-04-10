package java.lang;

import java.lang.Thread;
import java.util.Arrays;

public class ThreadGroup implements Thread.UncaughtExceptionHandler {
    static final ThreadGroup mainThreadGroup = new ThreadGroup(systemThreadGroup, "main");
    static final ThreadGroup systemThreadGroup = new ThreadGroup();
    boolean daemon;
    boolean destroyed;
    ThreadGroup[] groups;
    int maxPriority;
    int nUnstartedThreads;
    String name;
    int ngroups;
    int nthreads;
    private final ThreadGroup parent;
    Thread[] threads;
    boolean vmAllowSuspension;

    public final void checkAccess() {
    }

    private ThreadGroup() {
        this.nUnstartedThreads = 0;
        this.name = "system";
        this.maxPriority = 10;
        this.parent = null;
    }

    public ThreadGroup(ThreadGroup threadGroup, String str) {
        this(checkParentAccess(threadGroup), threadGroup, str);
    }

    private ThreadGroup(Void r1, ThreadGroup threadGroup, String str) {
        this.nUnstartedThreads = 0;
        this.name = str;
        this.maxPriority = threadGroup.maxPriority;
        this.daemon = threadGroup.daemon;
        this.vmAllowSuspension = threadGroup.vmAllowSuspension;
        this.parent = threadGroup;
        threadGroup.add(this);
    }

    private static Void checkParentAccess(ThreadGroup threadGroup) {
        threadGroup.checkAccess();
        return null;
    }

    public final String getName() {
        return this.name;
    }

    private final void add(ThreadGroup threadGroup) {
        synchronized (this) {
            if (!this.destroyed) {
                if (this.groups == null) {
                    this.groups = new ThreadGroup[4];
                } else if (this.ngroups == this.groups.length) {
                    this.groups = (ThreadGroup[]) Arrays.copyOf(this.groups, this.ngroups * 2);
                }
                this.groups[this.ngroups] = threadGroup;
                this.ngroups++;
            } else {
                throw new IllegalThreadStateException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addUnstarted() {
        synchronized (this) {
            if (!this.destroyed) {
                this.nUnstartedThreads++;
            } else {
                throw new IllegalThreadStateException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void add(Thread thread) {
        synchronized (this) {
            if (!this.destroyed) {
                if (this.threads == null) {
                    this.threads = new Thread[4];
                } else if (this.nthreads == this.threads.length) {
                    this.threads = (Thread[]) Arrays.copyOf(this.threads, this.nthreads * 2);
                }
                this.threads[this.nthreads] = thread;
                this.nthreads++;
                this.nUnstartedThreads--;
            } else {
                throw new IllegalThreadStateException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void threadStartFailed(Thread thread) {
        synchronized (this) {
            remove(thread);
            this.nUnstartedThreads++;
        }
    }

    private void remove(Thread thread) {
        synchronized (this) {
            if (!this.destroyed) {
                int i = 0;
                while (true) {
                    if (i >= this.nthreads) {
                        break;
                    } else if (this.threads[i] == thread) {
                        Thread[] threadArr = this.threads;
                        int i2 = this.nthreads - 1;
                        this.nthreads = i2;
                        System.arraycopy(this.threads, i + 1, threadArr, i, i2 - i);
                        this.threads[this.nthreads] = null;
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public String toString() {
        return ThreadGroup.class.getName() + "[name=" + getName() + ",maxpri=" + this.maxPriority + "]";
    }
}
