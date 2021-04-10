package X;

import android.os.Bundle;
import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xf  reason: case insensitive filesystem */
public final /* synthetic */ class C1347xf implements X1 {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1347xf(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.X1
    public final boolean A42(String str, String str2, Bundle bundle) {
        return AssistantService.A0H(this.A00, str, str2);
    }
}
