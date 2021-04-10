package X;

import android.os.Handler;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* renamed from: X.1y7  reason: invalid class name */
public final class AnonymousClass1y7 {
    public int A00 = 0;
    public boolean A01 = false;
    public boolean A02 = false;
    public final Handler A03;
    public final AbstractC11131xk A04;
    public final LinkedList<Runnable> A05 = new LinkedList<>();
    public final AtomicInteger A06 = new AtomicInteger(0);

    public final synchronized AbstractC11131xk A00(@Nullable Runnable runnable) {
        if (!this.A01) {
            this.A00++;
        } else {
            throw new IllegalStateException("Cannot generate callbacks after complete is called");
        }
        return new AnonymousClass1y8(this, runnable);
    }

    public final synchronized void A01() {
        this.A01 = true;
        if (this.A06.get() == this.A00) {
            AnonymousClass1z6.A00(this.A04, this.A03);
        }
    }

    public AnonymousClass1y7(AbstractC11131xk r3, Handler handler) {
        this.A04 = r3;
        this.A03 = handler;
    }
}
