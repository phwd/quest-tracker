package X;

import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.Wu  reason: case insensitive filesystem */
public final class RunnableC0417Wu implements Runnable {
    public static final RunnableC0417Wu A00 = new RunnableC0417Wu();
    public static final String __redex_internal_original_name = "com.oculus.assistant.panel.AssistantPanelManager$hideAttentionSystemInstant$1";

    public final void run() {
        if (!HandlerC0422Wz.A01) {
            BX.A00().sendBroadcast(SystemUXUtil.A04("hide-attention-instant"));
        }
    }
}
