package X;

import com.facebook.messenger.assistant.thrift.AssistantClientRequest;
import com.facebook.messenger.assistant.thrift.DialogUpdateRequest;
import com.oculus.assistant.service.AssistantService;

public final /* synthetic */ class XX implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$SzE2fF2g84R_SpQc4bhoRB_l3Yo";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ XX(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        AssistantService assistantService = this.A00;
        C0740gP gPVar = assistantService.A05;
        if (gPVar != null && gPVar.A13.get()) {
            C0740gP gPVar2 = assistantService.A05;
            C0869lT lTVar = new C0869lT();
            C0874m5 m5Var = new C0874m5();
            m5Var.A02(0, 1);
            m5Var.A02(4, null);
            Object[] A03 = m5Var.A03();
            DialogUpdateRequest dialogUpdateRequest = new DialogUpdateRequest();
            dialogUpdateRequest.A02("com.facebook.messenger.assistant.thrift.DialogUpdateRequest", A03);
            lTVar.A02(1, dialogUpdateRequest);
            lTVar.A00 = 2;
            Object[] A032 = lTVar.A03();
            AssistantClientRequest assistantClientRequest = new AssistantClientRequest();
            assistantClientRequest.A02("com.facebook.messenger.assistant.thrift.AssistantClientRequest", A032);
            assistantClientRequest.A01(lTVar.A00);
            C0740gP.A16.post(new C0726g8(gPVar2, assistantClientRequest, null, false));
        }
        assistantService.A18.A01(new i1());
    }
}
