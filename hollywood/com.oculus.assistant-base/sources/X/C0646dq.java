package X;

import com.facebook.assistant.common.config.tts.AssistantTtsLocalContentProvider;

/* renamed from: X.dq  reason: case insensitive filesystem */
public final class C0646dq extends AbstractC1403yh implements AbstractC0496aj {
    public final /* synthetic */ AssistantTtsLocalContentProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0646dq(AssistantTtsLocalContentProvider assistantTtsLocalContentProvider) {
        super(0);
        this.this$0 = assistantTtsLocalContentProvider;
    }

    @Override // X.AbstractC0496aj
    public final Object A3M() {
        return new AK(this.this$0.getContext());
    }
}
