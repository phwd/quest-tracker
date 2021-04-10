package X;

import android.content.Intent;
import android.os.Bundle;
import com.oculus.assistant.service.AssistantService;

/* renamed from: X.xV  reason: case insensitive filesystem */
public final /* synthetic */ class C1337xV implements X1 {
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ C1337xV(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    @Override // X.X1
    public final boolean A42(String str, String str2, Bundle bundle) {
        if (str.hashCode() != -810564157 || !str.equals("dlg-voice-selection")) {
            return false;
        }
        Intent intent = new Intent("com.oculus.assistant.ACTION_HANDLE_ASYNC_DIALOG_RESPONSE");
        intent.putExtra("id", str);
        intent.putExtra("action", str2);
        intent.putExtra("extras", bundle);
        Vu.A03(intent);
        return true;
    }
}
