package X;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: X.Al  reason: case insensitive filesystem */
public final class RunnableC0107Al implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.messagebus.AssistantMessageBus$1";
    public final /* synthetic */ AbstractC0105Aj A00;
    public final /* synthetic */ C0112Aq A01;
    public final /* synthetic */ Class A02;

    public RunnableC0107Al(C0112Aq aq, Class cls, AbstractC0105Aj aj) {
        this.A01 = aq;
        this.A02 = cls;
        this.A00 = aj;
    }

    public final void run() {
        C0112Aq aq = this.A01;
        if (aq.A04.get()) {
            C0139Dd.A09("AssistantMessageBus", "Not subscribing, shutdown");
            return;
        }
        Map map = aq.A03;
        Class cls = this.A02;
        if (!map.containsKey(cls)) {
            map.put(cls, Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap())));
        }
        ((Set) map.get(cls)).add(this.A00);
    }
}
