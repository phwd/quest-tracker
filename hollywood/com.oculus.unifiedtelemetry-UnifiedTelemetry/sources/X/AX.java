package X;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import java.util.Map;

public abstract class AX<T> {
    public static final Object A09 = new Object();
    public int A00 = 0;
    public AnonymousClass2U<Observer<? super T>, AX<T>.ObserverWrapper> A01 = new AnonymousClass2U<>();
    public int A02;
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();
    public final Runnable A06;
    public volatile Object A07;
    public volatile Object A08;

    private void A00(AX<T>.ObserverWrapper observerWrapper) {
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

    public final void A01(@Nullable AX<T>.ObserverWrapper observerWrapper) {
        if (this.A04) {
            this.A03 = true;
            return;
        }
        this.A04 = true;
        do {
            this.A03 = false;
            if (observerWrapper == null) {
                AnonymousClass2U<Observer<? super T>, AX<T>.ObserverWrapper> r0 = this.A01;
                C0301aZ aZVar = new C0301aZ(r0);
                r0.A03.put(aZVar, false);
                while (aZVar.hasNext()) {
                    A00((AW) ((Map.Entry) aZVar.next()).getValue());
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
        if (C0304ac.A00().A03()) {
            this.A02++;
            this.A08 = t;
            A01(null);
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A05("Cannot invoke ", "setValue", " on a background thread"));
    }

    public AX() {
        Object obj = A09;
        this.A07 = obj;
        this.A06 = new AV(this);
        this.A08 = obj;
        this.A02 = -1;
    }
}
