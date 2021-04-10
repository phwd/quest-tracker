package X;

import android.database.Observable;

public class Gz extends Observable<H1> {
    public final void A00() {
        synchronized (this.mObservers) {
            int size = this.mObservers.size();
            for (int i = 0; i < size; i++) {
                ((H1) this.mObservers.get(i)).A3c();
            }
        }
    }

    public final void A01() {
        synchronized (this.mObservers) {
            int size = this.mObservers.size();
            for (int i = 0; i < size; i++) {
                ((H1) this.mObservers.get(i)).A3l();
            }
        }
    }

    public final void A02() {
        synchronized (this.mObservers) {
            int size = this.mObservers.size();
            for (int i = 0; i < size; i++) {
                ((H1) this.mObservers.get(i)).A5c();
            }
        }
    }

    public final void A03(C0260Yj yj) {
        synchronized (this.mObservers) {
            int size = this.mObservers.size();
            for (int i = 0; i < size; i++) {
                ((H1) this.mObservers.get(i)).A5b(yj);
            }
        }
    }
}
