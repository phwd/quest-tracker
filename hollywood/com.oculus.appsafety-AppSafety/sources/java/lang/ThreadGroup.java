package java.lang;

import android.icu.impl.number.Padder;
import java.io.PrintStream;
import java.lang.Thread;
import java.util.Arrays;
import sun.misc.VM;

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

    private ThreadGroup() {
        this.nUnstartedThreads = 0;
        this.name = "system";
        this.maxPriority = 10;
        this.parent = null;
    }

    public ThreadGroup(String name2) {
        this(Thread.currentThread().getThreadGroup(), name2);
    }

    public ThreadGroup(ThreadGroup parent2, String name2) {
        this(checkParentAccess(parent2), parent2, name2);
    }

    private ThreadGroup(Void unused, ThreadGroup parent2, String name2) {
        this.nUnstartedThreads = 0;
        this.name = name2;
        this.maxPriority = parent2.maxPriority;
        this.daemon = parent2.daemon;
        this.vmAllowSuspension = parent2.vmAllowSuspension;
        this.parent = parent2;
        parent2.add(this);
    }

    private static Void checkParentAccess(ThreadGroup parent2) {
        parent2.checkAccess();
        return null;
    }

    public final String getName() {
        return this.name;
    }

    public final ThreadGroup getParent() {
        ThreadGroup threadGroup = this.parent;
        if (threadGroup != null) {
            threadGroup.checkAccess();
        }
        return this.parent;
    }

    public final int getMaxPriority() {
        return this.maxPriority;
    }

    public final boolean isDaemon() {
        return this.daemon;
    }

    public synchronized boolean isDestroyed() {
        return this.destroyed;
    }

    public final void setDaemon(boolean daemon2) {
        checkAccess();
        this.daemon = daemon2;
    }

    public final void setMaxPriority(int pri) {
        int ngroupsSnapshot;
        ThreadGroup[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            if (pri < 1) {
                pri = 1;
            }
            if (pri > 10) {
                pri = 10;
            }
            this.maxPriority = this.parent != null ? Math.min(pri, this.parent.maxPriority) : pri;
            ngroupsSnapshot = this.ngroups;
            if (this.groups != null) {
                groupsSnapshot = (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0; i < ngroupsSnapshot; i++) {
            groupsSnapshot[i].setMaxPriority(pri);
        }
    }

    public final boolean parentOf(ThreadGroup g) {
        while (g != null) {
            if (g == this) {
                return true;
            }
            g = g.parent;
        }
        return false;
    }

    public final void checkAccess() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r3 >= r1) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        r0 = r0 + r2[r3].activeCount();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int activeCount() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.destroyed     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r5)     // Catch:{ all -> 0x0029 }
            return r0
        L_0x0008:
            int r0 = r5.nthreads     // Catch:{ all -> 0x0029 }
            int r1 = r5.ngroups     // Catch:{ all -> 0x0029 }
            java.lang.ThreadGroup[] r2 = r5.groups     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x0019
            java.lang.ThreadGroup[] r2 = r5.groups     // Catch:{ all -> 0x0029 }
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r1)     // Catch:{ all -> 0x0029 }
            java.lang.ThreadGroup[] r2 = (java.lang.ThreadGroup[]) r2     // Catch:{ all -> 0x0029 }
            goto L_0x001a
        L_0x0019:
            r2 = 0
        L_0x001a:
            monitor-exit(r5)     // Catch:{ all -> 0x0029 }
            r3 = 0
        L_0x001c:
            if (r3 >= r1) goto L_0x0028
            r4 = r2[r3]
            int r4 = r4.activeCount()
            int r0 = r0 + r4
            int r3 = r3 + 1
            goto L_0x001c
        L_0x0028:
            return r0
        L_0x0029:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.ThreadGroup.activeCount():int");
    }

    public int enumerate(Thread[] list) {
        checkAccess();
        return enumerate(list, 0, true);
    }

    public int enumerate(Thread[] list, boolean recurse) {
        checkAccess();
        return enumerate(list, 0, recurse);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0045, code lost:
        if (r9 == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0048, code lost:
        if (r2 >= r0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004a, code lost:
        r8 = r1[r2].enumerate(r7, r8, true);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0054, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int enumerate(java.lang.Thread[] r7, int r8, boolean r9) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            monitor-enter(r6)
            boolean r2 = r6.destroyed     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x000a
            r2 = 0
            monitor-exit(r6)     // Catch:{ all -> 0x0055 }
            return r2
        L_0x000a:
            int r2 = r6.nthreads     // Catch:{ all -> 0x0055 }
            int r3 = r7.length     // Catch:{ all -> 0x0055 }
            int r3 = r3 - r8
            if (r2 <= r3) goto L_0x0013
            int r3 = r7.length     // Catch:{ all -> 0x0055 }
            int r2 = r3 - r8
        L_0x0013:
            r3 = 0
        L_0x0014:
            if (r3 >= r2) goto L_0x0030
            java.lang.Thread[] r4 = r6.threads     // Catch:{ all -> 0x0055 }
            r4 = r4[r3]     // Catch:{ all -> 0x0055 }
            boolean r4 = r4.isAlive()     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x002d
            int r4 = r8 + 1
            java.lang.Thread[] r5 = r6.threads     // Catch:{ all -> 0x002a }
            r5 = r5[r3]     // Catch:{ all -> 0x002a }
            r7[r8] = r5     // Catch:{ all -> 0x002a }
            r8 = r4
            goto L_0x002d
        L_0x002a:
            r2 = move-exception
            r8 = r4
            goto L_0x0056
        L_0x002d:
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0030:
            if (r9 == 0) goto L_0x0044
            int r3 = r6.ngroups
            r0 = r3
            java.lang.ThreadGroup[] r3 = r6.groups
            if (r3 == 0) goto L_0x0043
            java.lang.ThreadGroup[] r3 = r6.groups
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r0)
            java.lang.ThreadGroup[] r3 = (java.lang.ThreadGroup[]) r3
            r1 = r3
            goto L_0x0044
        L_0x0043:
            r1 = 0
        L_0x0044:
            monitor-exit(r6)
            if (r9 == 0) goto L_0x0054
            r2 = 0
        L_0x0048:
            if (r2 >= r0) goto L_0x0054
            r3 = r1[r2]
            r4 = 1
            int r8 = r3.enumerate(r7, r8, r4)
            int r2 = r2 + 1
            goto L_0x0048
        L_0x0054:
            return r8
        L_0x0055:
            r2 = move-exception
        L_0x0056:
            monitor-exit(r6)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.ThreadGroup.enumerate(java.lang.Thread[], int, boolean):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = r0;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r3 >= r0) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r2 = r2 + r1[r3].activeGroupCount();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int activeGroupCount() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.destroyed     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r5)     // Catch:{ all -> 0x0028 }
            return r0
        L_0x0008:
            int r0 = r5.ngroups     // Catch:{ all -> 0x0028 }
            java.lang.ThreadGroup[] r1 = r5.groups     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0017
            java.lang.ThreadGroup[] r1 = r5.groups     // Catch:{ all -> 0x0028 }
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r0)     // Catch:{ all -> 0x0028 }
            java.lang.ThreadGroup[] r1 = (java.lang.ThreadGroup[]) r1     // Catch:{ all -> 0x0028 }
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            monitor-exit(r5)     // Catch:{ all -> 0x0028 }
            r2 = r0
            r3 = 0
        L_0x001b:
            if (r3 >= r0) goto L_0x0027
            r4 = r1[r3]
            int r4 = r4.activeGroupCount()
            int r2 = r2 + r4
            int r3 = r3 + 1
            goto L_0x001b
        L_0x0027:
            return r2
        L_0x0028:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.ThreadGroup.activeGroupCount():int");
    }

    public int enumerate(ThreadGroup[] list) {
        checkAccess();
        return enumerate(list, 0, true);
    }

    public int enumerate(ThreadGroup[] list, boolean recurse) {
        checkAccess();
        return enumerate(list, 0, recurse);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        if (r8 == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        if (r2 >= r0) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        r7 = r1[r2].enumerate(r6, r7, true);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int enumerate(java.lang.ThreadGroup[] r6, int r7, boolean r8) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            monitor-enter(r5)
            boolean r2 = r5.destroyed     // Catch:{ all -> 0x0040 }
            r3 = 0
            if (r2 == 0) goto L_0x000a
            monitor-exit(r5)     // Catch:{ all -> 0x0040 }
            return r3
        L_0x000a:
            int r2 = r5.ngroups     // Catch:{ all -> 0x0040 }
            int r4 = r6.length     // Catch:{ all -> 0x0040 }
            int r4 = r4 - r7
            if (r2 <= r4) goto L_0x0013
            int r4 = r6.length     // Catch:{ all -> 0x0040 }
            int r2 = r4 - r7
        L_0x0013:
            if (r2 <= 0) goto L_0x001b
            java.lang.ThreadGroup[] r4 = r5.groups     // Catch:{ all -> 0x0040 }
            java.lang.System.arraycopy(r4, r3, r6, r7, r2)     // Catch:{ all -> 0x0040 }
            int r7 = r7 + r2
        L_0x001b:
            if (r8 == 0) goto L_0x002f
            int r3 = r5.ngroups     // Catch:{ all -> 0x0040 }
            r0 = r3
            java.lang.ThreadGroup[] r3 = r5.groups     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x002e
            java.lang.ThreadGroup[] r3 = r5.groups     // Catch:{ all -> 0x0040 }
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r0)     // Catch:{ all -> 0x0040 }
            java.lang.ThreadGroup[] r3 = (java.lang.ThreadGroup[]) r3     // Catch:{ all -> 0x0040 }
            r1 = r3
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            monitor-exit(r5)     // Catch:{ all -> 0x0040 }
            if (r8 == 0) goto L_0x003f
            r2 = 0
        L_0x0033:
            if (r2 >= r0) goto L_0x003f
            r3 = r1[r2]
            r4 = 1
            int r7 = r3.enumerate(r6, r7, r4)
            int r2 = r2 + 1
            goto L_0x0033
        L_0x003f:
            return r7
        L_0x0040:
            r2 = move-exception
            monitor-exit(r5)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.ThreadGroup.enumerate(java.lang.ThreadGroup[], int, boolean):int");
    }

    @Deprecated
    public final void stop() {
        if (stopOrSuspend(false)) {
            Thread.currentThread().stop();
        }
    }

    /* JADX INFO: Multiple debug info for r0v3 int: [D('i' int), D('ngroupsSnapshot' int)] */
    public final void interrupt() {
        int ngroupsSnapshot;
        ThreadGroup[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0; i < this.nthreads; i++) {
                this.threads[i].interrupt();
            }
            ngroupsSnapshot = this.ngroups;
            if (this.groups != null) {
                groupsSnapshot = (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i2 = 0; i2 < ngroupsSnapshot; i2++) {
            groupsSnapshot[i2].interrupt();
        }
    }

    @Deprecated
    public final void suspend() {
        if (stopOrSuspend(true)) {
            Thread.currentThread().suspend();
        }
    }

    /* JADX INFO: Multiple debug info for r4v2 int: [D('i' int), D('ngroupsSnapshot' int)] */
    private boolean stopOrSuspend(boolean suspend) {
        int ngroupsSnapshot;
        boolean suicide = false;
        Thread us = Thread.currentThread();
        ThreadGroup[] groupsSnapshot = null;
        synchronized (this) {
            checkAccess();
            for (int i = 0; i < this.nthreads; i++) {
                if (this.threads[i] == us) {
                    suicide = true;
                } else if (suspend) {
                    this.threads[i].suspend();
                } else {
                    this.threads[i].stop();
                }
            }
            ngroupsSnapshot = this.ngroups;
            if (this.groups != null) {
                groupsSnapshot = (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot);
            }
        }
        for (int i2 = 0; i2 < ngroupsSnapshot; i2++) {
            suicide = groupsSnapshot[i2].stopOrSuspend(suspend) || suicide;
        }
        return suicide;
    }

    /* JADX INFO: Multiple debug info for r0v3 int: [D('i' int), D('ngroupsSnapshot' int)] */
    @Deprecated
    public final void resume() {
        int ngroupsSnapshot;
        ThreadGroup[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0; i < this.nthreads; i++) {
                this.threads[i].resume();
            }
            ngroupsSnapshot = this.ngroups;
            if (this.groups != null) {
                groupsSnapshot = (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i2 = 0; i2 < ngroupsSnapshot; i2++) {
            groupsSnapshot[i2].resume();
        }
    }

    public final void destroy() {
        int ngroupsSnapshot;
        ThreadGroup[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            if (this.destroyed || this.nthreads > 0) {
                throw new IllegalThreadStateException();
            }
            ngroupsSnapshot = this.ngroups;
            groupsSnapshot = this.groups != null ? (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot) : null;
            if (this.parent != null) {
                this.destroyed = true;
                this.ngroups = 0;
                this.groups = null;
                this.nthreads = 0;
                this.threads = null;
            }
        }
        for (int i = 0; i < ngroupsSnapshot; i++) {
            groupsSnapshot[i].destroy();
        }
        ThreadGroup threadGroup = this.parent;
        if (threadGroup != null) {
            threadGroup.remove(this);
        }
    }

    private final void add(ThreadGroup g) {
        synchronized (this) {
            if (!this.destroyed) {
                if (this.groups == null) {
                    this.groups = new ThreadGroup[4];
                } else if (this.ngroups == this.groups.length) {
                    this.groups = (ThreadGroup[]) Arrays.copyOf(this.groups, this.ngroups * 2);
                }
                this.groups[this.ngroups] = g;
                this.ngroups++;
            } else {
                throw new IllegalThreadStateException();
            }
        }
    }

    private void remove(ThreadGroup g) {
        synchronized (this) {
            if (!this.destroyed) {
                int i = 0;
                while (true) {
                    if (i >= this.ngroups) {
                        break;
                    } else if (this.groups[i] == g) {
                        this.ngroups--;
                        System.arraycopy(this.groups, i + 1, this.groups, i, this.ngroups - i);
                        this.groups[this.ngroups] = null;
                        break;
                    } else {
                        i++;
                    }
                }
                if (this.nthreads == 0) {
                    notifyAll();
                }
                if (this.daemon && this.nthreads == 0 && this.nUnstartedThreads == 0 && this.ngroups == 0) {
                    destroy();
                }
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
    public void add(Thread t) {
        synchronized (this) {
            if (!this.destroyed) {
                if (this.threads == null) {
                    this.threads = new Thread[4];
                } else if (this.nthreads == this.threads.length) {
                    this.threads = (Thread[]) Arrays.copyOf(this.threads, this.nthreads * 2);
                }
                this.threads[this.nthreads] = t;
                this.nthreads++;
                this.nUnstartedThreads--;
            } else {
                throw new IllegalThreadStateException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void threadStartFailed(Thread t) {
        synchronized (this) {
            remove(t);
            this.nUnstartedThreads++;
        }
    }

    /* access modifiers changed from: package-private */
    public void threadTerminated(Thread t) {
        synchronized (this) {
            remove(t);
            if (this.nthreads == 0) {
                notifyAll();
            }
            if (this.daemon && this.nthreads == 0 && this.nUnstartedThreads == 0 && this.ngroups == 0) {
                destroy();
            }
        }
    }

    private void remove(Thread t) {
        synchronized (this) {
            if (!this.destroyed) {
                int i = 0;
                while (true) {
                    if (i >= this.nthreads) {
                        break;
                    } else if (this.threads[i] == t) {
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

    public void list() {
        list(System.out, 0);
    }

    /* JADX INFO: Multiple debug info for r0v4 int: [D('i' int), D('ngroupsSnapshot' int)] */
    /* access modifiers changed from: package-private */
    public void list(PrintStream out, int indent) {
        int indent2;
        int ngroupsSnapshot;
        ThreadGroup[] groupsSnapshot;
        synchronized (this) {
            for (int j = 0; j < indent; j++) {
                out.print(Padder.FALLBACK_PADDING_STRING);
            }
            out.println(this);
            indent2 = indent + 4;
            for (int i = 0; i < this.nthreads; i++) {
                for (int j2 = 0; j2 < indent2; j2++) {
                    out.print(Padder.FALLBACK_PADDING_STRING);
                }
                out.println(this.threads[i]);
            }
            ngroupsSnapshot = this.ngroups;
            if (this.groups != null) {
                groupsSnapshot = (ThreadGroup[]) Arrays.copyOf(this.groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i2 = 0; i2 < ngroupsSnapshot; i2++) {
            groupsSnapshot[i2].list(out, indent2);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        ThreadGroup threadGroup = this.parent;
        if (threadGroup != null) {
            threadGroup.uncaughtException(t, e);
            return;
        }
        Thread.UncaughtExceptionHandler ueh = Thread.getDefaultUncaughtExceptionHandler();
        if (ueh != null) {
            ueh.uncaughtException(t, e);
        } else if (!(e instanceof ThreadDeath)) {
            PrintStream printStream = System.err;
            printStream.print("Exception in thread \"" + t.getName() + "\" ");
            e.printStackTrace(System.err);
        }
    }

    @Deprecated
    public boolean allowThreadSuspension(boolean b) {
        this.vmAllowSuspension = b;
        if (b) {
            return true;
        }
        VM.unsuspendSomeThreads();
        return true;
    }

    public String toString() {
        return getClass().getName() + "[name=" + getName() + ",maxpri=" + this.maxPriority + "]";
    }
}
