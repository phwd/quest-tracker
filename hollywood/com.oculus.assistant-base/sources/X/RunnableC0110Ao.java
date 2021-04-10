package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.debug.tracer.Tracer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.Ao  reason: case insensitive filesystem */
public final class RunnableC0110Ao implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.messagebus.AssistantMessageBus$4";
    public final /* synthetic */ AbstractC0106Ak A00;
    public final /* synthetic */ C0112Aq A01;
    public final /* synthetic */ C0113Ar A02;
    public final /* synthetic */ List A03;

    public RunnableC0110Ao(C0112Aq aq, AbstractC0106Ak ak, List list, C0113Ar ar) {
        this.A01 = aq;
        this.A00 = ak;
        this.A03 = list;
        this.A02 = ar;
    }

    public final void run() {
        Set<AbstractC0105Aj> set;
        AbstractC0106Ak ak = this.A00;
        String simpleName = ak.getClass().getSimpleName();
        Tracer.A01(AnonymousClass08.A04("postMessageRunnable ", simpleName));
        try {
            C0112Aq aq = this.A01;
            if (aq.A04.get()) {
                C0139Dd.A09("AssistantMessageBus", "Not running messages, shutdown");
            } else {
                List list = this.A03;
                aq.A01 = aq.A00;
                aq.A00 = list;
                Map map = aq.A03;
                Iterator it = map.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Class cls = (Class) it.next();
                    if (cls.isInstance(ak) && (set = (Set) map.get(cls)) != null) {
                        C0113Ar ar = this.A02;
                        int size = set.size();
                        long now = RealtimeSinceBootClock.A00.now();
                        ar.A01 = now;
                        ar.A00 = size;
                        ar.A02 = now - ar.A03;
                        synchronized (set) {
                            for (AbstractC0105Aj aj : set) {
                                C0139Dd.A0F("AssistantMessageBus", "Found Subscriber For: %s", simpleName);
                                aj.A47(new C0104Ai(aq, ar), ak);
                            }
                        }
                    }
                }
                aq.A00 = aq.A01;
                aq.A01 = null;
            }
        } finally {
            Tracer.A00();
        }
    }
}
