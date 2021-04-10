package X;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData$LifecycleBoundObserver;
import androidx.lifecycle.Observer;
import java.util.Map;

/* renamed from: X.By  reason: case insensitive filesystem */
public abstract class AbstractC0046By<T> {
    public static final Object A09 = new Object();
    public int A00 = 0;
    public AnonymousClass2h<Observer<? super T>, AbstractC0046By<T>.ObserverWrapper> A01 = new AnonymousClass2h<>();
    public int A02;
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();
    public final Runnable A06;
    public volatile Object A07;
    public volatile Object A08;

    private void A00(AbstractC0046By<T>.ObserverWrapper observerWrapper) {
        if (!observerWrapper.A01) {
            return;
        }
        if (!((Tc) ((LiveData$LifecycleBoundObserver) observerWrapper).A00.getLifecycle()).A02.isAtLeast(EnumC0040Bp.STARTED)) {
            observerWrapper.A00(false);
            return;
        }
        int i = observerWrapper.A00;
        int i2 = this.A02;
        if (i < i2) {
            observerWrapper.A00 = i2;
            throw null;
        }
    }

    public final void A01(@Nullable AbstractC0046By<T>.ObserverWrapper observerWrapper) {
        if (this.A04) {
            this.A03 = true;
            return;
        }
        this.A04 = true;
        do {
            this.A03 = false;
            if (observerWrapper == null) {
                AnonymousClass2h<Observer<? super T>, AbstractC0046By<T>.ObserverWrapper> r0 = this.A01;
                UE ue = new UE(r0);
                r0.A03.put(ue, false);
                while (ue.hasNext()) {
                    A00((AbstractC0045Bx) ((Map.Entry) ue.next()).getValue());
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
        if (UH.A00().A03()) {
            this.A02++;
            this.A08 = t;
            A01(null);
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A04("Cannot invoke ", "setValue", " on a background thread"));
    }

    public AbstractC0046By() {
        Object obj = A09;
        this.A07 = obj;
        this.A06 = new RunnableC0044Bw(this);
        this.A08 = obj;
        this.A02 = -1;
    }
}
