package X;

import android.app.Application;
import android.content.ComponentName;
import android.os.Bundle;
import com.facebook.papaya.client.PapayaMetadata;
import com.facebook.papaya.client.PapayaService;
import com.facebook.papaya.fb.assistant.smart_keyboard.AssistantSmartKeyboardExecutorFactory;
import com.google.common.base.Function;
import com.oculus.assistant.learning.ExecutionJobService;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.w8  reason: case insensitive filesystem */
public final class C1252w8 implements Function {
    public final /* synthetic */ WE A00;

    public C1252w8(WE we) {
        this.A00 = we;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        long j;
        String str = (String) obj;
        WE we = this.A00;
        WB wb = we.A02;
        Application A002 = BX.A00();
        HashMap hashMap = new HashMap();
        hashMap.put("assistant_smart_keyboard_executor", AssistantSmartKeyboardExecutorFactory.class);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("MaximumExecutionTimePerDay", Long.valueOf((long) 600));
        hashMap2.put("MaximumNetworkConsumptionPerDay", 4194304000L);
        if (W0.A00().A00.getBoolean("fl_execute_on_idle", false)) {
            j = 1;
        } else {
            j = 0;
        }
        hashMap2.put("ExecuteOnIdle", Long.valueOf(j));
        hashMap2.put("IdleRetryDelay", Long.valueOf(W0.A00().A00.getLong("fl_idle_retry_delay_sec", 86400)));
        Bundle bundle = new Bundle();
        bundle.putString("access_token", str);
        bundle.putString("user_agent", YS.A00());
        bundle.putString("base_url_override", "https://graph.oculus.com/federated_learning/");
        Bundle bundle2 = new Bundle();
        ComponentName componentName = new ComponentName(A002, ExecutionJobService.class);
        ComponentName componentName2 = new ComponentName(A002, PapayaService.class);
        HH hh = wb.A00;
        ArrayList arrayList = new ArrayList();
        if (hh.A00) {
            arrayList.add("torch-code-gen");
        }
        PapayaMetadata papayaMetadata = new PapayaMetadata(hashMap, bundle2, bundle, hashMap2, componentName, componentName2, arrayList, EnumC0181Gs.INFO);
        synchronized (we) {
            if (we.A00 == null) {
                we.A00 = new C0904oW(BX.A00(), papayaMetadata);
            }
        }
        return we.A00;
    }
}
