package X;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData$LifecycleBoundObserver;
import java.util.Map;

/* renamed from: X.0AY  reason: invalid class name */
public abstract class AnonymousClass0AY<T> {
    public static final Object A09 = new Object();
    public int A00 = 0;
    public int A01;
    public AnonymousClass02b<AbstractC00450Aa<? super T>, AnonymousClass0AY<T>.ObserverWrapper> A02 = new AnonymousClass02b<>();
    public boolean A03;
    public boolean A04;
    public final Object A05 = new Object();
    public final Runnable A06;
    public volatile Object A07;
    public volatile Object A08;

    private void A00(AnonymousClass0AY<T>.ObserverWrapper observerWrapper) {
        if (!observerWrapper.A01) {
            return;
        }
        if (!observerWrapper.A02()) {
            observerWrapper.A01(false);
            return;
        }
        int i = observerWrapper.A00;
        int i2 = this.A01;
        if (i < i2) {
            observerWrapper.A00 = i2;
            observerWrapper.A02.A6q((Object) this.A08);
        }
    }

    @MainThread
    public final void A02(@NonNull AnonymousClass0AS r3, @NonNull AbstractC00450Aa<? super T> r4) {
        A01("observe");
        if (r3.getLifecycle().A05() != AnonymousClass0AP.DESTROYED) {
            AnonymousClass0AY<T>.ObserverWrapper liveData$LifecycleBoundObserver = new LiveData$LifecycleBoundObserver(this, r3, r4);
            AnonymousClass0AX A022 = this.A02.A02(r4, liveData$LifecycleBoundObserver);
            if (A022 == null) {
                r3.getLifecycle().A06(liveData$LifecycleBoundObserver);
            } else if (!A022.A03(r3)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
        }
    }

    public final void A03(@Nullable AnonymousClass0AY<T>.ObserverWrapper observerWrapper) {
        if (this.A04) {
            this.A03 = true;
            return;
        }
        this.A04 = true;
        do {
            this.A03 = false;
            if (observerWrapper == null) {
                AnonymousClass02b<AbstractC00450Aa<? super T>, AnonymousClass0AY<T>.ObserverWrapper> r0 = this.A02;
                AnonymousClass0wk r2 = new AnonymousClass0wk(r0);
                r0.A03.put(r2, false);
                while (r2.hasNext()) {
                    A00((AnonymousClass0AX) ((Map.Entry) r2.next()).getValue());
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
    public final void A04(@NonNull AbstractC00450Aa<? super T> r3) {
        A01("removeObserver");
        AnonymousClass0AX A012 = this.A02.A01(r3);
        if (A012 != null) {
            A012.A00();
            A012.A01(false);
        }
    }

    public AnonymousClass0AY() {
        Object obj = A09;
        this.A07 = obj;
        this.A06 = new AnonymousClass0AW(this);
        this.A08 = obj;
        this.A01 = -1;
    }

    public static void A01(String str) {
        if (!AnonymousClass0wn.A00().A03()) {
            throw new IllegalStateException(AnonymousClass006.A09("Cannot invoke ", str, " on a background thread"));
        }
    }
}
