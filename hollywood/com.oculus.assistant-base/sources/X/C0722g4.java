package X;

import com.facebook.assistant.oacr.Oacr;

/* renamed from: X.g4  reason: case insensitive filesystem */
public final class C0722g4 extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$31";
    public final /* synthetic */ C0740gP A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0722g4(C0740gP gPVar) {
        super("OacrPlatform_Shutdown");
        this.A00 = gPVar;
    }

    public final void run() {
        C0740gP gPVar = this.A00;
        Oacr oacr = gPVar.A04;
        if (oacr != null) {
            gPVar.A05 = null;
            gPVar.A02 = null;
            gPVar.A07 = null;
            oacr.shutdown();
            gPVar.A04 = null;
            gPVar.A0C = null;
        }
    }
}
