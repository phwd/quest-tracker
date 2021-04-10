package X;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import java.util.Map;

/* renamed from: X.0Dg  reason: invalid class name */
public abstract class AnonymousClass0Dg<T> {
    public static final Object A09 = new Object();
    public int A00 = 0;
    public C005905t<Observer<? super T>, AnonymousClass0Dg<T>.ObserverWrapper> A01 = new C005905t<>();
    public int A02;
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();
    public final Runnable A06;
    public volatile Object A07;
    public volatile Object A08;

    private void A00(AnonymousClass0Dg<T>.ObserverWrapper observerWrapper) {
        if (!observerWrapper.A01) {
            return;
        }
        if (!observerWrapper.A02()) {
            observerWrapper.A01(false);
            return;
        }
        int i = observerWrapper.A00;
        int i2 = this.A02;
        if (i < i2) {
            observerWrapper.A00 = i2;
            throw null;
        }
    }

    public final void A01(@Nullable AnonymousClass0Dg<T>.ObserverWrapper observerWrapper) {
        if (this.A04) {
            this.A03 = true;
            return;
        }
        this.A04 = true;
        do {
            this.A03 = false;
            if (observerWrapper == null) {
                C005905t<Observer<? super T>, AnonymousClass0Dg<T>.ObserverWrapper> r0 = this.A01;
                C03990dm r2 = new C03990dm(r0);
                r0.A03.put(r2, false);
                while (r2.hasNext()) {
                    A00((AbstractC01080Df) ((Map.Entry) r2.next()).getValue());
                    if (this.A03) {
                        break;
                    }
                }
            } else {
                A00(observerWrapper);
                observerWrapper = null;
            }
        } while (this.A03);
        this.A04 = false;
    }

    @MainThread
    public final void A02(T t) {
        if (C04020dp.A00().A03()) {
            this.A02++;
            this.A08 = t;
            A01(null);
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Cannot invoke ", "setValue", " on a background thread"));
    }

    public AnonymousClass0Dg() {
        Object obj = A09;
        this.A07 = obj;
        this.A06 = new RunnableC01070De(this);
        this.A08 = obj;
        this.A02 = -1;
    }
}
