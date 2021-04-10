package sun.nio.ch;

/* access modifiers changed from: package-private */
public class NativeThreadSet {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long[] elts;
    private int used = 0;
    private boolean waitingToEmpty;

    NativeThreadSet(int n) {
        this.elts = new long[n];
    }

    /* access modifiers changed from: package-private */
    public int add() {
        long th = NativeThread.current();
        if (th == 0) {
            th = -1;
        }
        synchronized (this) {
            int start = 0;
            if (this.used >= this.elts.length) {
                int on = this.elts.length;
                long[] nelts = new long[(on * 2)];
                System.arraycopy((Object) this.elts, 0, (Object) nelts, 0, on);
                this.elts = nelts;
                start = on;
            }
            for (int i = start; i < this.elts.length; i++) {
                if (this.elts[i] == 0) {
                    this.elts[i] = th;
                    this.used++;
                    return i;
                }
            }
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void remove(int i) {
        synchronized (this) {
            this.elts[i] = 0;
            this.used--;
            if (this.used == 0 && this.waitingToEmpty) {
                notifyAll();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void signalAndWait() {
        boolean interrupted = false;
        while (this.used > 0) {
            int u = this.used;
            int n = this.elts.length;
            int i = 0;
            while (true) {
                if (i >= n) {
                    break;
                }
                long th = this.elts[i];
                if (th != 0) {
                    if (th != -1) {
                        NativeThread.signal(th);
                    }
                    u--;
                    if (u == 0) {
                        break;
                    }
                }
                i++;
            }
            this.waitingToEmpty = true;
            try {
                wait(50);
            } catch (InterruptedException e) {
                interrupted = true;
            } finally {
                this.waitingToEmpty = false;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }
}
