package X;

import android.os.Handler;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1y2  reason: invalid class name */
public final class AnonymousClass1y2 {
    public int A00 = 0;
    public boolean A01 = false;
    public boolean A02 = false;
    public final Handler A03;
    public final AnonymousClass1zF A04;
    public final LinkedList<Runnable> A05 = new LinkedList<>();
    public final AtomicInteger A06 = new AtomicInteger(0);

    public AnonymousClass1y2(AnonymousClass1zF r3, Handler handler) {
        this.A04 = r3;
        this.A03 = handler;
    }
}
