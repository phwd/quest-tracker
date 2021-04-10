package sun.nio.ch;

/* access modifiers changed from: package-private */
public class NativeThreadSet {
    private long[] elts;
    private int used = 0;
    private boolean waitingToEmpty;

    NativeThreadSet(int i) {
        this.elts = new long[i];
    }

    /* access modifiers changed from: package-private */
    public int add() {
        int i;
        long current = NativeThread.current();
        if (current == 0) {
            current = -1;
        }
        synchronized (this) {
            if (this.used >= this.elts.length) {
                i = this.elts.length;
                long[] jArr = new long[(i * 2)];
                System.arraycopy(this.elts, 0, jArr, 0, i);
                this.elts = jArr;
            } else {
                i = 0;
            }
            while (i < this.elts.length) {
                if (this.elts[i] == 0) {
                    this.elts[i] = current;
                    this.used++;
                    return i;
                }
                i++;
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

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public synchronized void signalAndWait() {
        boolean z = false;
        while (this.used > 0) {
            int i = this.used;
            int length = this.elts.length;
            int i2 = i;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                long j = this.elts[i3];
                if (j != 0) {
                    if (j != -1) {
                        NativeThread.signal(j);
                    }
                    i2--;
                    if (i2 == 0) {
                        break;
                    }
                }
                i3++;
            }
            this.waitingToEmpty = true;
            try {
                wait(50);
                this.waitingToEmpty = false;
            } catch (InterruptedException unused) {
                this.waitingToEmpty = false;
                z = true;
            } catch (Throwable th) {
                this.waitingToEmpty = false;
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }
}
