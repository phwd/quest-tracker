package java.util;

public class Observable {
    private boolean changed = false;
    private Vector<Observer> obs = new Vector<>();

    public synchronized void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        } else if (!this.obs.contains(o)) {
            this.obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        this.obs.removeElement(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        ((java.util.Observer) r0[r1]).update(r3, r4);
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = r0.length - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r1 < 0) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyObservers(java.lang.Object r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.hasChanged()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            return
        L_0x0009:
            java.util.Vector<java.util.Observer> r0 = r3.obs     // Catch:{ all -> 0x0023 }
            java.lang.Object[] r0 = r0.toArray()     // Catch:{ all -> 0x0023 }
            r3.clearChanged()     // Catch:{ all -> 0x0023 }
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            int r1 = r0.length
            int r1 = r1 + -1
        L_0x0016:
            if (r1 < 0) goto L_0x0022
            r2 = r0[r1]
            java.util.Observer r2 = (java.util.Observer) r2
            r2.update(r3, r4)
            int r1 = r1 + -1
            goto L_0x0016
        L_0x0022:
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Observable.notifyObservers(java.lang.Object):void");
    }

    public synchronized void deleteObservers() {
        this.obs.removeAllElements();
    }

    /* access modifiers changed from: protected */
    public synchronized void setChanged() {
        this.changed = true;
    }

    /* access modifiers changed from: protected */
    public synchronized void clearChanged() {
        this.changed = false;
    }

    public synchronized boolean hasChanged() {
        return this.changed;
    }

    public synchronized int countObservers() {
        return this.obs.size();
    }
}
