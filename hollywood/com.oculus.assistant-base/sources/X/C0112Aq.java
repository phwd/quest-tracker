package X;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.time.RealtimeSinceBootClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.Aq  reason: case insensitive filesystem */
public final class C0112Aq {
    public static C0112Aq A05;
    public List A00 = new ArrayList();
    public List A01;
    public final Handler A02 = new Handler(Looper.getMainLooper());
    public final Map A03 = Collections.synchronizedMap(new HashMap());
    public final AtomicBoolean A04 = new AtomicBoolean();

    public static C0112Aq A00() {
        C0112Aq aq = A05;
        if (aq != null) {
            return aq;
        }
        C0112Aq aq2 = new C0112Aq();
        A05 = aq2;
        return aq2;
    }

    public final void A01(AbstractC0106Ak ak) {
        C0113Ar ar = new C0113Ar();
        ar.A03 = RealtimeSinceBootClock.A00.now();
        ArrayList arrayList = new ArrayList(this.A00);
        arrayList.add(new C0111Ap(Thread.currentThread().getStackTrace()[2]));
        C0139Dd.A09("AssistantMessageBus", AnonymousClass08.A04("Post Message: ", ak.getClass().getSimpleName()));
        this.A02.post(new RunnableC0110Ao(this, ak, arrayList, ar));
    }

    public final void A02(Class cls, AbstractC0105Aj aj) {
        C0139Dd.A09("AssistantMessageBus", AnonymousClass08.A04("Subscribe: ", cls.getSimpleName()));
        if (!AbstractC0114As.class.isAssignableFrom(cls)) {
            this.A02.post(new RunnableC0107Al(this, cls, aj));
            return;
        }
        throw new IllegalArgumentException("Only non-singleton message type are allowed.");
    }

    public final void A03(Class cls, AbstractC0105Aj aj) {
        C0139Dd.A09("AssistantMessageBus", AnonymousClass08.A04("Subscribe Singleton: ", cls.getSimpleName()));
        this.A02.post(new RunnableC0108Am(this, cls, aj));
    }

    public final void A04(Class cls, AbstractC0105Aj aj) {
        C0139Dd.A09("AssistantMessageBus", AnonymousClass08.A04("Unsubscribe: ", cls.getSimpleName()));
        this.A02.post(new RunnableC0109An(this, aj, cls));
    }
}
