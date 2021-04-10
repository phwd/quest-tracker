package X;

import java.util.Map;
import java.util.Set;

/* renamed from: X.An  reason: case insensitive filesystem */
public final class RunnableC0109An implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.messagebus.AssistantMessageBus$3";
    public final /* synthetic */ AbstractC0105Aj A00;
    public final /* synthetic */ C0112Aq A01;
    public final /* synthetic */ Class A02;

    public RunnableC0109An(C0112Aq aq, AbstractC0105Aj aj, Class cls) {
        this.A01 = aq;
        this.A00 = aj;
        this.A02 = cls;
    }

    public final void run() {
        C0112Aq aq = this.A01;
        if (aq.A04.get()) {
            C0139Dd.A09("AssistantMessageBus", "Not unsubscribing, shutdown");
            return;
        }
        AbstractC0105Aj aj = this.A00;
        if (aj != null) {
            Map map = aq.A03;
            Class cls = this.A02;
            if (map.containsKey(cls)) {
                ((Set) map.get(cls)).remove(aj);
            }
        }
    }
}
