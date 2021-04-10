package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.fy  reason: case insensitive filesystem */
public final class C0717fy extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$27$5";
    public final /* synthetic */ C0719g0 A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ String A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0717fy(C0719g0 g0Var, String str, String str2) {
        super("OacrVoiceInteractionListener: onOnDeviceTts");
        this.A00 = g0Var;
        this.A01 = str;
        this.A02 = str2;
    }

    public final void run() {
        C0740gP gPVar = this.A00.A00;
        AtomicBoolean atomicBoolean = gPVar.A13;
        if (atomicBoolean.get()) {
            C0139Dd.A0F("AssistantClientPlatform", "sending onDeviceTtts to VoiceInteraction, interactionId %s", this.A01);
            gPVar.A0o.onDeviceTts(new C0823iT(this.A02));
            return;
        }
        C0139Dd.A0H("AssistantClientPlatform", "failed to send onDeviceTts to VoiceInteraction, isInitialized=%b interactionId %s", Boolean.valueOf(atomicBoolean.get()), this.A01);
    }
}
