package X;

import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.Wt  reason: case insensitive filesystem */
public final class RunnableC0416Wt implements Runnable {
    public static final RunnableC0416Wt A00 = new RunnableC0416Wt();
    public static final String __redex_internal_original_name = "com.oculus.assistant.panel.AssistantPanelManager$hideAttentionSystem$1";

    public final void run() {
        if (!HandlerC0422Wz.A01) {
            BX.A00().sendBroadcast(SystemUXUtil.A04("hide-attention"));
        }
    }
}
