package X;

import com.facebook.assistant.oacr.OacrApi;

/* renamed from: X.gD  reason: case insensitive filesystem */
public final class C0729gD extends MQ {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.AssistantClientPlatform$41";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C0740gP A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0729gD(C0740gP gPVar, int i) {
        super("ACP: onNetworkStatus");
        this.A01 = gPVar;
        this.A00 = i;
    }

    public final void run() {
        OacrApi oacrApi = this.A01.A05;
        if (oacrApi != null) {
            oacrApi.onNetworkStatusChange(this.A00);
        }
    }
}
